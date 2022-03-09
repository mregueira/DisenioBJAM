package servicios;

import entidades.Accion;
import entidades.Articulo;
import entidades.Calibracion;
import entidades.DepartamentoResp;
import entidades.DepartamentoRolUsuario;
import entidades.Documento;
import entidades.DocumentoEdicion;
import entidades.DocumentoImagen;
import entidades.Equipo;
import entidades.EquipoMantenimiento;
import entidades.EquipoReparacion;
import entidades.Falla;
import entidades.FallaTipo;
import entidades.Instrumento;
import entidades.InstrumentoEstabilidad;
import entidades.Lote;
import entidades.LoteComponente;
import entidades.LoteComponenteAsignado;
import entidades.LoteCota;
import entidades.LoteMedida;
import entidades.LoteProceso;
import entidades.LoteProcesoParametro;
import entidades.LoteProcesoParametroMedida;
import entidades.LoteProcesoProducción;
import entidades.Monitor;
import entidades.MonitorProceso;
import entidades.Plan;
import entidades.PlanComponente;
import entidades.PlanCota;
import entidades.PlanProceso;
import entidades.PlanProcesoParametro;
import entidades.Sugerencia;
import entidades.Usuario;
import entidadesEspeciales.DoubleFecha;
import entidadesEspeciales.IntDoubleString;
import enumeraciones.EnumAnalisis;
import enumeraciones.EnumArchivoTipo;
import enumeraciones.EnumIndicador;
import enumeraciones.EnumInstrumentoEstado;
import enumeraciones.EnumJson;
import enumeraciones.EnumPermiso;
import gui.GUI;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.json.simple.JSONObject;

/**
 *
 * @author leandro
 */
public class ServicioEncode {
    
    
    /**
     * 
     * @param em
     * @param user
     * @param pass
     * @param monitor
     * @return un JSONObject conteniendo TRUE si autorizamos login y el nombre de los instrumentos conectados a cada puerto del monitor.
     */
    public static synchronized JSONObject login(EntityManager em, String user, String pass, Monitor monitor){

        JSONObject js = new JSONObject();
        Usuario usuario = Listas.verificarUsuario(em, user, pass);
        
        if(monitor != null && usuario != null){
            //Creamos el JSon para devolver 
            js.put(EnumJson.D_LOGIN_OK.getNombre(), true);
            js.put(EnumJson.D_LOGIN_FULLNAME.getNombre(), usuario.toString());
            js.put(EnumJson.D_LOGIN_PERMISO_LOTE_CREAR.getNombre(), Listas.permiso(em, usuario, EnumPermiso.A_SISTEMA_M_CREAR_LOTE));
            
            js.put(EnumJson.D_PORT0.getNombre(), nombreInstrumento(monitor.getIdInstrumento1()));
            js.put(EnumJson.D_PORT1.getNombre(), nombreInstrumento(monitor.getIdInstrumento2()));
            js.put(EnumJson.D_PORT2.getNombre(), nombreInstrumento(monitor.getIdInstrumento3()));
            js.put(EnumJson.D_PORT3.getNombre(), nombreInstrumento(monitor.getIdInstrumento4()));
            js.put(EnumJson.D_PORT4.getNombre(), nombreInstrumento(monitor.getIdInstrumento5()));
            js.put(EnumJson.D_PORT5.getNombre(), nombreInstrumento(monitor.getIdInstrumento6()));
            js.put(EnumJson.D_PORT6.getNombre(), nombreInstrumento(monitor.getIdInstrumento7()));
            js.put(EnumJson.D_PORT7.getNombre(), nombreInstrumento(monitor.getIdInstrumento8()));

            //y guardamos el usuario
            try{
                monitor.setIdUsuario(usuario);
                ServicioAbm.editar(em, monitor);
            }
            catch(NullPointerException e){}
            
        }
        else{
            js.put(EnumJson.D_LOGIN_OK.getNombre(), false);
            js.put(EnumJson.D_LOGIN_FULLNAME.getNombre(), null);
            js.put(EnumJson.D_PORT0.getNombre(), "");
            js.put(EnumJson.D_PORT1.getNombre(), "");
            js.put(EnumJson.D_PORT2.getNombre(), "");
            js.put(EnumJson.D_PORT3.getNombre(), "");
            js.put(EnumJson.D_PORT4.getNombre(), "");
            js.put(EnumJson.D_PORT5.getNombre(), "");
            js.put(EnumJson.D_PORT6.getNombre(), "");
            js.put(EnumJson.D_PORT7.getNombre(), "");
        }

        return js;
    }
    
    public static synchronized void unlogin(EntityManager em, Monitor monitor){

        if(monitor != null){
            monitor.setIdMonitorProcesoEnMedicion(null);
            ServicioAbm.editar(em, monitor);
        }
    }

    
    /**
     * 
     * @param instrumento
     * @return el nombre del instrumento. String vacio se instrumento == null
     */
    private static String nombreInstrumento(Instrumento instrumento){
        if(instrumento != null){
            return instrumento.toString();
        }
        return "---";
    }

    
    /**
     * 
     * @return 
     */
    public static synchronized JSONObject dateTime(){

        JSONObject js = new JSONObject();
        Calendar cal = Calendar.getInstance();
        String fecha = cal.get(Calendar.DAY_OF_MONTH) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR);
        String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        js.put(EnumJson.D_DATE.getNombre(), fecha);
        js.put(EnumJson.D_TIME.getNombre(), hora);
        return js;
    }

    
    /**
     * 
     * @param em
     * @param monitor
     * @return una JSONObject conteniendo un array con los datos de los procesos asignados al monitor
     */
    public static synchronized JSONObject listaProcesos(EntityManager em, Monitor monitor){

        if(monitor == null){
            return null;
        }
        List<JSONObject> lista = new ArrayList<>();
        
        int instrumentosPendientes = Listas.instrumentoPendiente(em);
        
        //Mostramos una leyenda en el log del servidor.
        if(instrumentosPendientes > 0){
            GUI.agregarTexto("Estado instrumental : " + instrumentosPendientes, Color.red);
        }
        
        
        
        
        for(MonitorProceso monitorProceso: Listas.procesosAsignados(em, monitor)){
            LoteProceso p = monitorProceso.getIdLoteProceso();
            
            //Verificamos el estado de control de estabilidad de los intrumentos de medición
            //Si hay que controlar alguno, le ponemos un 1. 0 indca que están todos bien.
            //Este valor se sumará a instrumentosPendientes (de calibración)
            //Si ambas son cero, todo joya.
            boolean instrumentosEstabilidadPendiente = true;
            for(Instrumento instrumento: Listas.listaInstrumento(em, p.getIdLoteProceso())){
                instrumentosEstabilidadPendiente =  !Listas.instrumentosEstabilidadVigente(em, instrumento);
            }
            
            
            JSONObject js = new JSONObject();
            js.put(EnumJson.D_PROCESS_ID.getNombre()            , p.getIdLoteProceso());
            js.put(EnumJson.D_PROCESS_ID_ARTICULO.getNombre()   , p.getIdLote().getIdArticulo().getIdArticulo());
            js.put(EnumJson.D_PROCESS_CODE.getNombre()          , p.getCodigo() + "");
            js.put(EnumJson.D_PROCESS_NAME.getNombre()          , p.toString());


            //Id del plano de la pieza.
            try{
                Plan plan = Listas.planUltimaEdicion(em, p.getIdLote().getIdArticulo());
                DocumentoEdicion edicion = Listas.DocumentoEdicionUltima(em, plan.getIdDocumento());
                int imagenId = edicion.getDocumentoImagenList().get(0).getIdDocumentoImagen();
                int imagenTipo = edicion.getDocumentoImagenList().get(0).getIdTipoArchivo();
                js.put(EnumJson.D_IMAGE_ID.getNombre()              , imagenId);
                js.put(EnumJson.D_IMAGE_TIPO.getNombre()            , imagenTipo);
            }
            catch(Exception e){
                js.put(EnumJson.D_IMAGE_ID.getNombre()              , -1);
                js.put(EnumJson.D_IMAGE_TIPO.getNombre()            , -1);
                GUI.agregarTexto("No se cargó imagen de " + p.getIdLote().getIdArticulo().toString(), Color.red);
            }
            
            
            try{
                js.put(EnumJson.D_MACHINE.getNombre()               , p.getIdStockUbicacion().getIdEquipo().getCodigo());
            }
            catch(Exception e){
                js.put(EnumJson.D_MACHINE.getNombre()               , "Sin equipo");
            }
            
            try{
                js.put(EnumJson.D_MACHINE_ID.getNombre()               , p.getIdStockUbicacion().getIdEquipo().getIdEquipo());
            }
            catch(Exception e){
                js.put(EnumJson.D_MACHINE_ID.getNombre()               , -1);
            }
            
            js.put(EnumJson.D_ARTICLE.getNombre()               , p.getIdLote().getIdArticulo().toString());
            
            
            if(p.getNota() == null){
                js.put(EnumJson.D_NOTA.getNombre()                  , "");
            }
            else{
                js.put(EnumJson.D_NOTA.getNombre()                  , p.getNota());
            }
                        
            
            //Si alguien lo liberó, 
            js.put(EnumJson.D_LIBERADO.getNombre()                  , p.getIdUsuarioLiberacion() != null);
            
            
            
            
            js.put(EnumJson.D_BATCH.getNombre()                 , p.getIdLote().getCodigo() + "");
            if(p.getIdLote().getCantidad() == null){
                js.put(EnumJson.D_QUANTITY_TO_PRODUCE.getNombre()   , 0);
            }
            else{
                js.put(EnumJson.D_QUANTITY_TO_PRODUCE.getNombre()   , p.getIdLote().getCantidad());
            }
            
            if(p.getCantidadProducida() == null){
                js.put(EnumJson.D_QUANTITY_PRODUCED.getNombre()   , 0);
            }
            else{
                js.put(EnumJson.D_QUANTITY_PRODUCED.getNombre()   , p.getCantidadProducida());
            }

            if(p.getTiempoCiclo() == null){
                js.put(EnumJson.D_PROCESS_CYCLETIME.getNombre()   , 0);
            }
            else{
                js.put(EnumJson.D_PROCESS_CYCLETIME.getNombre()   , p.getTiempoCiclo());
            }
                
            if(monitorProceso.getIdEstado() == null){
                js.put(EnumJson.D_PROCESS_STATE.getNombre()         , 1);
            }
            else{
                js.put(EnumJson.D_PROCESS_STATE.getNombre()         , monitorProceso.getIdEstado());
            }
            
            
            //Estados de cosas pendientes.
            js.put(EnumJson.D_PENDIENTE_CALIDAD.getNombre()         , Listas.loteProcesoPendienteCalidad(em, p));
            //Si no hay equipo asignado, no hay tareas de mantenimiento.
            try{
                js.put(EnumJson.D_PENDIENTE_MANTENIMIENTO.getNombre()   , 
                        Listas.mantenimientoPendiente(em, monitor.getIdMonitorProcesoEnMedicion().getIdLoteProceso().getIdStockUbicacion().getIdEquipo()));
            }
            catch(NullPointerException e){
                js.put(EnumJson.D_PENDIENTE_MANTENIMIENTO.getNombre(),  0);
            }
            js.put(EnumJson.D_PENDIENTE_HERRAMENTAL.getNombre()     , Listas.herramentalPendiente(em, p));
            js.put(EnumJson.D_PENDIENTE_INSTRUMENTOS.getNombre()    , instrumentosPendientes);            
            js.put(EnumJson.D_PENDIENTE_ESTABILIDAD.getNombre()    , instrumentosEstabilidadPendiente);            
            lista.add(js);
            
        }
        
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_PROCESSES.getNombre(), lista);
        return js;
    }

    /**
     * Guarda en la base el proceso elegido por el usuario
     * @param em
     * @param monitor
     * @param idLoteProceso 
     * @param idEstadoProceso 
     */
    /*
    public static synchronized void guardarProcesoElegido(EntityManager em, Monitor monitor, long idLoteProceso){
        if(monitor == null){
          GUI.agregarTexto("Se recibió un monitor no registrado en la base de datos", Color.red);
        }
        else{
            //Guardamos el proceso en el monitor
            monitor.setIdMonitorProcesoEnMedicion(Listas.MonitorProceso(em, monitor, idLoteProceso));
            ServicioAbm.editar(em, monitor);
            
            //Marcamos el proceso como activo (Debería estar, pero.....)
            MonitorProceso proceso = Listas.MonitorProceso(em, monitor, idLoteProceso);
            proceso.setActivo(true);
            ServicioAbm.editar(em, proceso);
        }
    }

    /**
    
    */
    public static synchronized void guardarEstadoProceso(EntityManager em, Monitor monitor, long idLoteProceso, long idEstadoProceso){
            if(monitor == null){
                GUI.agregarTexto("No se puede guardar el estado de un monitor porque el monitor no tiene datos asociados. (ServicioEncode.GuardarEstadoProceso())", Color.RED);
                return;
            }
            
            MonitorProceso monitorProceso = Listas.MonitorProceso(em, monitor, idLoteProceso);
            LoteProceso p = Listas.loteProceso(em, idLoteProceso);
            if(monitorProceso == null){
                GUI.agregarTexto("No se pudo guardar el estado del proceso " + idEstadoProceso + "   Monitor: " + monitor.getNombre() + "(No hay proceso en medición)", Color.RED);
                return;
            }
            monitorProceso.setIdEstado((int)idEstadoProceso);
            ServicioAbm.editar(em, monitorProceso);
    }
    
    /**
     * @param em     
     * @param cadena 
     * @param monitor 
     */
    public static synchronized void guardarMedida(EntityManager em, String cadena, Monitor monitor){
        JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadena);

        LoteMedida medida = new LoteMedida();
        
        LoteCota cota = Listas.loteCota(em, (long) js.get(EnumJson.D_MEASURE_FEATURE_ID.getNombre()));
       
        if(cota == null){
            GUI.agregarTexto("Se recibió una cota no registrada en la base de datos", Color.red);
            return;
        }
        medida.setIdLoteCota(cota);
        
        //String fecha = (String) js.get(EnumJson.D_MEASURE_DATE.getNombre());
        //String hora = (String) js.get(EnumJson.D_MEASURE_TIME.getNombre());
        Calendar cal = Calendar.getInstance();
        //Hay tablets que pueden tener la fecha mal seteada y las medidas vienen con culquier fecha /hora.
