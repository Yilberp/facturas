package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacturaVO {

    public static final double IVA = 0.19;

    private int numFactura;
    private String nomCliente;
    private Date fecha;
    private double subtotal;
    private double total;
    private List<DetalleVO> detalles;

    public FacturaVO() {
    }

    public FacturaVO(int numFactura, String nomCliente) {
        this.numFactura = numFactura;
        this.nomCliente = nomCliente;
        this.detalles = new ArrayList();
    }

    public FacturaVO(String nomCliente) {
        this.nomCliente = nomCliente;
        this.fecha = new Date();
        this.subtotal = 0;
        this.total = 0;
        this.detalles = new ArrayList();
    }

    public FacturaVO(String nomCliente, List<DetalleVO> detalles) {
        this.nomCliente = nomCliente;
        this.fecha = new Date();
        this.subtotal = 0;
        this.total = 0;
        this.detalles = detalles;
    }

    public void calcularSubtotal() {
        subtotal = 0;
        if (detalles != null) {
            detalles.stream().forEach((d) -> {
                subtotal += d.getCantidad() * d.getValor();
            });
        }
    }

    public void calcularTotal() {
        total = subtotal + subtotal * IVA;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetalleVO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVO> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Factura{" + "numFactura=" + numFactura + ", nomCliente=" + nomCliente + ", fecha=" + fecha + ", subtotal=" + subtotal + ", total=" + total + ", detalles=" + detalles + '}';
    }

}
