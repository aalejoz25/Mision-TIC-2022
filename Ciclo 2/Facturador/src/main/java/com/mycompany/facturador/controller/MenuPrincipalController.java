/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.facturador.controller;

import com.mycompany.facturador.view.MenuPrincipal;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alvar
 */
public class MenuPrincipalController implements ActionListener {

    private static MenuPrincipal vista = new MenuPrincipal();

    public void abrirVentana() {
        vista.setTitle("Menu Principal");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.btnCrearCliente.addActionListener(this);
        vista.btnCrearProducto.addActionListener(this);
        vista.btnCrearUsuario.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(vista.btnCrearCliente.getText())) {
            ClienteController cController = new ClienteController();
            cController.abrirVentana();
            vista.setVisible(false);
        } else if (e.getActionCommand().equals(vista.btnCrearProducto.getText())) {
            ProductoController pController = new ProductoController();
            pController.abrirVentana();
            vista.setVisible(false);
        } else if (e.getActionCommand().equals(vista.btnCrearUsuario.getText())) {
            System.out.println("algo");
            UsuarioController uController = new UsuarioController();
            uController.iniciarVentana();
            vista.setVisible(false);
        }
    }

    public static void mostrarVentana() {
        vista.setVisible(true);
    }

}
