package enumeraciones;

import java.util.List;

/**
 * Define el protocolo
 * @author leandro
 */
public enum EnumJson {

//Bloque principal
    VERSION                             ("version"              , String.class),
    SECUENCE                            ("secuence"             , long.class),
    COMMAND                             ("command"              , String.class),
    ACTION                              ("action"               , String.class),
    CHECKSUM                            ("checksum"             , Long.class),
    DATA                                ("data"                 , String.class),
   
//Comandos
        C_REQUEST                       ("request"              , String.class),
        C_RESPONSE                      ("response"             , String.class),
    
//Acciones y Datos. 
//Para cada acción, el primer grupo de comandos corresponde al request. El segundo corresponde al response
        A_LOGIN                         ("login"                , String.class),
            //Request
            D_USER                      ("user"                 , String.class),
            D_PASS                      ("pass"                 , String.class),
            //Response
            D_LOGIN_OK                  ("loginOk"              , boolean.class),
            D_LOGIN_FULLNAME            ("loginFullName"        , String.class),
            D_LOGIN_PERMISO_LOTE_CREAR  ("loginPermisoCrearLote", boolean.class),
            D_PORT0                     ("port0GageName"        ,String.class),
            D_PORT1                     ("port1GageName"        ,String.class),
            D_PORT2                     ("port2GageName"        ,String.class),
            D_PORT3                     ("port3GageName"        ,String.class),
            D_PORT4                     ("port4GageName"        ,String.class),
            D_PORT5                     ("port5GageName"        ,String.class),
            D_PORT6                     ("port6GageName"        ,String.class),
            D_PORT7                     ("port7GageName"        ,String.class),
            
//Para cada acción, el primer grupo de comandos corresponde al request. El segundo corresponde al response
        A_UNLOGIN                      ("unlogin"                , String.class),
            //Request
            //Response
            
            

        A_DATE_TIME                     ("dateTime"             , String.class),
            //Request
            //Response
            D_DATE                      ("date"                 , String.class),
            D_TIME                      ("time"                 , String.class),

                
        //Listado de procesos
        A_PROCESSES_LIST                ("processesList"        , String.class),
            //Request
            //Response
            D_PROCESSES                 ("processes"            , List.class),
                D_PROCESS_ID            ("processId"            , long.class),
                D_PROCESS_ID_ARTICULO   ("idArticulo"           , long.class),//Id del articulo. Lo usamos para poder cargar su foto.
                D_PROCESS_CODE          ("processCode"          , String.class),
                D_PROCESS_NAME          ("processName"          , String.class),
                D_MACHINE               ("machine"              , String.class),
                D_MACHINE_ID            ("machineId"            , long.class),
                D_ARTICLE               ("article"              , String.class),
                D_BATCH                 ("batch"                , String.class),
                D_QUANTITY_TO_PRODUCE   ("quantityToProduce"    , long.class),
                D_QUANTITY_PRODUCED     ("quantityProduced"     , long.class),
                D_PROCESS_CYCLETIME     ("processCycleTime"     , long.class),
                D_PROCESS_STATE         ("processState"         , long.class),
                D_IMAGE_ID              ("imageId"              , long.class),
                D_IMAGE_TIPO            ("imageTipo"            , long.class),
                D_NOTA                  ("procesoNota"          , String.class),
                D_LIBERADO              ("procesoLiberado"      , boolean.class),
                D_PENDIENTE_CALIDAD         ("pendienteCalidad"     , long.class), //0:OK  1:amarillo  2 :rojo
                D_PENDIENTE_MANTENIMIENTO   ("pendienteMantenimiento", long.class),
                D_PENDIENTE_HERRAMENTAL     ("pendienteHerramental"  , long.class),
                D_PENDIENTE_INSTRUMENTOS    ("pendienteInstrumentos" , long.class),
                D_PENDIENTE_ESTABILIDAD     ("pendienteInstrumentosEstabilidad" , boolean.class),
                
            
        //Guardar el proceso elegido
        A_SAVE_PROCESS                  ("saveProcess"          , String.class),
            //Request
            D_SELECTED_PROCESS_ID       ("selectedProcessId"    , long.class),
            //Response

