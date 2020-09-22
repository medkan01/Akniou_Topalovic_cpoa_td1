package td2.dao;

import java.sql.*;
import td2.connexion.Connexion;
import td2.pojo.Categorie;

public class MySQLCategorieDAO {

	private static MySQLCategorieDAO instance;

	public static MySQLCategorieDAO getInstance() {
		if (instance == null){
			instance = new MySQLCategorieDAO();
		}
		return instance;
	}

	public static void insert(Categorie categorie) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
			"INSERT INTO akniou1u_cpoa.Categorie (titre, visuel) VALUES ('?', '?');");
				requete.setString(1, categorie.getTitre());
				requete.setString(2, categorie.getVisuel());
		requete.close();
		laConnexion.close();
	}
	
	public static void delete(Categorie categorie) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
			"DELETE FROM akniou1u_cpoa.Categorie WHERE Categorie.id_categorie = '?';");
				requete.setInt(1, categorie.getId());
		requete.close();
		laConnexion.close();
	}
	
	public static void update(Categorie categorie) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
			"UPDATE akniou1u_cpoa.Categorie SET titre = '?', visuel = '?' WHERE Categorie.id_categorie = '?';");
				requete.setString(1, categorie.getTitre());
				requete.setString(2, categorie.getVisuel());
				requete.setInt(3, categorie.getId());
		requete.close();
		laConnexion.close();
	}
}
