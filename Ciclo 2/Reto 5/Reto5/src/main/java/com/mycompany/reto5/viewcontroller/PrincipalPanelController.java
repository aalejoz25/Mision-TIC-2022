/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.viewcontroller;

import com.mycompany.reto5.view.PrincipalForm;
import com.mycompany.reto5.view.PrincipalPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alvar
 */
public class PrincipalPanelController implements ActionListener {

    PrincipalPanel miPanel = PrincipalPanel.getInstance();
    private static PrincipalPanelController instance;

    private PrincipalPanelController() {
        miPanel.btnSalir.addActionListener(this);
        miPanel.btnRegistrarUsuario.addActionListener(this);
        miPanel.btnIniciarSesion.addActionListener(this);
    }

    public static PrincipalPanelController getInstance() {
        if (instance == null) {
            instance = new PrincipalPanelController();
        }
        return instance;
    }

    public void iniciar() {

        new PrincipalFormController().agregarPanel(miPanel, "Menu principal");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(miPanel.btnSalir.getText())) {
            PrincipalForm.getInstance().dispose();
        } else if (e.getActionCommand().equals(miPanel.btnRegistrarUsuario.getText())) {
            RegisterPanelController.getInstance().iniciar();
        } else if (e.getActionCommand().equals(miPanel.btnIniciarSesion.getText())) {
            LoginPanelController.getInstance().iniciar();
        }
    }

}
