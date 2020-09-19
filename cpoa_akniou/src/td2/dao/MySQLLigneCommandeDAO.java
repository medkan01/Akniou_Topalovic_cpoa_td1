package td2.dao;

import java.sql.*;
import td2.connexion.Connexion;
import td2.pojo.LigneCommande;

public class MySQLLigneCommandeDAO {
	public static void insert(LigneCommande ligneCommande) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("INSERT INTO akniou1u.Ligne_commande (id_produit, quantite, tarif_unitaire) VALUES ('"+ligneCommande.getIdProduit()+"', '"+ligneCommande.getQuantite()+"', '"+ligneCommande.getTarifUnitaire()+"');");
		requete.close();
		laConnexion.close();
	}
	
	public static void delete(LigneCommande ligneCommande) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("DELETE FROM akniou1u.Ligne_commande WHERE id_commande = '"+ligneCommande.getIdCommande()+"' AND id_produit ='"+ligneCommande.getIdProduit()+"';");
		requete.close();
		laConnexion.close();
	}
	
	public static void update(LigneCommande ligneCommande) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("UPDATE akniou1u.Ligne_commande SET id_produit = '"+ligneCommande.getIdProduit()+"', quantite= '"+ligneCommande.getQuantite()+"', tarif_unitaire='"+ligneCommande.getTarifUnitaire()
				+"' WHERE id_commande='"+ligneCommande.getIdCommande()+"' AND id_produit = '"+ligneCommande.getIdProduit()+"';");
		requete.close();
		laConnexion.close();
	}
}
