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
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
			"INSERT INTO akniou1u_cpoa.Categorie (titre, visuel) VALUES ('?', '?')",Statement.RETURN_GENERATED_KEYS);
				requete.setString(1, categorie.getTitre());
				requete.setString(2, categorie.getVisuel());
		requete.close();
	}

	public boolean delete(Categorie categorie) throws SQLException {
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
			"DELETE FROM akniou1u_cpoa.Categorie WHERE Categorie.id_categorie = '?';", Statement.RETURN_GENERATED_KEYS);
				requete.setInt(1, categorie.getId());
		int nbLignes = requete.executeUpdate();
		requete.close();
		return nbLignes == 1;
	}
	
	public static void update(Categorie categorie) throws SQLException {
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
			"UPDATE akniou1u_cpoa.Categorie SET titre = '?', visuel = '?' WHERE Categorie.id_categorie = '?';");
				requete.setString(1, categorie.getTitre());
				requete.setString(2, categorie.getVisuel());
				requete.setInt(3, categorie.getId());
		requete.close();
	}

	public static Categorie getById(int id) throws SQLException {
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
			"SELECT FROM akniou1u_cpoa.Categorie WHERE id_categorie ='?';");
				requete.setInt(1, id);
		ResultSet res = requete.getResultSet();
		Categorie categorieRes = new Categorie(
			res.getInt("id_categorie"),
			res.getString("titre"),
			res.getString("visuel"));
		return categorieRes;
	}
}
