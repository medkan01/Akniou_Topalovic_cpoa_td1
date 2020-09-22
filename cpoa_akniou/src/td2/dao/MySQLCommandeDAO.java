package td2.dao;

import java.sql.*;
import td1.Connexion;
import td2.pojo.Commande;

public class MySQLCommandeDAO {
	public static void insert(Commande commande) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
		"INSERT INTO akniou1u.Commande (date_commande, id_client) VALUES ('?', '?');");
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
}
