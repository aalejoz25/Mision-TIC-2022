/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.facturador.controller;

import com.mycompany.facturador.model.BaseDeDatosTemporal;
import com.mycompany.facturador.model.Cliente;
import com.mycompany.facturador.view.ClienteForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alvar
 */
public class ClienteController implements ActionListener {

    private ClienteForm vista = new ClienteForm();

    public void abrirVentana() {
        vista.setTitle("formulario_de_Cliente");
        if (BaseDeDatosTemporal.clientes.size() > 0) {
            vista.txtCodigo.setText(String.valueOf(BaseDeDatosTemporal.clientes.size() + 1));

        } else {
            vista.txtCodigo.setText("1");

        }
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.btnCancelar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(vista.btnGuardar.getText())) {
            String codigo = vista.txtCodigo.getText();
            String nombre = vista.txtNombre.getText();
            String direccion = vista.txtDireccion.getText();
            String telefono = vista.txtTelefono.getText();
            String cedula = vista.txtCedula.getText();
            long codigoNumero = Long.parseLong(codigo);
            long telefonoNumero = Long.parseLong(telefono);
            long cedulaNumero = Long.parseLong(cedula);

            Cliente cliente = new Cliente(codigoNumero, nombre, direccion, telefonoNumero, cedulaNumero);
            BaseDeDatosTemporal.clientes.add(cliente);
            if (BaseDeDatosTemporal.clientes.size() > 0) {
                vista.txtCodigo.setText(String.valueOf(BaseDeDatosTemporal.clientes.size() + 1));

            } else {
                vista.txtCodigo.setText("1");

            }

            vista.txtNombre.setText("");
            vista.txtDireccion.setText("");
            vista.txtTelefono.setText("");
            vista.txtCedula.setText("");
            System.out.println(BaseDeDatosTemporal.clientes.size());
        } else if (e.getActionCommand().equals(vista.btnCancelar.getText())) {
            vista.setVisible(false);
            MenuPrincipalController.mostrarVentana();
        }
    }

}
