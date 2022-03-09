/**
 *
 * @author leandro
 */

package gui;

import enumeraciones.EnumJson;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import org.json.simple.JSONObject;
import runnables.Receiver;
import servicios.ServicioEM;
import servicios.ServicioEncode;
import servicios.ServicioProtocolo;


//http://code.google.com/p/json-simple/

public class GUI extends javax.swing.JFrame {
    
    private final boolean versionFinal = true;
    /**
     * Creates new form UDPServerGui
     */
    public GUI() {
	initComponents();
	
        setExtendedState(Frame.NORMAL);
        setPreferredSize(new Dimension(800, 500));
        pack();
        setLocationRelativeTo(null);
        setTitle("Servidor de sistema M");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
        jLabelDatosInicio.setText("Inicio de actividad: " + dateFormat.format(Calendar.getInstance().getTime()));
        

        //Iniciamos el EM para que ya quede activo
        ServicioEM.versionFinal = versionFinal;
        ServicioEM.getInstancia();
        

        //Comienza el responder        
        inciarServicio();
        
        
    }
    
    
    
    
    
    
    private void inciarServicio(){
        new Thread(new Receiver()).start();
    }

    public static void agregarTexto(String texto, Color color){
        agregarTexto(texto, color, false);
    }    
    
    public static void setTextoConexion(String texto){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
        jLabelDatosReconexión.setText(texto + " " + dateFormat.format(Calendar.getInstance().getTime()));
    }
    /*
     * Agrega el texto recibido al textpane
     */
    public static void agregarTexto(String texto, Color color, boolean saltarLinea){
        
        
        //Solo mostramos los primeros caracteres
        try {
            jTextPane1.getStyledDocument().remove(0, jTextPane1.getText().length() - 50000);
        } catch (NullPointerException | BadLocationException ex) {}
        
        //Creamos un nuevo estilo...
        Style style = new StyleContext().addStyle("estilo", null);
        //y le ponemos el color
        StyleConstants.setForeground(style, color);
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
            jTextPane1.getStyledDocument().
                       insertString(jTextPane1.getDocument().getLength(), 
                                    dateFormat.format(Calendar.getInstance().getTime()) + " " + texto + "\n", 
                                    style);
            if(saltarLinea){
                jTextPane1.getStyledDocument().
                           insertString(jTextPane1.getDocument().getLength(), 
                                        "\n", 
                                        style);
            }
        }
        catch (BadLocationException ex) {
        }
        
        
        //Vamos al fondo.
        jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
    }
    
   
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jLabelDatosReconexión = new javax.swing.JLabel();
        jLabelDatosInicio = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButtonEnviarMedida = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado de los Requests"));
        jScrollPane1.setViewportView(jTextPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        jButton1.setText("Borrar log");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jButton1, gridBagConstraints);

        jLabelDatosReconexión.setForeground(new java.awt.Color(102, 102, 0));
        jLabelDatosReconexión.setText("Estado de la conexión");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jLabelDatosReconexión, gridBagConstraints);

        jLabelDatosInicio.setForeground(new java.awt.Color(51, 51, 255));
        jLabelDatosInicio.setText("Datos de inicio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jLabelDatosInicio, gridBagConstraints);

        jButton2.setText("Prender salida 1");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new java.awt.GridBagConstraints());

        jButtonEnviarMedida.setText("14.05");
        jButtonEnviarMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarMedidaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        getContentPane().add(jButtonEnviarMedida, gridBagConstraints);

        jButton3.setText("29.91");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jButton3, gridBagConstraints);

        jButton4.setText("7.51");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        getContentPane().add(jButton4, gridBagConstraints);

        jButton5.setText("14.20");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        getContentPane().add(jButton5, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            jTextPane1.getStyledDocument().remove(0, jTextPane1.getText().length());
        }
        catch (BadLocationException ex) {}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        InetAddress address;
        try{
            address = InetAddress.getByName("192.168.110.1");
            //Creamos el datagrama
            
            String accion = EnumJson.A_SET_SALIDA.getNombre();
            JSONObject data = new JSONObject();
            data.put(EnumJson.D_SALIDA_NUMERO.getNombre(), 0);
            data.put(EnumJson.D_SALIDA_ESTADO.getNombre(), "ON");
            
            byte[] buffer = ServicioProtocolo.crearDatagrama(1, EnumJson.C_REQUEST.getNombre(), 
                    accion, 
                    data);

            //y lo enviamos
            try {
                DatagramSocket socket = new DatagramSocket();
                socket.send(new DatagramPacket(buffer, buffer.length, address, 4444));
                GUI.agregarTexto("    Se envio el pedido de prender pata 0", Color.blue, true);
                
                
//                
                //Creamos un buffer para guardar los datos
//                byte[] bufRecibido = new byte[10000];
//                DatagramPacket datoRecibido = new DatagramPacket(bufRecibido, bufRecibido.length);
//                
//                GUI.agregarTexto("Recibiendo...", Color.RED);
//                socket.receive(datoRecibido);
//                String a= Arrays.toString(datoRecibido.getData());
//                GUI.agregarTexto("Recibido " + a, Color.RED);
//                int c =1;
                
                
            }
            catch (SocketException ex) {
                GUI.agregarTexto("    ERROR de socket", Color.RED);
            }
            catch (IOException ex) {
                //GUI.agregarTexto("    Largo del dato: " + data.toString().length() + " (Maximo: 65500)" , Color.RED);
                GUI.agregarTexto("    ERROR de IO", Color.RED);
            }
            
        }
        catch (UnknownHostException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonEnviarMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarMedidaActionPerformed

        InetAddress address;
        try{
            address = InetAddress.getByName("192.168.90.19");
            //Creamos el datagrama
            byte[] buffer = ServicioProtocolo.crearDatagrama(1, EnumJson.C_REQUEST.getNombre(), 
                    EnumJson.A_GUARDAR_DATOS_CALIBRES.getNombre(), 
                    ServicioEncode.medidaCalibre(null, 14.05, 2, "mm"));

            //y lo enviamos
            try {
                DatagramSocket socket = new DatagramSocket();
                socket.send(new DatagramPacket(buffer, buffer.length, address, 4445));
                GUI.agregarTexto("    Se envio datos de una medida a la tablet", Color.blue, true);
            }
            catch (SocketException ex) {
                GUI.agregarTexto("    ERROR de socket", Color.RED);
            }
            catch (IOException ex) {
                //GUI.agregarTexto("    Largo del dato: " + data.toString().length() + " (Maximo: 65500)" , Color.RED);
                GUI.agregarTexto("    ERROR de IO", Color.RED);
            }
            
        }
        catch (UnknownHostException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }//GEN-LAST:event_jButtonEnviarMedidaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        InetAddress address;
        try{
            address = InetAddress.getByName("192.168.90.19");
            //Creamos el datagrama
            byte[] buffer = ServicioProtocolo.crearDatagrama(1, EnumJson.C_REQUEST.getNombre(), 
                    EnumJson.A_GUARDAR_DATOS_CALIBRES.getNombre(), 
                    ServicioEncode.medidaCalibre(null, 29.91, 2, "mm"));

            //y lo enviamos
            try {
                DatagramSocket socket = new DatagramSocket();
                socket.send(new DatagramPacket(buffer, buffer.length, address, 4445));
                GUI.agregarTexto("    Se envio datos de una medida a la tablet", Color.blue, true);
            }
            catch (SocketException ex) {
                GUI.agregarTexto("    ERROR de socket", Color.RED);
            }
            catch (IOException ex) {
                //GUI.agregarTexto("    Largo del dato: " + data.toString().length() + " (Maximo: 65500)" , Color.RED);
                GUI.agregarTexto("    ERROR de IO", Color.RED);
            }
            
        }
        catch (UnknownHostException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        InetAddress address;
        try{
            address = InetAddress.getByName("192.168.90.19");
            //Creamos el datagrama
            byte[] buffer = ServicioProtocolo.crearDatagrama(1, EnumJson.C_REQUEST.getNombre(), 
                    EnumJson.A_GUARDAR_DATOS_CALIBRES.getNombre(), 
                    ServicioEncode.medidaCalibre(null, 7.51, 2, "mm"));

            //y lo enviamos
            try {
                DatagramSocket socket = new DatagramSocket();
                socket.send(new DatagramPacket(buffer, buffer.length, address, 4445));
                GUI.agregarTexto("    Se envio datos de una medida a la tablet", Color.blue, true);
            }
            catch (SocketException ex) {
                GUI.agregarTexto("    ERROR de socket", Color.RED);
            }
            catch (IOException ex) {
                //GUI.agregarTexto("    Largo del dato: " + data.toString().length() + " (Maximo: 65500)" , Color.RED);
                GUI.agregarTexto("    ERROR de IO", Color.RED);
            }
            
        }
        catch (UnknownHostException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        InetAddress address;
        try{
            address = InetAddress.getByName("192.168.90.19");
            //Creamos el datagrama
            byte[] buffer = ServicioProtocolo.crearDatagrama(1, EnumJson.C_REQUEST.getNombre(), 
                    EnumJson.A_GUARDAR_DATOS_CALIBRES.getNombre(), 
                    ServicioEncode.medidaCalibre(null, 14.20, 2, "mm"));

            //y lo enviamos
            try {
                DatagramSocket socket = new DatagramSocket();
                socket.send(new DatagramPacket(buffer, buffer.length, address, 4445));
                GUI.agregarTexto("    Se envio datos de una medida a la tablet", Color.blue, true);
            }
            catch (SocketException ex) {
                GUI.agregarTexto("    ERROR de socket", Color.RED);
            }
            catch (IOException ex) {
                //GUI.agregarTexto("    Largo del dato: " + data.toString().length() + " (Maximo: 65500)" , Color.RED);
                GUI.agregarTexto("    ERROR de IO", Color.RED);
            }
            
        }
        catch (UnknownHostException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(() -> { new GUI().setVisible(true);});
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonEnviarMedida;
    private javax.swing.JLabel jLabelDatosInicio;
    private static javax.swing.JLabel jLabelDatosReconexión;
    private static javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
