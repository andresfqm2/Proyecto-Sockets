/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rpc
 */
public class SocketClient {

    final static String HOST = "10.0.0.3";
    final static int PUERTO = 5000;

    public static void iniciarSocketClient(String numeroCuenta, String valor, int opcion) {

        DataInputStream in;
        DataOutputStream out;
        try {
            Socket socketClient = new Socket(HOST, PUERTO);
            // Lo que entra desde el cliente al servidor
            in = new DataInputStream(socketClient.getInputStream());
            // Lo que sale del server hacia el cliente
            out = new DataOutputStream(socketClient.getOutputStream());

            /*
             Parte 3 : Se arma el mensaje con los parametros recibidos para enviar al servidor
             Opción 1 = Insertar cuenta y valor en el archivo
             Opción 2 = Consultar saldo de la cuenta ingresada
             */
            if (opcion == 1) {
                out.writeUTF(numeroCuenta + "-" + valor);
            } else {
                out.writeUTF(numeroCuenta);
            }

            String mensajeRecibido = in.readUTF();
            System.out.println("Se recibe el siguiente mensaje del servidor : " + mensajeRecibido);
            socketClient.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Se presento el siguiente error en el metodo iniciarSocketClient :" + ex.getMessage());
            ex.printStackTrace();
            
        }
    }

}
