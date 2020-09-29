package td2.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import td2.pojo.Commande;

public interface CommandeDAO extends DAO<Commande>{
    Commande getById(int id)throws SQLException;
    ArrayList<Commande> getAll() throws SQLException;
    boolean insert(Commande obj)throws SQLException;
    boolean delete(Commande obj)throws SQLException;
    boolean update(Commande obj)throws SQLException;
}
