/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.viewcontroller;

import com.mycompany.reto5.controller.UsuariosJpaController;
import com.mycompany.reto5.model.Usuarios;
import com.mycompany.reto5.util.ConnectionHelper;
import com.mycompany.reto5.view.RegisterPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author alvar
 */
public class RegisterPanelController implements ActionListener, KeyListener {

    RegisterPanel miPanel = RegisterPanel.getInstance();
    private static RegisterPanelController instance;
    UsuariosJpaController usuariosController = new UsuariosJpaController(ConnectionHelper.getEmf());
    Usuarios usuario = new Usuarios();

    private RegisterPanelController() {

        miPanel.btnRegresar.addActionListener(this);
        miPanel.btnRegistrar.addActionListener(this);
        miPanel.txtUsername.addKeyListener(this);
    }

    public static RegisterPanelController getInstance() {
        if (instance == null) {
            instance = new RegisterPanelController();
        }
        return instance;
    }

    public void iniciar() {

        new PrincipalFormController().agregarPanel(miPanel, "Menu de registro");
    }

    public boolean validarDatos() {

        boolean isValid = true;

        if (miPanel.txtUsername.getText().equals("") || miPanel.txtNombre.getText().equals("") || miPanel.txtApellido.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ningun campo puede ir vacio", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        } else if (usuariosController.findUsuarios(miPanel.txtUsername.getText()) != null) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        }
        return isValid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(miPanel.btnRegresar.getText())) {
            PrincipalPanelController.getInstance().iniciar();

        } else if (e.getActionCommand().equals(miPanel.btnRegistrar.getText())) {

            boolean valido = validarDatos();
            if (valido) {
                usuario.setUsuario(miPanel.txtUsername.getText());
                usuario.setNombre(miPanel.txtNombre.getText());
                usuario.setApellido(miPanel.txtApellido.getText());
                usuario.setFechaRegistro(java.sql.Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())));

                try {
                    usuariosController.create(usuario);
                    try {
                        Connection conn = ConnectionHelper.getConexion();
                        PreparedStatement sentencia = conn.prepareStatement(String.format("UPDATE usuarios SET fecha_registro = '%1$s' WHERE usuario = '%2$s'", java.sql.Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())), usuario.getUsuario()));
                        sentencia.execute();

                    } catch (SQLException ex) {
                        Logger.getLogger(InitPanelController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("creado");
                    JOptionPane.showMessageDialog(null, "Se ha registrado el usuario", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    Logger.getLogger(RegisterPanelController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            miPanel.txtUsername.setText("");
            miPanel.txtNombre.setText("");
            miPanel.txtApellido.setText("");

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int position = miPanel.txtUsername.getCaretPosition();
        miPanel.txtUsername.setText(miPanel.txtUsername.getText().toLowerCase());
        miPanel.txtUsername.setCaretPosition(position);
    }
}
