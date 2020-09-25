package td2.dao;

import java.sql.*;
import td2.connexion.*;
import td2.pojo.Commande;

public class MySQLCommandeDAO implements CommandeDAO{

	private static MySQLCommandeDAO instance;

	public static MySQLCommandeDAO getInstance(){
		if (instance == null){
			instance = new MySQLCommandeDAO();
		}
		return instance;
	}

	public boolean insert(Commande commande) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"INSERT INTO akniou1u.Commande (date_commande, id_client) VALUES ('?', '?')",Statement.RETURN_GENERATED_KEYS);
			requete.setDate(1,  java.sql.Date.valueOf(commande.getDate()));
			requete.setInt(2, commande.getIdClient());
		int nbligne = requete.executeUpdate();
		ResultSet res = requete.getGeneratedKeys();
		if (res.next()) {
			int cle = res.getInt(1);
			commande.setId(cle);
		}
		requete.close();
		return nbligne == 1;
	}
	
	public boolean delete(Commande commande) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"DELETE FROM akniou1u.Commande WHERE id_commande='?';", Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, commande.getId());
		int nbLignes = requete.executeUpdate();
		requete.close();
		return nbLignes == 1;
	}
	
	public static void update(Commande commande) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"UPDATE akniou1u.Commande SET date_commande='?', id_client='?' WHERE id_commande='?';");
			requete.setDate(1, java.sql.Date.valueOf(commande.getDate()));
			requete.setInt(2, commande.getIdClient());
			requete.setInt(3, commande.getId());
		requete.close();
	}

	public static Commande getById(int id) throws SQLException {
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
			"SELECT FROM akniou1u_cpoa.Commande WHERE id_commande = '?';");
				requete.setInt(1, id);
		ResultSet res = requete.getResultSet();
		Commande commande = new Commande(
			res.getInt("id_commande"),
			res.getDate("date_commande").toLocalDate(),
			res.getInt("id_client"));
		return commande;
	}
}
