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
public enum ETipoProducto {

    LACTEOS(1, "Lacteos"),
    FRUTAS_Y_VERDURAS(2, "Frutas y Verduras"),
    PANADERIA(3, "Panaderia"),
    OTROS(4, "Otros");

    int id;
    String descripcion;

    ETipoProducto(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

}
