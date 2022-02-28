/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.viewcontroller;

import com.mycompany.reto5.controller.BaseJpaController;
import com.mycompany.reto5.controller.OperacionesJpaController;
import com.mycompany.reto5.controller.OperandosJpaController;
import com.mycompany.reto5.model.Binario;
import com.mycompany.reto5.model.Decimal;
import com.mycompany.reto5.model.EBase;
import com.mycompany.reto5.model.Hexadecimal;
import com.mycompany.reto5.model.Operaciones;
import com.mycompany.reto5.model.Operandos;
import com.mycompany.reto5.util.ConnectionHelper;
import com.mycompany.reto5.view.DivisionPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author alvar
 */
public class DivisionPanelController implements ActionListener {

    private DivisionPanel miPanel = DivisionPanel.getInstance();
    private static DivisionPanelController instance;
    private Operaciones operacion = new Operaciones();
    private Operandos operando = new Operandos();
    private OperacionesJpaController operacionesController = new OperacionesJpaController(ConnectionHelper.getEmf());
    private BaseJpaController baseController = new BaseJpaController(ConnectionHelper.getEmf());
    private OperandosJpaController operandosController = new OperandosJpaController(ConnectionHelper.getEmf());

    private DivisionPanelController() {
        miPanel.btnDividir.addActionListener(this);
        miPanel.btnRegresar.addActionListener(this);
        miPanel.cbxBase.setModel(new DefaultComboBoxModel<>(EBase.getValores()));
    }

    public static DivisionPanelController getInstance() {
        if (instance == null) {
            instance = new DivisionPanelController();
        }
        return instance;
    }

    public void iniciar() {
        miPanel.txtDividendo.setText("");
        miPanel.txtDivisor.setText("");
        miPanel.lblResultado.setText("");
        new PrincipalFormController().agregarPanel(miPanel, "Division");

    }

    public void guardarOperacion(String resultado, int base) {
        operacion.setOperadores(InitPanelController.getInstance().getCurrentOperator());
        operacion.setBase(baseController.findBase(base));
        operacion.setResultado(resultado);
        operacion.setFechaOperacion(java.sql.Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())));
        operacion.setSesion(LoginPanelController.getInstance().getCurrentSesion());
        operacionesController.create(operacion);
        try {
            Connection conn = ConnectionHelper.getConexion();
            PreparedStatement sentencia = conn.prepareStatement(String.format("UPDATE operaciones SET fecha_operacion = '%1$s' WHERE id_operacion = '%2$s'", java.sql.Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())), operacion.getIdOperacion()));
            sentencia.execute();
        } catch (SQLException ex) {
            Logger.getLogger(InitPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarOperandos(String[] operandos) {
        for (String p : operandos) {
            operando.setIdOperacion(operacion);
            operando.setOperando(p);
            operandosController.create(operando);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(miPanel.btnRegresar.getText())) {
            InitPanelController.getInstance().iniciar();
        } //division decimal
        else if (e.getActionCommand().equals(miPanel.btnDividir.getText()) && miPanel.cbxBase.getSelectedItem() == miPanel.cbxBase.getItemAt(0)) {

            try {

                double dividendo = Double.parseDouble(miPanel.txtDividendo.getText());
                double divisor = Double.parseDouble(miPanel.txtDivisor.getText());

                String resultado = String.valueOf(Decimal.dividir(dividendo, divisor));
                miPanel.lblResultado.setText("Resultado: " + resultado);

                String numsArr[] = new String[2];
                numsArr[0] = miPanel.txtDividendo.getText();
                numsArr[1] = miPanel.txtDivisor.getText();

                guardarOperacion(resultado, 1);
                guardarOperandos(numsArr);

            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Entrada no permitida", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } //division binaria 
        else if (e.getActionCommand().equals(miPanel.btnDividir.getText()) && miPanel.cbxBase.getSelectedItem() == miPanel.cbxBase.getItemAt(1)) {
            try {

                String resultado = Binario.dividir(miPanel.txtDividendo.getText(), miPanel.txtDivisor.getText());

                miPanel.lblResultado.setText("Resultado: " + resultado);

                String numsArr[] = new String[2];
                numsArr[0] = miPanel.txtDividendo.getText();
                numsArr[1] = miPanel.txtDivisor.getText();

                guardarOperacion(resultado, 2);
                guardarOperandos(numsArr);

            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Entrada no permitida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } // Division hexadecimal
        else if (e.getActionCommand().equals(miPanel.btnDividir.getText()) && miPanel.cbxBase.getSelectedItem() == miPanel.cbxBase.getItemAt(2)) {
            try {

                String resultado = Hexadecimal.dividir(miPanel.txtDividendo.getText(), miPanel.txtDivisor.getText());

                miPanel.lblResultado.setText("Resultado: " + resultado);

                String numsArr[] = new String[2];
                numsArr[0] = miPanel.txtDividendo.getText();
                numsArr[1] = miPanel.txtDivisor.getText();

                guardarOperacion(resultado, 3);
                guardarOperandos(numsArr);

            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Entrada no permitida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
