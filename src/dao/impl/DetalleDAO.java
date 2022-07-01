
package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;
import model.DetalleVO;
import dao.IDetalleDAO;

public class DetalleDAO implements IDetalleDAO {
    
    private static final String INSERT_DETALLE = "INSERT INTO detalle (articulo, cantidad, valor, num_factura) VALUES (?, ?, ?, ?);";
    private static final String SELECT_DETALLE = "SELECT * FROM detalle WHERE id = ?";
    private static final String SELECT_ALL_DETALLES = "SELECT * FROM detalle";
    private static final String DELETE_DETALLE = "DELETE FROM detalle WHERE id = ?;";
    private static final String SELECT_BY_ID_FACTURA = "SELECT * FROM detalle WHERE num_factura = ?;";
    private static final String UPDATE_DETALLE = "UPDATE detalle SET articulo = ?, cantidad = ?, valor = ?, num_factura = ? WHERE id = ?;";
    
    private final Conexion con;
    
    public DetalleDAO(Conexion con){
        this.con = con;
    }

    @Override
    public boolean insert(DetalleVO detalle) {
        boolean insertado = false;
        try (PreparedStatement preparedStatement = con.preparedStatement(INSERT_DETALLE)) {
            preparedStatement.setString(1, detalle.getArticulo());
            preparedStatement.setInt(2, detalle.getCantidad());
            preparedStatement.setDouble(3, detalle.getValor());
            preparedStatement.setInt(4, detalle.getNumFactura());
            insertado = preparedStatement.executeUpdate()>0;
        } catch (SQLException ex) {
            con.rollback();
            Logger.getLogger(IDetalleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return insertado;
    }
    
    @Override
    public List<DetalleVO> getAll() {
        List< DetalleVO> detalles = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.preparedStatement(SELECT_ALL_DETALLES);
                ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                DetalleVO detalle = new DetalleVO();
                detalle.setArticulo(rs.getString("articulo"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setValor(rs.getDouble("valor"));
                detalle.setNumFactura(rs.getInt("num_factura"));
                detalles.add(detalle);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IDetalleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalles;
    }
    
    @Override
    public boolean update(DetalleVO detalle) {
        boolean actualizado = false;
        try (PreparedStatement preparedStatement = con.preparedStatement(UPDATE_DETALLE)) {
            preparedStatement.setString(1, detalle.getArticulo());
            preparedStatement.setInt(2, detalle.getCantidad());
            preparedStatement.setDouble(3, detalle.getValor());
            preparedStatement.setInt(4, detalle.getNumFactura());
            preparedStatement.setInt(5, detalle.getId());
            actualizado = preparedStatement.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(IDetalleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actualizado;
    }
    
    @Override
    public DetalleVO getById(Integer id) {
        DetalleVO detalle = null;
        try (PreparedStatement preparedStatement = con.preparedStatement(SELECT_DETALLE)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    detalle = new DetalleVO();
                    detalle.setId(rs.getInt("id"));
                    detalle.setArticulo(rs.getString("articulo"));
                    detalle.setCantidad(rs.getInt("cantidad"));
                    detalle.setValor(rs.getDouble("valor"));
                    detalle.setNumFactura(rs.getInt("num_factura"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(IDetalleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalle;
    }
    
    @Override
    public boolean delete(Integer id) {
        boolean eliminado = false;
        try (PreparedStatement statement = con.preparedStatement(DELETE_DETALLE)) {
            statement.setInt(1, id);
            eliminado = statement.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(IDetalleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eliminado;
    }
    
    @Override
    public List<DetalleVO> getByIdFactura(int facturaId) {
        List<DetalleVO> detalles = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.preparedStatement(SELECT_BY_ID_FACTURA);) {
            preparedStatement.setInt(1, facturaId);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                DetalleVO detalle = new DetalleVO();
                detalle.setId(rs.getInt("id"));
                detalle.setArticulo(rs.getString("articulo"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setValor(rs.getDouble("valor"));
                detalle.setNumFactura(rs.getInt("num_factura"));
                detalles.add(detalle);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IDetalleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalles;
    }
    
}
