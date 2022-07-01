
package model;

public class DetalleVO {
    
    private int id;
    private String articulo;
    private double valor;
    private int cantidad;
    private int numFactura;

    public DetalleVO() {
    }

    public DetalleVO(String articulo, double valor, int cantidad) {
        this.articulo = articulo;
        this.valor = valor;
        this.cantidad = cantidad;
    }
    
    public DetalleVO(int id, String articulo, double valor, int cantidad) {
        this.id = id;
        this.articulo = articulo;
        this.valor = valor;
        this.cantidad = cantidad;
    }

    public DetalleVO(int id, String articulo, double valor, int cantidad, int numFactura) {
        this.id = id;
        this.articulo = articulo;
        this.valor = valor;
        this.cantidad = cantidad;
        this.numFactura = numFactura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    @Override
    public String toString() {
        return "DetalleVO{" + "id=" + id + ", articulo=" + articulo + ", valor=" + valor + ", cantidad=" + cantidad + ", numFactura=" + numFactura + '}';
    }
    
}
