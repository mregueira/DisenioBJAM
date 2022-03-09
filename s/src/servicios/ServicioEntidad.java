package servicios;

import javax.persistence.Column;

/**
 * @author leandro
 */
public class ServicioEntidad {

    public static int getColumnLength(Class entidad, String columnName){
        int ret = 0;

        try{
            Column annotation =  entidad.getDeclaredField(columnName).
                                 getAnnotation(javax.persistence.Column.class);
            ret = annotation.length();
        }
        catch(NoSuchFieldException | SecurityException e){}
        return ret;
    }
}
