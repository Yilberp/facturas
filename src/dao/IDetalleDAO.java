
package dao;

import java.util.List;
import model.DetalleVO;

public interface IDetalleDAO extends IDAO<DetalleVO,Integer>{
    List<DetalleVO> getByIdFactura(int facturaId);
}