        //Guardar el estado del proceso
        A_SAVE_PROCESS_ESTATE           ("saveProcessState"     , String.class),
            //Request
            D_SAVE_PROCESS_PROCESS_ID   ("idLoteProceso"       , long.class),
            D_PROCESS_STATE_ID          ("processStateId"       , long.class),//En producción: 0/Detención normal:1/ Falla de equipo:2/Falta de material: 3/Falta herramental:4/Inestabilidad:5
            //Response
        

            
            
        //Listado de características
        A_FEATURES_TODAS_LIST    ("featuresNormalTodasList"  , String.class),
        A_FEATURES_PEND_LIST     ("featuresNormalPendList"   , String.class),
            //Request
            D_FEATURE_PROCESS_ID        ("featureProcessId"     , long.class),//Proceso del cual se quieren las características
            //Response
            D_FEATURES                  ("features"             , List.class),
                D_FEATURE_ID            ("featureId"            , long.class),
                D_FEATURE_CODE          ("featureCode"          , String.class),
                D_FEATURE_NAME          ("featureName"          , String.class),
                D_TOLERANCE             ("tolerance"            , String.class),
                D_TOLERANCE_MAX         ("toleranceMax"         , double.class),
                D_TOLERANCE_MIN         ("toleranceMin"         , double.class),
                D_PERIOD                ("period"               , String.class),
                D_GAGE                  ("gage"                 , String.class),
                D_GAGE_PORT             ("gagePort"             , long.class),   //Puerto donde está conetado el calibre que usaremos para medir esta característica.
                D_DECIMALS              ("decimales"            , long.class),
                D_CRITICA               ("critica"              , long.class),


        A_LIBERAR                       ("liberar"              , String.class),
            //Request
            D_LIBERAR_PROCESS_ID        ("liberarProcessId"     , long.class),//Proceso que se quiere liberar
            D_LIBERAR_USER_FULLNAME     ("liberarUserFullName"  , String.class),
            //Response
            D_LIBERAR_MENSAJE           ("liberarMensaje"       , String.class),
                
                
                
        //Listado de herramentales
        A_TOOLS_ALL_LIST            ("toolsAllList"         , String.class),
        A_TOOLS_PEND_LIST           ("toolsPendList"        , String.class),
        //Request
        D_TOOL_PROCESS_ID           ("toolProcessId"        , long.class),//Proceso del cual se quieren las harramientas
        //Response
        D_TOOLS                     ("tools"                , List.class),
        D_TOOL_ID                   ("toolId"               , long.class),
        D_TOOL_NAME                 ("toolName"             , String.class),
        D_TOOL_CORRECTOR            ("toolCorrector"        , String.class),
        D_TOOL_PERIOD               ("toolPeriod"           , long.class),
        D_TOOL_ID_ACTION            ("toolIdAction"         , long.class),
        //Datos del último cambio realizado.
        D_TOOL_DATE                 ("fechaPrevia"          , String.class),
        D_TOOL_TIME                 ("horaPrevia"           , String.class),
        D_TOOL_CANT_PIEZAS          ("cantidadPiezasPrevia" , long.class),
        D_TOOL_PREV_ID_ACTION       ("accionPrevia"         , long.class),
        D_TOOL_USER_FULLNAME        ("toolPrevUserFullName" , String.class),
                
        
        //Listado del historial de cambios de un herramental particular
        A_TOOL_HISTORIAL               ("toolsHistList"        , String.class),
        //Request
        D_TOOL_HISTORIAL_TOOL_ID       ("toolId"               , long.class),//Harramental del cual se quieren su historial
        //Response
        D_TOOLS_HISTORIAL               ("tools"                , List.class),
        D_TOOL_HISTORIAL_ACTION        ("toolIdAction"         , long.class),
        D_TOOL_HISTORIAL_DATE          ("fecha"                , String.class),
        D_TOOL_HISTORIAL_TIME          ("hora"                 , String.class),
        D_TOOL_HISTORIAL_CANT_PIEZAS   ("cantidadPiezas"       , long.class),
        D_TOOL_HISTORIAL_USER_FULLNAME ("userFullName"         , String.class),

                
                
                
        //Listado de tareas de mantenimiento
        A_TASKS_ALL_LIST                ("tasksAllList"         , String.class), //Tareas de mantenimiento
        A_TASKS_PEND_LIST               ("tasksPendList"        , String.class),
        A_TASKS_HIST_LIST               ("tasksHistList"        , String.class),       
            //Request
            D_TASK_PROCESS_ID           ("taskProcessId"        , long.class),//Proceso del cual se quieren el mantenimiento
            //Response
            D_TASKS                     ("tasks"                , List.class),
                D_TASK_ID               ("taskId"               , long.class),
                D_TASK_NAME             ("taskName"             , String.class),
                D_TASK_PERIOD           ("taskPeriod"           , String.class),
                D_TASK_PERIOD_NAME      ("taskPeriodName"       , String.class),
                D_TASK_STATE            ("taskState"            , long.class),
        
                
    //Guardar pedido de reparacion
    A_SAVE_REPAIR_ORDER                  ("saveRepairOrder"          , String.class),
        //Request
        D_REPAIR_ORDER_EQUIPO_ID         ("RepairOrderEquipoId"      , long.class),//Id del torno que queremos reparar
        D_REPAIR_ORDER_TEXT              ("RepairOrderText"          , String.class),
        //Response
        
        
        
                
        
