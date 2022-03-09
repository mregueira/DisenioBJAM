package servicios;

import entidades.Accion;
import entidades.Articulo;
import entidades.Calibracion;
import entidades.DepartamentoResp;
import entidades.DepartamentoRol;
import entidades.Documento;
import entidades.DocumentoEdicion;
import entidades.Empresa;
import entidades.Equipo;
import entidades.EquipoMantenimiento;
import entidades.EquipoReparacion;
import entidades.Falla;
import entidades.FallaTipo;
import entidades.Instrumento;
import entidades.InstrumentoRango;
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
import entidades.PoliticaEdicion;
import entidades.Sugerencia;
import entidades.Usuario;
import entidades.UsuarioPermiso;
import enumeraciones.EnumAnalisis;
import enumeraciones.EnumInstrumentoEstado;
import enumeraciones.EnumPermiso;
import enumeraciones.EnumRegistro;
import gui.GUI;
import java.awt.Color;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.swing.JOptionPane;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author leandro
 */
public class Listas {

    
    
    public static synchronized List<Accion> accion(EntityManager em, java.sql.Date fechaIni, java.sql.Date fechaFin){
        return em.
               createQuery("SELECT a FROM Accion a " +
		"WHERE a.fecha >= :fechaIni AND a.fecha <= :fechaFin " +
		"ORDER By a.fecha", Accion.class).
              setParameter("fechaIni", fechaIni).
              setParameter("fechaFin", fechaFin).
               getResultList();
    }
    