//        cal.set(Calendar.YEAR       , Integer.parseInt(fecha.substring(0, 4)));
//        cal.set(Calendar.MONTH      , Integer.parseInt(fecha.substring(5, 7)) - 1);
//        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fecha.substring(8, 10)));
//        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora.substring(0, 2)));
//        cal.set(Calendar.MINUTE     , Integer.parseInt(hora.substring(3, 5)));
//        cal.set(Calendar.SECOND     , 0);
        
        
        //GUI.agregarTexto("Fecha:" + fecha, Color.red);
        medida.setFecha(cal.getTime());
        if(EnumAnalisis.POR_ATRIBUTO.getId().equals(medida.getIdLoteCota().getIdTipoAnalisisP())){
            int decimales = (int)(long)js.get(EnumJson.D_MEASURE_DECIMALS.getNombre());
            if(decimales <= 10){
                medida.setValor(1);
            }
            else{
                medida.setValor(0);
            }
        }
        else{
            if(medida.getIdLoteCota().getCambiarSigno() != null && medida.getIdLoteCota().getCambiarSigno()){
                medida.setValor((Double) js.get(EnumJson.D_MEASURE_VALUE.getNombre()) * -1);
            }
            else{
                Object o = js.get(EnumJson.D_MEASURE_VALUE.getNombre());
                if(o instanceof Double){
                    medida.setValor((Double) js.get(EnumJson.D_MEASURE_VALUE.getNombre()));
                }
                if(o instanceof Long){
                    Long l = (Long)js.get(EnumJson.D_MEASURE_VALUE.getNombre());
                    medida.setValor(l.doubleValue());
                }
                if(o instanceof Integer){
                    Integer l = (Integer)js.get(EnumJson.D_MEASURE_VALUE.getNombre());
                    medida.setValor(l.doubleValue());
                }
            }
            medida.setDecimales((int)(long)js.get(EnumJson.D_MEASURE_DECIMALS.getNombre()));
        }

        ServicioAbm.crear(em, medida);
        
        
        //Guardamos el ultimo medidor del lote.
        LoteProceso proceso = cota.getIdLoteProceso();
        proceso.setIdUsuarioControl(monitor.getIdUsuario());
        ServicioAbm.editar(em, proceso);
        
        
    }
    
    /**
     * @param em     
     * @param idLoteProceso     
     * @param cantidad     
     */
    public static synchronized void guardarCantidadProducida(EntityManager em, long idLoteProceso, int  cantidad){
        LoteProceso proceso =  Listas.loteProceso(em, idLoteProceso);
        proceso.setCantidadProducida(cantidad);
        ServicioAbm.editar(em, proceso);
    }

    /**
     * @param em     
     * @param idLoteProceso     
     * @param tiempo     
     */
    public static synchronized void guardarTiempoDeCiclo(EntityManager em, long idLoteProceso, int  tiempo){
        LoteProceso proceso =  Listas.loteProceso(em, idLoteProceso);
        proceso.setTiempoCiclo(tiempo);
        ServicioAbm.editar(em, proceso);
    }

    /**
     * @param em     
     * @param idLoteProceso     
     * @param nota     
     */
    public static synchronized void guardarNota(EntityManager em, long idLoteProceso, String nota){
        LoteProceso proceso =  Listas.loteProceso(em, idLoteProceso);
        proceso.setNota(nota);
        ServicioAbm.editar(em, proceso);
    }

    
    /**
     * 
     * @param em
     * @param cadena 
     * @param usuario 
     */
    public static synchronized void guardarEquipoMantenimiento(EntityManager em, String cadena, Usuario usuario){
        JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadena);
        EquipoMantenimiento mant = Listas.equipoMantenimiento(em, (long)js.get(EnumJson.D_TASK_TO_SAVE_ID.getNombre()));

        String fecha = (String) js.get(EnumJson.D_TASK_TO_SAVE_DATE.getNombre());
        String hora = (String) js.get(EnumJson.D_TASK_TO_SAVE_TIME.getNombre());
        Calendar cal = Calendar.getInstance();

//        cal.set(Calendar.YEAR       , Integer.parseInt(fecha.substring(0, 4)));
//        cal.set(Calendar.MONTH      , Integer.parseInt(fecha.substring(5, 7)) - 1);
//        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fecha.substring(8, 10)));
//        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora.substring(0, 2)));
//        cal.set(Calendar.MINUTE     , Integer.parseInt(hora.substring(3, 5)));
//        cal.set(Calendar.SECOND     , 0);
//        mant.setFechaControl(cal.getTime());
        mant.setFechaControl(Calendar.getInstance().getTime());
        mant.setIdUsuario(usuario);
        ServicioAbm.editar(em, mant);
    }
    
    /**
     * 
     * @param em
     * @param idLoteProceso
     * @param monitor
     * @return una JSONObject conteniendo un array con los datos de las cotas del proceso enviado
     */
    public static synchronized JSONObject listaTools(EntityManager em, long idLoteProceso, Monitor monitor){

        List<JSONObject> lista = new ArrayList<>();
        
        for(LoteProcesoParametro p: Listas.loteProcesoParametroLista(em, idLoteProceso)){
            JSONObject js = new JSONObject();


            js.put(EnumJson.D_TOOL_CORRECTOR.getNombre(), p.getCorrector());
            js.put(EnumJson.D_TOOL_ID.getNombre(), p.getIdLoteProcesoParametro());
            js.put(EnumJson.D_TOOL_ID_ACTION.getNombre(), p.getIdTipoAccion());
            js.put(EnumJson.D_TOOL_NAME.getNombre(), p.getNombre());
            js.put(EnumJson.D_TOOL_PERIOD.getNombre(), p.getPeriodo());
            js.put(EnumJson.D_TOOL_PROCESS_ID.getNombre(), p.getIdLoteProceso().getIdLoteProceso());

            
            //Datos de la última medición
            LoteProcesoParametroMedida m = Listas.loteProcesoParametroMedidaUltima(em, p);
            if(m != null){
                //Calendar cal = Calendar.getInstance();
                //cal.setTime(m.getFecha());
                
                //String fecha = cal.get(Calendar.DAY_OF_MONTH) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR);
                //String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);

                if(m.getCantidadPiezas() != null){    
                    js.put(EnumJson.D_TOOL_CANT_PIEZAS.getNombre(), m.getCantidadPiezas());
                }
                else{
                    js.put(EnumJson.D_TOOL_CANT_PIEZAS.getNombre(), 0);
                }
                js.put(EnumJson.D_TOOL_PREV_ID_ACTION.getNombre(), m.getIdTipoAccion());
                js.put(EnumJson.D_TOOL_USER_FULLNAME.getNombre(), m.getIdUsuario().toString());

                SimpleDateFormat f;
                f = new SimpleDateFormat("dd-MM-yyyy");
                js.put(EnumJson.D_TOOL_DATE.getNombre(), f.format(m.getFecha()));
                f = new SimpleDateFormat("hh:mm:ss");
                js.put(EnumJson.D_TOOL_TIME.getNombre(), f.format(m.getFecha()));
            }
            else{

                js.put(EnumJson.D_TOOL_CANT_PIEZAS.getNombre(), 0);
                js.put(EnumJson.D_TOOL_DATE.getNombre(), "00-00-0000");
                js.put(EnumJson.D_TOOL_PREV_ID_ACTION.getNombre(), -1);
                js.put(EnumJson.D_TOOL_TIME.getNombre(), "00:00:00");
                js.put(EnumJson.D_TOOL_USER_FULLNAME.getNombre(), "Ninguno");
            }
            
            
            lista.add(js);
        }

        JSONObject js = new JSONObject();
        js.put(EnumJson.D_TOOLS.getNombre(), lista);
        return js;
    }
    
    /**
     * 
     * @param em
     * @param idHerramental 
     * @return una JSONObject conteniendo un array con los datos de las cotas del proceso enviado
     */
    public static synchronized JSONObject toolHistorial(EntityManager em, long idHerramental){

        List<JSONObject> lista = new ArrayList<>();
        
        for(LoteProcesoParametroMedida p: Listas.loteProcesoParametroMedida(em, idHerramental)){
            JSONObject js = new JSONObject();

            js.put(EnumJson.D_TOOL_HISTORIAL_ACTION.getNombre(), p.getIdTipoAccion());
            js.put(EnumJson.D_TOOL_HISTORIAL_CANT_PIEZAS.getNombre(), p.getCantidadPiezas());
            js.put(EnumJson.D_TOOL_HISTORIAL_USER_FULLNAME.getNombre(), p.getIdUsuario().toString());
            SimpleDateFormat f;
            f = new SimpleDateFormat("dd-MM-yyyy");
            js.put(EnumJson.D_TOOL_HISTORIAL_DATE.getNombre(), f.format(p.getFecha()));
            f = new SimpleDateFormat("hh:mm:ss");
            js.put(EnumJson.D_TOOL_HISTORIAL_TIME.getNombre(), f.format(p.getFecha()));
            lista.add(js);
        }

        JSONObject js = new JSONObject();
        js.put(EnumJson.D_TOOLS_HISTORIAL.getNombre(), lista);
        return js;
    }



    
    public static synchronized void guardarTools(EntityManager em, String cadena, Usuario usuario){
        JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadena);


        String fecha = (String) js.get(EnumJson.D_TOOL_TO_SAVE_DATE.getNombre());
        String hora = (String) js.get(EnumJson.D_TOOL_TO_SAVE_TIME.getNombre());
        
        
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR       , Integer.parseInt(fecha.substring(0, 4)));
//        cal.set(Calendar.MONTH      , Integer.parseInt(fecha.substring(5, 7)) - 1);
//        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fecha.substring(8, 10)));
//        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora.substring(0, 2)));
//        cal.set(Calendar.MINUTE     , Integer.parseInt(hora.substring(3, 5)));
//        cal.set(Calendar.SECOND     , 0);
        
        LoteProcesoParametroMedida medida = new LoteProcesoParametroMedida();
        medida.setCantidadPiezas((int)(long)js.get(EnumJson.D_TOOL_TO_SAVE_CANT_PIEZAS.getNombre()));
