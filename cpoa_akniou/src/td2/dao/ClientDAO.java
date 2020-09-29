package td2.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import td2.pojo.Client;

public interface ClientDAO extends DAO<Client>{
    Client getById(int id)throws SQLException;
    ArrayList<Client> getAll() throws SQLException;
    boolean insert(Client obj)throws SQLException;
    boolean delete(Client obj)throws SQLException;
    boolean update(Client obj)throws SQLException;
}
