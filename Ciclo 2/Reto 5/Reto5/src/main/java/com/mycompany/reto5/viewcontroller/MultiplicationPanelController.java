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
import com.mycompany.reto5.view.MultiplicationPanel;
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
public class MultiplicationPanelController implements ActionListener {

    private MultiplicationPanel miPanel = MultiplicationPanel.getInstance();
    private static MultiplicationPanelController instance;
    private Operaciones operacion = new Operaciones();
    private Operandos operando = new Operandos();
    private OperacionesJpaController operacionesController = new OperacionesJpaController(ConnectionHelper.getEmf());
    private BaseJpaController baseController = new BaseJpaController(ConnectionHelper.getEmf());
    private OperandosJpaController operandosController = new OperandosJpaController(ConnectionHelper.getEmf());

    private MultiplicationPanelController() {
        miPanel.btnMultiplicar.addActionListener(this);
        miPanel.btnRegresar.addActionListener(this);
        miPanel.cbxBase.setModel(new DefaultComboBoxModel<>(EBase.getValores()));
    }

    public static MultiplicationPanelController getInstance() {
        if (instance == null) {
            instance = new MultiplicationPanelController();
        }
        return instance;
    }

    public void iniciar() {
        miPanel.txtNumeros.setText("");
        miPanel.lblResultado.setText("");
        new PrincipalFormController().agregarPanel(miPanel, "Multiplicacion");

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
        } //multiplicacion decimal
        else if (e.getActionCommand().equals(miPanel.btnMultiplicar.getText()) && miPanel.cbxBase.getSelectedItem() == miPanel.cbxBase.getItemAt(0)) {

            try {
                String numeros = miPanel.txtNumeros.getText();
                String[] numsArr = numeros.split(" ");
                int[] nums = new int[numsArr.length];
                for (int i = 0; i < numsArr.length; i++) {
                    nums[i] = Integer.parseInt(numsArr[i]);

                }
                String resultado = String.valueOf(Decimal.multiplicar(nums));
                miPanel.lblResultado.setText("Resultado: " + resultado);

                guardarOperacion(resultado, 1);
                guardarOperandos(numsArr);

            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Entrada no permitida", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } //Multiplicacion binaria 
        else if (e.getActionCommand().equals(miPanel.btnMultiplicar.getText()) && miPanel.cbxBase.getSelectedItem() == miPanel.cbxBase.getItemAt(1)) {
            try {

                String numeros = miPanel.txtNumeros.getText();
                String[] numsArr = numeros.split(" ");
                String resultado = Binario.multiplicar(numsArr);

                miPanel.lblResultado.setText("Resultado: " + String.valueOf(resultado));

                guardarOperacion(resultado, 2);
                guardarOperandos(numsArr);

            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Entrada no permitida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } // Multiplicacion hexadecimal
        else if (e.getActionCommand().equals(miPanel.btnMultiplicar.getText()) && miPanel.cbxBase.getSelectedItem() == miPanel.cbxBase.getItemAt(2)) {
            try {

                String numeros = miPanel.txtNumeros.getText();
                String[] numsArr = numeros.split(" ");
                String resultado = Hexadecimal.multiplicar(numsArr);

                miPanel.lblResultado.setText("Resultado: " + String.valueOf(resultado));

                guardarOperacion(resultado, 3);
                guardarOperandos(numsArr);

            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Entrada no permitida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
