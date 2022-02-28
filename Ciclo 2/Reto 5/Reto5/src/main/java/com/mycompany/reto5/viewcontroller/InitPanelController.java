/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.viewcontroller;

import com.mycompany.reto5.controller.OperadoresJpaController;
import com.mycompany.reto5.controller.SesionesJpaController;
import com.mycompany.reto5.controller.exceptions.NonexistentEntityException;
import com.mycompany.reto5.model.Operadores;
import com.mycompany.reto5.model.Sesiones;
import com.mycompany.reto5.util.ConnectionHelper;
import com.mycompany.reto5.view.InitPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class InitPanelController implements ActionListener {

    private InitPanel miPanel = InitPanel.getInstance();
    private static InitPanelController instance;
    private SesionesJpaController sesionesController = new SesionesJpaController(ConnectionHelper.getEmf());
    private Operadores operadorActual = new Operadores();
    private OperadoresJpaController operadoresController = new OperadoresJpaController(ConnectionHelper.getEmf());

    private InitPanelController() {
        miPanel.btnSuma.addActionListener(this);
        miPanel.btnResta.addActionListener(this);
        miPanel.btnMultiplicacion.addActionListener(this);
        miPanel.btnDivision.addActionListener(this);
        miPanel.btnPotenciacion.addActionListener(this);
        miPanel.btnRadicacion.addActionListener(this);
        miPanel.btnCerrarSesion.addActionListener(this);
    }

    public static InitPanelController getInstance() {
        if (instance == null) {
            instance = new InitPanelController();
        }
        return instance;
    }

    public void iniciar() {

        new PrincipalFormController().agregarPanel(miPanel, "Menu de seleccion");
    }

    public Operadores getCurrentOperator() {
        return operadorActual;
    }

    public void setLblUsuario(String nombre, String apellido) {
        miPanel.lblUsuario.setText("Usuario: " + nombre + " " + apellido);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(miPanel.btnCerrarSesion.getText())) {
            Sesiones currentSesion = LoginPanelController.getInstance().getCurrentSesion();
            try {
                Connection conn = ConnectionHelper.getConexion();
                PreparedStatement sentencia = conn.prepareStatement(String.format("UPDATE sesiones SET fecha_cierre_sesion = '%1$s' WHERE id_sesion = %2$s", java.sql.Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())), currentSesion.getIdSesion()));
                sentencia.execute();
            } catch (SQLException ex) {
                Logger.getLogger(InitPanelController.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Ha cerrado sesion", "Cierre de sesion", JOptionPane.INFORMATION_MESSAGE);
            PrincipalPanelController.getInstance().iniciar();
        } else if (e.getActionCommand().equals(miPanel.btnSuma.getText())) {
            operadorActual = operadoresController.findOperadores(1);
            SumPanelController.getInstance().iniciar();
        } else if (e.getActionCommand().equals(miPanel.btnResta.getText())) {
            SubstractionPanelController.getInstance().iniciar();
            operadorActual = operadoresController.findOperadores(2);
        } else if (e.getActionCommand().equals(miPanel.btnMultiplicacion.getText())) {
            MultiplicationPanelController.getInstance().iniciar();
            operadorActual = operadoresController.findOperadores(3);
        } else if (e.getActionCommand().equals(miPanel.btnDivision.getText())) {
            DivisionPanelController.getInstance().iniciar();
            operadorActual = operadoresController.findOperadores(4);
        } else if (e.getActionCommand().equals(miPanel.btnPotenciacion.getText())) {
            PowPanelController.getInstance().iniciar();
            operadorActual = operadoresController.findOperadores(5);
        } else if (e.getActionCommand().equals(miPanel.btnRadicacion.getText())) {
            RootPanelController.getInstance().iniciar();
            operadorActual = operadoresController.findOperadores(6);
        }
    }

}
