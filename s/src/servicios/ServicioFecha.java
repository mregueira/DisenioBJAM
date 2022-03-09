package servicios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author leandro
 */
public class ServicioFecha {

    private static final DateFormat FORMATEADOR_CORTO = DateFormat.getDateInstance(DateFormat.MEDIUM);

    
    /**
     * 
     * @param formato String del tipo "yyyy-MM-dd HH:mm:ss"
     * @param fecha fecha a formatear
     * @return un string mostrando la fecha ya formateada
     */
    static public String fechaFormateada(String formato, Date fecha){
        if(fecha == null){
            return "";
        }
        SimpleDateFormat f = new SimpleDateFormat(formato);
        return f.format(fecha);
        
    }
    
    /**
     * 
     * @param fecha
     * @return 
     */
    static public Date StringToDate(String fecha){
        try { 
            return FORMATEADOR_CORTO.parse(fecha);
        }
        catch (ParseException ex) {
            return null;
        }
    }
    
    /**
     * 
     * @param fecha1
     * @param fecha2
     * @return días desde la fecha1 hasta la fecha2
     */
    static public int diasEntreFecha(Date fecha1, Date fecha2){
        
        Calendar fechaA = Calendar.getInstance();
        fechaA.setTime(fecha1);
        
        Calendar fechaB = Calendar.getInstance();
        fechaB.setTime(fecha2);
        
        return (int)(fechaB.getTimeInMillis() - fechaA.getTimeInMillis()) / 1000 / 60 / 60 / 24 ;
    }    
    
    /**
     * 
     * @param fechaRecibida
     * @return días desde la fecha enviada y hoy
     */
    static public int diasHastaHoy(Date fechaRecibida){
        return diasEntreFecha(fechaRecibida, Calendar.getInstance().getTime());
    }
    
    static public Calendar fechaMasDias(Date fecha,  int dias){
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(Calendar.DAY_OF_MONTH, +dias);
        return cal;
    }

    static public String fechaMasDiasString(Date fecha, int dias){
        return FORMATEADOR_CORTO.format(fechaMasDias(fecha, dias).getTime());
    }
    
    /**
     * 
     * @param cal
     * @return la fecha lista para enviar a un query.
     */
    public static java.sql.Date fechaSql(Calendar cal){
        return new java.sql.Date(cal.getTime().getTime());
    }
}
