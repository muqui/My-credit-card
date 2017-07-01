/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author mq12
 */
public class Conexion {
    String ruta;
    Connection  conexion;
    public Conexion(){
 ruta = "creditcard.db";
}
    public Connection conectar(){
        try {
             Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        try {
             conexion = DriverManager.getConnection("jdbc:sqlite:"+ruta);
   
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return conexion;
        
    }
}
