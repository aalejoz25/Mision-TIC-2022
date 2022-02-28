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
public class Efectivo implements FormaPagoEfectivo {

    private long cedulaTitular;
    private int caja;
    private float dineroRecibido;
    private float dineroVuelta;

    public Efectivo(long cedulaTitular, int caja, float dineroRecibido, float dineroVuelta) {
        this.cedulaTitular = cedulaTitular;
        this.caja = caja;
        this.dineroRecibido = dineroRecibido;
        this.dineroVuelta = dineroVuelta;
    }

    public long getCedulaTitular() {
        return cedulaTitular;
    }

    public void setCedulaTitular(long cedulaTitular) {
        this.cedulaTitular = cedulaTitular;
    }

    public int getCaja() {
        return caja;
    }

    public void setCaja(int caja) {
        this.caja = caja;
    }

    public float getDineroRecibido() {
        return dineroRecibido;
    }

    public void setDineroRecibido(float dineroRecibido) {
        this.dineroRecibido = dineroRecibido;
    }

    public float getDineroVuelta() {
        return dineroVuelta;
    }

    public void setDineroVuelta(float dineroVuelta) {
        this.dineroVuelta = dineroVuelta;
    }

    @Override
    public String getNumeroCajaRecibe() {
        return "Caja" + this.caja;
    }

    @Override
    public String getInfoPago() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enviarPago() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDatosParaPago(float valorPago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
