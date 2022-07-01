
package fachada;

import java.util.List;
import mediador.MediadorFacturacion;
import model.FacturaVO;

public class FachadaFacturacion {
    
    private final MediadorFacturacion mediadorFacturacion;

    public FachadaFacturacion() {
        mediadorFacturacion = new MediadorFacturacion();
    }
    
    public FacturaVO crearFactura(FacturaVO factura){
        return mediadorFacturacion.crearFactura(factura);
    }
    
    public FacturaVO obtenerFactura(int numFactura){
         return mediadorFacturacion.obtenerFactura(numFactura);
    }
    
    public List<FacturaVO> ListarFacturas(){
        return mediadorFacturacion.listarFacturas();
    }
    
    public boolean eliminarFactura(int numFactura){
        return mediadorFacturacion.eliminarFactura(numFactura);
    }
    
    public boolean actualizarFactura(FacturaVO factura){
        return mediadorFacturacion.modificarFactura(factura);
    }
    
}