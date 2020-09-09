/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.util.Scanner;

/**
 *
 * @author aquintana
 */
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String numeroCuenta;
        String valor;
        int opcion = 0;
        /*
        Parte 1 : Se pide el número de cuenta y valor por consola
         */
        System.out.println("************** Bienvenido ***************");
        System.out.println("-----------------------------------------");
        System.out.println("Por favor seleccione la opción correspondiente");
        do {
            System.out.println("Digite 1 si va ha registrar una cuenta");
            System.out.println("Digite 2 si va ha consultar el saldo de una cuenta");
            opcion = scan.nextInt();
        } while (opcion == 0);

        if (opcion == 1) {
            System.out.println("Por favor ingrese el número de cuenta");
            do {
                numeroCuenta = scan.nextLine().trim();

            } while (numeroCuenta.isEmpty());
            System.out.println("Por favor ingrese el valor");
            do {
                valor = scan.nextLine().trim();

            } while (valor.isEmpty());
            /*
        Parte 2 : Se invoca el método iniciarSocketClient y se pasa por parametro el número de cuenta y valor 
        capturados por pantalla
             */
            SocketClient.iniciarSocketClient(numeroCuenta, valor, opcion);
        } else {
            String numeroCuentaConsultar;
            System.out.println("Por favor ingrese el número de cuenta a consultar ");
            do {
                numeroCuentaConsultar = scan.nextLine().trim();
            } while (numeroCuentaConsultar.isEmpty());

            SocketClient.iniciarSocketClient(numeroCuentaConsultar, null, opcion);
        }
    }

}