    public static synchronized Articulo articulo(EntityManager em, long idArticulo){
        try{
            return em.
               createQuery("SELECT a FROM Articulo a WHERE a.idArticulo = :idArticulo", Articulo.class).
               setParameter("idArticulo", idArticulo).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    public static synchronized List<Articulo> listaArticulo(EntityManager em, String prefijo){
        return em.
            createQuery(
                "SELECT a FROM Articulo a " + 
                "WHERE a.activo = TRUE  AND SUBSTRING(a.codigo, 1, :largo) = :prefijo " + 
                "ORDER BY a.codigo", Articulo.class).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               setParameter("prefijo", prefijo).
               setParameter("largo", prefijo.trim().length()).
               getResultList();                
    }



    public static synchronized Empresa empresa(EntityManager em){
        try{
            return em.
                   createQuery("SELECT a FROM Empresa a", Empresa.class).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getSingleResult();
        }
        catch (NoResultException ex){
            return null;
        }
        catch (NonUniqueResultException ex){
            return em.
                   createQuery("SELECT a FROM Empresa a", Empresa.class).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getResultList().get(0);
        }
    }

    
    public static synchronized Plan planUltimaEdicion(EntityManager em, Articulo articulo){
        try{
            return em.createQuery("SELECT a FROM Plan a " +
                                  "WHERE a.idArticulo = :articulo and a.codigo IN " +
                                        " (SELECT MAX(b.codigo) FROM Plan b WHERE b.idArticulo = :articulo)", Plan.class).
                   setParameter("articulo", articulo).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getSingleResult();
        }
        catch(NoResultException e){
            JOptionPane.showMessageDialog(null, "Este artículo no tiene planificación asociada",
                    "Mensaje del sistema", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    /**
     * Busca una lista de documentos
     * @param em
     * @return lista de documentos de deben verse en sistema M (Instrucciones operativas, etc)
     */
    public static synchronized List<Documento> listaDocumento(EntityManager em){
        return em.
               createQuery("SELECT a FROM Documento a WHERE a.verEnM = TRUE  AND a.activo = TRUE ORDER BY a.nombre", Documento.class).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getResultList();
    }
    
    
    public static synchronized DocumentoEdicion DocumentoEdicionUltima(EntityManager em, Documento documento){
        try{
            return em.
                   createQuery("SELECT a FROM DocumentoEdicion a " +
                               "WHERE a.idDocumento = :documento AND " +
                               " a.codigo IN " +
                     "(SELECT MAX(b.codigo) FROM DocumentoEdicion b WHERE b.idDocumento = :documento)", DocumentoEdicion.class).
                   setParameter("documento", documento).
                  setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getSingleResult();
        }
        catch (NoResultException ex){
            return null;
        }
    }
    
    public static synchronized Equipo equipo(EntityManager em, long idEquipo){
        try{
            return em.
               createQuery("SELECT a FROM Equipo a WHERE a.idEquipo = :idEquipo", Equipo.class).
               setParameter("idEquipo", idEquipo).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    
    public static synchronized List<EquipoMantenimiento> equipoMantenimientoNivel1PorCodigo(EntityManager em, Equipo equipo){
        return em.
               createQuery("SELECT a FROM EquipoMantenimiento a " +
                           "WHERE a.idNivel = 0 AND a.idEquipo = :equipo " +
                           "ORDER BY a.codigo", EquipoMantenimiento.class).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               setParameter("equipo", equipo).getResultList();
    }
    
    public static synchronized EquipoMantenimiento equipoMantenimiento(EntityManager em, long id){
        try{
            return em.
               createQuery("SELECT a FROM EquipoMantenimiento a " +
                           "WHERE a.idEquipoMantenimiento  = :id", EquipoMantenimiento.class).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               setParameter("id", id).getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    public static synchronized List<EquipoReparacion> equipoReparacion(EntityManager em, java.sql.Date fechaIni, java.sql.Date fechaFin){
        return em.
               createQuery("SELECT a FROM EquipoReparacion a WHERE a.fechaFalla >= :fechaIni AND a.fechaFalla <= :fechaFin ORDER By a.fechaFalla", EquipoReparacion.class).
              setParameter("fechaIni", fechaIni).
              setParameter("fechaFin", fechaFin).
               getResultList();
    }
    
    
    public static synchronized List<Falla> falla(EntityManager em, Date fechaIni, Date fechaFin){
        return em.
               createQuery("SELECT a FROM Falla a " +
		"WHERE a.fecha >= :fechaIni AND a.fecha <= :fechaFin " +
		"ORDER By a.fecha", Falla.class).
              setParameter("fechaIni", new java.sql.Date(fechaIni.getTime())).
              setParameter("fechaFin", new java.sql.Date(fechaFin.getTime())).
               getResultList();
    }
    

    public static synchronized List<FallaTipo> fallaTipo(EntityManager em){
        return em.
               createQuery("SELECT a FROM FallaTipo a ORDER BY a.nombre", FallaTipo.class).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getResultList();                
    }
    
    public static synchronized FallaTipo fallaTipo(EntityManager em, long id){
        try{
            return em.
               createQuery("SELECT a FROM FallaTipo a " +
                           "WHERE a.idFallaTipo  = :id", FallaTipo.class).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               setParameter("id", id).getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    
    public static synchronized Instrumento instrumento(EntityManager em, long codigo){
        try{
            return em.
               createQuery("SELECT a FROM Instrumento a WHERE a.codigo = :codigo", Instrumento.class).
               setParameter("codigo", codigo).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }

    
    /**
     * 
     * @param em
     * @param idLoteProceso
     * @return 
     */
    public static synchronized List<Instrumento> listaInstrumento(EntityManager em, long idLoteProceso){
        List<Instrumento> lista = new ArrayList();
        LoteProceso proceso = loteProceso(em, idLoteProceso);

        if(proceso.getIdInstrumento1() != null){
            lista.add(proceso.getIdInstrumento1());
        }
        if(proceso.getIdInstrumento2() != null){
            lista.add(proceso.getIdInstrumento2());
        }
        if(proceso.getIdInstrumento3() != null){
            lista.add(proceso.getIdInstrumento3());
        }
        if(proceso.getIdInstrumento4() != null){
            lista.add(proceso.getIdInstrumento4());
        }
        if(proceso.getIdInstrumento5() != null){
            lista.add(proceso.getIdInstrumento5());
        }
        if(proceso.getIdInstrumento6() != null){
            lista.add(proceso.getIdInstrumento6());
        }

        return lista;
    }
    
    
    /**
     * 
     * @param em
     * @param instrumento
     * @return true si la estabilidad está vigente.
     */
    public static synchronized boolean instrumentosEstabilidadVigente(EntityManager em, Instrumento instrumento){

         //Buscamos el periodo.
        int periodo = 0;
        for(InstrumentoRango rango : instrumento.getIdInstrumentoTipo().getInstrumentoRangoList()){
            if(rango.getPeriodoEstabilidad() != null &&
               rango.getPeriodoEstabilidad() > periodo) {
                periodo = rango.getPeriodoEstabilidad();
            }
        }
        if(periodo <=  0){
            return true;//No requiere control de estabilidad
        }

        
        
        //Buscamos la ultima fecha de medición de estabilidad
        Date fecha =  em.
               createQuery("SELECT MAX(a.fecha) FROM InstrumentoEstabilidad a " +
                           "WHERE a.idInstrumento = :instrumento", Date.class).
                setParameter("instrumento", instrumento).
                setHint(QueryHints.REFRESH, HintValues.TRUE).
                getSingleResult();

        
        if(fecha == null){
            return false;//Si no encontramos ninguna medición de estabilidad, entonces hay que hacer una. No está vigente
        }
        

        //Validamos la fecha de la última medición con el periodo y la fecha actual
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_YEAR, periodo);
        if(cal.after(Calendar.getInstance())){
            return true;
        }

        return false;
    }
    
    
    
    public static synchronized Calibracion calibracionActual(EntityManager em, Instrumento instrumento){
            List<Calibracion> lista = em.
               createQuery("SELECT a FROM Calibracion a " +
                           "WHERE a.idInstrumento = :instrumento " +
                           "ORDER BY a.fecha", Calibracion.class).
                setParameter("instrumento", instrumento).
                setHint(QueryHints.REFRESH, HintValues.TRUE).
                getResultList();
            
            try{
                return lista.get(lista.size() - 1);
            }
            catch(Exception e){
                return null;
            }
    }    
 
    /**
     *
     * @param em
     * @param loteProceso
     * @return
     */
    public static synchronized List<LoteComponente> loteComponente(EntityManager em,LoteProceso loteProceso){
        return em.
               createQuery("SELECT a FROM LoteComponente a WHERE a.idLoteProceso = :loteProceso ORDER BY a.grupo", LoteComponente.class).
               setParameter("loteProceso", loteProceso).
               getResultList();
    }

    /**
     * 
     * @param em
     * @param componente
     * @return lista de partidas del componente enviado, ordenadas por numero de partida.
     */
    public static synchronized List<LoteComponenteAsignado> loteComponenteAsignado(EntityManager em, LoteComponente componente ){
        return em.
               createQuery("SELECT a FROM LoteComponenteAsignado a " + 
                           "WHERE a.idLoteComponente = :componente " +
                           "ORDER BY a.idLoteProcesoMPC.idLote.codigo", LoteComponenteAsignado.class).
               setParameter("componente", componente).
               getResultList();
    }
    
 
 
     public static synchronized Lote loteReciente(EntityManager em, Articulo articulo){
        try{
            return em.createQuery(
                "SELECT a FROM Lote a " + 
                "WHERE a.idArticulo = :articulo AND a.codigo IN " + 
                    "(SELECT MAX(a.codigo) FROM Lote a WHERE a.idArticulo = :articulo)", Lote.class).
                   setParameter("articulo", articulo).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getSingleResult();
        }
        catch (NoResultException ex){
            return null;
        }
    }

    public static synchronized Lote lote(EntityManager em, Articulo articulo, int codigo){
        try{
            return em.
                   createQuery("SELECT a FROM Lote a WHERE a.idArticulo = :articulo AND a.codigo = :codigo", Lote.class).
                   setParameter("articulo", articulo).
                   setParameter("codigo", codigo).
                   setHint(QueryHints.REFRESH, HintValues.TRUE).
                   getSingleResult();
        }
        catch(NoResultException ex){
            return null;
        }
    }
     
    
    /**
     * 
     * @param em
     * @param idLoteCota
     * @return la entidad cota relacionado con el ID que nos manda el monitor.
     */
    public static synchronized LoteCota loteCota(EntityManager em, long idLoteCota){
        try{
            return em.
               createQuery("SELECT a FROM LoteCota a WHERE a.idLoteCota = :idLoteCota", LoteCota.class).
               setParameter("idLoteCota", (int)idLoteCota).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    
    /**
     * 
     * @param em
     * @param idLoteProceso
     * @return todas las cotas de un proceso
     */
    public static synchronized List<LoteCota> loteCotaList(EntityManager em, long idLoteProceso){
        try{
            LoteProceso proceso =  loteProceso(em, idLoteProceso);
            return em.
                   createQuery("SELECT a FROM LoteCota a WHERE a.idLoteProceso = :proceso " + 
                                "ORDER BY a.codigo", LoteCota.class).
                   setParameter("proceso", proceso).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getResultList();
        }
        catch(NoResultException e){
            GUI.agregarTexto("Cota sin datos", Color.RED);
            return null;
        }
    }
    
 
    /**
     * 
     * @param em
     * @param idLoteProceso
     * @return 
     */
    public static synchronized List<LoteCota> loteCotaNormalList(EntityManager em, long idLoteProceso){
        try{
            LoteProceso proceso =  loteProceso(em, idLoteProceso);
            return em.
                   createQuery("SELECT a FROM LoteCota a WHERE a.idLoteProceso = :proceso  AND " +
                               "(a.idTipoAnalisisP = 1 OR  a.idTipoAnalisisP = 2  OR a.idTipoAnalisisP = 3) " + 
                                "ORDER BY a.codigo", LoteCota.class).
                   setParameter("proceso", proceso).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getResultList();
        }
        catch(NoResultException e){
            GUI.agregarTexto("Cota sin datos", Color.RED);
            return null;
        }
    }
    /**
     * 
     * @param em
     * @param idLoteProceso
     * @return 
     */
    public static synchronized List<LoteCota> loteCotaEspecialList(EntityManager em, long idLoteProceso){
        try{
            LoteProceso proceso =  loteProceso(em, idLoteProceso);
            return em.
                   createQuery("SELECT a FROM LoteCota a WHERE a.idLoteProceso = :proceso  AND " +
                               "(a.idTipoAnalisisC = 1 OR  a.idTipoAnalisisC = 2  OR a.idTipoAnalisisC = 3) " + 
                                "ORDER BY a.codigo", LoteCota.class).
                   setParameter("proceso", proceso).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getResultList();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    
    
    /**
     * 
     * @param em
     * @param idLoteCota
     * @return 
     */
    public static synchronized List<LoteMedida> loteMedidaList(EntityManager em, long idLoteCota){
        try{
            LoteCota cota =  loteCota(em, idLoteCota);
            return em.
                   createQuery("SELECT a FROM LoteMedida a WHERE a.idLoteCota = :cota ORDER BY a.fecha", LoteMedida.class).
                   setParameter("cota", cota).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getResultList();
        }
        catch(NoResultException e){
            return null;
        }
    }

    
    /**
     * 
     * @param em
     * @param idLoteCota
     * @return la medida minima para una cota
     */
    public static synchronized Double loteMedidaMinimo(EntityManager em, long idLoteCota){
        try{
            LoteCota cota =  loteCota(em, idLoteCota);
            return em.
                   createQuery("SELECT MIN(a.valor) FROM LoteMedida a WHERE a IN " +
                               "(SELECT b FROM LoteMedida b WHERE b.idLoteCota = :cota)", Double.class).
                   setParameter("cota", cota).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }


    /**
     * 
     * @param em
     * @param idLoteCota
     * @return la medida maxima para una cota
     */
    public static synchronized Double loteMedidaMaximo(EntityManager em, long idLoteCota){
        try{
            LoteCota cota =  loteCota(em, idLoteCota);
            return em.
                   createQuery("SELECT MAX(a.valor) FROM LoteMedida a WHERE a IN " +
                               "(SELECT b FROM LoteMedida b WHERE b.idLoteCota = :cota)", Double.class).
                   setParameter("cota", cota).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    
    
    /**
     * Busca una lista de procesos de un dado lote
     * @param em
     * @param lote de los procesos que se quieren buscar
     * @return lista de procesos del lote
     */
    public static synchronized List<LoteProceso> loteProceso(EntityManager em, Lote lote){
        return em.
               createQuery("SELECT a FROM LoteProceso a WHERE a.idLote = :lote ORDER BY a.codigo", LoteProceso.class).
               setParameter("lote", lote).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getResultList();
    }
    

    /**
     * 
     * @param em
     * @param loteProceso
     * @return true si hay medicicones de calidad pendientes
     */
    public static synchronized int loteProcesoPendienteCalidad(EntityManager em, LoteProceso loteProceso){
        
        List<LoteCota> cotas = em.
               createQuery("SELECT a FROM LoteCota a WHERE a.idLoteProceso = :loteProceso", LoteCota.class).
               setParameter("loteProceso", loteProceso).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getResultList();
        
        for(LoteCota cota : cotas){

            //Saltamos cotas sin frecuencia de producción asignada.
            if(cota.getIdFrecuenciaP() == null){
                continue;
            }
            //Salteamos cotas sin registro 
            if(cota.getIdRegistroP() == null || EnumRegistro.NINGUNO.getId().equals(cota.getIdRegistroP())){
                continue;
            }
            
            if(cota.getIdTipoAnalisisP().equals(EnumAnalisis.NINGUNO.getId()) ||
               cota.getIdTipoAnalisisP().equals(EnumAnalisis.GARANTIZA_PROCESO.getId()) ||                  
               cota.getIdTipoAnalisisP().equals(EnumAnalisis.POR_CERTIFICADO.getId()) ||                  
               cota.getIdTipoAnalisisP().equals(EnumAnalisis.PROVEEDOR.getId()) ||                  
               cota.getIdTipoAnalisisP().equals(EnumAnalisis.SISTEMA_ANTIERROR.getId())){
                continue;
            }
            
            //Calculamos el momento en que debería estar la última medida
            Calendar inicio = Calendar.getInstance();
            Calendar fin = Calendar.getInstance();
            
            inicio.add(Calendar.SECOND, -cota.getIdFrecuenciaP().getSegundos() -cota.getIdFrecuenciaP().getTolerancia());
            fin.add(   Calendar.SECOND, -cota.getIdFrecuenciaP().getSegundos() +cota.getIdFrecuenciaP().getTolerancia());
            
            Date fechaUltimaMedida = loteMedidaFechaUltima(em, cota);

            //Si la última medida se tomó antes del inicio, entonces ya está vencida.
            if(fechaUltimaMedida ==  null || fechaUltimaMedida.before(inicio.getTime())){
                return 2;
            }
            //Si se tomó justo dentro del plazo, estamos en el momento de tomar otra
            if(fechaUltimaMedida.before(fin.getTime())){
                return 1;
            }
        }

        //Si llegamos hasta aca, todas las medidas están en plazo.
        return 0;
    }
    
    /**
     * 
     * @param em
     * @param equipo
     * @return true si hay medicicones de calidad pendientes
     */
    public static synchronized int mantenimientoPendiente(EntityManager em, Equipo equipo){
        
        
        List<EquipoMantenimiento> tareas = equipoMantenimientoNivel1PorCodigo(em, equipo);
        
        for(EquipoMantenimiento tarea : tareas){

            //Calculamos el momento en que debería estar la última medida
            Calendar hoy = Calendar.getInstance();
            Calendar fechaTarea = Calendar.getInstance();
            
            fechaTarea.setTime(tarea.getFechaControl());
            fechaTarea.add(Calendar.DAY_OF_YEAR, tarea.getPeriodoEnDias());
            
            //Si estamos despues de la fecha de la tarea + el plazo, vencida
            if(hoy.after(fechaTarea)){
                return 2;
            }
        }

        //Si llegamos hasta aca, todas las medidas están en plazo.
        return 0;
    }

    /**
     * 
     * @param em
     * @param loteProceso
     * @return true si hay medicicones de calidad pendientes
     */
    public static synchronized int herramentalPendiente(EntityManager em, LoteProceso loteProceso){
        
        List<LoteProcesoParametro> parametros = loteProcesoParametroLista(em, loteProceso.getIdLoteProceso());
        
        for(LoteProcesoParametro parametro : parametros){

            try{
                LoteProcesoParametroMedida medida= loteProcesoParametroMedidaUltima(em, parametro);
                //Calculamos si ya pasaron las piezas para cambiar el inserto

                //Ya se pasó
                if(loteProceso.getCantidadProducida() >= medida.getCantidadPiezas() + parametro.getPeriodo()){
                        return 2;
                }
                //Está dentro del 10%
                if(loteProceso.getCantidadProducida() >= medida.getCantidadPiezas() + parametro.getPeriodo() * 0.9){
                        return 1;
                }
            }
            catch(NullPointerException e){
                return 1;
            }
        }

        //Si llegamos hasta aca, todos los parametros están en plazo.
        return 0;
    }

    
    
    /**
     * 
     * @param em
     * @return true si hay medicicones de calidad pendientes
     */
    public static synchronized int instrumentoPendiente(EntityManager em){
        List<Instrumento> lista = em.
                   createQuery("SELECT a FROM Instrumento a " +
                               "WHERE a.idInstrumentoEstado = :estado ", Instrumento.class).
                   setParameter("estado", EnumInstrumentoEstado.EN_SERVICIO.getId()).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getResultList();

        //Buscamos un instrumento en servicio pero no calibrado
        for(Instrumento instrumento : lista){
            if(instrumento.getCalibracionList().isEmpty()) {
                GUI.agregarTexto("Estado instrumental 1 : " + instrumento.getIdInstrumentoTipo().getNombre()+ " " + instrumento.getCodigo(), Color.red);
                return 2;
            }
            if(instrumento.getCalibracionList().get(instrumento.getCalibracionList().size() - 1).getFechaVence().before(Calendar.getInstance().getTime())) {
                
                GUI.agregarTexto("Estado instrumental 2 : " + instrumento.getIdInstrumentoTipo().getNombre()+ " " +  instrumento.getCodigo() + 
                                instrumento.getCalibracionList().get(instrumento.getCalibracionList().size() - 1).getFechaVence().toString(), Color.red);
                return 2;
            }
            if(!instrumento.getCalibracionList().get(instrumento.getCalibracionList().size() - 1).getEsApto()){
                GUI.agregarTexto("Estado instrumental 3 : " + instrumento.getIdInstrumentoTipo().getNombre()+ " " +  instrumento.getCodigo(), Color.red);
                return 2;
            }
        }
        return 0;
    }







    
    
    
    

    /**
     * 
     * @param em
     * @param idLoteProceso
     * @return 
     */
    public static synchronized List<LoteProcesoParametro> loteProcesoParametroLista(EntityManager em, long idLoteProceso){
        try{
            LoteProceso proceso =  loteProceso(em, idLoteProceso);
            return em.
                   createQuery("SELECT a FROM LoteProcesoParametro a WHERE a.idLoteProceso = :proceso ORDER BY a.nombre", LoteProcesoParametro.class).
                   setParameter("proceso", proceso).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getResultList();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    
    /**
     * 
     * @param em
     * @param idLoteProcesoParametro 
     * @return 
     */
    public static synchronized List<LoteProcesoParametroMedida> loteProcesoParametroMedida(EntityManager em, long idLoteProcesoParametro){
        try{
            LoteProcesoParametro parametro =  loteProcesoParametro(em, idLoteProcesoParametro);
            return em.
                   createQuery("SELECT a FROM LoteProcesoParametroMedida a WHERE a.idLoteProcesoParametro = :parametro ORDER BY a.fecha", LoteProcesoParametroMedida.class).
                   setParameter("parametro", parametro).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getResultList();
        }
        catch(NoResultException e){
            return null;
        }
    }
    

    /**
     * 
     * @param em
     * @param lLoteProcesoParametro
     * @return 
     */
    public static synchronized LoteProcesoParametroMedida loteProcesoParametroMedidaUltima(EntityManager em, LoteProcesoParametro lLoteProcesoParametro){

        try{
            return em.
                   createQuery("SELECT a FROM LoteProcesoParametroMedida a " +
                               "WHERE a.idLoteProcesoParametro = :parametro AND " +
                               " a.fecha IN " +
                     "(SELECT MAX(b.fecha) FROM LoteProcesoParametroMedida b WHERE b.idLoteProcesoParametro = a.idLoteProcesoParametro)", LoteProcesoParametroMedida.class).
                   setParameter("parametro", lLoteProcesoParametro).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getSingleResult();
        }
        catch(Exception e){
            List<LoteProcesoParametroMedida> a = em.
                   createQuery("SELECT a FROM LoteProcesoParametroMedida a " +
                               "WHERE a.idLoteProcesoParametro = :parametro ORDER BY a.fecha", LoteProcesoParametroMedida.class).
                   setParameter("parametro", lLoteProcesoParametro).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getResultList();

            if(a == null || a.isEmpty()){
                return null;
            }
            else{
                return a.get(a.size() - 1);
            }
        }
    }
    
    public static synchronized Integer loteCodigoMax(EntityManager em, Articulo articulo){
        Integer codigo;
        try{
            codigo =  em.
                   createQuery("SELECT MAX(a.codigo) FROM Lote a " +
                               "WHERE a.idArticulo = :articulo", Integer.class).
                   setParameter("articulo", articulo).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getSingleResult();
            if(codigo == null){
                return 0;
            }
            return codigo;
        }
        catch (NoResultException ex){
            return 0;
        }
    }
    

    
    /**
     * 
     * @param em
     * @param cota
     * @return 
     */
    public static synchronized Date loteMedidaFechaUltima(EntityManager em, LoteCota cota){
        try{
            return em.
                   createQuery("SELECT MAX(a.fecha) FROM LoteMedida a WHERE a.idLoteCota = :cota", Date.class).
                   setParameter("cota", cota).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }

    
    public static synchronized LoteProceso loteProceso(EntityManager em, long idLoteProceso){
        try{
            return em.
               createQuery("SELECT a FROM LoteProceso a WHERE a.idLoteProceso = :idLoteProceso", LoteProceso.class).
               setParameter("idLoteProceso", idLoteProceso).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }


    /**
     * Busca el proceso de un dado lote y un dado código
     * @param em
     * @param lote del cual se quiere el proceso
     * @param codigo del proceso a buscar
     * @return el proceso según el codigo
     */
    public static synchronized LoteProceso loteProceso(EntityManager em, Lote lote, Integer codigo){
        try{
            return em.
                   createQuery("SELECT a FROM LoteProceso a " +
                               "WHERE a.idLote = :lote and a.codigo = :codigo", LoteProceso.class).
                   setParameter("lote", lote).
                   setParameter("codigo", codigo).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getSingleResult();
        }
        catch (NoResultException ex){
            return new LoteProceso();
        }
    }
    

    public static synchronized LoteProcesoParametro loteProcesoParametro(EntityManager em, long idLoteProcesoParametro){
        try{
            return em.
               createQuery("SELECT a FROM LoteProcesoParametro a WHERE a.idLoteProcesoParametro = :idLoteProcesoParametro", LoteProcesoParametro.class).
               setParameter("idLoteProcesoParametro", idLoteProcesoParametro).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    public static synchronized LoteProcesoProducción loteProcesoProducciónÚltimo(EntityManager em, LoteProceso loteProceso){
        try{
            return em.createQuery("SELECT a FROM LoteProcesoProducción a " +
                                  "WHERE a.idLoteProceso = :loteProceso AND " +
                                   "     a.fechaHora IN (SELECT MAX(b.fechaHora) FROM LoteProcesoProducción b)", LoteProcesoProducción.class).
               setParameter("loteProceso", loteProceso).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
        catch(NonUniqueResultException e){
            return null;
        }
    }


    


    public static synchronized List<Monitor> ListaMonitor(EntityManager em, long idLoteProceso, boolean  asignado){
        if(asignado){
            return em.
                createQuery("SELECT a FROM Monitor a WHERE a.idMonitor IN" +
                            "(SELECT b.idMonitor.idMonitor FROM MonitorProceso b WHERE b.idLoteProceso.idLoteProceso = :idLoteProceso)", Monitor.class).
               setParameter("idLoteProceso", idLoteProceso).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getResultList();
        }
        else{
            return em.
                createQuery("SELECT a FROM Monitor a WHERE a.idMonitor NOT IN" +
                            "(SELECT b.idMonitor.idMonitor FROM MonitorProceso b WHERE b.idLoteProceso.idLoteProceso = :idLoteProceso)", Monitor.class).
               setParameter("idLoteProceso", idLoteProceso).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getResultList();
        }
    }
    
    public static synchronized Monitor monitor(EntityManager em, long idMonitor){
        try{
            return em.
               createQuery("SELECT a FROM Monitor a WHERE a.idMonitor = :idMonitor", Monitor.class).
               setParameter("idMonitor", idMonitor).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    


    
    public static synchronized MonitorProceso MonitorProceso(EntityManager em, Monitor monitor, long idLoteProceso){
        try{
            return em.
               createQuery("SELECT a FROM MonitorProceso a WHERE a.idMonitor = :monitor AND a.idLoteProceso.idLoteProceso = :idLoteProceso", MonitorProceso.class).
               setParameter("monitor", monitor).
               setParameter("idLoteProceso", idLoteProceso).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    public static synchronized Monitor monitor(EntityManager em, InetAddress inetAddress){
        if(inetAddress == null || inetAddress.getHostAddress() == null){
            return null;
        }
        
        try{
            return em.
                   createQuery("SELECT a FROM Monitor a " +
                               "WHERE a.ip = :ip", Monitor.class).
                   setParameter("ip", inetAddress.getHostAddress()).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getSingleResult();
            }
        catch(NoResultException | java.lang.NullPointerException e){
            GUI.agregarTexto("Monitor sin IP", Color.red);
            return null;
        }
    }
    
    /**
     * 
     * @param em
     * @param monitor
     * @return 
     */
    public static synchronized MonitorProceso monitorProcesoActivo(EntityManager em, Monitor monitor){
        try{
            return em.
                   createQuery("SELECT a FROM MonitorProceso a " +
                               "WHERE a.idMonitor = :monitor AND a.activo = true", MonitorProceso.class).
                   setParameter("monitor", monitor).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }

    
    /**
     * 
     * @param em
     * @param nombreLogin
     * @param clave
     * @return 
     */
    public static synchronized Usuario verificarUsuario(EntityManager em, String nombreLogin, String clave){
        Usuario usuario;
        try{
            usuario = em.
                       createQuery("SELECT a FROM Usuario a " +
                                   "WHERE a.nombreLogin = :nombreLogin", Usuario.class).
                       setParameter("nombreLogin", nombreLogin).
                       setHint(QueryHints.REFRESH, HintValues.TRUE). 
                       getSingleResult();
        }
        catch(Exception e){
            return null;
        }
            
        if(usuario != null && MessageDigest.isEqual(ServicioCifrado.cifrar(clave.toCharArray()), usuario.getClave())){
            return usuario;
        }  
        return null;
    }

    
    public static synchronized List<LoteProceso> listaLoteProceso(EntityManager em, long idLote){
        return em.
               createQuery("SELECT a FROM LoteProceso a " +
                           "WHERE a.idLote.idLote = :idLote " +
                           "ORDER BY a.codigo", LoteProceso.class).
               setParameter("idLote", idLote).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getResultList();
    }
    
    
    public static synchronized List<Plan> plan(EntityManager em, Articulo articulo){
        return em.createQuery("SELECT p FROM Plan p WHERE p.idArticulo = :articulo", Plan.class).
               setParameter("articulo", articulo).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getResultList();
    }
    
    /**
     * 
     * @param em
     * @param monitor
     * @return 
     */
    public static synchronized List<MonitorProceso> procesosAsignados(EntityManager em, Monitor monitor){
        if(monitor == null){
            return null;
        }
        return em.
               createQuery("SELECT a FROM MonitorProceso a " +
                           "WHERE a.idMonitor = :monitor AND a.activo = TRUE  ORDER BY a.idLoteProceso.idLote.idArticulo.codigo", MonitorProceso.class).
               setParameter("monitor", monitor).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getResultList();
    }

    
    public static synchronized List<PlanCota> planCota(EntityManager em, PlanProceso planProceso){
        return em.
               createQuery("SELECT a FROM PlanCota a " +
                           "WHERE a.idPlanProceso = :planProceso " +
                           "ORDER BY a.codigo", PlanCota.class).
               setParameter("planProceso", planProceso).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getResultList();
    }
    public static synchronized List<PlanComponente> planComponente(EntityManager em, PlanProceso planProceso){
        return em.
               createQuery("SELECT a FROM PlanComponente a " +
                           "WHERE a.idPlanProceso = :planProceso", PlanComponente.class).
               setParameter("planProceso", planProceso).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getResultList();
    }

    public static synchronized List<PlanProceso> planProceso(EntityManager em, Plan plan){
        return em.
               createQuery("SELECT a FROM PlanProceso a WHERE a.idPlan = :plan ORDER BY a.codigo", PlanProceso.class).
               setParameter("plan", plan).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getResultList();
    }
    
    public static synchronized PlanProceso planProceso(EntityManager em, Plan plan, Integer codigo){
        try{
            return em.
                    createQuery("SELECT a FROM PlanProceso a " + 
                                "WHERE a.idPlan = :plan and a.codigo = :codigo", PlanProceso.class).
                    setParameter("plan", plan).
                    setParameter("codigo", codigo).
                    setHint(QueryHints.REFRESH, HintValues.TRUE). 
                    getSingleResult();
        }
        catch(NoResultException e){
            JOptionPane.showMessageDialog(null, "Este artículo no tiene planificación asociada",
                    "Mensaje del sistema", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    
    public static synchronized List<PlanProcesoParametro> PlanProcesoParametro(EntityManager em, PlanProceso planProceso){
        return em.
               createQuery("SELECT a FROM PlanProcesoParametro a " +
                           "WHERE a.idPlanProceso = :planProceso", PlanProcesoParametro.class).
               setParameter("planProceso", planProceso).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getResultList();
    }
    
    public static synchronized boolean permiso(EntityManager em, Usuario usuario, EnumPermiso permiso){
        try{
               em.
               createQuery("SELECT a FROM UsuarioPermiso a " +
                           "WHERE a.idUsuario = :usuario and a.idPermiso = :permiso",
                            UsuarioPermiso.class).
               setParameter("usuario", usuario).
               setParameter("permiso", permiso.getId()).
               setHint(QueryHints.REFRESH, HintValues.TRUE).                       
               getSingleResult();
        }
        catch(NoResultException e){
            return false;
        }
        catch(NonUniqueResultException e){
            return true;
        }
        return true;
    }
    
    public static synchronized PoliticaEdicion politica(EntityManager em){
        try{
            return em.createQuery("SELECT a FROM PoliticaEdicion a " +
                                  "WHERE a.fecha IN (SELECT MAX(b.fecha) FROM PoliticaEdicion b)", PoliticaEdicion.class).
                   setHint(QueryHints.REFRESH, HintValues.TRUE). 
                   getSingleResult();

            
        }
        catch(Exception e){
            return null;
        }
    }
    
    public static synchronized List<Sugerencia> sugerencia(EntityManager em, java.sql.Date fechaIni, java.sql.Date fechaFin){
        return em.
               createQuery("SELECT a FROM Sugerencia a WHERE a.fecha >= :fechaIni AND a.fecha <= :fechaFin ORDER By a.fecha", Sugerencia.class).
              setParameter("fechaIni", fechaIni).
              setParameter("fechaFin", fechaFin).
               getResultList();
    }
    
    /**
     * 
     * @param em
     * @param idUsuario
     * @return la instancia con datos del idUsuario enviado.
     */
    public static synchronized Usuario usuario(EntityManager em, long idUsuario){
        try{
            return em.
               createQuery("SELECT a FROM Usuario a WHERE a.idUsuario = :idUsuario", Usuario.class).
               setParameter("idUsuario", idUsuario).
               setHint(QueryHints.REFRESH, HintValues.TRUE). 
               getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    /**
     * 
     * @param em
     * @param rol
     * @return solo responsabilidades exclusivas de un rol
     */
    public static synchronized List<DepartamentoResp> DepartamentoResp(EntityManager em, DepartamentoRol rol){
        return em.
               createQuery("SELECT a FROM DepartamentoResp a WHERE a.idDepartamentoRol = :rol  ORDER BY a.nombre", DepartamentoResp.class).
               setHint(QueryHints.REFRESH, HintValues.TRUE).
               setParameter("rol", rol).
               getResultList();
    }
    
    /**
     * 
     * @param em
     * @return  solo responsabilidades comunes a todos los roles.
     */
    public static synchronized List<DepartamentoResp> DepartamentoRespGenerales(EntityManager em){
        return em.
               createQuery("SELECT a FROM DepartamentoResp a WHERE a.idDepartamentoRol IS NULL ORDER BY a.nombre", DepartamentoResp.class).
               setHint(QueryHints.REFRESH, HintValues.TRUE).
               getResultList();
    }
    
 
    
}