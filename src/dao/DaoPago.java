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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Pago;

/**
 *
 * @author mq12
 */
public class DaoPago {

    private ResultSet resultSet = null;
    private Statement statement = null;
    Conexion conexion;

    public DaoPago() {
        conexion = new Conexion();
    }

    public boolean insertar(Pago pago) {
        boolean res = false;
        String consulta = "insert into pago (cantidad, fecha, descripcion) values (" + pago.getCantidad() + ", '" + pago.getFecha() + "', '" + pago.getDescripcion() + "' )";
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

    public List<Pago> Pagos() {
        List<Pago> gastos = new ArrayList<>();
        try {
            statement = conexion.conectar().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM pago order by fecha; ");
            while (resultSet.next()) {
                Pago p = new Pago();
                p.setCantidad(new BigDecimal(resultSet.getString("cantidad")));
                p.setDescripcion(resultSet.getString("descripcion"));
             

                p.setFecha(resultSet.getString("fecha"));
                gastos.add(p);
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e);
        }
        return gastos;
    }
    public BigDecimal totalPago(){
        BigDecimal total = null;
        try {
            statement = conexion.conectar().createStatement();
            resultSet = statement.executeQuery("select sum(cantidad) from pago ; ");
            
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