//        medida.setFecha(cal.getTime());
        medida.setFecha(Calendar.getInstance().getTime());

        LoteProcesoParametro parametro = Listas.loteProcesoParametro(em, (long)js.get(EnumJson.D_TOOL_TO_SAVE_ID.getNombre()));
        medida.setIdLoteProcesoParametro(parametro);
        medida.setIdTipoAccion((int)(long)js.get(EnumJson.D_TOOL_TO_SAVE_ID_ACTION.getNombre()));
        medida.setIdUsuario(usuario);

        ServicioAbm.crear(em, medida);
    }
    
    
    
//    
//    /**
//     * 
//     * @param em
//     * @param idLoteProceso
//     * @param monitor
//     * @return una JSONObject conteniendo un array con los datos de las cotas del proceso enviado
//     */
//    public static synchronized JSONObject listaCotasTodas(EntityManager em, long idLoteProceso, Monitor monitor){
//        List<JSONObject> lista = new ArrayList<>();
//        for(LoteCota cota: Listas.loteCotaNormalList(em, idLoteProceso)){
//            //JSONObject js = new JSONObject();
//            lista.add(cotaToJson(cota, true, monitor));
//            //js.put(EnumJson.D_INSTRUMENTO_ID.getNombre()    , cota.getIdLoteCota());
//            //js.put(EnumJson.D_INSTRUMENTO_NOMBRE.getNombre()  , cota.getCodigo() + " ");
//        }
//        JSONObject js = new JSONObject();
//        js.put(EnumJson.D_FEATURES.getNombre(), lista);
//        return js;
//    }
//    
//
//    /**
//     * 
//     * @param em
//     * @param idLoteProceso
//     * @par
//     * @param monitor
//     * @return una JSONObject conteniendo un array con los datos de las cotas pendientes de medida del proceso enviado
//     */
//    public static synchronized JSONObject listaCotasPendientes(EntityManager em, long idLoteProceso, Monitor monitor){
//        List<JSONObject> lista = new ArrayList<>();
//
//        for(LoteCota cota: Listas.loteCotaNormalList(em, idLoteProceso)){
//            if(cotaVencida(em, cota)){
//                lista.add(cotaToJson(cota, true, monitor));
//            }
//        }
//        //Creamos un nuevo JSon y le metemos la listas de cotas
//        JSONObject js = new JSONObject();
//        js.put(EnumJson.D_FEATURES.getNombre(), lista);
//        return js;
//    }
    
    
    public static synchronized JSONObject listaCotas(EntityManager em, long idLoteProceso, Monitor monitor, boolean soloPendientes){
        List<JSONObject> lista = new ArrayList<>();

        for(LoteCota cota: Listas.loteCotaNormalList(em, idLoteProceso)){
            if((soloPendientes && cotaVencida(em, cota)) ||     //Solo pendientes y estan vencidad
                !soloPendientes){                               //ó  ver todas
                lista.add(cotaToJson(cota, monitor));
            }
        }
        //Creamos un nuevo JSon y le metemos la listas de cotas
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_FEATURES.getNombre(), lista);
        return js;
    }
    


    
    
    /**
     * 
     * @param em
     * @param idLoteProceso
     * @param monitor
     * @return una JSONObject conteniendo un array con los datos de las cotas del proceso enviado
    public static synchronized JSONObject listaCotasEspecialTodas(EntityManager em, long idLoteProceso, Monitor monitor){
        List<JSONObject> lista = new ArrayList<>();
        for(LoteCota cota: Listas.loteCotaEspecialList(em, idLoteProceso)){
            if(cotaMedible(cota, false)){
                lista.add(cotaToJson(cota, false, monitor));
            }
        }
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_FEATURES.getNombre(), lista);
        return js;
    }
*/    

    
    
    
    
    
    
    private static synchronized JSONObject cotaToJson(LoteCota cota, Monitor monitor){
        
        ServicioCota sC = new ServicioCota(cota.getTolerancia());
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_FEATURE_ID.getNombre()    , cota.getIdLoteCota());
        js.put(EnumJson.D_FEATURE_CODE.getNombre()  , cota.getCodigo() + " ");
        js.put(EnumJson.D_FEATURE_NAME.getNombre()  , cota.getNombre());
        js.put(EnumJson.D_TOLERANCE.getNombre()     , cota.getTolerancia());
        js.put(EnumJson.D_TOLERANCE_MAX.getNombre() , sC.getMaximo());
        js.put(EnumJson.D_TOLERANCE_MIN.getNombre() , sC.getMinimo());
        js.put(EnumJson.D_CRITICA.getNombre()       , cota.getIdClase());
        
        
        //PreSerie
        if(cota.getIdLoteProceso().getIdLote().getPreserie()){
            js.put(EnumJson.D_PERIOD.getNombre()        , cota.getIdFrecuenciaC().getNombre());
            if(cota.getIdInstrumentoTipoC() != null){
                js.put(EnumJson.D_GAGE.getNombre()          , cota.getIdInstrumentoTipoC().getNombre());
            }
            else{
                js.put(EnumJson.D_GAGE.getNombre()          , "Sin instrumento");
            }
            js.put(EnumJson.D_DECIMALS.getNombre()   , sC.getDecimales(cota.getIdTipoAnalisisC()));
        }
        else{ //Serie
            js.put(EnumJson.D_PERIOD.getNombre()        , cota.getIdFrecuenciaP().getNombre());
            if(cota.getIdInstrumentoTipoP() != null){
                js.put(EnumJson.D_GAGE.getNombre()          , cota.getIdInstrumentoTipoP().getNombre());
            }
            else{
                js.put(EnumJson.D_GAGE.getNombre()          , "Sin instrum.");
            }
            js.put(EnumJson.D_DECIMALS.getNombre()   , sC.getDecimales(cota.getIdTipoAnalisisP()));
        }
        
        
        if(instrumentoOK(monitor.getIdInstrumento1(), cota)){
            js.put(EnumJson.D_GAGE_PORT.getNombre()     , 0);
        }
        else if(instrumentoOK(monitor.getIdInstrumento2(), cota)){
            js.put(EnumJson.D_GAGE_PORT.getNombre()     , 1);
        }
        else if(instrumentoOK(monitor.getIdInstrumento3(), cota)){
            js.put(EnumJson.D_GAGE_PORT.getNombre()     , 2);
        }
        else if(instrumentoOK(monitor.getIdInstrumento4(), cota)){
            js.put(EnumJson.D_GAGE_PORT.getNombre()     , 3);
        }
        else if(instrumentoOK(monitor.getIdInstrumento5(), cota)){
            js.put(EnumJson.D_GAGE_PORT.getNombre()     , 4);
        }
        else if(instrumentoOK(monitor.getIdInstrumento6(), cota)){
            js.put(EnumJson.D_GAGE_PORT.getNombre()     , 5);
        }
        else if(instrumentoOK(monitor.getIdInstrumento7(), cota)){
            js.put(EnumJson.D_GAGE_PORT.getNombre()     , 6);
        }
        else if(instrumentoOK(monitor.getIdInstrumento8(), cota)){ 
            js.put(EnumJson.D_GAGE_PORT.getNombre()     , 7);
        }
        else{
            js.put(EnumJson.D_GAGE_PORT.getNombre()     , -1);
        }
        return js;
    }
    

    /**
     * Libera un lote de producción verificando el estado de todas sus mediciones.
     * @param em
     * @param idLoteProceso
     * @param monitor 
     * @return un mensaje con el estado de la liberacion 
     */        
    public static synchronized JSONObject liberar (EntityManager em, long idLoteProceso, Monitor monitor){
        
        boolean cotasOk = true;
        
        for(LoteCota cota: Listas.loteCotaList(em, idLoteProceso)){
            if(!cotaMedible(cota)){
                continue;
            }
                
            if(Listas.loteMedidaList(em, cota.getIdLoteCota()).isEmpty()){
                cotasOk = false;
                break;
            }

            if(!cotasOk){
                break;
            }
        }
        
        String mensaje;
        if(!cotasOk){
            mensaje = "Faltan mediciones"; 
        }
        else{
            mensaje = "Proceso liberado";
            LoteProceso proceso = Listas.loteProceso(em, idLoteProceso);
            proceso.setIdUsuarioLiberacion(monitor.getIdUsuario());
            ServicioAbm.editar(em, proceso);
        }
        
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_LIBERAR_MENSAJE.getNombre(), mensaje);
        return js;
    }

    
    
    
    /**
     * 
     * @param em
     * @param idLoteProceso
     * @return una JSONObject conteniendo un array con los datos de las componentes del proceso enviado
     */
    public static synchronized JSONObject listaComponentes(EntityManager em, long idLoteProceso){
        List<JSONObject> lista = new ArrayList<>();
        LoteProceso proceso = Listas.loteProceso(em, idLoteProceso);
        
        for(LoteComponente componente : Listas.loteComponente(em, proceso)){
            boolean hayAsignados = false;
            for(LoteComponenteAsignado asignado : Listas.loteComponenteAsignado(em, componente)){
                hayAsignados = true;
                JSONObject js = new JSONObject();
                js.put(EnumJson.D_COMPONENTE_ARTICULO_ID.getNombre(), componente.getIdArticuloMPC().getIdArticulo());
                js.put(EnumJson.D_COMPONENTE_CODIGO.getNombre(), componente.getIdArticuloMPC().getCodigo());
                js.put(EnumJson.D_COMPONENTE_NOMBRE.getNombre(), componente.getIdArticuloMPC().getNombre());
                js.put(EnumJson.D_COMPONENTE_LOTE.getNombre(), asignado.getIdLoteProcesoMPC().getIdLote().getCodigo());
                js.put(EnumJson.D_COMPONENTE_CONSUMO.getNombre(), componente.getConsumo());
                lista.add(js);
                
            }
            if(!hayAsignados){
                JSONObject js = new JSONObject();
                js.put(EnumJson.D_COMPONENTE_ARTICULO_ID.getNombre(), componente.getIdArticuloMPC().getIdArticulo());
                js.put(EnumJson.D_COMPONENTE_CODIGO.getNombre(), componente.getIdArticuloMPC().getCodigo());
                js.put(EnumJson.D_COMPONENTE_NOMBRE.getNombre(), componente.getIdArticuloMPC().getNombre());
                js.put(EnumJson.D_COMPONENTE_LOTE.getNombre(), 0);
                js.put(EnumJson.D_COMPONENTE_CONSUMO.getNombre(), componente.getConsumo());
                lista.add(js);
            }
        }


        JSONObject js = new JSONObject();
        js.put(EnumJson.D_COMPONENTE_DATOS.getNombre(), lista);
        return js;
    }


    /**
     * 
     * @param em
     * @param idLoteProceso
     * @return una JSONObject conteniendo un array con los datos de las cotas del proceso enviado
     */
    public static synchronized JSONObject listaInstrumento(EntityManager em, long idLoteProceso){
        List<JSONObject> lista = new ArrayList<>();
        
        for(Instrumento instrumento: Listas.listaInstrumento(em, idLoteProceso)){
            JSONObject js = new JSONObject();
            js.put(EnumJson.D_INSTRUMENTO_ID.getNombre(), instrumento.getIdInstrumento());
            js.put(EnumJson.D_INSTRUMENTO_NOMBRE.getNombre(), instrumento.getCodigo() + " " + 
                                                              instrumento.getIdInstrumentoTipo().getNombre().trim() + " " + 
                                                              instrumento.getNombre());
            js.put(EnumJson.D_INSTRUMENTO_DECIMALES.getNombre(), ServicioInstrumento.decimales(instrumento.getResolucion1()));

            js.put(EnumJson.D_INSTRUMENTO_FALTA_ESTABILIDAD.getNombre(), !Listas.instrumentosEstabilidadVigente(em, instrumento));
            lista.add(js);
        }
        
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_INSTRUMENTOS.getNombre(), lista);
        return js;
    }
    
    
    private boolean estabilidadVencidad(){
        
        
        return false;
    }
    
    
    /**
     * Asocia un instrumento de medición a un proceso.
     * 
     * @param em
     * @param idLoteProceso
     * @param idInstrumento
     */
    public static synchronized void guardarInstrumentoAcociado(EntityManager em, long idLoteProceso, long idInstrumento){
        LoteProceso proceso = Listas.loteProceso(em, idLoteProceso);
        Instrumento instrumento = Listas.instrumento(em, idInstrumento);
        
        if(instrumento == null || proceso == null){
            return;
        }
        
        
        //Si el instrumento ya está ingresado, no hacemos nada
        if(proceso.getIdInstrumento1() != null && proceso.getIdInstrumento1().equals(instrumento)){
            return;
        }
        if(proceso.getIdInstrumento2() != null && proceso.getIdInstrumento2().equals(instrumento)){
            return;
        }
        if(proceso.getIdInstrumento3() != null && proceso.getIdInstrumento3().equals(instrumento)){
            return;
        }
        if(proceso.getIdInstrumento4() != null && proceso.getIdInstrumento4().equals(instrumento)){
            return;
        }
        if(proceso.getIdInstrumento5() != null && proceso.getIdInstrumento5().equals(instrumento)){
            return;
        }
        if(proceso.getIdInstrumento6() != null && proceso.getIdInstrumento6().equals(instrumento)){
            return;
        }

        //Si no está ingresado, buscamos el primer null
        if(proceso.getIdInstrumento1() == null){
            proceso.setIdInstrumento1(instrumento);
        }
        else if(proceso.getIdInstrumento2() == null){
            proceso.setIdInstrumento2(instrumento);
        }
        else if(proceso.getIdInstrumento3() == null){
            proceso.setIdInstrumento3(instrumento);
        }
        else if(proceso.getIdInstrumento4() == null){
            proceso.setIdInstrumento4(instrumento);
        }
        else if(proceso.getIdInstrumento5() == null){
            proceso.setIdInstrumento5(instrumento);
        }
        else if(proceso.getIdInstrumento6() == null){
            proceso.setIdInstrumento6(instrumento);
        }


        ServicioAbm.editar(em, proceso);
    }


    /**
     * Gaurda una medición de estabilidad de un instrumento de medición
     * 
     * @param em
     * @param idInstrumento
     * @param usuario
     * @param patron
     * @param medida1
     * @param medida2
     * @param medida3
     */
    public static synchronized void guardarInstrumentoEstabilidad(EntityManager em, long idInstrumento, Usuario usuario, double patron, double medida1, double medida2, double medida3){
        Instrumento instrumento = Listas.instrumento(em, idInstrumento);
        
        if(instrumento == null){
            return;
        }
        
        InstrumentoEstabilidad estabilidad = new InstrumentoEstabilidad();
        estabilidad.setFecha(Calendar.getInstance().getTime());
        estabilidad.setIdInstrumento(instrumento);
        estabilidad.setIdUsuario(usuario);
        estabilidad.setPatron(patron);
        estabilidad.setMedida1(medida1);
        estabilidad.setMedida2(medida2);
        estabilidad.setMedida3(medida3);
        
        ServicioAbm.editar(em, estabilidad);
    }

    
    
    
    
    
    
    
    
    
    
    
    /**
     * 
     * @param em
     * @param usuario
     * @return una JSONObject conteniendo un array con los datos de las componentes del proceso enviado
     */
    public static synchronized JSONObject listaResponsabilidad(EntityManager em, Usuario usuario){
        List<JSONObject> lista = new ArrayList<>();
        List<String> cargados = new ArrayList<>();

        
        //Cargamos los responsabilidades generales
        for(DepartamentoResp responsabilidad : Listas.DepartamentoRespGenerales(em)){
            if(cargados.contains(responsabilidad.getNombre())){
                continue;
            }
            
            JSONObject js = new JSONObject();
            js.put(EnumJson.D_RESPONSABILIDAD_NOMBRE.getNombre(), responsabilidad.getNombre());
            js.put(EnumJson.D_RESPONSABILIDAD_R.getNombre(), responsabilidad.getResponsabilidad());
            js.put(EnumJson.D_RESPONSABILIDAD_A.getNombre(), responsabilidad.getAutoridad());
            lista.add(js);
            cargados.add(responsabilidad.getNombre());
        }
        
        //Cargamos los responsabilidades personales del usario
        for(DepartamentoRolUsuario rol: usuario.getDepartamentoRolUsuarioList()){
            //Saltemos los no activos.
            if(!rol.getActivo()){
                continue;
            }
            for(DepartamentoResp responsabilidad : Listas.DepartamentoResp(em, rol.getIdDepartamentoRol())){

                if(cargados.contains(responsabilidad.getNombre())){
                    continue;
                }

                JSONObject js = new JSONObject();
                js.put(EnumJson.D_RESPONSABILIDAD_NOMBRE.getNombre(), responsabilidad.getNombre());
                js.put(EnumJson.D_RESPONSABILIDAD_R.getNombre(), responsabilidad.getResponsabilidad());
                js.put(EnumJson.D_RESPONSABILIDAD_A.getNombre(), responsabilidad.getAutoridad());
                lista.add(js);
                cargados.add(responsabilidad.getNombre());
                
            }
        }

        JSONObject js = new JSONObject();
        js.put(EnumJson.D_RESPONSABILIDAD_DATOS.getNombre(), lista);
        return js;
    }
    
    
    
    
    

    /**
     * Verifica si el instrumento puede medir una cota
     * @param instrumento
     * @param cota
     * @return 
     */
    private static synchronized  boolean instrumentoOK(Instrumento instrumento, LoteCota cota){
        return instrumento != null &&
               instrumento.getTieneSalida() != null &&
               instrumento.getTieneSalida() &&
               instrumento.getIdInstrumentoTipo() != null &&
               instrumento.getIdInstrumentoTipo().equals(cota.getIdInstrumentoTipoP());
    }

    /**
     * Verifica el estado de los datos en el monitor e indica cuales hay que actualizar.
     * @param em
     * @param idInstrumento
     * @return 
     */
    public static synchronized JSONObject instrumentoVerificar(EntityManager em, Long idInstrumento){
        String estado;
        
        Instrumento instrumento = Listas.instrumento(em, idInstrumento);        
        if (instrumento != null){
            estado  = "Tipo de instrumento: " + instrumento.getIdInstrumentoTipo().getNombre() + "   -  " +  instrumento.getNombre() + " \n";
            estado += "Ubicación                  : ";
            
            if(instrumento.getLugar() != null){
                estado += instrumento.getLugar() + " \n";
            }
            else{
                estado += "NO registrada" + " \n";
            }
            
            
            

            if (instrumento.getIdInstrumentoEstado().equals(EnumInstrumentoEstado.ELIMINADO.getId())){
                estado += "Instrumento eliminado";
            }
            else if (instrumento.getIdInstrumentoEstado().equals(EnumInstrumentoEstado.SUSPENDIDO.getId())){
                estado += "Instrumento suspendido";
            }
            else if(instrumento.getIdInstrumentoEstado().equals(EnumInstrumentoEstado.EN_SERVICIO.getId())){
                Calibracion calibracion = Listas.calibracionActual(em, instrumento);
                estado += "Calibración                : ";
                if(calibracion != null){
                    if(calibracion.getFechaVence().before(Calendar.getInstance().getTime())){
                        estado += "VENCIDA  (venció el " + ServicioFecha.fechaFormateada("dd/MM/yyyy", calibracion.getFechaVence()) + ")";
                    }
                    else{
                        estado += "APTA. Vence el " + ServicioFecha.fechaFormateada("dd/MM/yyyy", calibracion.getFechaVence());
                    }
                }
                else{
                    estado += "NO calibrado";
                }
            }
            else{
                    estado += "Instrumento sin estado definido";
            }
        }
        else{
            estado = "Instrumento inexistente";
        }
        
        
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_GAGE_ESTATE.getNombre(), estado);
        return js;
    }
    

    /**
     * @param em
     * @param idLoteProceso
     * @param asignados
     * @return JSON conteniendo lista de articulos
     */
    public static synchronized JSONObject listaMonitoresAsigandos(EntityManager em, long idLoteProceso, boolean asignados){

        List<JSONObject> lista = new ArrayList<>();
        for(Monitor monitor: Listas.ListaMonitor(em, idLoteProceso, asignados)){
            JSONObject js = new JSONObject();
            js.put(EnumJson.D_MONITOR_ID.getNombre()       , monitor.getIdMonitor());
            js.put(EnumJson.D_MONITOR_NOMBRE.getNombre()   , monitor.getNombre());
            lista.add(js);
        }
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_MONITORES.getNombre(), lista);
        return js;
    }
    
    /**
     * @param em
     * @param idLoteProceso
     * @param idMonitor
     * @param asignar
     */
    public static synchronized void monitorAsignar(EntityManager em, long idLoteProceso, long idMonitor, boolean asignar){

        if(asignar){
            LoteProceso loteProceso = Listas.loteProceso(em, idLoteProceso);
            Monitor monitor = Listas.monitor(em, idMonitor);

            //Si el monitor ya fue asigando
            if(Listas.MonitorProceso(em, monitor, idLoteProceso) != null){
                return;
            }

            if(loteProceso == null || monitor == null){
                return;
            }
            
            MonitorProceso nuevo = new MonitorProceso();
            nuevo.setActivo(true);
            nuevo.setIdEstado(0);
            nuevo.setIdLoteProceso(Listas.loteProceso(em, idLoteProceso));
            nuevo.setIdMonitor(Listas.monitor(em, idMonitor));
            ServicioAbm.crear(em, nuevo);
        }
        else{
            LoteProceso loteProceso = Listas.loteProceso(em, idLoteProceso);
            Monitor monitor = Listas.monitor(em, idMonitor);
            if(loteProceso == null || monitor == null){
                return;
            }
            MonitorProceso borrar = Listas.MonitorProceso(em, monitor, idLoteProceso);
            if(borrar != null){
                ServicioAbm.borrar(em, borrar);
            }
        }
    }

    
    

    public static synchronized JSONObject loteCrear(EntityManager em, long idArticulo, long cantidad){

        
        JSONObject js = new JSONObject();
        
        //Si el articulo es null
        Articulo articulo = Listas.articulo(em, idArticulo);
        if(articulo == null){
            js.put(EnumJson.D_LOTE_CREAR_MENSAJE.getNombre(), "Artículo no seleccionado");
            return js;
        }

        //Si hay partida con menos de 120 dias, no creamos nada
        Lote loteReciente = Listas.loteReciente(em, articulo);
        Calendar cal = Calendar.getInstance();
        Calendar calLote = Calendar.getInstance();
        calLote.setTime(loteReciente.getFecha());
        
        cal.add(Calendar.DAY_OF_YEAR, -120);
        if(cal.before(calLote)){
            js.put(EnumJson.D_LOTE_CREAR_MENSAJE.getNombre(), "Usar la partida actual");
            return js;
        }
            
            
        //Buscamos la planificación a copiar
        List<Plan> planLista = Listas.plan(em, articulo);
        Plan plan;

        //Si no tiene planificación
        if(planLista.size() < 1){
            js.put(EnumJson.D_LOTE_CREAR_MENSAJE.getNombre(), "Articulo sin planificación de calidad");
            return js;
        }
        //Si hay un solo plan.
        else if(planLista.size() == 1){
            plan = planLista.get(0);
        }
        //Si hay mas de uno, elegimos el ultimo
        else{
            plan = planLista.get(planLista.size() - 1);
        }
        
        if(plan == null){
            js.put(EnumJson.D_LOTE_CREAR_MENSAJE.getNombre(), "Articulo sin planificación de calidad");
            return js;
        }
        
        crearLoteDePLan(em, plan);

        js.put(EnumJson.D_LOTE_CREAR_MENSAJE.getNombre(), "Lote creado");
        return js;
        
    }
    
    /**
     * Crea un lote a partir de un plan.
     * @param planOriginal 
     */
    private static void crearLoteDePLan(EntityManager em, Plan planOriginal){
        //creamos el lote
        Lote loteNuevo = new Lote();
        loteNuevo.setIdArticulo(planOriginal.getIdArticulo());
        loteNuevo.setEdicion(planOriginal.getEdicion());
        loteNuevo.setFecha(Calendar.getInstance().getTime());
        loteNuevo.setIdPlan(planOriginal);
        loteNuevo.setNota(planOriginal.getNota());
        loteNuevo.setLoteProcesoList(new ArrayList<>());
        loteNuevo.setCodigo(Listas.loteCodigoMax(em, planOriginal.getIdArticulo()) + 1);

        //Creamos las operaciones, cotas y componentes
        for(PlanProceso planProcesoOriginal: Listas.planProceso(em, planOriginal)){
            LoteProceso loteProcesoNuevo = new LoteProceso();
            loteProcesoNuevo.setCodigo(planProcesoOriginal.getCodigo());
            loteProcesoNuevo.setIdLote(loteNuevo);
            loteProcesoNuevo.setIdStockAlmacen(planProcesoOriginal.getIdStockAlmacen());
            loteProcesoNuevo.setIdStockEstado(planProcesoOriginal.getIdStockEstado());
            loteProcesoNuevo.setIdStockUbicacion(planProcesoOriginal.getIdStockUbicacion());
            loteProcesoNuevo.setIdTipoProceso(planProcesoOriginal.getIdTipoProceso());
            loteProcesoNuevo.setNota(planProcesoOriginal.getNota());
            loteProcesoNuevo.setNombre(planProcesoOriginal.getNombre());
            loteProcesoNuevo.setUsarStockEnEquipo(planProcesoOriginal.getUsarStockEnEquipo());
            
            loteProcesoNuevo.setLoteComponenteList(new ArrayList<>());
            loteProcesoNuevo.setLoteCotaList(new ArrayList<>());
            loteProcesoNuevo.setLoteProcesoParametroList(new ArrayList<>());
            
            //Creamos las cotas
            for(PlanCota planCota: Listas.planCota(em, planProcesoOriginal)){
                LoteCota loteCotaNuevo = new LoteCota();
                loteCotaNuevo.setCodigo(planCota.getCodigo());
		loteCotaNuevo.setIdPlanCotaTipo(planCota.getIdPlanCotaTipo());
                loteCotaNuevo.setIdClase(planCota.getIdClase());
                loteCotaNuevo.setIdFrecuenciaC(planCota.getIdFrecuenciaC());
                loteCotaNuevo.setIdFrecuenciaP(planCota.getIdFrecuenciaP());
                loteCotaNuevo.setIdInstrumentoTipoC(planCota.getIdInstrumentoTipoC());
                loteCotaNuevo.setIdInstrumentoTipoP(planCota.getIdInstrumentoTipoP());
                loteCotaNuevo.setIdLoteProceso(loteProcesoNuevo);
                loteCotaNuevo.setIdTipoAnalisisC(planCota.getIdTipoAnalisisC());
                loteCotaNuevo.setIdTipoAnalisisP(planCota.getIdTipoAnalisisP());
                loteCotaNuevo.setIdRegistroC(planCota.getIdRegistroC());
                loteCotaNuevo.setIdRegistroP(planCota.getIdRegistroP());
                loteCotaNuevo.setMuestraC(planCota.getMuestraC());
                loteCotaNuevo.setMuestraP(planCota.getMuestraP());
                loteCotaNuevo.setNombre(planCota.getNombre());
                loteCotaNuevo.setTolerancia(planCota.getTolerancia());

                loteProcesoNuevo.getLoteCotaList().add(loteCotaNuevo);
            }

            //Creamos los componentes
            for(PlanComponente planComp: Listas.planComponente(em, planProcesoOriginal)){
                
                LoteComponente loteComponenteNuevo = new LoteComponente();
                loteComponenteNuevo.setGrupo(planComp.getGrupo());
                loteComponenteNuevo.setConsumo(planComp.getConsumo());
                loteComponenteNuevo.setIdArticuloMPC(planComp.getIdArticuloMPC());
                loteComponenteNuevo.setIdLoteProceso(loteProcesoNuevo);

                loteProcesoNuevo.getLoteComponenteList().add(loteComponenteNuevo);
            }
            
            
            //Creamos los parametos de procesos (insertos y otros controles)
            for(PlanProcesoParametro parametro: Listas.PlanProcesoParametro(em, planProcesoOriginal)){
                
                LoteProcesoParametro parametroNuevo = new LoteProcesoParametro();
                
                parametroNuevo.setCorrector(parametro.getCorrector());
                parametroNuevo.setIdLoteProceso(loteProcesoNuevo);
                parametroNuevo.setIdTipoAccion(parametro.getIdTipoAccion());
                parametroNuevo.setNombre(parametro.getNombre());
                parametroNuevo.setPeriodo(parametro.getPeriodo());

                loteProcesoNuevo.getLoteProcesoParametroList().add(parametroNuevo);
            }
            

            //Agregamos el proceso nuevo con sus listas al nuevo lote.
            loteNuevo.getLoteProcesoList().add(loteProcesoNuevo);
        }
        

        //Persisitimos el lote y lo refrescamos en la memoria
        ServicioAbm.persistir(em, loteNuevo, true);
        loteNuevo = Listas.lote(em, planOriginal.getIdArticulo(), loteNuevo.getCodigo());
        
        
        //Ajustamos los procesos de origen
        for(LoteProceso loteProceso : Listas.loteProceso(em, loteNuevo)){
        
            //Obtenemos el proceso de origen y el liberador según la planificación
            PlanProceso planProcesoOrigen = Listas.planProceso(em, planOriginal, loteProceso.getCodigo()).getIdPlanProcesoO();
            
            //Proceso de origen
            if(planProcesoOrigen != null){
                //Según el código de la planificación, obtenemos el proceso de origen
                loteProceso.setIdLoteProcesoO(Listas.loteProceso(em, loteNuevo, planProcesoOrigen.getCodigo()));
                //Persistimos el cambio.
                ServicioAbm.editar(em, loteProceso);
            }
        }
        
        //Ajustamos el proceso liberador
        PlanProceso planProcesoLiberador = planOriginal.getIdProcesoLiberador();
        if(planProcesoLiberador != null){
            //Según el código de la planificación, obtenemos el proceso de origen
            loteNuevo.setIdProcesoLiberador(Listas.loteProceso(em, loteNuevo, planProcesoLiberador.getCodigo()));
            //Persistimos el cambio.
            ServicioAbm.persistir(em, loteNuevo, false);
        }
        
    }















    

    
    /**
     * @param em
     * @return Json conteniendo el plan de contingencia
     */
    public static synchronized JSONObject datosGenerales(EntityManager em){
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_DATOS_GENERALES_POLITICA.getNombre(), Listas.politica(em).getPolitica());
        js.put(EnumJson.D_DATOS_GENERALES_CONTINGENCIA.getNombre(), Listas.empresa(em).getPlanContingencia());
        return js;
    }


    /**
     * @param em
     * @param prefijo
     * @return JSON conteniendo lista de articulos
     */
    public static synchronized JSONObject listaArticulos(EntityManager em, String prefijo){

        
        List<JSONObject> lista = new ArrayList<>();
        
        if(prefijo.trim().length() > 0){
            for(Articulo articulo: Listas.listaArticulo(em, prefijo)){
                JSONObject js = new JSONObject();
                js.put(EnumJson.D_ARTICULO_ID.getNombre()                   , articulo.getIdArticulo());
                js.put(EnumJson.D_ARTICULO_CODIGO.getNombre()               , articulo.getCodigo());
                js.put(EnumJson.D_ARTICULO_NOMBRE.getNombre()               , articulo.getNombre());

                Lote loteReciente = Listas.loteReciente(em, articulo);
                if(loteReciente != null){
                    js.put(EnumJson.D_ARTICULO_LOTE_ULTIMO_ID.getNombre()       , loteReciente.getIdLote());
                    js.put(EnumJson.D_ARTICULO_LOTE_ULTIMO.getNombre()          , loteReciente.getCodigo() + "");
                    js.put(EnumJson.D_ARTICULO_LOTE_ULTIMO_FECHA.getNombre()    , ServicioFecha.fechaFormateada("dd-MM-yyyy", loteReciente.getFecha()));
                }
                else{
                    js.put(EnumJson.D_ARTICULO_LOTE_ULTIMO_ID.getNombre()       , 0);
                    js.put(EnumJson.D_ARTICULO_LOTE_ULTIMO.getNombre()          , " ");
                    js.put(EnumJson.D_ARTICULO_LOTE_ULTIMO_FECHA.getNombre()    , " ");

                }
                lista.add(js);
            }
        }
        
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_ARTICULOS.getNombre(), lista);
        return js;
    }

    /**
     * @param em
     * @param idLote
     * @return JSON conteniendo lista de articulos
     */
    public static synchronized JSONObject listaPlanProcesos(EntityManager em, long idLote){

        List<JSONObject> lista = new ArrayList<>();
        for(LoteProceso loteProceso: Listas.listaLoteProceso(em, idLote)){
            JSONObject js = new JSONObject();
            js.put(EnumJson.D_LOTE_PROCESO_ID.getNombre()       , loteProceso.getIdLoteProceso());
            js.put(EnumJson.D_LOTE_PROCESO_CODIGO.getNombre()   , loteProceso.getCodigo());
            js.put(EnumJson.D_LOTE_PROCESO_NOMBRE.getNombre()   , loteProceso.toString());

            lista.add(js);
        }
        
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_LOTE_PROCESOS.getNombre(), lista);
        return js;
    }




    

