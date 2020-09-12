package cpoa_akniou;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class tClient {
	public static void ajouterClient(int id_client, String nom, String prenom, String identifiant, String mdp, String adr_numero, String adr_voie, 
			String adr_code_postal, String adr_ville, String adr_pays) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("INSERT INTO `akniou1u_cpoa`.`Client` (`id_client`, `nom`, `prenom`, `identifiant`, `mot_de_passe`, `adr_numero`, "
					+ "`adr_voie`, `adr_code_postal`, `adr_ville`, `adr_pays`) VALUES ('"+id_client+"', '"+nom+"', '"+prenom+"', '"+identifiant+"', '"+mdp+"', '"+adr_numero+"', "
					+ "'"+adr_voie+"', '"+adr_code_postal+"', '"+adr_ville+"', '"+adr_pays+"');");
			affClient();
			requete.close();
			laConnexion.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}
	
	public static void suppClient(int id_client) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `akniou1u_cpoa`.`Client` WHERE `Client`.`id_client` = "+id_client+"");
			affClient();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Pb select"+sqle.getMessage());
		}
	}
	
	public static void editClient(int id_client, String nom, String prenom, String identifiant, String mdp, String adr_numero, String adr_voie, 
			String adr_code_postal, String adr_ville, String adr_pays) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("UPDATE `akniou1u_cpoa`.`Client` SET `nom` = '"+nom+"', `prenom` = '"+prenom+"', `identifiant` = '"+identifiant+"', `mot_de_passe` = '"+mdp+"', "
					+ "`adr_numero` = '"+adr_numero+"', `adr_voie` = '"+adr_voie+"', `adr_code_postal` = '"+adr_code_postal+"', `adr_ville` = '"+adr_ville+"', `adr_pays` = '"+adr_pays+"' "
					+ "WHERE `Client`.`id_client` = "+id_client+";");
			affClient();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Pb select"+sqle.getMessage());
		}
	}
	
	public static void affClient(){
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			ResultSet res = requete.executeQuery("SELECT * FROM `akniou1u_cpoa`.`Client`");
			ArrayList<String> tCli = new ArrayList<String>();
			while (res.next()){
				String cli = new String();
				cli = "["+res.getInt("id_client")+", "+ res.getString("nom")+ ", "+res.getString("prenom")+", "+ res.getString("identifiant")+ ", "+res.getString("mot_de_passe")+", "
				+ res.getString("adr_numero")+ ", "+res.getString("adr_voie")+", "+ res.getString("adr_code_postal")+ ", "+res.getString("adr_ville")+", "+ res.getString("adr_pays")+"]";
				tCli.add(cli);
			}
			for(int i=0; i<tCli.size(); i++) {
				System.out.println(tCli.get(i));
			}
		}catch(SQLException sqle) {
			System.out.println("Pb select"+sqle.getMessage());
		}
	}
}
