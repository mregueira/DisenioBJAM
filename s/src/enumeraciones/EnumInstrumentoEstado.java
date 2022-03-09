package enumeraciones;

/**
 *
 * @author leandro
 */
public enum EnumInstrumentoEstado {

    EN_SERVICIO (0, "En servicio"),
    SUSPENDIDO  (1, "Suspendido"),
    ELIMINADO   (2, "Eliminado");

    private Integer id;
    private String nombre;

    private EnumInstrumentoEstado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return this.id;
    }
    public String getNombre() {
        return this.nombre;
    }
    @Override
    public String toString(){
        return this.nombre;
    }

}