/**
     * Verifica el estado de los datos en el monitor e indica cuales hay que actualizar.
     * @param monitor
     * @return 
     */
    
/*    public static synchronized JSONObject checkUpdates(Monitor monitor){
        String listaProcesosCambiada = "0";
        String listaCotasCambiada = "0";
        
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_UPDATES.getNombre(), listaProcesosCambiada + listaCotasCambiada + 0 + 0);
        return js;
    }
    */


     /**
     * Verifica el estado de los datos en el monitor e indica cuales hay que actualizar.
     * @param em
     * @param monitor
     * @return 
     */
/*    public static synchronized JSONObject updateIndicators(EntityManager em, Monitor monitor){
        String cotasVencidas = "false";
        String herramientasVencidas = "false";
        String mantenimientosVencidos = "false";
        
        
        //Verificamos mediciones pendientes
        try{
            int idProceso = monitor.getIdMonitorProcesoEnMedicion().getIdLoteProceso().getIdLoteProceso();
            for(LoteCota cota: Listas.loteCotaList(em, idProceso)){
                if(cotaVencida(em, cota, true)){
                    cotasVencidas = "true";
                    break;
                }
            }
        }
        catch(NullPointerException e){}
        
        //Verificamos tareas de mantenimeinto
        try{
            Equipo equipo = monitor.getIdMonitorProcesoEnMedicion().getIdLoteProceso().getIdStockUbicacion().getIdEquipo();
            for(EquipoMantenimiento tarea: Listas.equipoMantenimientoNivel1PorCodigo(em, equipo)){
                Calendar cal = Calendar.getInstance();

                if(tarea.getFechaControl() != null){
                    cal.setTime(tarea.getFechaControl());
                }
                else{
                    cal.add(Calendar.YEAR, -1000);
                }
                cal.add(Calendar.DAY_OF_YEAR, tarea.getPeriodoEnDias());
                if(!cal.after(Calendar.getInstance())){
                    mantenimientosVencidos = "true";
                    break;
                }
            }
        }
        catch(NullPointerException e){}
        
        
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_QUALITY_CONTROL_INDICATOR.getNombre(), cotasVencidas);
        js.put(EnumJson.D_TOOLING_INDICATOR.getNombre(), herramientasVencidas);
        js.put(EnumJson.D_MAINTENANCE_INDICATOR.getNombre(), mantenimientosVencidos);
        return js;
    }
    
    */
    
    
    /**
     * Verifica el estado de vencimiento del periodo de control de la cota, teniendo en cuenta si se trata de control del Dpto. de producción o del Dpto. de Calidad
     * @param cota
     * @param produccion
     * @return true si el periodo de control de la cota ha vencido
     */
    private static boolean cotaVencida(EntityManager em, LoteCota cota){
            
        if(!cotaMedible(cota)){
            return false;
        }
            
        Date fechaUltimaMedida = Listas.loteMedidaFechaUltima(em, cota);
        if(fechaUltimaMedida == null){
            return true;
        }
        
        long tiempoDesdeUltimaMedicion = Calendar.getInstance().getTimeInMillis() - fechaUltimaMedida.getTime();
        long tiempoMáximo; 
        
        if(cota.getIdLoteProceso().getIdLote().getPreserie()){
            tiempoMáximo = (long) (cota.getIdFrecuenciaC().getSegundos() * 0.8 * 1000);
        }
        else{
            tiempoMáximo = (long) (cota.getIdFrecuenciaP().getSegundos() * 0.8 * 1000);
        }
        return tiempoDesdeUltimaMedicion > tiempoMáximo; 
        
    }
    
    /**
     * Verifica si la cota es medible, según el método de analisis.
     * @param cota
     * @param produccion
     * @return true si la cota fue indicada por el usuario de Intampentu como para ser medida.
     */
    private static boolean cotaMedible(LoteCota cota){
    
        boolean preserie = cota.getIdLoteProceso().getIdLote().getPreserie();
        
        //preserie
        if(     preserie && 
                cota.getIdFrecuenciaC() != null &&
                cota.getIdTipoAnalisisC() != null &&
                !EnumAnalisis.NINGUNO.getId().equals(cota.getIdTipoAnalisisC())){
                return true;
        }

        //serie
        return cota.getIdFrecuenciaP() != null &&
               cota.getIdTipoAnalisisP() != null && 
               !EnumAnalisis.NINGUNO.getId().equals(cota.getIdTipoAnalisisP());
    }
    
    
    
    /**
     * Verifica si se trata de un control del Dpto. de Producción o del Dpto. de Calidad
     * @param idLoteProceso
     * @return true si el control de por parte del Dpto de Producción. False si debe controlar el Dpto. de Ctrl. de Calidad.
     */
    private static boolean esControlProduccion(EntityManager em, int idLoteProceso, Monitor monitor){
        LoteProceso proceso = Listas.loteProceso(em, idLoteProceso);
        try{
            return !(monitor.getIdUsuario() != null && 
                    (monitor.getIdUsuario().equals(proceso.getIdUsuarioControl()) ||
                     monitor.getIdUsuario().equals(proceso.getIdUsuarioLiberacion())));
        }
        catch(NullPointerException e){
            return true;
        }
    }
    
    /**
     * 
     * @param em
     * @param monitor
     * @param idLoteProceso
     * @return una JSONObject conteniendo un array con los datos de los procesos asignados al monitor
     */
    public static synchronized JSONObject listaTareasMantenimiento(EntityManager em, Monitor monitor,long idLoteProceso){
        if(monitor == null){
            return null;
        }
            
        List<JSONObject> lista = new ArrayList<>();
        try{
            LoteProceso proceso = Listas.loteProceso(em, idLoteProceso);
            Equipo equipo = proceso.getIdStockUbicacion().getIdEquipo();
            

            for(EquipoMantenimiento tarea: Listas.equipoMantenimientoNivel1PorCodigo(em, equipo)){
                JSONObject js = new JSONObject();
                js.put(EnumJson.D_TASK_ID.getNombre()            , tarea.getIdEquipoMantenimiento());
                js.put(EnumJson.D_TASK_NAME.getNombre()          , tarea.getNombre());
                js.put(EnumJson.D_TASK_PERIOD.getNombre()        , tarea.getPeriodoEnDias());
                js.put(EnumJson.D_TASK_PERIOD_NAME.getNombre()   , "Dias");
                js.put(EnumJson.D_TASK_STATE.getNombre()         , 0);
                lista.add(js);
            }
            JSONObject js = new JSONObject();
            js.put(EnumJson.D_TASKS.getNombre(), lista);
            return js;
        }
        catch(NullPointerException e){
            GUI.agregarTexto("WARNING: lista de mantenimiento vacia", Color.red, true);

            //Creamos una lista vacia.
            JSONObject js = new JSONObject();
            js.put(EnumJson.D_TASKS.getNombre(), lista);
            return js;
        }
    }

    /**
     * 
     * @param em
     * @param monitor
     * @param idLoteProceso
     * @return una JSONObject conteniendo un array con los datos de los procesos asignados al monitor
     */
    public static synchronized JSONObject listaTareasMantenimientoPend(EntityManager em, Monitor monitor,long idLoteProceso){
        if(monitor == null){
            GUI.agregarTexto("monitor null", Color.MAGENTA, true);
            return null;
        }

        List<JSONObject> lista = new ArrayList<>();
        try{
            LoteProceso proceso = Listas.loteProceso(em, idLoteProceso);
            Equipo equipo = proceso.getIdStockUbicacion().getIdEquipo();
            
            GUI.agregarTexto("Equipo: " + equipo.getCodigo(), Color.MAGENTA, true);
            

            for(EquipoMantenimiento tarea: Listas.equipoMantenimientoNivel1PorCodigo(em, equipo)){
                Calendar cal = Calendar.getInstance();
                if(tarea.getFechaControl() != null){
                    cal.setTime(tarea.getFechaControl());
                }
                else{
                    cal.add(Calendar.YEAR, -1000);
                }
                cal.add(Calendar.DAY_OF_YEAR, tarea.getPeriodoEnDias());
            
                if(cal.after(Calendar.getInstance())){
                    continue;
                }

                JSONObject js = new JSONObject();
                
                js.put(EnumJson.D_TASK_ID.getNombre()            , tarea.getIdEquipoMantenimiento());
                js.put(EnumJson.D_TASK_NAME.getNombre()          , tarea.getNombre());
                js.put(EnumJson.D_TASK_PERIOD.getNombre()        , tarea.getPeriodoEnDias());
                js.put(EnumJson.D_TASK_PERIOD_NAME.getNombre()   , "Dias");
                js.put(EnumJson.D_TASK_STATE.getNombre()         , 0);
                lista.add(js);
            }
            JSONObject js = new JSONObject();
            js.put(EnumJson.D_TASKS.getNombre(), lista);
            return js;
        }
        catch(NullPointerException e){
            GUI.agregarTexto("WARNING: lista de mantenimiento vacia", Color.red, true);

            //Creamos una lista vacia.
            JSONObject js = new JSONObject();
            js.put(EnumJson.D_TASKS.getNombre(), lista);
            return js;
        }
    }
    
    /**
     * 
     * @param em
     * @return una JSONObject conteniendo un array con los datos de los procesos asignados al monitor
     */
    public static synchronized JSONObject listaTiposDeFallas(EntityManager em){
        try{
            List<JSONObject> lista = new ArrayList<>();
            for(FallaTipo fallaTipo: Listas.fallaTipo(em)){
                JSONObject js = new JSONObject();
                js.put(EnumJson.D_FAILURE_TYPE_ID.getNombre(), fallaTipo.getIdFallaTipo());
                js.put(EnumJson.D_FAILURE_TYPE_NAME.getNombre(), fallaTipo.getNombre());
                lista.add(js);
            }
            JSONObject js = new JSONObject();
            js.put(EnumJson.D_FAILURE_TYPES.getNombre(), lista);
            return js;
        }
        catch(NullPointerException e){
            System.out.print(e.getMessage());
            return null;
        }
    }
    
    /**
     * 
     * @param em
     * @param cadena 
     * @param usuario 
     */
    public static synchronized void guardarFalla(EntityManager em, String cadena, Usuario usuario){
        JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadena);
        FallaTipo fallaTipo = Listas.fallaTipo(em, (long)js.get(EnumJson.D_FAILURE_TO_SAVE_IDTYPE.getNombre()));
        LoteProceso proceso = Listas.loteProceso(em, (int)(long)js.get(EnumJson.D_FAILURE_TO_SAVE_PROCESS_ID.getNombre()));

        
        String fecha = (String) js.get(EnumJson.D_FAILURE_TO_SAVE_DATE.getNombre());
        String hora = (String) js.get(EnumJson.D_FAILURE_TO_SAVE_TIME.getNombre());
        Calendar cal = Calendar.getInstance();
