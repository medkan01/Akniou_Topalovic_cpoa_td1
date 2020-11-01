package td2.dao.daofactory;

import java.sql.SQLException;
import java.util.ArrayList;

import td2.pojo.Produit;

public interface ProduitDAO extends DAO<Produit>{
    ArrayList<Produit> getAllByCategorie(int idCategorie)throws SQLException;
    Produit getById(int id)throws SQLException;
    ArrayList<Produit> getAll() throws SQLException;
    boolean insert(Produit obj)throws SQLException;
    boolean delete(Produit obj)throws SQLException;
    boolean update(Produit obj)throws SQLException;
}
