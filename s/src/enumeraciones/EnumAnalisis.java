package enumeraciones;

/**
 *
 * @author leandro
 */
public enum EnumAnalisis {

    NINGUNO             ( 0, "Ninguno"          , "Ning."),
    CPK                 ( 1, "CPK"              , "CPK"),
    POR_VARIABLE        ( 2, "Por variable"     , "Variab."),
    POR_ATRIBUTO        ( 3, "Por atributo"     , "Atrib."),
    PROVEEDOR           ( 4, "Medido en externo", "Extr."),
    POR_CERTIFICADO     ( 5, "Por certificado"  , "Certif."),
    GARANTIZA_PROCESO   ( 6, "Garantiza proceso", "Garan.Pr."),
    SISTEMA_ANTIERROR   ( 7, "Sistema antierror", "AntiError");

    private final Integer id;
    private final String nombre;
    private final String nombreCorto;

    private EnumAnalisis(Integer id, String nombre, String nombreCorto) {
        this.id = id;
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
    }

    public Integer getId() {
        return this.id;
    }
    public String getNombre() {
        return this.nombre;
    }
    public String getNombreCorto() {
        return this.nombreCorto;
    }
    @Override
    public String toString(){
        return this.nombre;
    }
}