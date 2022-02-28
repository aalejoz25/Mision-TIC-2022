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
public interface FormaPago {
    
    public abstract String getInfoPago();
    public abstract void enviarPago();
    public abstract String getDatosParaPago(float valorPago);
    
    
    
}
