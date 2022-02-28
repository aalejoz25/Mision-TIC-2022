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
public enum EBase {
    DECIMAL(1, "Decimal"),
    BINARIO(2, "Binario"),
    HEXADECIMAL(3, "Hexadecimal");

    int numero;

    String descripcion;

    EBase(int numero, String descripcion) {
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public static String[] getValores() {
        String[] resultado;
        EBase[] objetos = EBase.values();
        int longitud = objetos.length;
        resultado = new String[longitud];
        for (int i = 0; i < longitud; i++) {
            resultado[i] = objetos[i].descripcion;
        }
        return resultado;
    }

    public static EBase getCodigo(String base) {
        EBase[] objetos = EBase.values();
        int longitud = objetos.length;

        for (int i = 0; i < longitud; i++) {
            if (objetos[i].descripcion.equals(base)) {
                return objetos[i];

            }

        }
        return null;

    }
}
