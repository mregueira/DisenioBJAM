package servicios;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author leandro
 */
public class ServicioString {
    

    /**
     * 
     * @param cadena
     * @return true si la cadena es un numero
     */
    public static boolean esNumero(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }    

    /**
     * 
     * @param caracter
     * @return true si el caracter es un numero
     */
    public static boolean esNumero(char caracter){
    	return esNumero(String.valueOf(caracter));
    }    
    
    
    
    /**
     * 
     * @param string  texto a  formatear
     * @param length   largo del texto a devolver
     * @return un caracter de largo length, que ha sido completado con espacios en blanco en la derecha
     */
    public static String padR(String string, int length){
	//%1: argumento 1 (el único que tenemos)
	//-: formateamos a la derecha
	//$length: longitud
	//s: string
	return String.format("%1$-" + length + "s", string);
    }
    
    /**
     *
     * @param string
     * @param length
     * @return
     */
    public String pR(String string, int length){
        return padR(string, length);
    }
    /**
     * 
     * @param string
     * @param length
     * @param letra
     * @return un caracter de largo length, que ha sido completado con "letra" en la derecha
     */
    public static String padR(String string, int length, String letra){
        return ServicioString.padR(string, length).replace(" ", letra);
    }

    /**
     * 
     * @param string  texto a  formatear
     * @param length   largo del textoa devolver
     * @return un caracter de largo length, que ha sido completado con espacios en blanco en la izquierda
     */
    public static String padL(String string, int length){
	//%1: argumento 1 (el único que tenemos)
	//$length: longitud
	//s: string
	return String.format("%1$" + length + "s", string);
    }
    /**
     * 
     * @param string  texto a  formatear
     * @param length   largo del textoa devolver
     * @return un caracter de largo length, que ha sido completado con espacios en blanco en la izquierda
     */
    public String pL(String string, int length){
	return padL(string, length);
    }

    /**
     * 
     * @param string
     * @param length
     * @param letra
     * @return un caracter de largo length, que ha sido completado con "letra" en la izquierda
     */
    public static String padL(String string, int length, String letra){
        return ServicioString.padL(string, length).replace(" ", letra);
    }

    /**
     * Devuelve espacios en blanco
     * @param length: largo de la cadena
     * @return cadena de espacios en blanco
     */
    public static String bls(int length){
	return ServicioString.padR("", length);
    }
    /**
     * Devuelve espacios en blanco
     * @param length: largo de la cadena
     * @return cadena de espacios en blanco
     */
    public String bs(int length){
	return bls(length);
    }
    
    
    public static String textoAjustado(String viejo, int columnas){
        if(viejo == null){
            viejo = "";
        }
        String nuevo = "";
        int col = 0;
        
        for(String palabra : viejo.split("[ ]+")) {
            if(col + palabra.length() >= columnas){
                col = palabra.length();
                nuevo += "\n" + palabra + " ";
            }
            else{
                col += palabra.length();
                nuevo += palabra + " ";
                
            }
        }
        
        return nuevo;
    }
    public static List<String> textoAjustadoLista(String viejo, int columnas){
        List<String> nuevo = new ArrayList<>();
        int col = 0;
        String t = "";
        
        for(String palabra : viejo.split("[ ]+")) {
            if(col + palabra.length() >= columnas){
                col = palabra.length();
                nuevo.add(t);
                t = "\n" + palabra + " ";
            }
            else{
                col += palabra.length();
                t += palabra + " ";
            }
        }
        nuevo.add(t);
        
        
        return nuevo;
    }
    
    /**
     * 
     * @param d
     * @return el valor expresado como String. Ejemplo: 10.0 => "10"   10.15 => "10.15"
     */
    public static String DobleToString(double d){
        //Si no tiene decimales
        if(d == (long) d)
            return String.format("%d",(long)d);
        
        //Si tiene
        else
            return String.format("%s",d);
    } 

    /**
     * 
     * @param d: double a formatear
     * @param formato: ejemplo "%5.2f": 5 enteros, 2 decimales
     * @return el valor expresado como String. 
     */
    public static String DobleToString(double d, String formato){
        return String.format(formato,  d);
    } 
    
    /**
     * 
     * @param s
     * @param c
     * @return la cantidad que un caracter está presente en un string
     */
    public static int ocurrencias(String s, char c){
        if(s == null){
            return 0;
        }
        return (int)s.chars().filter(ch -> ch == c).count();        
    }
        
    
    
}
