package td2.dao;

import java.sql.*;

import td2.connexion.*;
import td2.pojo.Produit;

public class MySQLProduitDAO implements ProduitDAO {

	private static MySQLProduitDAO instance;

	public static MySQLProduitDAO getInstance(){
		if (instance == null){
			instance = new MySQLProduitDAO();
		}
		return instance;
	}

	public boolean insert(Produit produit) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"INSERT INTO akniou1u.Produit (nom, description, tarif, visuel, id_categorie) VALUES ('?', '?', '?', '?', '?');",Statement.RETURN_GENERATED_KEYS);
			requete.setString(1, produit.getNom());
			requete.setString(2, produit.getDescription());
			requete.setDouble(3, produit.getTarif());
			requete.setString(4, produit.getVisuel());
			requete.setInt(5, produit.getIdCategorie());
		int nbligne = requete.executeUpdate();
		ResultSet res = requete.getGeneratedKeys();
		if (res.next()) {
			int cle = res.getInt(1);
			produit.setId(cle);
		}
		requete.close();
		return nbligne == 1;
	}
	
	public boolean delete(Produit produit) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"DELETE FROM akniou1u.Produit WHERE id_produit = '?';", Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, produit.getId());
		int nbLignes = requete.executeUpdate();
		requete.close();
		return nbLignes == 1;
	}
	
	public static void update(Produit produit) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"UPDATE akniou1u.Produit SET nom = '?', description= '?', tarif='?', visuel='?', id_categorie='?' WHERE id_produit'?';");
			requete.setString(1, produit.getNom());
			requete.setString(2, produit.getDescription());
			requete.setDouble(3, produit.getTarif());
			requete.setString(4, produit.getVisuel());
			requete.setInt(5, produit.getIdCategorie());
			requete.setInt(6, produit.getId());
		requete.close();
	}

	public static Produit getById(int id) throws SQLException {
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
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
