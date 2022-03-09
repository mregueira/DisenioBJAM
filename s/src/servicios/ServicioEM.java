package servicios;

import gui.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author leandro
 */
public class ServicioEM {

    private static ServicioEM instancia;
    private static EntityManagerFactory emf;
    public static boolean versionFinal;

    private ServicioEM(){
    }

    private synchronized static void crearInstancia(){
        if(instancia == null) {
            instancia = new ServicioEM();
            
            conectar();
            
            //Refrescamos la conexión 
            new Timer(9 * 60 * 60 * 1000, new RefrescarConexión()).start();  //Maximo 10 horas. Lo refrescamos una vez por hora.
        }
    }
    
//    /**
//     * Cada n milisegundos (varias horas) la conexión a la base de datos debe ser usada.
//     * Caso contrario tenemos:
//     * Internal Exception: com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: The last packet successfully received from the server was 227.979.890 milliseconds ago
//     */
    private static class RefrescarConexión implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            conectar();
        }
    }
    
    
    private static synchronized  void conectar(){
        Map<String, String> propiedades = new HashMap<>();
        //El autorecconect sirve para evitar mensajes cuando el sistema ha pasado varias horas sin consultar al servidor MySQL
        if(!versionFinal){
            propiedades.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/campiutti?autoReconnect=true");
            propiedades.put("javax.persistence.jdbc.user", "root");
            propiedades.put("javax.persistence.jdbc.password", "iaio470");
        }
        else{
            propiedades.put("javax.persistence.jdbc.url", "jdbc:mysql://192.168.40.21:3306/campiutti?autoReconnect=true");
            propiedades.put("javax.persistence.jdbc.user", "intampentu");
            propiedades.put("javax.persistence.jdbc.password", "nanoiaio");
        }


        if(emf != null && emf.isOpen()){
            emf.close();
        }
        if(emf != null){
            emf = null;
        }

        
        emf = javax.persistence.Persistence.createEntityManagerFactory("IntampentuPU", propiedades);

        //Creamos un em para verificar el estado de la conexión. El primero siempre tarda un poco mas.
        try{
            emf.createEntityManager();
            GUI.setTextoConexion("Conexión establecida.");
        }
        catch(PersistenceException e){
            GUI.setTextoConexion("Conexión NO establecida.");
            JOptionPane.showMessageDialog(null,
                    "Error con la conexión a la base de datos. El sistema de cerrará",
                    "Mensaje del sistema.(Paquete:servicios / Clase: EMServicio)",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(9999);
        }
        
    }

    //Debe estar sincronizado porque al arrancar, podemos tener un em  = null
    public synchronized static ServicioEM getInstancia(){
	if(instancia == null) {
            crearInstancia();
        }
        return instancia;
    }


    
    /**
     * El EMF define el modo de conexión a la base
     * El EM es en si mismo una conexión.
     * 
     * Para cada hilo de ejecución debe crearse un EM a partir de un mismo EMF haciendo
     * ServicioEM.getEMF().createEntityManager();
     *
     * Esta función no es estatica. Solo se la puede acceder a través de em.getInstancia.getEMF()
     *
     * @return el único EMF de toda la aplicación
     */
    public synchronized EntityManagerFactory getEMF(){
        return emf;
    }
}
