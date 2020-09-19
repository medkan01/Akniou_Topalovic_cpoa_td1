package td2.dao;

import java.sql.*;
import td1.Connexion;
import td2.pojo.Produit;

public class MySQLProduitDAO {
	public static void insert(Produit produit) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("INSERT INTO akniou1u.Produit (nom, description, tarif, visuel, id_categorie) VALUES ('"+produit.getNom()+"', '"+produit.getDescription()+"', '"
					+produit.getTarif()+"','"+produit.getVisuel()+"', '"+produit.getIdCategorie()+"');");
		requete.close();
		laConnexion.close();
	}
	
	public static void delete(Produit produit) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("DELETE FROM akniou1u.Produit WHERE id_produit = '"+produit.getId()+"';");
		requete.close();
		laConnexion.close();
	}
	
	public static void update(Produit produit) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("UPDATE akniou1u.Produit SET nom = '"+produit.getNom()+"', description= '"+produit.getDescription()+"', tarif='"+produit.getTarif()+"', visuel='"+produit.getTarif()
				+"', id_categorie='"+produit.getIdCategorie()+"' WHERE id_produit'"+produit.getId()+"';");
		requete.close();
		laConnexion.close();
	}
}
