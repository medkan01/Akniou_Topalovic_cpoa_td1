package TD1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class tCommande {
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
	public static void ajouterCommandeSQL(int id_commande, String date, int id_client) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("INSERT INTO `akniou1u_cpoa`.`Commande` (`id_commande`, `date_commande`, `id_client`) VALUES ('"+id_commande+"', '"+date+"', '"+id_client+"');");
			affCommande();
			requete.close();
			laConnexion.close();
		} catch (SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n" + sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour supprimer un �l�ment de la table, la table s'affiche une fois la modification effectu�e
	public static void suppCommandeSQL(int id_commande) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `akniou1u_cpoa`.`Commande` WHERE `Commande`.`id_commande` = "+id_commande+"");
			affCommande();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour editer un �l�ment de la table, la table s'affiche une fois la modification effectu�e
	public static void editCommandeSQL(int id_commande, String date_commande, int id_client) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("UPDATE `akniou1u_cpoa`.`Commande` SET `date_commande` = '"+date_commande+"', `id_client` = '"+id_client+"' WHERE `Commande`.`id_commande` = "+id_commande+";");
			affCommande();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour afficher la table
	public static void affCommande(){
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			ResultSet res = requete.executeQuery("SELECT * FROM `akniou1u_cpoa`.`Commande`");
			ArrayList<String> tCom = new ArrayList<String>();
			while (res.next()){
				String com = new String();
				com = "["+res.getInt("id_commande")+", "+ res.getString("date_commande")+ ", "+res.getInt("id_client")+"]";
				tCom.add(com);
			}
			for(int i=0; i<tCom.size(); i++) {
				System.out.println(tCom.get(i));
			}
		}catch(SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}

	//M�thode qui va permettre la saisie clavier des attributs d'un �l�ment � ajouter
	@SuppressWarnings("resource")
	public static void ajouterCommande() {
		System.out.println("Ajout d'une commande: \n");
		
		System.out.println("id_commande: ");
		int id_commande = sc.nextInt();
		
		System.out.println("date au format 'yyyy-mm-jj': ");
		String date = new Scanner(System.in).nextLine();
		
		System.out.println("heure au format 'hh:mm:ss");
		String heure =  new Scanner(System.in).nextLine();
		
		String dateSQL = date + " " + heure;
		
		System.out.println("id_client: ");
		int id_client = sc.nextInt();
		
		tCommande.ajouterCommandeSQL(id_commande, dateSQL, id_client);;
	}

	//M�thodes qui va permettre la saisie clavier des attributs d'un �l�ment � �diter
	@SuppressWarnings("resource")
	public static void editCommande() {
		System.out.println("Modification d'une commande: \n");
		
		System.out.println("id_commande � modifier: ");
		int id_commande = sc.nextInt();
		
		System.out.println("Modifier les �l�ments suivant: ");
		System.out.println("date au format 'yyyy-mm-jj': ");
		String date = new Scanner(System.in).nextLine();
		
		System.out.println("heure au format 'hh:mm:ss");
		String heure =  new Scanner(System.in).nextLine();
		
		String dateSQL = date + " " + heure;
		
		System.out.println("id_client: ");
		int id_client = sc.nextInt();
		
		tCommande.editCommandeSQL(id_commande, dateSQL, id_client);;
	}
	
	//M�thode qui va permettre la saisie clavier des attributs d'un �l�ment � supprimer
	public static void suppCommande() {
		System.out.println("Suppresion d'une commande: \n");
		
		System.out.println("id_commande: ");
		int id_commande = sc.nextInt();
		
		tCommande.suppCommandeSQL(id_commande);;
	}
}
