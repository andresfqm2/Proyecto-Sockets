/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author aquintana
 */
public class Util {

    /**
     * Se crea el siguiente método para escribir en el fichero el número de
     * cuenta y valor enviados des el cliente
     */
    static String escribirEnFichero(long numeroCuenta, long valor) {

        System.out.println("Ingreso al metodo escribirEnFichero, parametros " + numeroCuenta + " " + valor);
        FileWriter flwriter = null;
        try {
            ///además de la ruta del archivo recibe un parámetro de tipo boolean, que le indican que se va añadir más registros 
            flwriter = new FileWriter("/home/ArchivoSocket/datos.txt", true);
            //crea un buffer o flujo intermedio antes de escribir directamente en el archivo
            BufferedWriter bfwriter = new BufferedWriter(flwriter);

            //escribe los datos en el archivo
            bfwriter.write(numeroCuenta + "," + valor + "\n");

            //cierra el buffer intermedio
            bfwriter.close();

        } catch (IOException e) {
            return "NO-OK";
        } finally {
            if (flwriter != null) {
                try {
                    //cierra el flujo principal
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Registro grabado OK";
    }

    // retorna el saldo de la cuenta ingresada desde el cliente, si la cuenta no existe retorna -1
    public static long leerArchivo(long numeroCuenta) {
        // crea el flujo para leer desde el archivo
        File file = new File("/home/ArchivoSocket/datos.txt");
        Scanner scanner;
        try {
            //se pasa el flujo al objeto scanner
            scanner = new Scanner(file);
            long numeroCuentaObtenido;
            long valorObtenido;

            while (scanner.hasNextLine()) {
                // el objeto scanner lee linea a linea desde el archivo
                String linea = scanner.nextLine();
                Scanner delimitar = new Scanner(linea);
                //se usa una expresión regular
                //que valida que antes o despues de una coma (,) exista cualquier cosa
                //parte la cadena recibida cada vez que encuentre una coma				
                delimitar.useDelimiter("\\s*,\\s*");
                numeroCuentaObtenido = Long.parseLong(delimitar.next());
                valorObtenido = Long.parseLong(delimitar.next());

                if (numeroCuentaObtenido == numeroCuenta) {
                    return valorObtenido;
                }
            }
            //se cierra el ojeto scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
