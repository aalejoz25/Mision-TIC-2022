/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto3;

import java.util.*;

/**
 *
 * @author alvaro
 * @since 9/07/2021
 */
public class Main {

    static long cedula;
    static String nombre;
    static String apellido;
    static int edad;
    static float peso;
    static float altura;
    static ArrayList<Persona> personas;
    static Persona persona;
    static CalculosIndiceMasaCorporal calculador = new CalculosIndiceMasaCorporal();
    static String clasificacion;
    static Scanner miScanner;
    static float imc;

    public static void main(String[] args) {
        personas = new ArrayList<>();
        miScanner = new Scanner(System.in);
        int contador = 1;

        do {
            persona = new Persona();
            if (contador == 11) {
                break;
            }
            System.out.println("Registro " + String.valueOf(contador));
            System.out.println("Ingrese cedula: ");
            cedula = miScanner.nextLong();
            if (cedula == -1) {
                break;
            }
            while (Long.toString(cedula).length() < 7 || Long.toString(cedula).length() > 10 || cedula < -1) {
                System.out.println("La cedula no esta en el rango");
                cedula = miScanner.nextLong();
                if (cedula == -1) {
                    break;
                }
            }

            miScanner.nextLine();
            System.out.println("Ingrese nombre: ");
            nombre = miScanner.nextLine();

            System.out.println("Ingrese apellido: ");
            apellido = miScanner.nextLine();
            System.out.println("Ingrese edad: ");
            edad = miScanner.nextInt();
            System.out.println("Ingrese peso: ");

            do {
                try {
                    peso = miScanner.nextFloat();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("No ha sido un float");
                    miScanner.nextLine();
                }
            } while (true);

            System.out.println("Ingrese altura: ");
            do {
                try {
                    altura = miScanner.nextFloat();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("No ha sido un float");
                    miScanner.nextLine();
                }

            } while (true);
            if (personas.isEmpty()) {
                agregarPersona();
                contador++;
            } else if (!personas.isEmpty()) {
                boolean isIn = false;
                for (Persona personaExistente : personas) {
                    if (personaExistente.getCedula() == cedula) {
                        isIn = true;
                        personaExistente.setNombre(nombre);
                        personaExistente.setApellido(apellido);
                        personaExistente.setEdad(edad);
                        personaExistente.setPeso(peso);
                        personaExistente.setAltura(altura);
                        personaExistente.calcularImc();
                        personaExistente.setClase(calculador.calcularClase(personaExistente.getImc()));
                        personaExistente.setEtapa(calculador.calcularEtapa(personaExistente.getEdad(), personaExistente.getImc()));
                        contador++;
                        break;
                    }
                }
                if (!isIn) {
                    agregarPersona();
                    contador++;
                }

            }

        } while (true);

        Collections.sort(personas);

        for (Persona persona : personas) {
            System.out.println("REG-" + Integer.toString(personas.indexOf(persona) + 1) + " -> {'cédula': " + Long.toString(persona.getCedula()) + ", 'apellido': " + "'" + persona.getApellido() + "'" + ", 'nombre': " + "'" + persona.getNombre() + "'" + ", 'edad': " + Integer.toString(persona.getEdad()) + ", 'clase': " + "'" + persona.getClase() + "'" + ", 'imc': " + Float.toString(persona.getImc()) + ", 'etapa': " + Integer.toString(persona.getEtapa()) + "}");
        }

    }

    public static void agregarPersona() {
        persona.setCedula(cedula);
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        persona.setEdad(edad);
        persona.setPeso(peso);
        persona.setAltura(altura);
        persona.calcularImc();
        persona.setClase(calculador.calcularClase(persona.getImc()));
        persona.setEtapa(calculador.calcularEtapa(persona.getEdad(), persona.getImc()));
        personas.add(persona);

    }

}
