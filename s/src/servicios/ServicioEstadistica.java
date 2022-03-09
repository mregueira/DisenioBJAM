/**
 *
 * @author leandro
 */

package servicios;

import java.util.List;

/**
 * Calcula la varianza de una lista de Doubles.  Usa la fórmula:
 * varianze = sum((x_i - media)^2) / (n - 1)
 */
public class ServicioEstadistica {

    private List<Double> valores;


    /**
     * Constructor de la clase
     * @param valores: lista de Doubles. Es la muestra de la cual dse calcularán los datos estadisticos
     */
    public ServicioEstadistica(List<Double> valores) {
        this.valores = valores;
    }

    /**
     * Calcula la media muestral
     * @return valor tipo Double
     */
    public Double getMedia(){

        if(valores == null || valores.isEmpty()){
            return Double.NaN;
        }

        double media = 0d;
        for(int i = 0; i < valores.size() ; i++){
            media += valores.get(i);
        }
        return media/valores.size();
    }


     /**
     * Calcula la varianza
     * @return valor tipo Double
     */
    public Double getVarianza() {

        if(valores == null || valores.isEmpty()){
            return Double.NaN;
        }
        else if(valores.size() == 1){
            return 0d;
        }

        Double media = getMedia();

        //Calculamos la varianza
        double accum = 0d;
        for (int i = 0; i < valores.size(); i++) {
            accum += Math.pow(valores.get(i) - media, 2.0);
        }
        return accum / (valores.size() - 1);
    }

    /**
     * Calcula de desviación estandard como raiz cuadrada de la varianza
     * 
     * @return valor tipo Double
     */
    public Double getDesviacionStd(){
        return Math.sqrt(getVarianza());
    }
}

