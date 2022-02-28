/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.viewcontroller;

import com.mycompany.reto5.controller.SesionesJpaController;
import com.mycompany.reto5.controller.UsuariosJpaController;
import com.mycompany.reto5.model.Operadores;
import com.mycompany.reto5.model.Sesiones;
import com.mycompany.reto5.model.Usuarios;
import com.mycompany.reto5.util.ConnectionHelper;
import com.mycompany.reto5.view.LoginPanel;
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
public class LoginPanelController implements ActionListener, KeyListener {

    private LoginPanel miPanel = LoginPanel.getInstance();
    private static LoginPanelController instance;
    private UsuariosJpaController usuariosController = new UsuariosJpaController(ConnectionHelper.getEmf());
    private SesionesJpaController sesionesController = new SesionesJpaController(ConnectionHelper.getEmf());
    private Usuarios currentUser = new Usuarios();
    private Sesiones currentSesion = new Sesiones();

    private LoginPanelController() {
        miPanel.btnIniciarSesion.addActionListener(this);
        miPanel.btnRegresar.addActionListener(this);
        miPanel.txtUsuario.addKeyListener(this);

    }

    public static LoginPanelController getInstance() {
        if (instance == null) {
            instance = new LoginPanelController();
        }
        return instance;
    }

    public Sesiones getCurrentSesion() {
        return currentSesion;
    }

    public void iniciar() {

        new PrincipalFormController().agregarPanel(miPanel, "Menu de inicio de sesion");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(miPanel.btnIniciarSesion.getText())) {
            if (miPanel.txtUsuario.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "El campo de Usuario no puede ir vacio", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (usuariosController.findUsuarios(miPanel.txtUsuario.getText()) == null) {
                JOptionPane.showMessageDialog(null, "El usuario no existe en la base de datos", "Usuario Inexistente", JOptionPane.ERROR_MESSAGE);
            } else {
                currentUser = usuariosController.findUsuarios(miPanel.txtUsuario.getText());
                JOptionPane.showMessageDialog(null, "Bienvenid@ " + currentUser.getNombre() + " " + currentUser.getApellido(), "Sesion Iniciada", JOptionPane.INFORMATION_MESSAGE);
                currentSesion.setUsuario(currentUser);
                currentSesion.setFechaInicioSesion(java.sql.Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())));
                sesionesController.create(currentSesion);
                try {
                    Connection conn = ConnectionHelper.getConexion();
                    PreparedStatement sentencia = conn.prepareStatement(String.format("UPDATE sesiones SET fecha_inicio_sesion = '%1$s' WHERE id_sesion = %2$s", java.sql.Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())), currentSesion.getIdSesion()));
                    sentencia.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(InitPanelController.class.getName()).log(Level.SEVERE, null, ex);
                }

                InitPanelController.getInstance().iniciar();
                InitPanelController.getInstance().setLblUsuario(currentUser.getNombre(), currentUser.getApellido());
                
            }
        } else if (e.getActionCommand().equals(miPanel.btnRegresar.getText())) {
            PrincipalPanelController.getInstance().iniciar();
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
        int position = miPanel.txtUsuario.getCaretPosition();
        miPanel.txtUsuario.setText(miPanel.txtUsuario.getText().toLowerCase());
        miPanel.txtUsuario.setCaretPosition(position);
    }
}
