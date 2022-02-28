/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.facturador.controller;

import com.mycompany.facturador.model.BaseDeDatosTemporal;
import com.mycompany.facturador.view.ProductoForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.facturador.model.Producto;

/**
 *
 * @author alvar
 */
public class ProductoController implements ActionListener {

    private ProductoForm vista = new ProductoForm();

    public void abrirVentana() {
        vista.setTitle("formulario_de_Producto");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.btnCancelar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        long codigo = BaseDeDatosTemporal.productos.size() + 1;
        vista.txtCodigo.setText(String.valueOf(codigo));
        vista.txtCodigo.setEnabled(false);
       

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(vista.btnGuardar.getText())) {
            String codigo = vista.txtCodigo.getText();
            String descripcion = vista.txtDescripcion.getText();
            String porcetajeIva = vista.txtPorcentajeIva.getText();
            String valor = vista.txtValor.getText();
            long codigoNumero = Long.parseLong(codigo);
            float valorNumero = Float.parseFloat(valor);
            int porcentajeNUmero = Integer.parseInt(porcetajeIva);

            Producto producto = new Producto(codigoNumero, descripcion, valorNumero, porcentajeNUmero);
            BaseDeDatosTemporal.productos.add(producto);
            System.out.println(BaseDeDatosTemporal.productos.size());
        } else if (e.getActionCommand().equals(vista.btnCancelar.getText())) {
            vista.setVisible(false);
            MenuPrincipalController.mostrarVentana();
        }

    }

}
