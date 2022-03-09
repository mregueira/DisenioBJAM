package servicios;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;


/**
 *
 * @author leandro
 */
public class ServicioAbm {

    static public void borrar(EntityManager em, Object obj) {
        persistir(em, obj, null);
    }
    static public void crear(EntityManager em, Object obj) {
        persistir(em, obj, true);
    }
    static public void editar(EntityManager em, Object obj) {
        persistir(em, obj, false);
    }

    /**
     * Crear, edita o borra un objeto, actulizando sus tablas maestras
     * 
     * @param obj: objeto a persistir
     * @param esAlta: alta de un nuevo objeto o modificación de un objeto existente. Null para borrar el objeto
     */
    static synchronized void persistir(EntityManager em, Object obj, Boolean esAlta) {
        try {
            em.getTransaction().begin();
            
            
            //Borrado
            if(esAlta == null){
                //Para evitar problemas con los foreingKeys, anulamos las relaciones entre procesos.
                em.remove(obj);
            }
            
            //Creación
            else if(esAlta){
                em.persist(obj); 
            }
            
            //Modificación
            else {
                em.merge(obj);
            }

            //enviamos los datos todos juntos....
            em.getTransaction().commit(); 

        }
        catch (Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }

            //Enviamos la descripción de la exc. a quien lo reciba. En modo versión final, queda guardado en nuestro log.
            e.printStackTrace();

            //Informamos al usuario
            JOptionPane.showMessageDialog(null,
                                        "No se pudieron grabar los datos"  + "\n" + 
                                         e.getMessage(),
                                        "Mensaje del sistema",
                                        JOptionPane.WARNING_MESSAGE);
        }
    }
}
