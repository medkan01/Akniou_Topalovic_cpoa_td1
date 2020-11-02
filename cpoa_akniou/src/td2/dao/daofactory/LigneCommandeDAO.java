package td2.dao.daofactory;

import java.sql.SQLException;
import java.util.HashMap;
import td2.pojo.LigneCommande;
import td2.pojo.Produit;

public interface LigneCommandeDAO{
    HashMap<Produit,LigneCommande> getAll(int idCommande) throws SQLException;
    HashMap<Produit,LigneCommande> getByIdClient(int idClient) throws SQLException;
    boolean insert(int idCommande, int idProduit, LigneCommande ligneCommande)throws SQLException;
    boolean update(int idCommande, int idProduit, LigneCommande ligneCommande)throws SQLException;
    boolean delete(int idCommande, int idProduit)throws SQLException;
}
