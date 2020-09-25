package td2.dao;

import java.sql.SQLException;
import td2.pojo.Commande;

public interface CommandeDAO {
    Commande getById(int id) throws SQLException;
    boolean insert(Commande commande) throws SQLException;
    boolean delete(Commande commande) throws SQLException;
    boolean update(Commande commande) throws SQLException;
}
