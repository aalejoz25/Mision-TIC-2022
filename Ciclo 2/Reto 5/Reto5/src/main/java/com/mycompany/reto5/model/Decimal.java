/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author alvar
 */
public class Decimal {

    public static int sumar(int[] numeros) {
        int longitud = numeros.length;
        int suma = 0;
        for (int i = 0; i < longitud; i++) {
            suma += numeros[i];
        }
        return suma;
    }

    public static int restar(int[] numeros) {
        int longitud = numeros.length;
        int resta = numeros[0];
        for (int i = 1; i < longitud; i++) {
            resta -= numeros[i];
        }
        return resta;
    }

    public static int multiplicar(int[] numeros) {
        int longitud = numeros.length;
        int multiplicacion = 1;
        for (int i = 0; i < longitud; i++) {
            multiplicacion *= numeros[i];
        }
        return multiplicacion;
    }

    public static double dividir(double dividendo, double divisor) {
        double division = dividendo / divisor;
        return division;
    }

    public static int potencia(int base, int exponente) {
        int potencia = (int) Math.pow(base, exponente);
        return potencia;
    }

    public static double radicacion(double radicando, double indice) {
        double raiz = Math.round(Math.pow(radicando, 1 / indice));
        return raiz;
    }

}
