
import controlador.DetalleControlador;
import controlador.GastoControlador;
import controlador.PagoControlador;
import modelo.Gasto;
import modelo.Pago;
import vista.GastoVista;
import vista.PagoVista;
import vista.Principal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mq12
 */
public class Main {
    
    public static void main(String args[]){
    //Modelo
    Gasto gasto = new Gasto();
    Pago pago = new Pago();
    
    //Vista
    Principal principal = new Principal();
    GastoVista gastoVista = new GastoVista(principal, true);
    PagoVista pagoVista= new PagoVista(principal, true);
    //Controlador
    GastoControlador gastoControlador = new GastoControlador(principal, gasto, gastoVista);
    PagoControlador pagoControlador = new PagoControlador(principal, pago, pagoVista);
    DetalleControlador detalleControlador = new DetalleControlador(principal);
    
    gastoControlador.inicar();
    }
   
}
