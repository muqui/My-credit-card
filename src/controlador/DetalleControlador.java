/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DaoGasto;
import dao.DaoPago;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import modelo.Gasto;
import modelo.Pago;
import vista.Principal;

/**
 *
 * @author mq12
 */
public class DetalleControlador implements ActionListener {

    private List<Gasto> listaGastos;
    private List<Pago> listaPagos;
    private Principal principal;

    DaoGasto daoGasto;
    DaoPago daoPago;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public DetalleControlador(Principal principal) {
        daoGasto = new DaoGasto();
        daoPago = new DaoPago();
        listaGastos = new ArrayList<>();
        listaPagos = new ArrayList<>();
        this.principal = principal;
        this.principal.jMenuItemActualizar.addActionListener(this);
        principal.jTableGasto.setModel(Gastos());
        principal.jTablePago.setModel(Pago());
        
        totalDeuda();
    }

    public DefaultTableModel Gastos() {

        listaGastos = daoGasto.gastos();

        DefaultTableModel tableModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        String[] columnNames = {"FECHA", "CANTIDAD", "DESCRIPCION"};
        tableModel.setColumnIdentifiers(columnNames);
        Object[] fila = new Object[tableModel.getColumnCount()];

        for (int i = 0; i < listaGastos.size(); i++) {
            try {
                Date date = sdf.parse(listaGastos.get(i).getFecha());
            System.out.println("formato tabla fecha " + date );
            fila[0] =""+date.getDate() + "/"+ (date.getMonth()+1) +  "/" + (1900+ date.getYear()) ;
            } catch (Exception e) {
                System.out.println("errror tabla " + e);
            }
            
//            fila[0] = listaGastos.get(i).getFecha();
            fila[1] = listaGastos.get(i).getCantidad();
            fila[2] = listaGastos.get(i).getDescripcion();

            tableModel.addRow(fila);

        }
        return tableModel;

    }

    public DefaultTableModel Pago() {

        listaPagos = daoPago.Pagos();
        DefaultTableModel tableModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        String[] columnNames = {"FECHA", "CANTIDAD", "DESCRIPCION"};
        tableModel.setColumnIdentifiers(columnNames);
        Object[] fila = new Object[tableModel.getColumnCount()];

        for (int i = 0; i < listaPagos.size(); i++) {
           
             try {
                Date date = sdf.parse(listaPagos.get(i).getFecha());
            System.out.println("formato tabla fecha " + date );
            fila[0] =""+date.getDate() + "/"+ (date.getMonth()+1) +  "/" + (1900+ date.getYear()) ;
            } catch (Exception e) {
                System.out.println("errror tabla " + e);
            }
           // String date = sdf.format();
            //fila[0] = listaPagos.get(i).getFecha();
            
            fila[1] = listaPagos.get(i).getCantidad();
            fila[2] = listaPagos.get(i).getDescripcion();

            tableModel.addRow(fila);

        }
        return tableModel;

    }

    public void totalDeuda() {
        BigDecimal totalDeuda;
        BigDecimal totalGasto;
        BigDecimal totalPago;
        if(listaGastos.isEmpty()){
        totalGasto = new BigDecimal("0");
        }
        else{
        totalGasto = daoGasto.totalGasto();
        }
            
        if(listaPagos.isEmpty()){
         totalPago = new BigDecimal("0");
        }
        else{
        totalPago = daoPago.totalPago();
        }
        totalDeuda = totalGasto;
        totalDeuda = totalDeuda.subtract(totalPago);
        principal.txtTotal.setText(totalGasto + " - " + totalPago + " = " + totalDeuda + " ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == principal.jMenuItemActualizar) {
            principal.jTableGasto.setModel(Gastos());
            principal.jTablePago.setModel(Pago());
            totalDeuda();
        }
    }
}
