package mediador;

import dao.IDetalleDAO;
import dao.impl.FacturaDAO;
import java.util.List;
import model.FacturaVO;
import dao.impl.DetalleDAO;
import java.sql.SQLException;
import util.Conexion;
import dao.IFacturaDAO;
import java.util.Date;

public class MediadorFacturacion {

    public FacturaVO crearFactura(FacturaVO factura) {
        Conexion con = new Conexion();
        IFacturaDAO facturaDao = new FacturaDAO(con);
        IDetalleDAO detalleDao = new DetalleDAO(con);
        try {
            con.setAutoCommit(false);
            factura.calcularSubtotal();
            factura.calcularTotal();
            factura.setFecha(new Date());
            facturaDao.insert(factura);
            factura.getDetalles().stream().forEach((d) -> {
                d.setNumFactura(factura.getNumFactura());
                detalleDao.insert(d);
            });
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("Error al crear factura " + factura + " " + ex.getMessage());
        } finally {
            con.close();
        }
        return factura;
    }

    public boolean modificarFactura(FacturaVO factura) {
        boolean modificado = false;

        Conexion con = new Conexion();
        IFacturaDAO facturaDao = new FacturaDAO(con);
        IDetalleDAO detalleDao = new DetalleDAO(con);

        try {

            con.setAutoCommit(false);

            if (factura.getDetalles() != null) {
                factura.getDetalles().stream().forEach((d) -> {
                    detalleDao.update(d);
                });
            }
            
            factura.calcularSubtotal();
            factura.calcularTotal();
            factura.setFecha(new Date());
            
            modificado = facturaDao.update(factura);

            con.commit();

        } catch (SQLException ex) {
            con.rollback();
            System.out.println("Error al crear factura " + factura + " " + ex.getMessage());
        } finally {
            con.close();
        }
        return modificado;
    }

    public boolean eliminarFactura(int numFactura) {
        boolean eliminado = false;
        Conexion con = new Conexion();
        IFacturaDAO facturaDao = new FacturaDAO(con);
        try {
            con.setAutoCommit(false);
            eliminado = facturaDao.delete(numFactura);
            con.commit();
            return eliminado;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar factura #" + numFactura + " " + ex.getMessage());
        } finally {
            con.close();
        }
        return eliminado;
    }

    public List<FacturaVO> listarFacturas() {
        List<FacturaVO> facturas = null;
        Conexion con = new Conexion();
        IFacturaDAO facturaDao = new FacturaDAO(con);
        try {
            con.setAutoCommit(false);
            facturas = facturaDao.getAll();
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("Error al listar facturas " + ex.getMessage());
        } finally {
            con.close();
        }
        return facturas;
    }

    public FacturaVO obtenerFactura(int numFactura) {
        FacturaVO factura = null;
        Conexion con = new Conexion();
        IFacturaDAO facturaDao = new FacturaDAO(con);
        IDetalleDAO detalleDao = new DetalleDAO(con);
        try {
            con.setAutoCommit(false);
            factura = facturaDao.getById(numFactura);
            if (factura != null) {
                factura.setDetalles(detalleDao.getByIdFactura(factura.getNumFactura()));
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("Error al obtener factura #" + numFactura + " " + ex.getMessage());
        }
        return factura;
    }

}