//
//        cal.set(Calendar.YEAR           , Integer.parseInt(fecha.substring(0, 4)));
//        cal.set(Calendar.MONTH          , Integer.parseInt(fecha.substring(5, 7)) - 1);
//        cal.set(Calendar.DAY_OF_MONTH   , Integer.parseInt(fecha.substring(8, 10)));
//        cal.set(Calendar.HOUR_OF_DAY    , Integer.parseInt(hora.substring(0, 2)));
//        cal.set(Calendar.MINUTE         , Integer.parseInt(hora.substring(3, 5)));
//        cal.set(Calendar.SECOND         , 0);
        
        Falla falla = new Falla();
        falla.setCantidad((int)(long)js.get(EnumJson.D_FAILURE_TO_SAVE_QUANTITY.getNombre()));
        //falla.setFecha(cal.getTime());
        falla.setFecha(Calendar.getInstance().getTime());
        falla.setIdArticulo(proceso.getIdLote().getIdArticulo());
        falla.setIdDepartamento(4);
        falla.setIdFallaTipo(fallaTipo);
        falla.setIdUsuario(usuario);

        
        
        ServicioAbm.editar(em, falla);
    }
    
    
    /**
     * @param em
     * @return JSON conteniendo lista de articulos
     */
    public static synchronized JSONObject listaDocumentos(EntityManager em){

        List<JSONObject> lista = new ArrayList<>();
        for(Documento documento: Listas.listaDocumento(em)){
            JSONObject js = new JSONObject();
            js.put(EnumJson.D_DOCUMENTO_ID.getNombre()    , documento.getIdDocumento());
            js.put(EnumJson.D_DOCUMENTO_NOMBRE.getNombre(), documento.getNombre());
            js.put(EnumJson.D_DOCUMENTO_TEMA.getNombre(), documento.getTema());
            
            //Id del plano de la pieza.
            try{
                DocumentoEdicion edicion = Listas.DocumentoEdicionUltima(em, documento);
                //Buscamos una imagen pdf o JPG. LibreOffice no puede mostrarse
                boolean cargado = false;
                for(DocumentoImagen imagen: edicion.getDocumentoImagenList()){
                    if(imagen.getIdTipoArchivo() == EnumArchivoTipo.JPG.getId() ||
                       imagen.getIdTipoArchivo() == EnumArchivoTipo.PDF.getId()){
                        js.put(EnumJson.D_DOCUMENTO_IMAGEN_ID.getNombre()              , imagen.getIdDocumentoImagen());
                        js.put(EnumJson.D_DOCUMENTO_IMAGEN_TIPO.getNombre()            , imagen.getIdTipoArchivo());
                        GUI.agregarTexto("Se cargó imagen de " + documento.toString(), Color.blue);
                        cargado = true;
                        break;
                    }
                }
                if(!cargado){
                    js.put(EnumJson.D_DOCUMENTO_IMAGEN_ID.getNombre()              , -1);
                    js.put(EnumJson.D_DOCUMENTO_IMAGEN_TIPO.getNombre()            , -1);
                }
            }
            catch(Exception e){
                js.put(EnumJson.D_DOCUMENTO_IMAGEN_ID.getNombre()              , -1);
                js.put(EnumJson.D_DOCUMENTO_IMAGEN_TIPO.getNombre()            , -1);
                GUI.agregarTexto("No se cargó imagen de " + documento.toString(), Color.red);
            }

            
            lista.add(js);
        }
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_DOCUMENTOS.getNombre(), lista);
        return js;
    }
    
    
    /**
     * @param em
     * @param IdLoteCota
     * @return JSON conteniendo lista de articulos
     */
    public static synchronized JSONObject listaCharts(EntityManager em, long IdLoteCota){
        
        
        List<JSONObject> listaXy    = new ArrayList<>();
        List<JSONObject> listaBarras   = new ArrayList<>();
        
        LoteCota cota = Listas.loteCota(em, IdLoteCota);
        ServicioCota sc = new ServicioCota(cota.getTolerancia());
        sc.setListaMedidas(cota.getLoteMedidaList());

        //Serie vacias para el gráfico de barras.
        double minimo = Math.min(Listas.loteMedidaMinimo(em, IdLoteCota), sc.getMinimo());
        double maximo = Math.max(Listas.loteMedidaMaximo(em, IdLoteCota), sc.getMaximo());
        double intervalo = (maximo - minimo)/20;
        List<IntDoubleString> listaBarrasInicial = new ArrayList<>();
        for(double i = minimo; i <= maximo; i +=intervalo){
            listaBarrasInicial.add(new IntDoubleString(0, i, ServicioNumero.formateado(sc.getDecimales(EnumAnalisis.CPK.getId()), i)));
            //GUI.agregarTexto("ListaCharts- Barras: " + i, Color.red);
        }
        
        
        //Cargamos las medidas  a las listas
        int contador = 0;
        for(LoteMedida medida: Listas.loteMedidaList(em, IdLoteCota)){

            //Grafico de lineas
            JSONObject js = new JSONObject();
            js.put(EnumJson.D_LINECHART_X.getNombre()    , contador++);
            js.put(EnumJson.D_LINECHART_Y.getNombre()    , medida.getValor());
            js.put(EnumJson.D_LINECHART_COLOR.getNombre()  , sc.colorDeMedida(medida.getValor()));
            listaXy.add(js);
            
            //Color del punto en el grafico de lineas.
            
            
            //Gráfico de barras. Recorremos la lista de datos y sumamos 1 según el rango en que caiga la medición
            for(IntDoubleString item: listaBarrasInicial){
                if(medida.getValor() >= item.getDoble() &&
                   medida.getValor() < item.getDoble() + intervalo){
                    item.setEntero(item.getEntero() + 1);
                    break;
                }
            }
        }
        
        
        //Pasamos la lista de grafico de barras a JSon
        int barContador = 0;
        for(IntDoubleString item: listaBarrasInicial){
            //GUI.agregarTexto("ListaCharts- Barras: " + contador  + " - " + item.getEntero() + "  -   "  + item.getNombre(),   Color.red);
            JSONObject js = new JSONObject();
            js.put(EnumJson.D_BARCHART_X.getNombre()        , barContador++);
            js.put(EnumJson.D_BARCHART_Y.getNombre()        , item.getEntero());
            js.put(EnumJson.D_BARCHART_X_LABEL.getNombre()  , item.getNombre());

            //Color de la columna. El valor de la columna está en el nombre
            js.put(EnumJson.D_BARCHART_COLOR.getNombre()  , sc.colorDeMedida(Double.parseDouble(item.getNombre().replace(",", "."))));
            
            listaBarras.add(js);
            
        }

        //GUI.agregarTexto("Rojo " + Color.RED, Color.red);
        //GUI.agregarTexto("Amarillo " + Color.YELLOW, Color.red);
        //GUI.agregarTexto("Verde " + Color.GREEN, Color.red);
        
        
        //Cargamos ambas listas a un JSON
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_LINECHARTLISTA.getNombre(), listaXy);
        js.put(EnumJson.D_BARCHARTLISTA.getNombre(), listaBarras);
        js.put(EnumJson.D_CHART_N.getNombre(), contador);
        js.put(EnumJson.D_CHART_MEDIA_IDEAL.getNombre(), sc.getMedioDeTolerancia());
        js.put(EnumJson.D_CHART_MEDIA_REAL.getNombre(), sc.getMedia());
        js.put(EnumJson.D_CHART_CP.getNombre(), sc.getCP());
        js.put(EnumJson.D_CHART_CPK.getNombre(), sc.getCPK());
        js.put(EnumJson.D_CHART_DECIMALES.getNombre(), sc.getDecimales(EnumAnalisis.CPK.getId()));
        
        return js;
    }
    
    
    
    
    /**
     * @param em
     * @param IdIndicador 
     * @return JSON conteniendo lista de articulos
     */
    public static synchronized JSONObject listaIndicador(EntityManager em, long IdIndicador){
        List<JSONObject> listaXy    = new ArrayList<>();
        
        int contador;
        int rojo        = -65536;   //Rojo para Android
        int amarillo    = -256;     //Amarillo para Android
        int verde       = -16711936;//Verde para android
        
        
        //Fecha final (Final del mes en curso)
        Calendar fin = Calendar.getInstance();
        fin.set(Calendar.DAY_OF_MONTH, 20);//Si no fijamos el dia por debajo de 28, al fijar el mes puede causar error en febrero si es que el día era 29 o mas, dando como resultado final un mes mas.
        fin.set(Calendar.HOUR , 23);
        fin.set(Calendar.MINUTE , 59);
        //a = ServicioFecha.fechaFormateada("dd/MM/yyyy", cal.getTime());
        fin.getTime();//Si no hacemos esto, suele fallar ¿¿¿¿¿????
        fin.set(Calendar.DAY_OF_MONTH, fin.getActualMaximum(Calendar.DAY_OF_MONTH));

        //Fecha inicial (Un año antes, inicio del mes)
        Calendar ini = Calendar.getInstance();
        ini.set(Calendar.HOUR , 0);
        ini.set(Calendar.MINUTE , 0);
        ini.set(Calendar.DAY_OF_MONTH, 1);
        ini.add(Calendar.YEAR, -1);
        ini.add(Calendar.MONTH, 1);
        
        
        //Creamos una lista con los todos los meses del último año
        List<DoubleFecha> listaTotal = new ArrayList<>();
        Calendar inicioAux = Calendar.getInstance();
        inicioAux.setTime(ini.getTime());
        Calendar finAux = Calendar.getInstance();
        while(inicioAux.before(fin)){
            listaTotal.add(new DoubleFecha(0, inicioAux.getTime()));
            inicioAux.add(Calendar.MONTH, 1);
        }
        
        EnumIndicador ind = EnumIndicador.getIndicadorEnum((int)IdIndicador);
                
        //Indicadores que vienes de No conformidades
        if(ind.equals(EnumIndicador.INTERNO_ACCIDENTE_AMBIENTAL) ||
           ind.equals(EnumIndicador.INTERNO_ACCIDENTE_RRHH) ||                
           ind.equals(EnumIndicador.INTERNO_DENUNCIA) ||                
           ind.equals(EnumIndicador.CLIENTE_FLETE_EXTRA) ||                
           ind.equals(EnumIndicador.CLIENTE_INTERUPCIONES) ||                
           ind.equals(EnumIndicador.CLIENTE_LOGISTICA) ||                
           ind.equals(EnumIndicador.CLIENTE_NOTIFICACION) ||                
           ind.equals(EnumIndicador.CLIENTE_RECHAZO) ||                
           ind.equals(EnumIndicador.PROVEEDOR_FLETE_EXTRA) ||                
           ind.equals(EnumIndicador.PROVEEDOR_INTERUPCIONES) ||                
           ind.equals(EnumIndicador.PROVEEDOR_LOGISTICA) ||                
           ind.equals(EnumIndicador.PROVEEDOR_NOTIFICACION) ||                
           ind.equals(EnumIndicador.PROVEEDOR_RECHAZO)){    
            //Agregamos a la lista del indicador la sumatoria de fallas de cada mes
            for(DoubleFecha item: listaTotal){

                //Establecemos las fecha de inicio 
                inicioAux.setTime(item.getFecha());
                //y fin del mes
                finAux.setTime(item.getFecha());
                finAux.add(Calendar.MONTH, 1);
                finAux.add(Calendar.DAY_OF_MONTH, -1);//Vamos hasta fin de mes

                List<Accion> listaAccion = Listas.accion(em, new java.sql.Date(inicioAux.getTimeInMillis()), new java.sql.Date(finAux.getTimeInMillis()));
                //Recorremos todas las fallas y cargamos las del mes seleccionado en item
                for(Accion accion: listaAccion){

                    //Salteamos las no conformidades que no corresponden con el indicador
                    if(!considerarAccion(accion, ind)){
                        continue;
                    }
                    //Acumulamos en el item del mes los valores de la no conformidad
                    item.setNumero(item.getNumero() + valor(accion, ind));//Acumulamos sobre   item.numero
                }
            }
            
            //Creamos el json
            contador = 1;
            for(DoubleFecha item: listaTotal){
                JSONObject js = new JSONObject();
                js.put(EnumJson.D_INDICADOR_X.getNombre()    , contador++);
                js.put(EnumJson.D_INDICADOR_Y.getNombre()    , item.getNumero());
                js.put(EnumJson.D_INDICADOR_COLOR.getNombre()  , verde);
                listaXy.add(js);
            }
        }


        //Sugerencias del personal
        if(ind.equals(EnumIndicador.INTERNO_SUGERENCIAS_PERSONAL)){    
            
            //Cargamos una listas con las sugerencias
            List<Sugerencia> listaSugerencias = Listas.sugerencia(em, new java.sql.Date(ini.getTimeInMillis()), new java.sql.Date(fin.getTimeInMillis()));
                    
            //Agregamos a la lista del indicador la sumatoria de sujerencias de cada mes
            for(DoubleFecha item: listaTotal){
                //y fin del mes
                finAux.setTime(item.getFecha());
                finAux.add(Calendar.MONTH, 1);
                finAux.add(Calendar.DAY_OF_MONTH, -1);//Nos vamos hasta fin de mes

                //Recorremos todas las sugerencias y cargamos las del mes seleccionado en item
                for(Sugerencia sugerencia: listaSugerencias){
                    if(!sugerencia.getFecha().before(item.getFecha()) &&
                       !sugerencia.getFecha().after(finAux.getTime())){
                        item.setNumero(item.getNumero() + 1);
                    }
                }
            }
            
            //Creamos el json
            contador = 1;
            for(DoubleFecha item: listaTotal){
                JSONObject js = new JSONObject();
                js.put(EnumJson.D_INDICADOR_X.getNombre()    , contador++);
                js.put(EnumJson.D_INDICADOR_Y.getNombre()    , item.getNumero());
                js.put(EnumJson.D_INDICADOR_COLOR.getNombre()  , verde);
                listaXy.add(js);
            }
        }

        //Fallas de calidad internas
        if(ind.equals(EnumIndicador.INTERNO_RECHAZO)){    
            List<Falla> listaFallas= Listas.falla(em, ini.getTime(), fin.getTime());

            //Agregamos a la lista del indicador la sumatoria de fallas de cada mes
            for(DoubleFecha item: listaTotal){

                //Establecemos la fecha de fin del mes
                finAux.setTime(item.getFecha());
                finAux.add(Calendar.MONTH, 1);
                finAux.add(Calendar.DAY_OF_MONTH, -1);//Nos vamos hasta fin de mes

                //Recorremos todas las fallas y cargamos las del mes seleccionado en item
                for(Falla falla: listaFallas){
                    if(!falla.getFecha().before(item.getFecha()) &&
                       !falla.getFecha().after(finAux.getTime())){
                        item.setNumero(item.getNumero() + falla.getCantidad());
                    }
                }
            }
            //Creamos el json
            contador = 1;
            for(DoubleFecha item: listaTotal){
                JSONObject js = new JSONObject();
                js.put(EnumJson.D_INDICADOR_X.getNombre()    , contador++);
                js.put(EnumJson.D_INDICADOR_Y.getNombre()    , item.getNumero());
                js.put(EnumJson.D_INDICADOR_COLOR.getNombre()  , verde);
                listaXy.add(js);
            }
        }



        //Disponibilidad de equipos
        if(ind.equals(EnumIndicador.INTERNO_FALLAS_DE_EQUIPOS)){    
            
            //Cargamos una listas con las reparaciones
            List<EquipoReparacion> listaReparacion = Listas.equipoReparacion(em, new java.sql.Date(ini.getTimeInMillis()), new java.sql.Date(fin.getTimeInMillis()));

            //Agregamos a la lista del indicador la sumatoria de fallas de cada mes
            for(DoubleFecha item: listaTotal){

                //Establecemos la fecha de fin del mes
                finAux.setTime(item.getFecha());
                finAux.add(Calendar.MONTH, 1);
                finAux.add(Calendar.DAY_OF_MONTH, -1);//Nos vamos hasta fin de mes

                //Recorremos todas las fallas y cargamos las del mes seleccionado en item
                for(EquipoReparacion reparacion: listaReparacion){
                    int horasParada = 0;
                    if(reparacion.getHorasParada() != null){
                        horasParada = reparacion.getHorasParada();
                    }
                    if(!reparacion.getFechaFalla().before(item.getFecha()) &&
                       !reparacion.getFechaFalla().after(finAux.getTime())){
                        item.setNumero(item.getNumero() + horasParada);
                    }
                }
            }
            //Creamos el json
            contador = 1;
            for(DoubleFecha item: listaTotal){
                JSONObject js = new JSONObject();
                js.put(EnumJson.D_INDICADOR_X.getNombre()    , contador++);
                js.put(EnumJson.D_INDICADOR_Y.getNombre()    , item.getNumero());
                js.put(EnumJson.D_INDICADOR_COLOR.getNombre()  , verde);
                listaXy.add(js);
            }
        }

        //Cargamos ambas listas a un JSON
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_INDICADOR_DATOS.getNombre(), listaXy);
        return js;
    }
    
    
    /**
     * Verifica si una accion debe ser sumada al indicador
     * @param accion
     * @return 
     */
    private static boolean considerarAccion(Accion accion, EnumIndicador indicador){
        return accion.getOrigen().charAt(indicador.getId()) == '1';
    }

    /**
     * Según el indicador, devuelve que valor cargamos
     * @return 
     */
    private static int valor(Accion accion, EnumIndicador indicador){
        if(indicador.equals(EnumIndicador.CLIENTE_RECHAZO)){
            if(accion.getCantidadRechazoCliente() == null){
                return 0;
            }
            return accion.getCantidadRechazoCliente();
        }
        if(indicador.equals(EnumIndicador.CLIENTE_FLETE_EXTRA)){
            if(accion.getCostoFleteCliente() == null){
                return 0;
            }
            return accion.getCostoFleteCliente();
        }
        if(indicador.equals(EnumIndicador.PROVEEDOR_RECHAZO)){
            if(accion.getCantidadRechazoProveedor() == null){
                return 0;
            }
            return accion.getCantidadRechazoProveedor();
        }
        if(indicador.equals(EnumIndicador.PROVEEDOR_FLETE_EXTRA)){
            if(accion.getCostoFleteProveedor() == null){
                return 0;
            }
            return accion.getCostoFleteProveedor();
        }
        
        //Si no es una cantidad, devolvemos sumar 1 no conformidad al indicador
        return 1;
    }
    
    
    
    
    
    
    
    
    
    
    /**
     * @param em     
     * @param idEquipo     
     * @param nota     
     * @param usuario     
     */
    public static synchronized void guardarReparacion(EntityManager em, long idEquipo, String nota, Usuario usuario){
        EquipoReparacion reparacion = new EquipoReparacion();
        
        reparacion.setFechaFalla(Calendar.getInstance().getTime());
        reparacion.setIdEquipo(Listas.equipo(em, idEquipo));
        reparacion.setIdUsuario(usuario);
        reparacion.setIdCorreccionTipo(1);
        reparacion.setNotaFalla(nota);
        ServicioAbm.editar(em, reparacion);
    }
    
    
    
    public static synchronized JSONObject medidaCalibre(EntityManager em, double medida, int numeroCalibre, String unidad){
        JSONObject js = new JSONObject();
        js.put(EnumJson.D_MEASURE.getNombre(), medida);
        js.put(EnumJson.D_CALIPER_NUMBER.getNombre(), numeroCalibre);
        js.put(EnumJson.D_UNIT.getNombre(), unidad);
        return js;
    }
    
    
    /**
     * Guardar una pieza hecha.
     * @param em
     * @param cadena 
     * @param monitor 
     */
    public static synchronized void guardarProducción(EntityManager em, String cadena, Monitor monitor){

        LoteProceso loteProceso = monitor.getMonitorProcesoList().get(0).getIdLoteProceso();

        LoteProcesoProducción ultimo = Listas.loteProcesoProducciónÚltimo(em, loteProceso);
        
        //Si el último registro de producción tiene menos de 1 segundo, no hacemos nada. Antirrebotes
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, -1);
        if(ultimo != null && ultimo.getFechaHora().after(cal.getTime())){
            return;
        }

        LoteProcesoProducción prod = new LoteProcesoProducción();
        prod.setIdLoteProceso(loteProceso);
        prod.setCantidad(1);
        prod.setFechaHora(Calendar.getInstance().getTime());
        ServicioAbm.editar(em, prod);
    }
}
