package enumeraciones;

/**
 *
 * @author leandro
 */
public enum EnumIndicador {

    
    CLIENTE_RECHAZO                 (0, "CLIENTE: PPM rechazos.", 
                                    "Valor expresado en PPM máximo por mes.\n" +
                                    "\n" +
                                    "Total de piezas rechazadas\n" +
                                    "----------------------------------------  * 1.000.000\n" +
                                    "Total de piezas entregadas"),         
    CLIENTE_INTERUPCIONES           (1, "CLIENTE: Interrupción/paradas de linea.", "Cantidad máxima anual de interrupciones de la linea productiva del cliente"),               
    CLIENTE_LOGISTICA               (2, "CLIENTE: PPM de entregas fuera de fecha.", 
                                    "Valor expresado en PPM máximo por mes.\n" +
                                    "\n" +
                                    "Total de piezas entregadas fuera de fecha\n" +
                                    "------------------------------------------------------------  * 1.000.000\n" +
                                    "Total de piezas entregadas"),         
    CLIENTE_FLETE_EXTRA             (3, "CLIENTE: costo de flete extra.", "Costo máximo anual, en $AR, de TODOS los fletes extras."),
    CLIENTE_NOTIFICACION            (4, "CLIENTE: cantidad de notificaciones y/o reclamos.", "Cantidad máxima anual de notificaciones, reclamos del cliente"),


    PROVEEDOR_RECHAZO               (10, "PROVEEDOR: PPM rechazos.", 
                                    "Valor expresado en PPM máximo por mes.\n" +
                                    "\n" +
                                    "Total de piezas rechazadas\n" +
                                    "----------------------------------------  * 1.000.000\n" +
                                    "Total de piezas entregadas"),         
    PROVEEDOR_INTERUPCIONES         (11, "PROVEEDOR: interrupción o paradas de linea.", "Cantidad máxima anual de interrupciones de nuestra linea productiva"),               
    PROVEEDOR_LOGISTICA             (12, "PROVEEDOR: PPM de entregas fuera de fecha.", 
                                    "Valor expresado en PPM máximo por mes.\n" +
                                    "\n" +
                                    "Total de piezas entregadas fuera de fecha\n" +
                                    "------------------------------------------------------------  * 1.000.000\n" +
                                    "Total de piezas entregadas"),         
    PROVEEDOR_FLETE_EXTRA           (13, "PROVEEDOR: costo de flete extra.", "Costo máximo anual, en $AR, de todos los fletes extras pagados por Campiutti."),
    PROVEEDOR_NOTIFICACION          (14, "PROVEEDOR: cantidad de notificaciones y/o reclamos.", "Cantidad máxima anual de notificaciones, reclamos al proveedor"),
    
    
   
    INTERNO_RECHAZO                 (20, "INTERNO: fallas."                            , "Cantidad máxima anual de fallas internas en producción"),
    INTERNO_DENUNCIA                (21, "INTERNO: denuncias."                          , "Cantidad máxima anual de denuncias a la empresa"),
    INTERNO_ACCIDENTE_RRHH          (22, "INTERNO: accidentes RR.HH."                   , "Cantidad anual máxima de accidentes de personas"),
    INTERNO_ACCIDENTE_AMBIENTAL     (23, "INTERNO: accidentes ambiental"                , "Cantidad anual máxima de accidentes ambientales"),
    INTERNO_SUGERENCIAS_PERSONAL    (24, "INTERNO: Sugerencias del personal"            , "Cantidad anual mínima de sugerencias recibidas"),
    INTERNO_FALLAS_DE_EQUIPOS       (25, "INTERNO: Mantenimiento - Fallas de equipos"   , "Cantidad anual máxima de horas de fallas"),
    INTERNO_OEE                     (26, "INTERNO: OEE - Eficacia general"              , "Valor mínimo del OEE"),


    COSTO_NO_CALIDAD                (100, "COSTO DE NO CALIDAD"                          , "Valor máximo anual en $AR\n" +
                                    "\n" +
                                    "Costo fletes extras a clientes + "  + "\n" +
                                    "Costo piezas rechazadas por cliente + " + "\n" +
                                    "Costo piezas rechazadas internanmente (Fallas internas)");
    
    
    
    private final Integer id;
    private final String nombre;
    private final String nota;
    

    private EnumIndicador(Integer id, String nombre, String nota) {
        this.id = id;
        this.nombre = nombre;
        this.nota = nota;
    }

    public Integer getId() {
        return this.id;
    }
    public String getNombre() {
        return nombre;
    }

    public String getNota() {
        return nota;
    }
    
    
    static public EnumIndicador getIndicadorEnum(int id){
        for(EnumIndicador indicador : EnumIndicador.values()){
            if(indicador.getId().equals(id)){
                return indicador;
            }
        }
        return null;
            
    }

    @Override
    public String toString(){
        return nombre;
    }
}