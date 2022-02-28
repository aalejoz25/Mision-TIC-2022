/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.facturador.model;

import java.util.Date;

/**
 *
 * @author alvar
 */
public class ProductoPerecedero extends Producto {

    private Date fechaVencimiento = null;

    public ProductoPerecedero() {
    }

    public ProductoPerecedero(long codigo) {
        super(codigo);
    }

    public ProductoPerecedero(long codigo, String descripcion, float valor, int porcentajeIva) {
        super(codigo, descripcion, valor, porcentajeIva);
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    

}
