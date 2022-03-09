package servicios;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leandro
 * Created on 12/07/2010, 10:33:30
 */
public class ServicioNumero {

    static double  numero;


    /**
     * Clase que ofrece distintos servicios para un número
     *      Pasarlo a letras
     *      Redondearlo
     */
    public ServicioNumero() {
    }

    
    /**
     * @param numero nuḿero a formatear
     */
    public ServicioNumero(Double numero) {
        ServicioNumero.numero = numero;
    }

    
    /**
     * 
     * @param decimales: cantidad de decimales deseados
     * @param numero: numero a formatear
     * @return un string represetnando al numero 
     */
    public static String toString(int decimales, double numero){
        String formato = "#,##0";
        if(decimales > 0){
            formato += ".";
            for(int i = 0; i < decimales; i++){
                formato += "0";
            }
        }
        DecimalFormat formatter= new DecimalFormat(formato);
        return formatter.format(numero);
    }

    /**
     * 
     * @return el número escrito en letras
     */
    public String getEnLetras() {
        String parcial;

        //Pasamos los primeros tres números.
        parcial = enLetras(String.format("%9.2f", numero).substring(0, 3), true);

        if(!parcial.isEmpty()){
            parcial += " mil ";
        }

        //Pasamos los segundos tres números.
        parcial += enLetras(String.format("%9.2f", numero).substring(3, 6), false);

        //Agregamos, si hay, los decimales.
        if(!String.format("%9.2f", numero).substring(7, 9).equals("00")){
            parcial += " con " +  String.format("%9.2f", numero).substring(7, 9)  + "/100";
        }

        try{
            parcial = parcial.substring(0, 1).toUpperCase() + parcial.substring(1);
        }
        catch(StringIndexOutOfBoundsException e){}
        return parcial;
    }


    private String enLetras(String cadena, Boolean primeros){
     
        String ret = "";
        //Centenas
        switch (cadena.substring(0, 1)) {
            case "1":
                if(cadena.substring(1, 2).equals("0") && cadena.substring(2, 3).equals("0")) ret += "cien";
                else  ret += "ciento ";
                break;
            case "2":
                ret += "doscientos ";
                break;
            case "3":
                ret += "trescientos ";
                break;
            case "4":
                ret += "cuatrocientos ";
                break;
            case "5":
                ret += "quinientos ";
                break;
            case "6":
                ret += "seiscientos ";
                break;
            case "7":
                ret += "setecientos ";
                break;
            case "8":
                ret += "ochocientos ";
                break;
            case "9":
                ret += "novecientos ";
                break;
        }
        //Decenas
        switch (cadena.substring(1, 2)) {
            case "1":
                if     (cadena.substring(2, 3).equals("0"))  ret += "diez ";
                else if(cadena.substring(2, 3).equals("1"))  ret += "once ";
                else if(cadena.substring(2, 3).equals("2"))  ret += "doce ";
                else if(cadena.substring(2, 3).equals("3"))  ret += "trece ";
                else if(cadena.substring(2, 3).equals("4"))  ret += "catorce ";
                else if(cadena.substring(2, 3).equals("5"))  ret += "quince ";
                else if(cadena.substring(2, 3).equals("6"))  ret += "dieciseis ";
                else if(cadena.substring(2, 3).equals("7"))  ret += "diecisiete ";
                else if(cadena.substring(2, 3).equals("8"))  ret += "dieciocho ";
                else if(cadena.substring(2, 3).equals("9"))  ret += "diecinueve ";

                return ret;//Ya terminamos aca. Si seguimos, nos agregará el resto de la cadena.
            case "2":
                if(cadena.substring(2, 3).equals("0")) ret += "veinte ";
                else ret += "veinti";
                break;
            case "3":
                ret += "treinta ";
                break;
            case "4":
                ret += "cuarenta ";
                break;
            case "5":
                ret += "cincuenta ";
                break;
            case "6":
                ret += "sesenta ";
                break;
            case "7":
                ret += "setenta ";
                break;
            case "8":
                ret += "ochenta ";
                break;
            case "9":
                ret += "noventa ";
                break;
        }

        //Agregamos la "y" si...
        if( !cadena.substring(2, 3).equals("0") &&  //..el proximo numero no es cero
            !cadena.substring(1, 2).equals("2") &&  //..el anterior no fue 2. Es el caso de veinti...
            !cadena.substring(1, 2).equals("0") &&  //..el anterior no fue 0. Es el caso de ciento uno.
            !ret.isEmpty()){                        //..la cadena no llegó hasta aca vacia
            ret += "y ";
        }
        //Unidades
        switch (cadena.substring(2, 3)) {
            case "1":
                if(primeros){
                    ret += "un";
                }
                else{
                    ret += "uno ";
                }
                break;
            case "2":
                ret += "dos";
                break;
            case "3":
                ret += "tres";
                break;
            case "4":
                ret += "cuatro";
                break;
            case "5":
                ret += "cinco";
                break;
            case "6":
                ret += "seis";
                break;
            case "7":
                ret += "siete";
                break;
            case "8":
                ret += "ocho";
                break;
            case "9":
                ret += "nueve";
                break;
        }

        return ret;
    }
    
    
    public static double redondear(Double numero, int cantidadDecimales){
//        DecimalFormat df=new DecimalFormat("0.00" );
//        String formate = df.format(numero); 
//        try {
//            return (Double)df.parse(formate) ;
//        } catch (ParseException ex) {
//            Logger.getLogger(ServicioNumero.class.getName()).log(Level.SEVERE, null, ex);
//        }
        if(numero == null){
            return 0d;
        }
        
        double roundOff = Math.round(numero * Math.pow(10, cantidadDecimales))/Math.pow(10, cantidadDecimales);
        return roundOff;
        
        //Esto recorta los decimales, pero no redodneo. 13.1999 lopasa a 13.19 y debeŕia pasarlo a 13.20
//        //Multiplicamos por 100 y recortamos todos los decimales. Usamos long porque int puede resultar demasiado corto.
//	long a = (long)(numero * Math.pow(10, cantidadDecimales));
//	
//	//Al dividir por 100, volvemos al valor original que ya no tiene mas de 2 decimales.
//	return  ((double)a)/Math.pow(10, cantidadDecimales);
    }

    public static double redondear(int cantidadDecimales){
        return redondear(ServicioNumero.numero, cantidadDecimales);
    }
    
    public static String formateado(int decimales, Double numero){
        return String.format("%4." + decimales + "f", numero);        
    }
}
