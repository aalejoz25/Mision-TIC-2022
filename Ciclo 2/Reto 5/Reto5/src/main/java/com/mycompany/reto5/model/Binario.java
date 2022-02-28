/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.model;

/**
 *
 * @author alvar
 */
public class Binario {

    public static String sumar(String[] binarios) {
        int longitud = binarios.length;
        int aDecimal[] = new int[longitud];
        int suma = 0;
        for (int i = 0; i < longitud; i++) {
            aDecimal[i] = Integer.parseInt(binarios[i], 2);
            suma += aDecimal[i];
        }
        String resultado = Integer.toString(suma, 2);
        return resultado;
    }

    public static String restar(String[] binarios) {
        int longitud = binarios.length;
        int aDecimal[] = new int[longitud];
        for (int i = 0; i < longitud; i++) {
            aDecimal[i] = Integer.parseInt(binarios[i], 2);
        }
        int resta = aDecimal[0];
        for (int i = 1; i < longitud; i++) {
            resta -= aDecimal[i];
        }
        String resultado = Integer.toString(resta, 2);
        return resultado;
    }

    public static String multiplicar(String[] binarios) {
        int longitud = binarios.length;
        int aDecimal[] = new int[longitud];
        int multiplicacion = 1;
        for (int i = 0; i < longitud; i++) {
            aDecimal[i] = Integer.parseInt(binarios[i], 2);
            multiplicacion *= aDecimal[i];
        }
        String resultado = Integer.toString(multiplicacion, 2);
        return resultado;
    }

    public static String dividir(String dividendoBinario, String divisorBinario) {
        double dividendoDecimal = Integer.parseInt(dividendoBinario, 2);
        double divisorDecimal = Integer.parseInt(divisorBinario, 2);
        double division = dividendoDecimal / divisorDecimal;
        String resultado = Integer.toString((int) division, 2);
        return String.valueOf(resultado);
    }

    public static String potencia(String baseBinaria, String exponenteBinario) {
        int baseDecimal = Integer.parseInt(baseBinaria, 2);
        int exponenteDecimal = Integer.parseInt(exponenteBinario, 2);
        int potencia = (int) Math.pow(baseDecimal, exponenteDecimal);
        String resultado = Integer.toString(potencia, 2);
        return resultado;
    }

    public static String radicacion(String radicandoBinario, String indiceBinario) {
        double radicandoDecimal = Integer.parseInt(radicandoBinario, 2);
        double indiceDecimal = Integer.parseInt(indiceBinario, 2);
        int raiz = (int) Math.pow(radicandoDecimal, 1 / indiceDecimal);
        String resultado = Integer.toString(raiz, 2);
        return resultado;
    }
}
