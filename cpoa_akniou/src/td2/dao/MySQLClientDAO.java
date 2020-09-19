package td2.dao;

import java.sql.*;
import td1.Connexion;
import td2.pojo.Client;

public class MySQLClientDAO {
	public static void insert(Client client) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("INSERT INTO akniou_cpoa.Client (nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postale, adr_ville, adr_pays) VALUES ('"+client.getNom()+"', '"+client.getPrenom()
				+"', '"+client.getIdentifiant()+"', '"+client.getMotDePasse()+"', '"+client.getAdrNumero()+"', '"+client.getAdrVoie()+"', '"+client.getAdrCodePostal()+"', '"+client.getAdrVille()+"', '"+client.getAdrPays()+"';");
		requete.close();
		laConnexion.close();
	}
	
	public static void delete(Client client) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("DELETE FROM akniou1u_cpoa.Client WHERE id_client='"+client.getId()+"';");
		requete.close();
		laConnexion.close();
	}
	
	public static void update(Client client) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("UPDATE akniou1u.Client SET nom='"+client.getNom()+"', prenom ='"+client.getPrenom()+"', identifiant = '"+client.getIdentifiant()+"', mot_de_passe='"+client.getMotDePasse()
				+"',adr_numero='"+client.getAdrNumero()+"', adr_voie='"+client.getAdrVoie()+"', adr_code_postal='"+client.getAdrCodePostal()+"', adr_ville='"+client.getAdrVille()+"',adr_pays='"+client.getAdrPays()
				+"' WHERE Client.id_client = '"+client.getId()+"';");
		requete.close();
		laConnexion.close();
	}
}
