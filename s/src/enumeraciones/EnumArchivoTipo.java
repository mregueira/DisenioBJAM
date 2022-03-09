package enumeraciones;

/**
 *
 * @author leandro
 */
public enum EnumArchivoTipo {

    LIBRE_OFFICE    ( 0, "Libre Office"),
    PDF             ( 1, "PDF"),
    DRAFT_SIGHT     ( 2, "Draft sight"),
    JPG             ( 3, "Imagen jpg");
    
    private final Integer id;
    private final String nombre;

    private EnumArchivoTipo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return this.id;
    }
    public String getNombre() {
        return nombre;
    }
    
    static public EnumArchivoTipo getEnum(int id){
        for(EnumArchivoTipo tipo : EnumArchivoTipo.values()){
            if(tipo.getId().equals(id)){
                return tipo;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return nombre;
    }
}