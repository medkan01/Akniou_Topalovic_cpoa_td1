package td2.dao;

import java.sql.SQLException;
import td2.pojo.Client;

public interface ClientDAO {
    Client getById(int id) throws SQLException;
    boolean insert(Client client)throws SQLException;
    boolean delete(Client client)throws SQLException;
    boolean update(Client client)throws SQLException;
}
