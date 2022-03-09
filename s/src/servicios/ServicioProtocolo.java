package servicios;

import enumeraciones.EnumJson;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author leandro
 */

public class ServicioProtocolo {
   
    static final String  VERSION = "0.0";  

    

    public static synchronized String getVersion(String datagrama){
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(datagrama);
            return (String) jsonObject.get(EnumJson.VERSION.getNombre());  
        }
        catch (ParseException ex) {
            return null;
        }
    }
    public static synchronized Long getSecuencia(String datagrama){
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(datagrama);
            return (Long) jsonObject.get(EnumJson.SECUENCE.getNombre());  
        }
        catch (ParseException ex) {
            return null;
        }
    }

    public static synchronized String getComando(String datagrama){
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(datagrama);
            return (String) jsonObject.get(EnumJson.COMMAND.getNombre());  
        }
        catch (ParseException ex) {
            return null;
        }
    }
    public static synchronized String getAccion(String datagrama){
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(datagrama);
            return (String) jsonObject.get(EnumJson.ACTION.getNombre());  
        }
        catch (ParseException ex) {
            return null;
        }
    }
    public static synchronized long getChecksum(String datagrama){
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(datagrama);
            return (long) jsonObject.get(EnumJson.CHECKSUM.getNombre());  
        }
        catch (ParseException ex) {
            return -1;
        }
    }
    public static synchronized Object getDato(String datagrama){
        try {
            JSONObject js = (JSONObject) new JSONParser().parse(datagrama);
            Object obj = js.get(EnumJson.DATA.getNombre());  
            if(obj instanceof List){
                return (List)obj;
            }
            if(obj instanceof JSONObject){
                return (JSONObject)obj;
            }
            return null;
        }
        catch (ParseException ex) {
            return null;
        }
    }

    /**
     * Devuelve solamente los datagramas, eliminando el checkSum
     * @param datagrama
     * @return 
     */
    public static synchronized String getSoloDatos(String datagrama){
        try {
            JSONObject js = (JSONObject) new JSONParser().parse(datagrama);
            js.remove(EnumJson.CHECKSUM.getNombre());
            return js.toJSONString();
        }
        catch (ParseException ex) {
            return null;
        }
    }
    
    
    
    



    
    
    /**
     * Crear un datagrama listo para enviar
     * @param secuence
     * @param command
     * @param action
     * @param data
     * @return 
     */
    public static synchronized byte[] crearDatagrama(long secuence, String command, String action, JSONObject data){
        JSONObject js = new JSONObject();
        js.put(EnumJson.VERSION.getNombre(), VERSION);
        js.put(EnumJson.SECUENCE.getNombre(), secuence);
        js.put(EnumJson.COMMAND.getNombre(), command);
        js.put(EnumJson.ACTION.getNombre(), action);
        js.put(EnumJson.DATA.getNombre(), data);
        js.put(EnumJson.CHECKSUM.getNombre(), ServicioCRC.getCrc(VERSION + secuence + command + action + data));

        return js.toJSONString().getBytes();
    }

}
      
