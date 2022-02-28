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
public class DetalleFacturaNoPerecederos extends DetalleFactura {

    private ProductoNoPerecedero productoNoPerecedero = null;

    public DetalleFacturaNoPerecederos() {
    }

    public ProductoNoPerecedero getProductoNoPerecedero() {
        return productoNoPerecedero;
    }

    public void setProductoNoPerecedero(ProductoNoPerecedero productoNoPerecedero) {
        this.productoNoPerecedero = productoNoPerecedero;
    }

   

}
