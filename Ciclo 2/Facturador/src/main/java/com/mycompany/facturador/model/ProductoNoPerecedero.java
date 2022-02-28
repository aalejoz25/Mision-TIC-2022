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
public class ProductoNoPerecedero extends Producto {

    ETipoAlmacenamiento tipoAlmacenamiento = ETipoAlmacenamiento.ALMACEN;

    public ProductoNoPerecedero() {
    }

    public ETipoAlmacenamiento getTipoAlmacenamiento() {
        return tipoAlmacenamiento;
    }

    public void setTipoAlmacenamiento(ETipoAlmacenamiento tipoAlmacenamiento) {
        this.tipoAlmacenamiento = tipoAlmacenamiento;
    }

    @Override
    public String getInfoProducto() {
        return "NP" + super.getInfoProducto(); //To change body of generated methods, choose Tools | Templates.
    }

}
