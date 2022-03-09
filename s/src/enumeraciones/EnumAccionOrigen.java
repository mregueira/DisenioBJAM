package enumeraciones;

/**
 *
 * @author leandro
 */
public enum EnumAccionOrigen {


    CLIENTE_RECHAZO                 ( 0, "CLIENTE: rechazo de piezas."                , EnumIndicador.CLIENTE_RECHAZO),         
    CLIENTE_INTERRUPCIONES          ( 1, "CLIENTE: Interrupción o paradas de linea."  , EnumIndicador.CLIENTE_INTERUPCIONES),         
    CLIENTE_LOGISTICA               ( 2, "CLIENTE: no conformidad logística."         , EnumIndicador.CLIENTE_LOGISTICA),         
    CLIENTE_FLETE_EXTRA             ( 3, "CLIENTE: flete extra."                      , EnumIndicador.CLIENTE_FLETE_EXTRA),               
    CLIENTE_NOTIFICACION            ( 4, "CLIENTE: notificación."                     , EnumIndicador.CLIENTE_NOTIFICACION),
    CLIENTE_AUDITORIA               ( 5, "CLIENTE: auditoría."                        , null),

    PROVEEDOR_RECHAZO               ( 10, "PROVEEDOR: rechazo de piezas."              , EnumIndicador.PROVEEDOR_RECHAZO),         
    PROVEEDOR_INTERRUPCIONES        ( 11, "PROVEEDOR: Interrupción o paradas de linea.", EnumIndicador.PROVEEDOR_INTERUPCIONES),         
    PROVEEDOR_LOGISTICA             ( 12, "PROVEEDOR: no conformidad logística."       , EnumIndicador.PROVEEDOR_LOGISTICA),
    PROVEEDOR_FLETE_EXTRA           ( 13, "PROVEEDOR: flete extra."                    , EnumIndicador.PROVEEDOR_FLETE_EXTRA),               
    PROVEEDOR_NOTIFICACION          ( 14, "PROVEEDOR: notificación."                   , EnumIndicador.PROVEEDOR_NOTIFICACION),
    PROVEEDOR_AUDITORIA             ( 15, "PROVEEDOR: auditoría."                      , null),

    INTERNO_RECHAZO                 ( 20, "INTERNO: piezas No conformes."              , EnumIndicador.INTERNO_RECHAZO),
    INTERNO_DENUNCIA                ( 21, "INTERNO: denuncia."                         , EnumIndicador.INTERNO_DENUNCIA),
    INTERNO_ACCIDENTE_RRHH          ( 22, "INTERNO: accidente RR.HH."                  , EnumIndicador.INTERNO_ACCIDENTE_RRHH),
    INTERNO_ACCIDENTE_AMBIENTAL     ( 23, "INTERNO: accidente ambiental"               , EnumIndicador.INTERNO_ACCIDENTE_AMBIENTAL),
    INTERNO_AUDITORIA_INTERNA       ( 24, "INTERNO: auditoría interna."                , null),
    INTERNO_AUDITORIA_CERTIFICACIÓN ( 25, "INTERNO: auditoría de certificación."       , null),
    INTERNO_REVISIÓN_DIRECCIÓN      ( 26, "INTERNO: revisión por la Dirección."        , null);
    

    private final Integer id;
    private final String nombre;
    private final EnumIndicador indicador;

    private EnumAccionOrigen(Integer id, String nombre, EnumIndicador indicador) {
        this.id = id;
        this.nombre = nombre;
        this.indicador = indicador;
    }

    public Integer getId() {
        return this.id;
    }
    public String getNombre() {
        return nombre;
    }

    public EnumIndicador getIndicador() {
        return indicador;
    }

    
    static public EnumAccionOrigen getEnum(int id){
        for(EnumAccionOrigen e : EnumAccionOrigen.values()){
            if(e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return nombre;
    }
}