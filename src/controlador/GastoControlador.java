/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import dao.DaoGasto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Gasto;
import vista.GastoVista;
import vista.Principal;

/**
 *
 * @author mq12
 */
public class GastoControlador  implements ActionListener{
private Principal principal;
private Gasto gasto;
private GastoVista gastoVista;
    public GastoControlador(Principal principal, Gasto gasto, GastoVista gastoVista) {
        this.gasto = gasto;
        this.principal = principal;
        this.gastoVista = gastoVista;
        gastoVista.jButtonGasto.addActionListener(this);
        principal.jMenuItemGasto.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == gastoVista.jButtonGasto){
        insertar();
       }
       if(e.getSource() == principal.jMenuItemGasto){
         gastoVista.setVisible(true);
       }
    }
    public void inicar(){
        principal.setVisible(true);
      
    
    }
    public void insertar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DaoGasto daoGasto = new DaoGasto();
        try {
            gasto.setCantidad(new BigDecimal(gastoVista.txtGasto.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" +  e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        gasto.setDescripcion(gastoVista.txtDescripcion.getText());
        //fecha
       
            String fecha =  sdf.format(gastoVista.jDateChooserGasto.getDate());
        gasto.setFecha(fecha);
        if(daoGasto.insertar(gasto)){
            JOptionPane.showMessageDialog(null, "Registro insertado", "Exito", JOptionPane.INFORMATION_MESSAGE);
            gastoVista.txtDescripcion.setText("");
            gastoVista.txtGasto.setText("");
        }
        else{
            JOptionPane.showMessageDialog(null, "no se pudo insertar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
