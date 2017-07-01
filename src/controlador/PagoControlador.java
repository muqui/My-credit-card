/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DaoPago;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import modelo.Pago;
import vista.PagoVista;
import vista.Principal;

/**
 *
 * @author mq12
 */
public class PagoControlador implements ActionListener{
    private Principal principal;
    private Pago pago;
    private PagoVista pagoVista;
    public PagoControlador(Principal principal, Pago pago, PagoVista pagoVista) {
       this.principal = principal;
       this.pago = pago;
       this.pagoVista = pagoVista;
       pagoVista.jButtonPago.addActionListener(this);
       principal.jMenuItemPago.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == pagoVista.jButtonPago){
         insertar();
        }
        if(e.getSource() == principal.jMenuItemPago){
         pagoVista.setVisible(true);
        }
    }
     public void insertar(){
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DaoPago daoPago = new DaoPago();
        try {
            pago.setCantidad(new BigDecimal(pagoVista.txtPago.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" +  e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        pago.setDescripcion(pagoVista.txtDescripcionPago.getText());
           String fecha =  sdf.format(pagoVista.jDateChooserPago.getDate());
        pago.setFecha(fecha);
        if(daoPago.insertar(pago)){
            JOptionPane.showMessageDialog(null, "Registro insertado", "Exito", JOptionPane.INFORMATION_MESSAGE);
            pagoVista.txtDescripcionPago.setText("");
            pagoVista.txtPago.setText("");
        }
        else{
             JOptionPane.showMessageDialog(null, "no se pudo insertar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
