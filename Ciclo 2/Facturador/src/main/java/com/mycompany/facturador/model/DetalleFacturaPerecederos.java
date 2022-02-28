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
public class DetalleFacturaPerecederos extends DetalleFactura {
    private ProductoPerecedero producto = null;

    public DetalleFacturaPerecederos() {
    }

    public ProductoPerecedero getProducto() {
        return producto;
    }

    public void setProducto(ProductoPerecedero producto) {
        this.producto = producto;
    }
    
    
    
}
