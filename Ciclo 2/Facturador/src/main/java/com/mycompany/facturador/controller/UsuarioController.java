/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.facturador.controller;

import com.mycompany.facturador.model.BaseDeDatosTemporal;
import com.mycompany.facturador.model.EGenero;
import com.mycompany.facturador.model.ETipoUsuario;
import com.mycompany.facturador.model.Usuario;
import com.mycompany.facturador.view.UsuarioForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author alvar
 */
public class UsuarioController implements ActionListener {

    UsuarioForm vista = new UsuarioForm();

    public void iniciarVentana() {
        vista.setTitle("Vista usuario");
        vista.setLocationRelativeTo(null);
        vista.cbGenero.setModel(new DefaultComboBoxModel<>(EGenero.getValores()));
        vista.cbTipoUsuario.setModel(new DefaultComboBoxModel<>(ETipoUsuario.getValores()));
        vista.btnCancelar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.setVisible(true);

        long codigo = BaseDeDatosTemporal.usuarios.size() + 1;
        vista.txtCodigo.setText(String.valueOf(codigo));
        vista.txtCodigo.setEnabled(false);
    }

    @Override

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(vista.btnCancelar.getText())) {
            vista.setVisible(false);
            MenuPrincipalController.mostrarVentana();
        } else if (e.getActionCommand().equals(vista.btnGuardar.getText())) {
            String codigo = vista.txtCodigo.getText();
            String nombreCompleto = vista.txtNombre.getText();
            String usuario = vista.txtUsuario.getText();
            String clave = vista.txtClave.getText();
            String genero = vista.cbGenero.getItemAt(vista.cbGenero.getSelectedIndex());
            EGenero codigoGenero = EGenero.getCodigo(genero);
            String tipoUsuario = vista.cbTipoUsuario.getItemAt(vista.cbTipoUsuario.getSelectedIndex());
            ETipoUsuario codigoTipoUsuario = ETipoUsuario.getCodigo(tipoUsuario);

            long codigoNumero = Long.parseLong(codigo);
            Usuario user = new Usuario(codigoNumero, nombreCompleto, usuario, clave, codigoGenero, codigoTipoUsuario);
            BaseDeDatosTemporal.usuarios.add(user);
            System.out.println("guardado");
            System.out.println(BaseDeDatosTemporal.usuarios.size());
        }
    }

}
