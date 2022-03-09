package servicios;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author leandro
 */
public class ServicioCifrado {

    /**
     * cifra, usando MD5, un array de char
     * @param clave
     * @return un array de bytes cifrados
     */
    public static byte[] cifrar(char[] clave){
        byte[] claveCifrada = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            claveCifrada = md.digest(charArrayAByteArray(clave));
        }
        catch (NoSuchAlgorithmException ex) {}
        return claveCifrada;
    }

    private static byte[] charArrayAByteArray(char[] ca) {
     byte[] ba = new byte[ca.length * 2];
     java.nio.ByteBuffer.wrap(ba).asCharBuffer().put(ca);
     return ba;
   }
}
