package td2.dao;

import java.sql.*;
import td1.Connexion;
import td2.pojo.Commande;

public class MySQLCommandeDAO {

	private static MySQLCommandeDAO instance;

	public static MySQLCommandeDAO getInstance(){
		if (instance == null){
			instance = new MySQLCommandeDAO();
		}
		return instance;
	}

	public static void insert(Commande commande) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
		"INSERT INTO akniou1u.Commande (date_commande, id_client) VALUES ('?', '?');",Statement.RETURN_GENERATED_KEYS);
			requete.setDate(1, commande.getDate());
			requete.setInt(2, commande.getIdClient());
		requete.close();
		laConnexion.close();
	}
	
	public static void delete(Commande commande) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
		"DELETE FROM akniou1u.Commande WHERE id_commande='?';");
			requete.setInt(1, commande.getId());
		requete.close();
		laConnexion.close();
	}
	
	public static void update(Commande commande) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
		"UPDATE akniou1u.Commande SET date_commande='?', id_client='?' WHERE id_commande='?';");
			requete.setDate(1, commande.getDate());
			requete.setInt(2, commande.getIdClient());
			requete.setInt(3, commande.getId());
		requete.close();
		laConnexion.close();
	}

	public static Commande getById(int id) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
			"SELECT FROM akniou1u_cpoa.Commande WHERE id_commande = '?';");
				requete.setInt(1, id);
		ResultSet res = requete.getResultSet();
		Commande commande = new Commande(
			res.getInt("id_commande"),
			res.getDate("date_commande"),
			res.getInt("id_client"));
		return commande;
	}
}
