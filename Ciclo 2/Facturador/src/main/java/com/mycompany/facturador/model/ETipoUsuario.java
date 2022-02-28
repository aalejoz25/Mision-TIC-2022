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
public enum ETipoUsuario {

    ADMINISTRADOR(1, "Administrador"),
    CAJERO(2, "Cajero");

    int id;
    String descripcion;

    ETipoUsuario(int numero, String descripcion) {
        this.id = numero;
        this.descripcion = descripcion;
    }

    public static String[] getValores() {
        String[] resultado;
        ETipoUsuario[] objetos = ETipoUsuario.values();
        int longitud = objetos.length;
        resultado = new String[longitud];
        for (int i = 0; i < longitud; i++) {
            resultado[i] = objetos[i].descripcion;
        }
        return resultado;

    }

    public static ETipoUsuario getCodigo(String usuario) {
        ETipoUsuario[] objetos = ETipoUsuario.values();
        int longitud = objetos.length;

        for (int i = 0; i < longitud; i++) {
            if (objetos[i].descripcion.equals(usuario)) {
                return objetos[i];

            }

        }
        return null;

    }

}
