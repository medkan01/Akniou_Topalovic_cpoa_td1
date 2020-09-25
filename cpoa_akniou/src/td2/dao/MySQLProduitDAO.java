package td2.dao;

import java.sql.*;

import td1.Connexion;
import td2.pojo.Produit;

public class MySQLProduitDAO {

	private static MySQLProduitDAO instance;

	public static MySQLProduitDAO getInstance(){
		if (instance == null){
			instance = new MySQLProduitDAO();
		}
		return instance;
	}

	public static void insert(Produit produit) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
		"INSERT INTO akniou1u.Produit (nom, description, tarif, visuel, id_categorie) VALUES ('?', '?', '?', '?', '?');");
			requete.setString(1, produit.getNom());
			requete.setString(2, produit.getDescription());
			requete.setDouble(3, produit.getTarif());
			requete.setString(4, produit.getVisuel());
			requete.setInt(5, produit.getIdCategorie());
		requete.close();
		laConnexion.close();
	}
	
	public static void delete(Produit produit) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
		"DELETE FROM akniou1u.Produit WHERE id_produit = '?';");
			requete.setInt(1, produit.getId());
		requete.close();
		laConnexion.close();
	}
	
	public static void update(Produit produit) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
		"UPDATE akniou1u.Produit SET nom = '?', description= '?', tarif='?', visuel='?', id_categorie='?' WHERE id_produit'?';");
			requete.setString(1, produit.getNom());
			requete.setString(2, produit.getDescription());
			requete.setDouble(3, produit.getTarif());
			requete.setString(4, produit.getVisuel());
			requete.setInt(5, produit.getIdCategorie());
			requete.setInt(6, produit.getId());
		requete.close();
		laConnexion.close();
	}

	public static Produit getById(int id) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
			"SELECT FROM akniou1u_cpoa.Produit WHERE id_produit = '?';");
				requete.setInt(1,id);
		ResultSet res = requete.getResultSet();
		Produit produit = new Produit(
			res.getInt("id_produit"),
			res.getString("nom"),
			res.getString("description"),
			res.getDouble("tarif"),
			res.getString("visuel"),
			res.getInt("id_categorie"));
		return produit;
	}
}
