package td2.dao;

import java.sql.*;
import td1.Connexion;
import td2.pojo.Client;

public class MySQLClientDAO {

	private static MySQLClientDAO instance;

	public static MySQLClientDAO getInstance(){
		if (instance == null) {
			instance = new MySQLClientDAO();
		}
		return instance;
	}
	
	public static void insert(Client client) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
			"INSERT INTO akniou_cpoa.Client (nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postale, adr_ville, adr_pays) VALUES ('?','?','?','?','?','?','?','?','?';");
				requete.setString(1, client.getNom());
				requete.setString(2, client.getPrenom());
				requete.setString(3, client.getIdentifiant());
				requete.setString(4, client.getMotDePasse());
				requete.setString(5, client.getAdrNumero());
				requete.setString(6, client.getAdrVoie());
				requete.setString(7, client.getAdrCodePostal());
				requete.setString(8, client.getAdrVille());
				requete.setString(9, client.getAdrPays());
		requete.close();
		laConnexion.close();
	
	}
	
	public static void delete(Client client) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
			"DELETE FROM akniou1u_cpoa.Client WHERE id_client='?'");
				requete.setInt(1, client.getId());
		laConnexion.close();
	}
	public static void update(Client client) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(	
			"UPDATE akniou1u.Client SET nom='?', prenom='?', identifiant='?', mot_de_passe='?', adr_numero='?', adr_voie='?', adr_code_postale='?', adr_ville='?', adr_pays='?')WHERE Client.id_client = '?';");
				requete.setString(1, client.getNom());
				requete.setString(2, client.getPrenom());
				requete.setString(3, client.getIdentifiant());
				requete.setString(4, client.getMotDePasse());
				requete.setString(5, client.getAdrNumero());
				requete.setString(6, client.getAdrVoie());
				requete.setString(7, client.getAdrCodePostal());
				requete.setString(8, client.getAdrVille());
				requete.setString(9, client.getAdrPays());
				requete.setInt(10, client.getId());
		requete.close();
		laConnexion.close();
	}

	public static Client getById(int id) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
			"SELECT FROM akbiou1u_cpoa.Client WHERE id_client='?';");
				requete.setInt(1, id);
		ResultSet res = requete.getResultSet();
		Client client = new Client(
			res.getInt("id_client"),
			res.getString("nom"),
			res.getString("prenom"),
			res.getString("identifiant"),
			res.getString("mot_de_pase"),
			res.getString("adr_numero"),
			res.getString("adr_voie"),
			res.getString("adr_code_postal"),
			res.getString("adr_ville"),
			res.getString("adr_pays"));
		return client;
	}
}