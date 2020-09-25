package td2.dao;

import java.sql.SQLException;
import td2.pojo.Categorie;

public interface CategorieDAO {
    Categorie getById(int id) throws SQLException;
}