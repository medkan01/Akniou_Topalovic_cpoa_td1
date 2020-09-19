package td2.dao;

import java.sql.*;
import td1.Connexion;
import td2.pojo.Commande;

public class MySQLCommandeDAO {
	public static void insert(Commande commande) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("INSERT INTO akniou1u.Commande (date_commande, id_client) VALUES ('"+commande.getDate()+"', '"+commande.getIdClient()+"');");
		requete.close();
		laConnexion.close();
	}
	
	public static void delete(Commande commande) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("DELETE FROM akniou1u.Commande WHERE id_commande='"+commande.getId()+"';");
		requete.close();
		laConnexion.close();
	}
	
	public static void update(Commande commande) throws SQLException{
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("UPDATE akniou1u.Commande SET date_commande='"+commande.getDate()+"', id_client='"+commande.getIdClient()+"' WHERE id_commande='"+commande.getId()+"';");
		requete.close();
		laConnexion.close();
	}
}
