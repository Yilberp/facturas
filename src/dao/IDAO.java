package dao;

import java.util.List;

public interface IDAO<T, ID> {

    T getById(ID id);
    boolean insert(T o);
    boolean update(T o);
    boolean delete(ID id);
    List<T> getAll();

}
