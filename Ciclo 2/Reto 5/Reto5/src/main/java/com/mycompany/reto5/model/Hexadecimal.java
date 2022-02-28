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
public class Hexadecimal {

    public static String sumar(String[] hexadecimales) {
        int longitud = hexadecimales.length;
        int aDecimal[] = new int[longitud];
        int suma = 0;
        for (int i = 0; i < longitud; i++) {
            aDecimal[i] = Integer.parseInt(hexadecimales[i], 16);
            suma += aDecimal[i];
        }
        String resultado = Integer.toString(suma, 16);
        return resultado;
    }

    public static String restar(String[] hexadecimales) {
        int longitud = hexadecimales.length;
        int aDecimal[] = new int[longitud];
        for (int i = 0; i < longitud; i++) {
            aDecimal[i] = Integer.parseInt(hexadecimales[i], 16);
        }
        int resta = aDecimal[0];
        for (int i = 1; i < longitud; i++) {
            resta -= aDecimal[i];
        }
        String resultado = Integer.toString(resta, 16);
        return resultado;
    }

    public static String multiplicar(String[] hexadecimales) {
        int longitud = hexadecimales.length;
        int aDecimal[] = new int[longitud];
        int multiplicacion = 1;
        for (int i = 0; i < longitud; i++) {
            aDecimal[i] = Integer.parseInt(hexadecimales[i], 16);
            multiplicacion *= aDecimal[i];
        }
        String resultado = Integer.toString(multiplicacion, 16);
        return resultado;
    }

    public static String dividir(String dividendoHexadecimal, String divisorHexadecimal) {
        int dividendoDecimal = Integer.parseInt(dividendoHexadecimal, 16);
        int divisorDecimal = Integer.parseInt(divisorHexadecimal, 16);
        int division = dividendoDecimal / divisorDecimal;
        String resultado = Integer.toString(division, 16);
        return resultado;
    }

    public static String potencia(String baseHexadecimal, String exponenteHexadecimal) {
        int baseDecimal = Integer.parseInt(baseHexadecimal, 16);
        int exponenteDecimal = Integer.parseInt(exponenteHexadecimal, 16);

        int potencia = (int) Math.pow(baseDecimal, exponenteDecimal);
        String resultado = Integer.toString(potencia, 16);
        return resultado;
    }

    public static String radicacion(String radicandoHexadecimal, String indiceHexadecimal) {
        double radicandoDecimal = Integer.parseInt(radicandoHexadecimal, 16);
        double indiceDecimal = Integer.parseInt(indiceHexadecimal, 16);
        int raiz = (int) Math.pow(radicandoDecimal, 1 / indiceDecimal);
        String resultado = Integer.toString(raiz, 16);
        return resultado;
    }

}
