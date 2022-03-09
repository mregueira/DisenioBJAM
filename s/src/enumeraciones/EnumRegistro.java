package enumeraciones;

/**
 *
 * @author leandro
 */
public enum EnumRegistro {

    NINGUNO (0, "Sin registro", "No"),
    SISTEMA (1, "Electrónico", "Elec."),
    PAPEL   (2, "Papel", "Papel");

    private final Integer id;
    private final String nombre;
    private final String nombreCorto;

    private EnumRegistro(Integer id, String nombre, String nombreCorto) {
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
        return nombreCorto;
    }

    @Override
    public String toString(){
        return nombre;
    }
}