/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.facturador.model;

/**
 *
 * @author alvar
 */
public enum EGenero {

    MASCULINO(1, "Masculino"),
    FEMENINO(2, "Femenino"),
    OTROS(3, "Otro");

    int numero;
    String descripcion;

    EGenero(int numero, String descripcion) {
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public static String[] getValores() {
        String[] resultado;
        EGenero[] objetos = EGenero.values();
        int longitud = objetos.length;
        resultado = new String[longitud];
        for (int i = 0; i < longitud; i++) {
            resultado[i] = objetos[i].descripcion;
        }
        return resultado;
    }

    public static EGenero getCodigo(String genero) {
        EGenero[] objetos = EGenero.values();
        int longitud = objetos.length;

        for (int i = 0; i < longitud; i++) {
            if (objetos[i].descripcion.equals(genero)) {
                return objetos[i];

            }

        }
        return null;

    }

}
