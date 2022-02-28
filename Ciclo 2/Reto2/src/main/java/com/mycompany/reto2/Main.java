/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto2;

import java.util.*;

/**
 *
 * @author alvaro
 * @since 9/07/2021
 */
public class Main {

    static ArrayList<Float> pesos;
    static ArrayList<Float> alturas;
    static String clasificacion;
    static Scanner miScanner;
    static boolean flag = true;
    static float imc;

    public static void main(String[] args) {
        pesos = new ArrayList<>();
        alturas = new ArrayList<>();
        miScanner = new Scanner(System.in);
        int contador = 0;

        try {
            do {
                
              //  System.out.println("Registro " + String.valueOf(contador));
               // System.out.println("Ingrese peso: ");
                float peso = miScanner.nextFloat();
                if (peso == -1) {
                    break;
                } else {
                    pesos.add(peso);
                }
               // System.out.println("Ingrese altura: ");
                alturas.add(new Float(Float.valueOf(miScanner.nextFloat())));
                contador++;
                if (contador == 10) {
                    flag = false;
                }
            } while (flag == true);
        } catch (Exception e) {
            System.out.println("Error, digite un numero valido");
            main(args);
        }

        for (int i = 0; i < contador; i++) {
            int numeroRegistro = i+1;
            imc = (float) (pesos.get(i) / Math.pow(alturas.get(i), 2));
            float imcRedondeado = (float) (Math.round(imc * 10.0) / 10.0);
           

            if (imcRedondeado < 18.5) {
                clasificacion = "Peso Bajo";
            } else if (imcRedondeado >= 18.5 && imcRedondeado <= 24.9) {
                clasificacion = "Peso Normal";
            } else if (imcRedondeado >= 25.0 && imcRedondeado <= 30.0) {
                clasificacion = "Sobrepeso";
            } else if (imcRedondeado > 30.0) {
                clasificacion = "Obesidad";
            }
            System.out.println("Registro " + numeroRegistro + " - " + clasificacion + " : " + imcRedondeado);
        }
    }
}
