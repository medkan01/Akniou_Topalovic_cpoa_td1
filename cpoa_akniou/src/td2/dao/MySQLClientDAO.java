package td2.dao;

import java.sql.*;
import td2.connexion.*;
import td2.pojo.Client;

public class MySQLClientDAO implements ClientDAO{

	private static MySQLClientDAO instance;

	public static MySQLClientDAO getInstance(){
		if (instance == null) {
			instance = new MySQLClientDAO();
		}
		return instance;
	}
	
	public boolean insert(Client client) throws SQLException {
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"INSERT INTO akniou_cpoa.Client (nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postale, adr_ville, adr_pays) VALUES ('?','?','?','?','?','?','?','?','?';",Statement.RETURN_GENERATED_KEYS);
			requete.setString(1, client.getNom());
			requete.setString(2, client.getPrenom());
			requete.setString(3, client.getIdentifiant());
			requete.setString(4, client.getMotDePasse());
			requete.setString(5, client.getAdrNumero());
			requete.setString(6, client.getAdrVoie());
			requete.setString(7, client.getAdrCodePostal());
			requete.setString(8, client.getAdrVille());
			requete.setString(9, client.getAdrPays());
		int nbligne = requete.executeUpdate();
		ResultSet res = requete.getGeneratedKeys();
		if (res.next()) {
			int cle = res.getInt(1);
			client.setId(cle);
		}
		requete.close();
		return nbligne == 1;
	}
	
	public boolean delete(Client client) throws SQLException {
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"DELETE FROM akniou1u_cpoa.Client WHERE id_client='?'", Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, client.getId());
		int nbLignes = requete.executeUpdate();
		requete.close();
		return nbLignes == 1;
	}
	public boolean update(Client client) throws SQLException {
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(	
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
		int nbLignes = requete.executeUpdate();
		requete.close();
		return nbLignes == 1;
	}

	public Client getById(int id) throws SQLException {
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
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