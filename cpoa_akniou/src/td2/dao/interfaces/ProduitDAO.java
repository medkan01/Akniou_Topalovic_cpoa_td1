package td2.dao.interfaces;

import java.sql.SQLException;
import td2.pojo.Produit;

public interface ProduitDAO extends DAO<Produit>{
    Produit getById(int id)throws SQLException;
    boolean insert(Produit obj)throws SQLException;
    boolean delete(Produit obj)throws SQLException;
    boolean update(Produit obj)throws SQLException;
}
