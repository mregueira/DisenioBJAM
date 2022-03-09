package servicios;

import java.util.zip.CRC32;
import java.util.zip.Adler32;

/**
 *
 * @author leandro
 */
public class ServicioCRC {

    /**
     * Enviado un texto y el crc recibido, los compara y evalua su igualdad
     * @param texto: texto recibido al cual se quiere verificar su CRC
     * @return true si el crc le corresponde al texto.
     */
    static public synchronized boolean crcCorrecto(String texto){
        
        Adler32 a1 = new Adler32();
        a1.update("123".getBytes());
        long a = a1.getValue();

        Adler32 a2 = new Adler32();
        a2.update("321".getBytes());
        long b = a2.getValue();
        
//        
//        cs.update("123".getBytes());
//        long a = cs.getValue();
//
//        CRC32 cs1 = new CRC32();
//        cs1.update("123".getBytes());
//        long c = cs1.getValue();
//
//        CRC32 cs2 = new CRC32();
//        cs2.update("321".getBytes());
//        long b = cs2.getValue();
//        
//        
//        //String a = ServicioProtocolo.getSoloDatos(texto);
        CRC32 cs = new CRC32();
        cs.update((ServicioProtocolo.getSoloDatos(texto)).getBytes());
        return ServicioProtocolo.getChecksum(texto) == cs.getValue();
    }

    /**
     * Dado un texto, devuelve su crc
     * @param texto del cual se quiere calcular el CRC
     * @return el valor int del CRC del texto enviado
     */
    static public synchronized long getCrc(String texto){
        CRC32 cs = new CRC32();
        cs.update(texto.getBytes());
        return cs.getValue();
    }
    
    
    
}
