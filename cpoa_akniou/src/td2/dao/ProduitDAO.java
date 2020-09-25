package td2.dao;

import java.sql.SQLException;
import td2.pojo.Produit;

public interface ProduitDAO {
    Produit getById(int id) throws SQLException;
    boolean insert(Produit produit) throws SQLException;
    boolean delete(Produit produit) throws SQLException;
    boolean update(Produit produit) throws SQLException;
}