        //Listado de tipos de fallas de calidad
        A_FAILURE_TYPES_LIST              ("failureTypesList"       , String.class),
        //Request
        //Response
        D_FAILURE_TYPES                 ("failureTypes"        , List.class),
        D_FAILURE_TYPE_NAME             ("failureTypesId"       , Long.class),
        D_FAILURE_TYPE_ID               ("failureTypesName"     , String.class),
                
    
        //Guardar medida
        A_SAVE_MEASURE                  ("saveMeasure"          , String.class),
            //Request
            D_MEASURE_FEATURE_ID        ("measureFeatureId"     , long.class),//Id de la característica a la cual le corresponde la medición.
            D_MEASURE_DATE              ("measureDate"          , String.class),
            D_MEASURE_TIME              ("measureTime"          , String.class),
            D_MEASURE_VALUE             ("measureValue"         , double.class),
            D_MEASURE_DECIMALS          ("measureDecimals"      , long.class),
            D_MEASURE_USER_FULLNAME     ("measureUserFullName"  , String.class),
            D_MEASURE_NORMAL            ("measureNormal"        , boolean.class),//Normal: las de siempre        Especial: solo si se pide.
            //Response
        

            
            
        //Guardar herramienta cambiada
        A_SAVE_TOOL                     ("saveTool"             , String.class),
            //Request
            D_TOOL_TO_SAVE_ID           ("toolToSaveId"         , long.class),//Id del parámetro al cual se le cargará la nueva medida/cambio de herramienta
            D_TOOL_TO_SAVE_USER         ("toolToSaveUser"       , String.class),
            D_TOOL_TO_SAVE_DATE         ("toolToSaveDate"       , String.class),
            D_TOOL_TO_SAVE_TIME         ("toolToSaveTime"       , String.class),
            D_TOOL_TO_SAVE_CANT_PIEZAS  ("toolToSavePiezas"     , long.class),
            D_TOOL_TO_SAVE_ID_ACTION    ("toolToSaveIdAccion"   , long.class),
        
            
            
        //Guardar tarea de mantenimeinto ejecutada     
        A_SAVE_TASK                     ("saveTask"             , String.class),
            //Request
            D_TASK_TO_SAVE_ID           ("taskToSaveId"         , long.class),
            D_TASK_TO_SAVE_USER         ("taskToSaveUser"       , String.class),
            D_TASK_TO_SAVE_DATE         ("taskToSaveDate"       , String.class),
            D_TASK_TO_SAVE_TIME         ("taskToSaveTime"       , String.class),
            //Response

            
        //Guardar nueva falla de calidad
        A_SAVE_FAILURE                  ("saveFailure"              , String.class),
        //Request
        D_FAILURE_TO_SAVE_IDTYPE        ("failureToSaveIdType"      , long.class),//Alimentación del material:0/Error del operador:1/Falta de entrenamiento:2/Falla de planificación:3/Inestabilidad:4
        D_FAILURE_TO_SAVE_QUANTITY      ("failureToSaveQuantity"    , long.class),
        D_FAILURE_TO_SAVE_PROCESS_ID    ("failureToSaveProcessId"  , long.class),
        D_FAILURE_TO_SAVE_DATE          ("failureToSaveDate"       , String.class),
        D_FAILURE_TO_SAVE_TIME          ("failureToSaveTime"        , String.class),
        D_FAILURE_TO_SAVE_USER          ("failureToSaveUser"        , String.class),
        //Response
            


