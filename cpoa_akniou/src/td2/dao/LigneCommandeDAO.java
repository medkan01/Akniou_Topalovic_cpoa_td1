package td2.dao;

import java.sql.SQLException;
import td2.pojo.LigneCommande;

public interface LigneCommandeDAO {
    LigneCommande getById(int id) throws SQLException;
    boolean insert(LigneCommande LigneCommande) throws SQLException;
    boolean delete(LigneCommande LigneCommande) throws SQLException;
    boolean update(LigneCommande LigneCommande) throws SQLException;
}
