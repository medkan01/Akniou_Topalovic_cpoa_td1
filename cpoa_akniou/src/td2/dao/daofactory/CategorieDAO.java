package td2.dao.daofactory;

import java.sql.SQLException;
import java.util.ArrayList;
import td2.pojo.Categorie;

public interface CategorieDAO extends DAO<Categorie>{
    Categorie getCategorieByProduit(int idProduit)throws SQLException;
    Categorie getById(int id)throws SQLException;
    ArrayList<Categorie> getAll() throws SQLException;
    boolean insert(Categorie obj)throws SQLException;
    boolean delete(Categorie obj)throws SQLException;
    boolean update(Categorie obj)throws SQLException;
}