        //Guardar cantidad producida
        A_SAVE_PRODUCEDQUANTITY         ("saveProducedQuantity"  , String.class),
            //Request
            D_SAVE_PRODUCEDQUANTITY_PROCESS_ID   ("idLoteProceso"       , long.class),
            D_PRODUCEDQUANTITY                   ("ProducedQuantity"      , long.class),//Cantidad producida
            //Response

        //Guardar tiempo e ciclo
        A_SAVE_CYCLETIME                ("saveCycleTime"        , String.class),
            //Request
            D_SAVE_CYCLETIME_PROCESS_ID   ("idLoteProceso"       , long.class),
            D_CYCLETIME                 ("CycleTime"            , long.class),
            //Response
            
    //Guardar la nota del proceso
    A_SAVE_PROCESS_NOTE             ("saveProcessNote"      , String.class),
        //Request
        D_SAVE_PROCESS_NOTE_PROCESS_ID   ("idLoteProceso"       , long.class),
        D_PROCESS_NOTE_TO_SAVE      ("ProcessNoteToSave"    , String.class),
        //Response
            
            
        //Actualizar lista de procesos
        A_CHECK_UPDATES                 ("checkUpdates"         , String.class),
            //Request
            //Response
            D_UPDATES                   ("update"               , String.class),//String con 4 bits: listaDeProcesos/listaDeCaracterísticas/listaDeHerramentales/ListaDeTareasMantenimeinto

            
        //INdicadores de tareas a realizar
        A_UPDATE_INDICATORS            ("updateIndicators"         , String.class),
            //Request
            //Response
            D_QUALITY_CONTROL_INDICATOR ("qualityControlIndicator"       , String.class),//true/false
            D_TOOLING_INDICATOR         ("toolingIndicator"              , String.class),
            D_MAINTENANCE_INDICATOR     ("maintenanceIndicator"          , String.class),


    //Listado de instrumentos de medición
    A_INSTRUMENTOS_LIST                 ("instrumentosList"        , String.class),
        //Request
        D_INSTRUMENTO_PROCESS_ID        ("instrumentoProcessId"     , long.class),//Proceso del cual se quieren los instrumentos
        //Response
        D_INSTRUMENTOS                  ("instrumentos"            , List.class),
            D_INSTRUMENTO_ID            ("instrumentoId"           , long.class),
            D_INSTRUMENTO_NOMBRE        ("instrumentoNombre"       , String.class),
            D_INSTRUMENTO_DECIMALES     ("instrumentoDecimales"    , long.class),
            D_INSTRUMENTO_FALTA_ESTABILIDAD ("instrumentoFaltaEstabilidad"          , boolean.class),

    //Guardar la medición de estabilidad
    A_SAVE_INSTRUMENTO_ESTABILIDAD                  ("saveEstabilidad"          , String.class),
        //Request
        D_INSTRUMENTOESTABILIDAD_ID_INSTRUMENTO     ("estabilidadIdIntrumento"  , long.class),
        D_INSTRUMENTOESTABILIDAD_ID_USUARIO         ("estabilidadIdUsaurio"     , long.class),
        D_INSTRUMENTOESTABILIDAD_PATRON             ("estabilidadPatron"        , double.class),
        D_INSTRUMENTOESTABILIDAD_MEDIDA1            ("estabilidadMedida1"       , double.class),
        D_INSTRUMENTOESTABILIDAD_MEDIDA2            ("estabilidadMedida2"       , double.class),
        D_INSTRUMENTOESTABILIDAD_MEDIDA3            ("estabilidadMedida3"       , double.class),
        //Response


        //Guardar la medición de estabilidad
    A_SAVE_INSTRUMENTO_ASOCIADO                     ("saveInstrumentoAsociado"              , String.class),
        //Request
        D_SAVE_INSTRUMENTO_ASOCIADO_IDINSTRUMENTO  ("saveInstrumentoAsociadoIdInstrumento" , long.class),
        D_SAVE_INSTRUMENTO_ASOCIADO_IDLOTEPROCESO   ("saveInstrumentoAsociadoIdLoteProceso" , double.class),
        //Response
        
        
            
    //Consulta por instrumento de medición. Para saber el estado de la calibración y otros datos.
    A_GAGE_VERIFY               ("gageVerify"                   , String.class),
        //Request
        D_GAGE_ID                   ("gageId"                       , long.class),//Numero de instrumento a verificar
        //Response
        D_GAGE_ESTATE               ("gageEstate"                    , String.class),//Datos del instrumento(Tipo, ubicación, estado de calibración, etc)
            

        
        
        
        
