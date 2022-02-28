/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alvar
 */
public class ConnectionHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/reto_5";
    private static final String USUARIO = "root";
    private static final String CLAVE = "root";
    private static Connection conexion;
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEmf() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("persistenceUnit");
        }
        return emf;
    }

    public static Connection getConexion() {
        if (ConnectionHelper.conexion == null) {
            try {
                ConnectionHelper.conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ConnectionHelper.conexion;
    }
}
