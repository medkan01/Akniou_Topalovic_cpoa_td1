/*
package td2.dao;

import java.sql.*;
import td2.connexion.*;
import td2.pojo.Commande;
import td2.pojo.LigneCommande;

public class MySQLLigneCommandeDAO{

	private static MySQLLigneCommandeDAO instance;

	public static MySQLLigneCommandeDAO getInstance(){
		if (instance == null){
			instance = new MySQLLigneCommandeDAO();
		}
		return instance;
	}

	public boolean insert(LigneCommande ligneCommande) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"INSERT INTO akniou1u.Ligne_commande (id_produit, quantite, tarif_unitaire) VALUES (?, ?, ?);",Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, commande.);
			requete.setInt(2, ligneCommande.getQuantite());
			requete.setDouble(3, ligneCommande.getTarifUnitaire());
		int nbligne = requete.executeUpdate();
		ResultSet res = requete.getGeneratedKeys();
		if (res.next()) {
			int cle = res.getInt(1);
			commande.setId(cle);
		}
		requete.close();
		return nbligne == 1;
	}
	
	public boolean delete(LigneCommande ligneCommande) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"DELETE FROM akniou1u.Ligne_commande WHERE id_commande = ? AND id_produit =?;");
			requete.setInt(1, ligneCommande.getIdCommande());
			requete.setInt(2, ligneCommande.getIdProduit());
		int nbLignes = requete.executeUpdate();
		requete.close();
		return nbLignes == 1;
	}
	
	public boolean update(LigneCommande ligneCommande) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"UPDATE akniou1u.Ligne_commande SET id_produit = ?, quantite= ?, tarif_unitaire=? WHERE id_commande=? AND id_produit = ?;");
			requete.setInt(1, ligneCommande.getIdProduit());
			requete.setInt(2, ligneCommande.getQuantite());
			requete.setDouble(3, ligneCommande.getTarifUnitaire());
		int nbLignes = requete.executeUpdate();
		requete.close();
		return nbLignes == 1;
	}

	public static LigneCommande getById(int id_commande, int id_produit) throws SQLException {
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"SELECT FROM akniou1u_cpoa.Ligne_commande WHERE id_commande=? AND id_produit=?;");
			requete.setInt(1, id_commande);
			requete.setInt(2, id_produit);
		ResultSet res = requete.getResultSet();
		LigneCommande ligneCommande = new LigneCommande(
			res.getInt("id_commande"),
			res.getInt("id_produit"),
			res.getInt("quantite"),
			res.getDouble("tarif_unitaire"));
		return ligneCommande;
	}
	
}
*/