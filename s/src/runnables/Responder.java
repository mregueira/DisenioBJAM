package runnables;

/**
 *
 * @author leandro
 */
import entidades.Monitor;
import enumeraciones.EnumJson;
import java.io.IOException;
import servicios.ServicioProtocolo;
import java.net.*;
import servicios.Listas;
import gui.GUI;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.json.simple.JSONObject;
import servicios.ServicioEM;
import servicios.ServicioEncode;

public class Responder implements Runnable {

    InetAddress address;
    String cadenaRecibida;
    Monitor monitor;
    DatagramSocket socket;
    


    /**
     * Constructor de la clase
     * @param cadenaRecibida
     * @param address 
     */
    public Responder(String cadenaRecibida, InetAddress address, DatagramSocket socket) {
        this.cadenaRecibida = cadenaRecibida;
        this.address = address;
        this.socket = socket;
        EntityManager em;    

        //Esta clase se llama en multiThread. Es necesario que siempre cree su propio em
//        em = ServicioEM.getInstancia().getEMF().createEntityManager();
        em = null;

//        this.monitor = Listas.monitor(em, address);
        
        //Cerramos el em.
//        em.close();
    }

    
    @Override
    public void run() {
        
        EntityManager em;    
        //Esta clase se llama en multiThread. Es necesario que siempre cree su propio em
//        em = ServicioEM.getInstancia().getEMF().createEntityManager();
        em = null;

        //Obtenemos los datos del encabezado del protocolo
        Long secuencia = ServicioProtocolo.getSecuencia(cadenaRecibida);
        String comando = ServicioProtocolo.getComando(cadenaRecibida);
        String accion = ServicioProtocolo.getAccion(cadenaRecibida);
        JSONObject data = null;
        
        
                
        //Mostramos el requets en la pantalla, evitando mostrar constraseñas.
        String dato = null;
//        if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_LOGIN.getNombre())){
//            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
//            dato = "Login de " + (String) js.get(EnumJson.D_USER.getNombre());
//        }
//        else{
//            dato = "" + ServicioProtocolo.getDato(cadenaRecibida);
//
//        }

        if(comando.equals(EnumJson.C_REQUEST.getNombre())){
            try{
                 GUI.agregarTexto(
                 "    Requerimiento del monitor " + monitor.getNombre().trim() + " " + 
                 "    Accion: "    + ServicioProtocolo.getAccion(cadenaRecibida) + 
                 "    Dato :"      + dato, Color.BLACK);
            }
            catch(NullPointerException e){
                 GUI.agregarTexto(
                 "    Error de NULL", Color.RED);
            }
            
        }
        
        //Login
        if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_LOGIN.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            String user = (String) js.get(EnumJson.D_USER.getNombre());
            String pass = (String) js.get(EnumJson.D_PASS.getNombre());
            data = ServicioEncode.login(em, user, pass, monitor);
        }
        //unLogin
        if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_UNLOGIN.getNombre())){
            data = null;
            ServicioEncode.unlogin(em, monitor);
        }

        //Fecha-hora
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_DATE_TIME.getNombre())){
            data = ServicioEncode.dateTime();
        }
        //Lista de procesos asignados al monitor
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_PROCESSES_LIST.getNombre())){
            data = ServicioEncode.listaProcesos(em, monitor);
        }
        //Guardamos cantidad producida
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_SAVE_PRODUCEDQUANTITY.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_SAVE_PRODUCEDQUANTITY_PROCESS_ID.getNombre());
            int cantidad = Math.toIntExact((Long)js.get(EnumJson.D_PRODUCEDQUANTITY.getNombre()));
            ServicioEncode.guardarCantidadProducida(em, idLoteProceso, cantidad);
        }
        //Guardamos el tiempo de ciclo
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_SAVE_CYCLETIME.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_SAVE_CYCLETIME_PROCESS_ID.getNombre());
            int tiempo = Math.toIntExact((Long)js.get(EnumJson.D_CYCLETIME.getNombre()));
            ServicioEncode.guardarTiempoDeCiclo(em, idLoteProceso, tiempo);
        }
        //Guardamos la nota del proceso
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_SAVE_PROCESS_NOTE.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_SAVE_PROCESS_NOTE_PROCESS_ID.getNombre());
            String nota = (String)js.get(EnumJson.D_PROCESS_NOTE_TO_SAVE.getNombre());
            ServicioEncode.guardarNota(em, idLoteProceso, nota);
        }
        //Guardamos el estado del proceso elegido por el usuario
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_SAVE_PROCESS_ESTATE.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_SAVE_PROCESS_PROCESS_ID.getNombre());
            long idEstadoProceso = (long) js.get(EnumJson.D_PROCESS_STATE_ID.getNombre());
            ServicioEncode.guardarEstadoProceso(em, monitor, idLoteProceso, idEstadoProceso);
        }
        //Lista de herramentales asignados al proceso elegido
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_TOOLS_ALL_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_TOOL_PROCESS_ID.getNombre());
            data = ServicioEncode.listaTools(em, idLoteProceso, monitor);
        }
        //Lista de herramentales asignados al proceso elegido
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_TOOLS_PEND_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_TOOL_PROCESS_ID.getNombre());
            data = ServicioEncode.listaTools(em, idLoteProceso, monitor);
        }
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_TOOL_HISTORIAL.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idHerramental = (long) js.get(EnumJson.D_TOOL_HISTORIAL_TOOL_ID.getNombre());
            data = ServicioEncode.toolHistorial(em, idHerramental);
        }
        
        //Guardamos el pedido de reparación
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_SAVE_REPAIR_ORDER.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idEquipo = (long) js.get(EnumJson.D_REPAIR_ORDER_EQUIPO_ID.getNombre());
            String nota = (String)js.get(EnumJson.D_REPAIR_ORDER_TEXT.getNombre());
            ServicioEncode.guardarReparacion(em, idEquipo, nota, monitor.getIdUsuario());
        }
        
        //Lista de cotas asignadas al proceso elegido
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_FEATURES_TODAS_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_FEATURE_PROCESS_ID.getNombre());
            data = ServicioEncode.listaCotas(em, idLoteProceso, monitor, false);
        }
        //Lista de cotas asignadas al proceso elegido
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_FEATURES_PEND_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_FEATURE_PROCESS_ID.getNombre());
            data = ServicioEncode.listaCotas(em, idLoteProceso, monitor, true);
        }
        
        //Liberamos el lote
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_LIBERAR.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_LIBERAR_PROCESS_ID.getNombre());
            data = ServicioEncode.liberar(em, idLoteProceso, monitor);
        }
        
        
        //Guardamos medida
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_SAVE_MEASURE.getNombre())){
            ServicioEncode.guardarMedida(em, cadenaRecibida, monitor);
        }
        //Guardamos herramientas
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_SAVE_TOOL.getNombre())){
            ServicioEncode.guardarTools(em, cadenaRecibida, monitor.getIdUsuario());
        }
        
        //Mantenimiento: guardar tareas de mantenimiento de nivel 1
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_SAVE_TASK.getNombre())){
            ServicioEncode.guardarEquipoMantenimiento(em, cadenaRecibida, monitor.getIdUsuario());
        }
        
        //Mantenimiento: todas las tareas
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_TASKS_ALL_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_TASK_PROCESS_ID.getNombre());
            data = ServicioEncode.listaTareasMantenimiento(em, monitor, idLoteProceso);
        }
        //Mantenimiento: control efectuados
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_TASKS_HIST_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_TASK_PROCESS_ID.getNombre());
            data = ServicioEncode.listaTareasMantenimiento(em, monitor, idLoteProceso);
        }
        //Mantenimiento: todas pendientes solamente
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_TASKS_PEND_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_TASK_PROCESS_ID.getNombre());
            data = ServicioEncode.listaTareasMantenimientoPend(em, monitor, idLoteProceso);
        }
        
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_FAILURE_TYPES_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            data = ServicioEncode.listaTiposDeFallas(em);
        }
        
        //Guarda una nueva falla de calidad
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_SAVE_FAILURE.getNombre())){
            ServicioEncode.guardarFalla(em, cadenaRecibida, monitor.getIdUsuario());
        }


        //Lista de instrumentos de medición
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_INSTRUMENTOS_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_INSTRUMENTO_PROCESS_ID.getNombre());
            data = ServicioEncode.listaInstrumento(em, idLoteProceso);
        }

        //Guarda una asociación instrumento- proceso
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_SAVE_INSTRUMENTO_ASOCIADO.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_SAVE_INSTRUMENTO_ASOCIADO_IDLOTEPROCESO.getNombre());
            long idInstrumento = (long) js.get(EnumJson.D_SAVE_INSTRUMENTO_ASOCIADO_IDINSTRUMENTO.getNombre());
            ServicioEncode.guardarInstrumentoAcociado(em, idLoteProceso, idInstrumento);
        }

        //Guarda una asociación instrumento-proceso
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_SAVE_INSTRUMENTO_ESTABILIDAD.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idInstrumento  = (long) js.get(EnumJson.D_INSTRUMENTOESTABILIDAD_ID_INSTRUMENTO.getNombre());
            double patron       = stringToDouble(js, EnumJson.D_INSTRUMENTOESTABILIDAD_PATRON.getNombre());
            double medida1      = stringToDouble(js, EnumJson.D_INSTRUMENTOESTABILIDAD_MEDIDA1.getNombre());
            double medida2      = stringToDouble(js, EnumJson.D_INSTRUMENTOESTABILIDAD_MEDIDA2.getNombre());
            double medida3      = stringToDouble(js, EnumJson.D_INSTRUMENTOESTABILIDAD_MEDIDA3.getNombre());


            ServicioEncode.guardarInstrumentoEstabilidad(em, idInstrumento, monitor.getIdUsuario(), patron, medida1, medida2, medida3);
        }
        
        
        //Instrumento de medición
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_GAGE_VERIFY.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idInstrumento = (long) js.get(EnumJson.D_GAGE_ID.getNombre());
            data = ServicioEncode.instrumentoVerificar(em, idInstrumento);
        }

        //Plan de contingencia
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_DATOS_GENERALES.getNombre())){
            data = ServicioEncode.datosGenerales(em);
        }

        //Lista de articulos
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_ARTICULOS_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            String prefijo = (String) js.get(EnumJson.D_ARTICULO_PREFIJO.getNombre());

            data = ServicioEncode.listaArticulos(em, prefijo);
            GUI.agregarTexto("    Largo del dato: " + data.toString().length() + " (Maximo: 65500)" , Color.BLUE);
            
        }
        
        
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_LOTE_PROCESO_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLote = (long) js.get(EnumJson.D_LOTE_PROCESO_LOTE_ID.getNombre());
            data = ServicioEncode.listaPlanProcesos(em, idLote);
        }
        
        //Lista de monitores
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_MONITORES_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_MONITORES_PROCESO_ID.getNombre());
            boolean asignados = (boolean) js.get(EnumJson.D_MONITORES_ASIGANDOS.getNombre());
            data = ServicioEncode.listaMonitoresAsigandos(em, idLoteProceso, asignados);
        }

        //Asiganción de procesos a monitores
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_MONITOR_ASIGNAR.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_MONITOR_ASIGNAR_LOTE_PROCESO_ID.getNombre());
            long idMonitor = (long) js.get(EnumJson.D_MONITOR_ASIGNAR_MONITOR_ID.getNombre());
            boolean asignar = (boolean) js.get(EnumJson.D_MONITOR_ASIGNAR_SI.getNombre());
            ServicioEncode.monitorAsignar(em, idLoteProceso, idMonitor, asignar);
            data = null;
        }

        //Crea lote
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_LOTE_CREAR.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idArticulo = (long) js.get(EnumJson.D_LOTE_CREAR_ARTICULO_ID.getNombre());
            long cantidad = (long) js.get(EnumJson.D_LOTE_CREAR_CANTIDAD.getNombre());
            data = ServicioEncode.loteCrear(em, idArticulo, cantidad);
        }
        
        //Lista de instrucciones operativas
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_DOCUMENTO_LIST.getNombre())){
            data = ServicioEncode.listaDocumentos(em);
        }
        
        //Datos para gráficos estadísiticos de mediciones
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_CHART_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteCota = (long) js.get(EnumJson.D_CHART_FEATURE_ID.getNombre());
            data = ServicioEncode.listaCharts(em, idLoteCota);
        }

        //Datos para graficos de indicadores de gestión
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_INDICADOR_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idIndicador = (long) js.get(EnumJson.D_INDICADOR_ID.getNombre());
            data = ServicioEncode.listaIndicador(em, idIndicador);
        }

        //Datos de componentes
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_COMPONENTES_LIST.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);
            long idLoteProceso = (long) js.get(EnumJson.D_COMPONENTE_PROCESS_ID.getNombre());
            data = ServicioEncode.listaComponentes(em, idLoteProceso);
        }

        //Responsabilidad y autoridad del operador
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_RESPONSABILIDAD_LIST.getNombre())){
            data = ServicioEncode.listaResponsabilidad(em, monitor.getIdUsuario());
        }
        
        
        //Recibimos medida de los lectores de calibres ITBA
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_GUARDAR_DATOS_CALIBRES.getNombre())){
            JSONObject js = (JSONObject)ServicioProtocolo.getDato(cadenaRecibida);

            int caliperNumer = ((Long) js.get(EnumJson.D_CALIPER_NUMBER.getNombre())).intValue();
            Double medida = (Double) js.get(EnumJson.D_MEASURE.getNombre());
            String unidad = (String) js.get(EnumJson.D_UNIT.getNombre());
            
            GUI.agregarTexto("*** Mensaje del ITBA " + caliperNumer + " --  " + medida + " ---- " + unidad, Color.CYAN, true);
            
            
            //Mandamos la medida a la tablet
            try{
                InetAddress addressTablet = InetAddress.getByName("192.168.142.1");
                //Creamos el datagrama
                byte[] buffer = ServicioProtocolo.crearDatagrama(1, EnumJson.C_REQUEST.getNombre(), EnumJson.A_GUARDAR_DATOS_CALIBRES.getNombre(), 
                    ServicioEncode.medidaCalibre(null, medida, 0, "mm"));

                //y lo enviamos
                try {
                    socket.send(new DatagramPacket(buffer, buffer.length, addressTablet, 4444));
                    GUI.agregarTexto("    Se envio datos de una medida a la tablet", Color.blue, true);
                }
                catch (SocketException ex) {
                    GUI.agregarTexto("    ERROR de socket", Color.RED);
                }
                catch (IOException ex) {
                    //GUI.agregarTexto("    Largo del dato: " + data.toString().length() + " (Maximo: 65500)" , Color.RED);
                    GUI.agregarTexto("    ERROR de IO", Color.RED);
                }
            }
            catch (UnknownHostException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        

        //Recibimos pieza hecha desde la placa del torno (ITBA)
        else if(comando.equals(EnumJson.C_REQUEST.getNombre()) && accion.equals(EnumJson.A_CUENTA_PIEZA.getNombre())){
            GUI.agregarTexto("*** Mensaje del ITBA " + "    Producción", Color.CYAN, true);
            ServicioEncode.guardarProducción(em, cadenaRecibida, monitor);
        }
        
        
        
        
        if(data != null){
            //Creamos el datagrama
            byte[] buffer = ServicioProtocolo.crearDatagrama(secuencia, EnumJson.C_RESPONSE.getNombre(), accion,  data);

            //y lo enviamos
            try {
                socket.send(new DatagramPacket(buffer, buffer.length, address, 4444));
                GUI.agregarTexto("    Se respondió el request", Color.blue, true);
                //GUI.agregarTexto("    Dato" + data.toJSONString(), Color.blue, true); Esto suele causar errores de pantalla. Quizás con textos muy largos.
                
            GUI.agregarTexto("Puerto: " + socket.getPort(), Color.blue, true);
            //SocketAddress a = socket.getRemoteSocketAddress();
            SocketAddress a = socket.getRemoteSocketAddress();
            //String b = a.toString();
            //GUI.agregarTexto("IP: " + b, Color.blue, true);
                
                
            }
            catch (SocketException ex) {
                GUI.agregarTexto("    ERROR de socket", Color.RED);
            }
            catch (IOException ex) {
                GUI.agregarTexto("    Largo del dato: " + data.toString().length() + " (Maximo: 65500)" , Color.RED);
                GUI.agregarTexto("    ERROR de IO", Color.RED);
            }
        }
        
        
        //Es importante cerrar el em para que todo vaya bien.
//        em.close();
    }
    
    
    private double stringToDouble(JSONObject js, String claveJs){
        Object o = js.get(claveJs);
        double ret = 0d;
        if(o instanceof Double){
            ret = (Double) js.get(claveJs);
        }
        if(o instanceof Long){
            Long l = (Long)js.get(claveJs);
            ret = l.doubleValue();
        }
        if(o instanceof Integer){
            Integer l = (Integer)js.get(claveJs);
            ret = l.doubleValue();
        }
        return ret;
    }
}