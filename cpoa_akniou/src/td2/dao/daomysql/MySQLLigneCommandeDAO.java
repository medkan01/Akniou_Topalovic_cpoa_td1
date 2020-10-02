
package td2.dao.daomysql;

import java.sql.*;
import java.util.HashMap;

import td2.connexion.*;
import td2.dao.daofactory.LigneCommandeDAO;
import td2.pojo.LigneCommande;
import td2.pojo.Produit;

public class MySQLLigneCommandeDAO implements LigneCommandeDAO{
	private MySQLProduitDAO produitInstance;
	private static MySQLLigneCommandeDAO instance;

	public static MySQLLigneCommandeDAO getInstance(){
		if (instance == null){
			instance = new MySQLLigneCommandeDAO();
		}
		return instance;
	}

	public boolean insert(int idProduit,int idCommande,LigneCommande ligne) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"INSERT INTO akniou1u.Ligne_commande (id_commande, id_produit, quantite, tarif_unitaire) VALUES (?, ?, ?, ?);");
			requete.setInt(1, idCommande);
			requete.setInt(2, idProduit);
			requete.setInt(3, ligne.getQuantite());
			requete.setDouble(4, ligne.getTarifUnitaire());
		int nbligne = requete.executeUpdate();
		requete.close();
		return nbligne == 1;
	}
	
	
	public boolean delete(int idCommande,int idProduit) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"DELETE FROM akniou1u.Ligne_commande WHERE id_commande = ? AND id_produit=?");	
			requete.setInt(1, idCommande);
			requete.setInt(2, idProduit);	
			int nbLignes = requete.executeUpdate();
		requete.close();
		return nbLignes == 1;
	}
	
	public boolean update(int idCommande,int idProduit, LigneCommande ligne) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"UPDATE akniou1u.Ligne_commande SET id_commande = ? ,id_produit = ?, quantite= ?, tarif_unitaire=? WHERE id_commande=? AND id_produit = ?;");
		requete.setInt(1, idCommande);
		requete.setInt(2, idProduit);
		requete.setInt(3, ligne.getQuantite());
		requete.setDouble(4, ligne.getTarifUnitaire());
		int nbLignes = requete.executeUpdate();
		requete.close();
		return nbLignes == 1;
	}

	public HashMap<Produit, LigneCommande> getAll(int idCommande)throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		Statement requete = c.createStatement();
		HashMap<Produit, LigneCommande> hash = new HashMap<Produit,LigneCommande>();
		requete.executeQuery("SELECT * FROM akniou1u_cpoa.Ligne_commande WHERE id_commande ='"+idCommande+"';");
		ResultSet res = requete.getResultSet();
		while (res.next()){
			Produit produit = produitInstance.getById(res.getInt("id_produit"));
			LigneCommande ligneCommande = new LigneCommande(res.getInt("quantite"), res.getDouble("tarif_unitaire"));
			hash.put(produit, ligneCommande);
		}
		return hash;

	}
}
