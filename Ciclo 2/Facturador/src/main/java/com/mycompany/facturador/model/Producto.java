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
public class Producto {

    private long codigo;
    private String descripcion;
    private float valor;
    private int porcentajeIva;
    private ETipoProducto tipoProducto = ETipoProducto.OTROS;

    public Producto() {
    }

    public Producto(long codigo) {
        this.codigo = codigo;
    }

    
    public Producto(long codigo, String descripcion, float valor, int porcentajeIva) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.valor = valor;
        this.porcentajeIva = porcentajeIva;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(int porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public ETipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(ETipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

  public String getInfoProducto(){
      return this.codigo + " " + this.getDescripcion();
  }  
    
}
