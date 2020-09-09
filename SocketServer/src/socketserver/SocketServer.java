/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aquintana
 */
public class SocketServer {

    final int PUERTO = 875;

    public void iniciarSocketServer() {
        // Socket del cliente
        Socket socketClient = null;
        DataInputStream in;
        DataOutputStream out;

        try {

            ServerSocket server = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado ...");

            // Utilizamos el siguiente while y el metodo acept para que siempre este esperando una solicitud de un cliente
            while (true) {
                socketClient = server.accept();
                System.out.println("Cliente conectado");
                // Lo que entra desde el cliente al servidor
                in = new DataInputStream(socketClient.getInputStream());
                // Lo que sale del server hacia el cliente
                out = new DataOutputStream(socketClient.getOutputStream());

                /*
                 Parte 4 : Se recibe el mensaje enviado por el cliente
                 */
                String mensaje = in.readUTF();
                //System.out.println("Mensaje que se recibe en el server enviado desde el cliente : " + mensaje);

                if (mensaje.contains("-")) {
                    /*
                Parte 5:  Se extra las subcadenas cuenta y valor y  se convierte a valores numéricos 
                y así pueda ingresar al archivo y grabar los datos separados por comas
                     */
                    String[] cadena = null;
                    long cuenta = 0, valor = 0;

                    for (int i = 0; i < 2; i++) {
                        cadena = mensaje.split("-");
                        switch (i) {
                            case 0:
                                cuenta = Long.parseLong(cadena[i]);
                                break;
                            case 1:
                                valor = Long.parseLong(cadena[i]);
                                break;
                        }
                    }

                    /*
                Parte 6: Se llama al método escribirEnFichero el cual recibe como parametros el número de cuenta y valor
                para ingresarlos en el archivo y grabar los datos separados por comas
                     */
                    String mensajeEnviar = Util.escribirEnFichero(cuenta, valor);
                    out.writeUTF(mensajeEnviar);
                } else {

                    long numCuenta = Long.parseLong(mensaje);
                    long res = Util.leerArchivo(numCuenta);
                    if (res != -1) {
                        out.writeUTF("El saldo de la cuenta " + numCuenta + " " + "es : " + res);
                    } else {
                        out.writeUTF("El número de cuenta ingresado no se encuentra registrado");
                    }
                }

                // Cerramos el cliente
                socketClient.close();

            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Se presento el siguiente error en el metodo iniciarSocketServer :" + ex.getMessage());
            ex.printStackTrace();
        }

    }

}
