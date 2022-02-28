/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.facturador;

import com.mycompany.facturador.controller.MenuPrincipalController;
import com.mycompany.facturador.controller.ProductoController;
import com.mycompany.facturador.view.ProductoForm;

/**
 *
 * @author alvar
 */
public class App {

    public static void main(String[] args) {
        MenuPrincipalController menu = new MenuPrincipalController();
        menu.abrirVentana();

    }
}
