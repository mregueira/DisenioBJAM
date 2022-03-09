package servicios;

/**
 *
 * @author leandro
 */
public class ServicioInstrumento {
    
    /**
     * Según la resolución, devuelve el número de decimales para  mostrar los datos.
     * @param resolucion
     * @return
     */
    public static synchronized Integer decimales(Double resolucion){

        try{
            if(resolucion < 0.00009 ){
                return 5;
            }
            else if(resolucion >= 0.0001 && resolucion <= 0.0009){
                return 4;
            }
            else if(resolucion >= 0.001 && resolucion <= 0.009 ){
                return 3;
            }
            else if(resolucion >= 0.01  && resolucion <= 0.09 ){
                return 2;
            }
            else if(resolucion >= 0.1 &&  resolucion <= 0.9 ){
                return 1;
            }
            else if(resolucion >= 1 ){
                return 0;
            }
        }
        catch(NullPointerException e){
            return 0;
        }
        return 0;
    }
    
    
}
