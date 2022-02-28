/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto1;

import java.util.Scanner;

/**
 *
 * @author alvar
 */
public class Main {

    public static void main(String[] args) {

        String nombre = "";
        String apellidos = "";
        Scanner miScanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre y apellido: ");

        String nombreyApelldo = miScanner.nextLine();

        for (int i = 0; i < nombreyApelldo.length(); i++) {
            if (nombreyApelldo.substring(i, i + 1).equals(" ")) {
                nombre = nombreyApelldo.substring(0, i);
                apellidos = nombreyApelldo.substring(i + 1);
                break;

            }
        }

        if (nombre.length() > 7) {
            nombre = nombre.substring(0, 7);
            if (apellidos.length() > 7) {
                apellidos = apellidos.substring(0, 7);
            }
        } else {
            int caracteresRestantes = 7 - nombre.length();
            if (apellidos.length() > 7) {
                apellidos = apellidos.substring(0, 7 + caracteresRestantes);
            }
        }

        System.out.println(nombre + " " + apellidos);

    }
}