    //Consulta por plan de contingencia
    A_DATOS_GENERALES          ("datosGenerales"                       , String.class),
    //Request
    //Response
    D_DATOS_GENERALES_POLITICA        ("datosGeneralesPolitica"                , String.class),
    D_DATOS_GENERALES_CONTINGENCIA    ("datosGeneralesContingencia"                , String.class),

        

        

        
    //Lista de articulos
    A_ARTICULOS_LIST             ("ArticulosLista"                   , String.class),
        //Request
        D_ARTICULO_PREFIJO              ("articuloPrefijo"              , String.class),
        //Response
        D_ARTICULOS                     ("articulos"                    , List.class),
            D_ARTICULO_ID               ("articuloId"                    , Long.class),
            D_ARTICULO_CODIGO           ("articuloCodigo"                , String.class),
            D_ARTICULO_NOMBRE           ("articuloNombre"                , String.class),
            D_ARTICULO_LOTE_ULTIMO_ID   ("articuloLoteUltimoId"          , Long.class),
            D_ARTICULO_LOTE_ULTIMO      ("articuloLoteUltimo"            , String.class),
            D_ARTICULO_LOTE_ULTIMO_FECHA ("articuloLoteUltimoFecha"                , String.class),
        
            
            
    //Lista de procesos planificados
    A_LOTE_PROCESO_LIST              ("loteProcesoLista"           , String.class),
    //Request
    D_LOTE_PROCESO_LOTE_ID          ("loteProcesoLoteId"                    , Long.class),
    //Response
    D_LOTE_PROCESOS                 ("loteProcesos"                    , List.class),
    D_LOTE_PROCESO_ID               ("loteProcesoId"                    , Long.class),
    D_LOTE_PROCESO_CODIGO           ("loteProcesoCodigo"                , String.class),
    D_LOTE_PROCESO_NOMBRE           ("loteProcesoNombre"                , String.class),

    
    //Lista de monitores asignados o no  a un proceso
    A_MONITORES_LIST            ("monitoresList"              , String.class),
    //Request
    D_MONITORES_PROCESO_ID      ("procesoId"                       , String.class),
    D_MONITORES_ASIGANDOS       ("asigandos"                       , boolean.class),
    //Response
    D_MONITORES                 ("monitores"                       , List.class),
    D_MONITOR_ID                 ("monitorId"                       , Long.class),
    D_MONITOR_NOMBRE             ("monitorNombre"                   , String.class),


    //Asignar loteProceso monitor
    A_MONITOR_ASIGNAR                       ("monitorAsignar"                       , String.class),
    //Request
    D_MONITOR_ASIGNAR_LOTE_PROCESO_ID       ("idLoteProceso"                        , Long.class),
    D_MONITOR_ASIGNAR_MONITOR_ID            ("idMonitor"                            , Long.class),
    D_MONITOR_ASIGNAR_SI                    ("si"                                   , boolean.class),
    //Response
    
    //Crear partida
    A_LOTE_CREAR                            ("LoteCrear"                    , String.class),
    //Request
    D_LOTE_CREAR_ARTICULO_ID                ("LoteCrearArticuloId"          , Long.class),
    D_LOTE_CREAR_CANTIDAD                   ("LoteCrearCantidad"            , Long.class),
    //Response
    D_LOTE_CREAR_MENSAJE                    ("LoteCrearMensaje"             , boolean.class),
    
    
    //Lista de documentos - instrucciones operativas
    A_DOCUMENTO_LIST                    ("documentoLista"                 , String.class),
        //Request
        //Response
        D_DOCUMENTOS                    ("documentos"                    , List.class),
            D_DOCUMENTO_ID              ("DocumentoId"                    , Long.class),
            D_DOCUMENTO_NOMBRE          ("documentoNombre"                , String.class),
            D_DOCUMENTO_TEMA            ("documentoTema"                , String.class),
            D_DOCUMENTO_IMAGEN_ID       ("documentoImagenId"              , long.class),
            D_DOCUMENTO_IMAGEN_TIPO     ("documentoImagenTipo"            , long.class),
    

    //Lista de mediciones para grafico XYChart en estadísticas
    A_CHART_LIST                    ("lineChartLista"               , String.class),
        //Request
            D_CHART_FEATURE_ID      ("lineChartFeatureId"           , long.class),//Id de la característica a la cual le corresponden los datos.

