/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto3;

/**
 *
 * @author alvar
 */
public class Persona implements Comparable<Persona> {

    private long cedula;
    private String nombre;
    private String apellido;
    private int edad;
    private float peso;
    private float altura;
    private float imc;
    private String clase;
    private int etapa;

    public Persona(long cedula, String nombre, String apellido, int edad, float peso, float altura) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
    }

    public Persona() {

    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public void calcularImc() {
        float indice = (float) (this.peso / Math.pow(this.altura, 2));
        float imcRedondeado = (float) (Math.round(indice * 10.0) / 10.0);
        this.imc = imcRedondeado;
    }

    @Override

    //comparar por imc
    public int compareTo(Persona o) {
        if (this.imc < o.imc) {
            return 1;
        }
        if (this.imc > o.imc) {
            return -1;
        }
        return 0;
    }

}
