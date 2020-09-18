package td1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import td2.connexion.Connexion;

public class tClient {
	private static Scanner sc = new Scanner(System.in);
	
	/* 
	 * Il est possible de r�duire le nombre de m�thodes en fusionnant
	 * les m�thodes des requetes SQL avec les m�thodes qui vont g�rer
	 * les entr�es clavier afin d'ajouter, de supprimer, ou d'editer
	 * les �l�ments de la table. On a choisi de les s�parer pour
	 * "clarifier" le code et ne pas surcharger visuellement une
	 * m�thode. 
	*/
	
	//Requ�te SQL pour ajouter un �l�ment de la table, la table s'affiche une fois la modification effectu�e
	public static void ajouterClientSQL(int id_client, String nom, String prenom, String identifiant, String mdp, String adr_numero, String adr_voie, 
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
			System.out.println("Il y a un probl�me avec la base de donn�e: \n" + sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour supprimer un �l�ment de la table, la table s'affiche une fois la modification effectu�e
	public static void suppClientSQL(int id_client) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `akniou1u_cpoa`.`Client` WHERE `Client`.`id_client` = "+id_client+"");
			affClient();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour editer un �l�ment de la table, la table s'affiche une fois la modification effectu�e
	public static void editClientSQL(int id_client, String nom, String prenom, String identifiant, String mdp, String adr_numero, String adr_voie, 
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
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour afficher la table
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
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}
	
	//M�thode qui va permettre la saisie clavier des attributs d'un �l�ment � ajouter
	@SuppressWarnings("resource")
	public static void ajouterClient() {
		System.out.println("Ajout d'un client: \n");
		
		System.out.println("id_client: ");
		int id_client = sc.nextInt();
		
		System.out.println("nom: ");
		String nom =  new Scanner(System.in).nextLine();
		
		System.out.println("prenom: ");
		String prenom =  new Scanner(System.in).nextLine();
		
		System.out.println("identifiant: ");
		String identifiant =  new Scanner(System.in).nextLine();
		
		System.out.println("mot_de_passe: ");
		String mot_de_passe =  new Scanner(System.in).nextLine();
		
		System.out.println("adr_numero: ");
		String adr_numero =  new Scanner(System.in).nextLine();
		
		System.out.println("adr_voie: ");
		String adr_voie =  new Scanner(System.in).nextLine();
		
		System.out.println("adr_code_postal: ");
		String adr_code_postal =  new Scanner(System.in).nextLine();
		
		System.out.println("adr_ville: ");
		String adr_ville =  new Scanner(System.in).nextLine();
		
		System.out.println("adr_pays: ");
		String adr_pays =  new Scanner(System.in).nextLine();
		
		tClient.ajouterClientSQL(id_client, nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays);
	}

	//M�thodes qui va permettre la saisie clavier des attributs d'un �l�ment � �diter
	@SuppressWarnings("resource")
	public static void editClient() {
		System.out.println("Ajout d'un client: \n");
		
		System.out.println("id_client � modifier: ");
		int id_client = sc.nextInt();
		
		System.out.println("Modifier les �l�ments suivants: ");
		System.out.println("nom: ");
		String nom =  new Scanner(System.in).nextLine();
		
		System.out.println("prenom: ");
		String prenom =  new Scanner(System.in).nextLine();
		
		System.out.println("identifiant: ");
		String identifiant =  new Scanner(System.in).nextLine();
		
		System.out.println("mot_de_passe: ");
		String mot_de_passe =  new Scanner(System.in).nextLine();
		
		System.out.println("adr_numero: ");
		String adr_numero =  new Scanner(System.in).nextLine();
		
		System.out.println("adr_voie: ");
		String adr_voie =  new Scanner(System.in).nextLine();
		
		System.out.println("adr_code_postal: ");
		String adr_code_postal =  new Scanner(System.in).nextLine();
		
		System.out.println("adr_ville: ");
		String adr_ville =  new Scanner(System.in).nextLine();
		
		System.out.println("adr_pays: ");
		String adr_pays =  new Scanner(System.in).nextLine();
		
		tClient.editClientSQL(id_client, nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays);
	}
	
	//M�thode qui va permettre la saisie clavier des attributs d'un �l�ment � supprimer
	public static void suppClient() {
		System.out.println("Suppression d'un client: \n");
		
		System.out.println("id_client: ");
		int id_client = sc.nextInt();
		
		tClient.suppClientSQL(id_client);
	}
}
