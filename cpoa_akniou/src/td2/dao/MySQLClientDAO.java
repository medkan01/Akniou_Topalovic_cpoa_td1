package td2.dao;

import java.sql.*;
import td1.Connexion;
import td2.pojo.Client;

public class MySQLClientDAO {
	public static void insert(Client client) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.preapareStatement(
			"INSERT INTO akniou_cpoa.Client (nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postale, adr_ville, adr_pays) 
			VALUES ('?','?','?','?','?','?','?','?','?';");
				requete.setString(1, client.getNom());
				requete.setString(2, client.getPrenom());
				requete.setString(3, client.getIdentifiant());
				requete.setString(4, client.getMotDePasse());
				requete.setString(5, client.getAdrNumero());
				requete.setString(6, client.getAdrVoie());
				requete.setString(7, client.getAdrCodePostal());
				requete.setString(8, client.getAdrVille());
				requete.setString(9, client.getAdrPays());
			ResultSet res = requete.executeQuery();
		requete.close();
		laConnexion.close();
	
	}
	
	public static void delete(Client client) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.preapareStatement(
			"DELETE FROM akniou1u_cpoa.Client WHERE id_client='?'");
				requete.setString(1, client.getId());
			ResultSet res = requete.executeQuery();
		requete.close();
		laConnexion.close();
	}
	
	public static void update(Client client) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.preapareStatement(	
			"UPDATE akniou1u.Client SET nom='?', prenom='?', identifiant='?', mot_de_passe='?', adr_numero='?', adr_voie='?', adr_code_postale='?', adr_ville='?', adr_pays='?')
			WHERE Client.id_client = '?';");
				requete.setString(1, client.getNom());
				requete.setString(2, client.getPrenom());
				requete.setString(3, client.getIdentifiant());
				requete.setString(4, client.getMotDePasse());
				requete.setString(5, client.getAdrNumero());
				requete.setString(6, client.getAdrVoie());
				requete.setString(7, client.getAdrCodePostal());
				requete.setString(8, client.getAdrVille());
				requete.setString(9, client.getAdrPays());
				requete.setString(10, client.getId());
			ResultSet res = requete.executeQuery();
		requete.close();
		laConnexion.close();
	}
}
