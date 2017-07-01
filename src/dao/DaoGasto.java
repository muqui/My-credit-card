/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Gasto;

/**
 *
 * @author mq12
 */
public class DaoGasto {
    
    private ResultSet resultSet = null;
    private Statement statement = null;
    Conexion conexion;
    
    public DaoGasto() {
        conexion = new Conexion();
    }
    
    public boolean insertar(Gasto gasto) {
        boolean res = false;
        String consulta = "insert into gasto (cantidad, fecha, descripcion) values (" + gasto.getCantidad() + ", '" + gasto.getFecha() + "', '" + gasto.getDescripcion() + "' )";
        try {
            PreparedStatement pstm = conexion.conectar().prepareStatement(consulta);
            pstm.execute();
            pstm.close();
            res = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }
    
    public List<Gasto> gastos() {
        List<Gasto> gastos = new ArrayList<>();
        try {
            statement = conexion.conectar().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM gasto order by fecha ; ");
            
            while (resultSet.next()) {
                Gasto g = new Gasto();
                g.setCantidad(new BigDecimal(resultSet.getString("cantidad")));
                g.setDescripcion(resultSet.getString("descripcion"));
                
               
                g.setFecha(resultSet.getString("fecha"));
                gastos.add(g);
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e);
        }
        return gastos;
    }
    
    public BigDecimal totalGasto(){
        BigDecimal total = null;
        try {
            statement = conexion.conectar().createStatement();
            resultSet = statement.executeQuery("select sum(cantidad) from gasto ; ");
            
            while (resultSet.next()) {
               total = new BigDecimal(resultSet.getString(1));
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e);
        }
    //select sum(cantidad) from gasto;
        System.out.println("total Gasto " +  total);
     return total;
    }
}
