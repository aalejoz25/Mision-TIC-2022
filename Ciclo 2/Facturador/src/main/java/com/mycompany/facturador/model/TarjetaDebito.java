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
public class TarjetaDebito implements FormaPagoElectronico {

    private long cedulaTitular;
    private String nombreTitular;
    private String numeroCuenta;
    private String claveTarjeta;
    private Banco banco;
    private EFranquicia franquicia;
    private String cuentaDestino;
    private ETipoCuenta tipoCuenta;

    public TarjetaDebito(long cedulaTitular, String nombreTitular, String numeroCuenta, String claveTarjeta, Banco banco, EFranquicia franquicia, String cuentaDestino, ETipoCuenta tipoCuenta) {
        this.cedulaTitular = cedulaTitular;
        this.nombreTitular = nombreTitular;
        this.numeroCuenta = numeroCuenta;
        this.claveTarjeta = claveTarjeta;
        this.banco = banco;
        this.franquicia = franquicia;
        this.cuentaDestino = cuentaDestino;
        this.tipoCuenta = tipoCuenta;
    }

    @Override
    public String getCuentaBancariaDestino() {
        return "Cuenta destino" + this.cuentaDestino;
    }

    @Override
    public String getInfoPago() {
        return "Banco: " + banco.getNombre()
                + "Cuenta:" + this.numeroCuenta;
    }

    @Override
    public void enviarPago() {
        System.out.println("pago con T Debito");
    }

    @Override
    public String getDatosParaPago(float valorPago) {
        return "Tipo de cuenta" + this.tipoCuenta
                + "Numero Cuenta: " + this.numeroCuenta
                + "Clave: " + this.claveTarjeta
                + "Cuenta destino:" + this.cuentaDestino
                + "Valor a pagar" + valorPago;

    }

}
