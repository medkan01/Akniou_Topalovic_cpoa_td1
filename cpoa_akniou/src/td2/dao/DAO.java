package td2.dao;

import java.sql.SQLException;

public interface DAO<T> {
    public T getById(int id) throws SQLException;
    public boolean insert(T objet) throws SQLException;
    public boolean delete(T objet) throws SQLException;
    public boolean update(T objet) throws SQLException;
}