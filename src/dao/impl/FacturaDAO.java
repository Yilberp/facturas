package dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.FacturaVO;
import util.Conexion;
import dao.IFacturaDAO;

public class FacturaDAO implements IFacturaDAO {

    private static final String INSERT_FACTURA = "INSERT INTO factura (nom_cliente, fecha, subtotal, total) VALUES (?, ?, ?, ?);";
    private static final String SELECT_FACTURA = "SELECT * FROM factura WHERE num_factura = ?";
    private static final String SELECT_ALL_FACTURAS = "SELECT * FROM factura";
    private static final String DELETE_FACTURA = "DELETE FROM factura WHERE num_factura = ?;";
    private static final String UPDATE_FACTURA = "UPDATE factura SET nom_cliente = ?, fecha = ?, subtotal = ?, total = ? WHERE num_factura = ?;";

    private final Conexion con;

    public FacturaDAO(Conexion con) {
        this.con = con;
    }

    @Override
    public FacturaVO getById(Integer id) {

        FacturaVO factura = null;

        try (PreparedStatement preparedStatement = con.preparedStatement(SELECT_FACTURA)) {

            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    factura = new FacturaVO();
                    factura.setNumFactura(rs.getInt("num_factura"));
                    factura.setNomCliente(rs.getString("nom_cliente"));
                    factura.setFecha(rs.getDate("fecha"));
                    factura.setSubtotal(rs.getDouble("subtotal"));
                    factura.setTotal(rs.getDouble("total"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("No se pudo obtener la factura #" + factura + " " + ex.getMessage());
        }
        return factura;
    }

    @Override
    public boolean insert(FacturaVO factura) {
        boolean insertado = false;
        try (PreparedStatement preparedStatement = con.preparedStatement(INSERT_FACTURA, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, factura.getNomCliente());
            preparedStatement.setDate(2, new Date(factura.getFecha().getTime()));
            preparedStatement.setDouble(3, factura.getSubtotal());
            preparedStatement.setDouble(4, factura.getTotal());
            insertado = preparedStatement.executeUpdate() > 0;

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                factura.setNumFactura(generatedKeys.getInt(1));
            }

        } catch (SQLException ex) {
            System.out.println("No se pudo insertar la factura " + factura + " " + ex.getMessage());
        }
        return insertado;
    }

    @Override
    public boolean update(FacturaVO factura) {
        boolean actualizado = false;
        try (PreparedStatement preparedStatement = con.preparedStatement(UPDATE_FACTURA)) {
            preparedStatement.setString(1, factura.getNomCliente());
            preparedStatement.setDate(2, new Date(factura.getFecha().getTime()));
            preparedStatement.setDouble(3, factura.getSubtotal());
            preparedStatement.setDouble(4, factura.getTotal());
            preparedStatement.setInt(5, factura.getNumFactura());
            actualizado = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la factura  " + factura + " " + ex.getMessage());
        }
        return actualizado;
    }

    @Override
    public boolean delete(Integer id) {
        boolean eliminado = false;
        try (PreparedStatement statement = con.preparedStatement(DELETE_FACTURA)) {
            statement.setInt(1, id);
            eliminado = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar la factura  #" + id + " " + ex.getMessage());
        }
        return eliminado;
    }

    @Override
    public List<FacturaVO> getAll() {
        List<FacturaVO> facturas = new ArrayList<>();

        try (PreparedStatement preparedStatement = con.preparedStatement(SELECT_ALL_FACTURAS); ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                FacturaVO factura = new FacturaVO();
                factura.setNumFactura(rs.getInt("num_factura"));
                factura.setNomCliente(rs.getString("nom_cliente"));
                factura.setFecha(rs.getDate("fecha"));
                factura.setSubtotal(rs.getDouble("subtotal"));
                factura.setTotal(rs.getDouble("total"));

                facturas.add(factura);
            }

        } catch (SQLException ex) {
            System.out.println("Error al obtener las factura  " + ex.getMessage());
        }
        return facturas;
    }

}
