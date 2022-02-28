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
public class TarjetaCredito implements FormaPagoElectronico {

    private long cedulaTitular;
    private String nombreTitular;
    private String numeroTarjeta;
    private Banco banco;
    private EFranquicia franquicia;
    private String fechaVencimiento;
    private String cvc;
    private String cuentaDestino;

    public TarjetaCredito(long cedulaTitular, String nombreTitular, String numeroTarjeta, Banco banco, EFranquicia franquicia, String cvc) {
        this.cedulaTitular = cedulaTitular;
        this.nombreTitular = nombreTitular;
        this.numeroTarjeta = numeroTarjeta;
        this.banco = banco;
        this.franquicia = franquicia;
        this.cvc = cvc;
    }

    public TarjetaCredito() {
    }

    @Override
    public String getInfoPago() {
        return "Banco: " + banco.getNombre() + ", Numero tarjeta:" + this.numeroTarjeta;
    }

    @Override
    public void enviarPago() {
        System.out.println("Enviar pago on TDC");
    }

    @Override
    public String getDatosParaPago(float valorPago) {
        return "Banco:" + this.banco.getNombre()
                + ", Numero de Tarjeta: " + this.numeroTarjeta
                + ", Fecha de Vencimiento: " + this.fechaVencimiento
                + ", CVC" + this.cvc
                + ", Valor del Pago" + valorPago;
    }

    public long getCedulaTitular() {
        return cedulaTitular;
    }

    public void setCedulaTitular(long cedulaTitular) {
        this.cedulaTitular = cedulaTitular;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public EFranquicia getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(EFranquicia franquicia) {
        this.franquicia = franquicia;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    @Override
    public String getCuentaBancariaDestino() {
        return cuentaDestino;
    }

}
