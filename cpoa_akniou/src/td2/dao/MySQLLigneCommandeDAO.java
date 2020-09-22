package td2.dao;

import java.sql.*;
import td2.connexion.Connexion;
import td2.pojo.LigneCommande;

public class MySQLLigneCommandeDAO {
	public static void insert(LigneCommande ligneCommande) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
		"INSERT INTO akniou1u.Ligne_commande (id_produit, quantite, tarif_unitaire) VALUES ('?', '?', '?');");
			requete.setInt(1, ligneCommande.getIdProduit());
			requete.setInt(2, ligneCommande.getQuantite());
			requete.setDouble(3, ligneCommande.getTarifUnitaire());
		requete.close();
		laConnexion.close();
	}
	
	public static void delete(LigneCommande ligneCommande) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
		"DELETE FROM akniou1u.Ligne_commande WHERE id_commande = '?' AND id_produit ='?';");
			requete.setInt(1, ligneCommande.getIdCommande());
			requete.setInt(2, ligneCommande.getIdProduit());
		requete.close();
		laConnexion.close();
	}
	
	public static void update(LigneCommande ligneCommande) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
		"UPDATE akniou1u.Ligne_commande SET id_produit = '?', quantite= '?', tarif_unitaire='?' WHERE id_commande='?' AND id_produit = '?';");
			requete.setInt(1, ligneCommande.getIdProduit());
			requete.setInt(2, ligneCommande.getQuantite());
			requete.setDouble(3, ligneCommande.getTarifUnitaire());
			requete.setInt(1, ligneCommande.getIdCommande());
			requete.setInt(2, ligneCommande.getIdProduit());
		requete.close();
		laConnexion.close();
	}
}