        //Response
        D_LINECHARTLISTA            ("lineChartLista"               , List.class),
            D_LINECHART_X           ("lineChartX"                   , Long.class),
            D_LINECHART_Y           ("lineChartY"                   , Long.class),
            D_LINECHART_COLOR       ("lineChartColor"               , int.class),
        D_BARCHARTLISTA             ("barChartLista"                , List.class),
            D_BARCHART_X            ("barChartX"                    , Long.class),
            D_BARCHART_Y            ("barChartY"                    , Long.class),
            D_BARCHART_X_LABEL      ("barChartXLabel"               , String.class),
            D_BARCHART_COLOR        ("barChartColor"                , int.class),
        D_CHART_N                   ("chartN"                       , double.class),
        D_CHART_MEDIA_IDEAL         ("chartMediaIdeal"              , double.class),
        D_CHART_MEDIA_REAL          ("chartMediaReal"               , double.class),
        D_CHART_CP                  ("chartCp"                      , double.class),
        D_CHART_CPK                 ("chartCpk"                     , double.class),
        D_CHART_DECIMALES           ("chartDecimales"               , int.class),

        
    //Lista de datos para gráficos de indicadores
    A_INDICADOR_LIST                ("indicadorLista"               , String.class),
    //Request
        D_INDICADOR_ID              ("indicadorId"                  , long.class),//Id del indicador
    //Response
        D_INDICADOR_DATOS           ("indicadorLista"               , List.class),//Lista de datos en x, y, colores de cada punto
            D_INDICADOR_X           ("indicadorX"                   , Long.class),
            D_INDICADOR_Y           ("indicadorY"                   , Long.class),
            D_INDICADOR_COLOR       ("indicadorColor"               , int.class),
            
    //Lista de datos para gráficos de indicadores
    A_COMPONENTES_LIST              ("componentesLista"    , String.class),
        //Request
        D_COMPONENTE_PROCESS_ID     ("idLoteProceso"       , long.class),//Id del proceso que se quieren ver los componentes
        //Response
        D_COMPONENTE_DATOS           ("datos"              , List.class),//Lista de datos en x, y, colores de cada punto
            D_COMPONENTE_ARTICULO_ID ("articuloId"         , long.class),
            D_COMPONENTE_CODIGO      ("codigo"             , String.class),
            D_COMPONENTE_NOMBRE      ("nombre"             , String.class),
            D_COMPONENTE_CONSUMO     ("consumo"            , long.class),
            D_COMPONENTE_LOTE        ("lote"               , long.class),
            

    //Lista de datos para gráficos de indicadores
    A_RESPONSABILIDAD_LIST           ("responsabilidadLista"    , String.class),
        //Request
        D_RESPONSABILIDAD_USUARIO_ID  ("idUsuario"              , long.class),
        //Response
        D_RESPONSABILIDAD_DATOS       ("datos"                  , List.class),
            D_RESPONSABILIDAD_NOMBRE  ("nombre"                 , String.class),
            D_RESPONSABILIDAD_R       ("esResponsabilidad"      , Boolean.class),
            D_RESPONSABILIDAD_A       ("esAutoridad"            , Boolean.class),
        
            

    //Datos desde módulos lectores de calibres
    A_GUARDAR_DATOS_CALIBRES    ("guardarDatoCalibre"    , String.class),
        //Request
            D_CALIPER_NUMBER    ("caliperNumber"            , long.class),
            D_MEASURE           ("measure"                    , Double.class),
            D_UNIT              ("unit"                    , String.class),
        //Response
            //Vacio

    //Datos desde módulos lectores de calibres
    A_CUENTA_PIEZA           ("contarPieza"    , String.class),
        //Request
            //Vacio
        //Response
            //Vacio

    //Datos desde módulos lectores de calibres
    A_SET_SALIDA           ("setSalidaDigital"    , String.class),
        //Request
            D_SALIDA_NUMERO ("outputNumber"      , long.class),
            D_SALIDA_ESTADO ("outputState"       , String.class);
        //Response
            //Vacio

    
            
    
    private final String nombre;
    private final Class clase;

    private EnumJson(String nombre, Class clase) {
        this.nombre = nombre;
        this.clase = clase;
    }

    public String getNombre() {
        return nombre;
    }

    public Class getClase() {
        return clase;
    }
    
    
}