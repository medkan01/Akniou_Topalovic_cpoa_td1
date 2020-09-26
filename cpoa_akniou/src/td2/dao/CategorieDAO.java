package td2.dao;

import java.sql.SQLException;

import td2.pojo.Categorie;

public interface CategorieDAO extends DAO<Categorie>{
    Categorie getById(int id)throws SQLException;
    boolean insert(Categorie obj)throws SQLException;
    boolean delete(Categorie obj)throws SQLException;
    boolean update(Categorie obj)throws SQLException;
}