package util;

import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mediador.MediadorFacturacion;
import model.DetalleVO;
import model.FacturaVO;

public class test {

    public static void main(String[] args) {

            MediadorFacturacion mf = new MediadorFacturacion();
            
            mf.listarFacturas().forEach(System.out::println);
            
            FacturaVO f = new FacturaVO();
            //f.setNumFactura(81);
            f.setNomCliente("Cristian");
            f.setSubtotal(12.2);
            f.setTotal(15.2);
            f.setDetalles(Arrays.asList());
            
           mf.crearFactura(f);
           
            mf.listarFacturas().forEach(System.out::println);
           // FacturaVO a = mf.obtenerFactura(81);
//
//        System.out.println(a);
//        a.getDetalles().get(0).setCantidad(23);
//        a.getDetalles().get(0).setArticulo("Jabon");
//        a.setNomCliente("jhon jairo");
//        
//        mf.modificarFactura(a);
//        a.getDetalles().get(0).setCantidad(23);
//        System.out.println(a);
//mf.listarFacturas().stream().forEach(System.out::println);
//mf.eliminarFactura(81);
//mf.listarFacturas().stream().forEach(System.out::println);
     

    }

}
