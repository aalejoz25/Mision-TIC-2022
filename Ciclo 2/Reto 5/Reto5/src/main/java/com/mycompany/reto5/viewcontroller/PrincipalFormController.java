/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.viewcontroller;

import com.mycompany.reto5.view.PrincipalForm;
import com.mycompany.reto5.view.PrincipalPanel;
import javax.swing.JPanel;

/**
 *
 * @author alvar
 */
public class PrincipalFormController {

    public void abrirForm() {
        PrincipalForm.getInstance().setLocationRelativeTo(null);
        PrincipalPanelController.getInstance().iniciar();
        PrincipalForm.getInstance().setVisible(true);
    }

    public void agregarPanel(JPanel panel, String title) {
        PrincipalForm.getInstance().setContentPane(panel);
        PrincipalForm.getInstance().setTitle(title);
    }
}
