package entidadesEspeciales;

/**
 *
 * @author leandro
 */
public class IntDoubleString {

    private int entero;
    private double doble;
    private String nombre;

    public IntDoubleString(int entero, double doble) {
        this.entero = entero;
        this.doble = doble;
    }

    
    public IntDoubleString(int entero, double doble, String nombre) {
        this.entero = entero;
        this.doble = doble;
        this.nombre = nombre;
    }

    public double getDoble() {
        return doble;
    }

    public void setDoble(double doble) {
        this.doble = doble;
    }

    public int getEntero() {
        return entero;
    }

    public void setEntero(int entero) {
        this.entero = entero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
}
