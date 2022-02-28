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
public class Usuario {

    private long codigo;
    private String nombreCompleto;
    private String usuario;
    private String clave;
    private EGenero genero;
    private ETipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(long codigo, String nombreCompleto, String usuario, String clave, EGenero codigoGenero, ETipoUsuario codigoTipoUsuario) {
        this.codigo = codigo;
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.clave = clave;
        this.genero = codigoGenero;
        this.tipoUsuario = codigoTipoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    private void generarClave() {
        String[] lista = this.nombreCompleto.split(" ");
        String nombre2 = lista[1];
        int longitud = nombre2.length();
        String nombreArreglado = "";
        for (int i = longitud; i > 0; i--) {
            char letra = nombre2.charAt(i);
            nombreArreglado = nombreArreglado + String.valueOf(letra);

        }

        this.clave = nombreArreglado;

    }

    public boolean validarClave(String usuario, String clave) {
        /* if (usuario.equalsIgnoreCase(this.usuario) && clave.equals(this.clave)) {
            return true;
        }else{
            return false;
        }*/
        boolean resultado = (usuario.equalsIgnoreCase(this.usuario) && clave.equals(this.clave));
        return resultado;
    }

}
