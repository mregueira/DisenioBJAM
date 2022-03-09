package entidadesEspeciales;

import java.util.Date;

/**
 *
 * @author leandro
 */
public class DoubleFecha {
    
    double numero;
    Date fecha;

    public DoubleFecha(double numero, Date fecha) {
        this.numero = numero;
        this.fecha = fecha;
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    
    
}
