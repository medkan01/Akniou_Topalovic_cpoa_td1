package td2.dao;

import java.sql.SQLException;
import td2.pojo.Categorie;

public interface CategorieDAO {
    Categorie getById(int id) throws SQLException;
    boolean insert(Categorie categorie) throws SQLException;
    boolean delete(Categorie categorie) throws SQLException;
    boolean update(Categorie categorie) throws SQLException;
}