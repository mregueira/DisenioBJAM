package runnables;

/**
 *
 * @author leandro
 */
import gui.GUI;

import java.awt.Color;
import java.io.*;
import java.net.*;
 

public class Receiver implements  Runnable{

    private final DatagramSocket socket;

    public Receiver(DatagramSocket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            //Creamos un buffer para guardar los datos
            byte[] bufRecibido = new byte[10000];
       
            //Ciclo de lectura constante.
            while (true) {
                //Creamos una datagrama
                DatagramPacket datoRecibido = new DatagramPacket(bufRecibido, bufRecibido.length);

                //Esperamos el pedido del cliente
                socket.receive(datoRecibido);
                
                GUI.agregarTexto("*** Mensaje del ITBA " + new String(datoRecibido.getData(), 0, datoRecibido.getLength()), Color.CYAN, true);
                GUI.agregarTexto("ADDRESS IP: " + datoRecibido.getAddress().getHostAddress(), Color.BLACK, true);
                GUI.agregarTexto("Puerto: "     + datoRecibido.getPort(), Color.BLACK, true);
                

                //Respondemos, en otro hilo, a quien nos consultó
                new Thread(new Responder(new String(datoRecibido.getData(), 0, datoRecibido.getLength()), datoRecibido.getAddress(), socket)).
                    start();
                
            }
        }
        catch (SocketException ex) {}
        catch (IOException ex) {}
    }
}    