/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.facturador.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author alvar
 */
public class Factura {

    private long codigo;
    private Cliente cliente;
    private ArrayList<DetalleFacturaNoPerecederos> listaDeProductosNoPerecederos = new ArrayList<>();
    private ArrayList<DetalleFacturaPerecederos> listaDeProductosPerecederos = new ArrayList<>();
    private float subtotal;
    private float iva;
    private float valorTotal;
    private Usuario usuario;
    private Date fechaRegistro = new Date();

    public Factura() {
    }

    public float getIva() {
        return iva;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /*  public void agregarProducto(Producto producto, int cantidad) {
        DetalleFactura detalle = new DetalleFactura();
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.setValorUnitario(producto.getValor());
        float valorIva = producto.getValor() * cantidad * producto.getPorcentajeIva() / 100;
        detalle.setValorIva(valorIva);
        //this.listaDeProductos.add(detalle);
    }*/
    public ArrayList<DetalleFacturaNoPerecederos> getListaDeProductosNoPerecederos() {
        return listaDeProductosNoPerecederos;
    }

    public void setListaDeProductosNoPerecederos(ArrayList<DetalleFacturaNoPerecederos> listaDeProductosNoPerecederos) {
        this.listaDeProductosNoPerecederos = listaDeProductosNoPerecederos;
    }

    public ArrayList<DetalleFacturaPerecederos> getListaDeProductosPerecederos() {
        return listaDeProductosPerecederos;
    }

    public void setListaDeProductosPerecederos(ArrayList<DetalleFacturaPerecederos> listaDeProductosPerecederos) {
        this.listaDeProductosPerecederos = listaDeProductosPerecederos;
    }

    public void agregarProducto(ProductoPerecedero productoPerecedero, ProductoNoPerecedero productoNoPerecedero, int cantidad) {
        if (productoPerecedero != null) {
            DetalleFacturaPerecederos detalle = new DetalleFacturaPerecederos();
            detalle.setProducto(productoPerecedero);
            detalle.setCantidad(cantidad);
            detalle.setValorUnitario(productoPerecedero.getValor());
            float valorIva = productoPerecedero.getValor() * cantidad * productoPerecedero.getPorcentajeIva() / 100;
            detalle.setValorIva(valorIva);
            this.listaDeProductosPerecederos.add(detalle);
        } else {
            DetalleFacturaNoPerecederos detalle = new DetalleFacturaNoPerecederos();
            detalle.setProductoNoPerecedero(productoNoPerecedero);
            detalle.setCantidad(cantidad);
            detalle.setValorUnitario(productoNoPerecedero.getValor());
            float valorIva = productoNoPerecedero.getValor() * cantidad * productoNoPerecedero.getPorcentajeIva() / 100;
            detalle.setValorIva(valorIva);
            this.listaDeProductosNoPerecederos.add(detalle);

        }
    }
}
