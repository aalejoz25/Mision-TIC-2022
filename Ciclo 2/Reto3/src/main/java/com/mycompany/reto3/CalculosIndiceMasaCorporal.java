/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto3;

/**
 *
 * @author alvar
 */
public class CalculosIndiceMasaCorporal {

    public String calcularClase(float imc) {
        String clase = null;
        if (imc < 18.5) {
            clase = "Peso Bajo";
        } else if (18.5 <= imc && imc <= 24.9) {
            clase = "Peso Normal";
        } else if (25.0 <= imc && imc <= 30) {
            clase = "Sobrepeso";
        } else {
            clase = "Obesidad";
        }
        return clase;
    }

    public int calcularEtapa(int edad, float imc) {
        int etapa = 0;
        if (edad > 90) {
            etapa = 1;
        } else if (edad > 60 && edad <= 90) {
            etapa = 2;
        } else if (edad > 40 && edad <= 60 && imc > 25) {
            etapa = 3;
        } else {
            etapa = 4;
        }
        return etapa;
    }
}
