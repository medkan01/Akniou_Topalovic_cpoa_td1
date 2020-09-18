package td1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import td2.connexion.Connexion;

public class tLigne_commande {
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
	public static void ajouterLigne_commandeSQL(int id_commande, int id_produit, int quantite, double tarif_unitaire) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("INSERT INTO `akniou1u_cpoa`.`Ligne_commande` (`id_commande`, `id_produit`, `quantite`, `tarif_unitaire`) VALUES ('"+id_commande+"', '"+id_produit+"', '"+quantite+"','"+tarif_unitaire+"');");
			affLigne_commande();
			requete.close();
			laConnexion.close();
		} catch (SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n" + sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour supprimer un �l�ment de la table, la table s'affiche une fois la modification effectu�e
	public static void suppLigne_commandeSQL(int id_commande, int id_produit) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `akniou1u_cpoa`.`Ligne_commande` WHERE `Ligne_commande`.`id_commande` = "+id_commande+" AND `id_produit` = "+id_produit);
			affLigne_commande();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour editer un �l�ment de la table, la table s'affiche une fois la modification effectu�e
	public static void editLigne_commandeSQL(int id_commande, int id_produit1, int quantite, double tarif_unitaire, int id_produit2) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("UPDATE `akniou1u_cpoa`.`Ligne_commande` SET `id_produit` = '"+id_produit2+"', `quantite` = '"+quantite+"', `tarif_unitaire` = '"+tarif_unitaire+"' WHERE `Ligne_commande`.`id_commande` = '"+id_commande+"' AND `id_produit` = '"+id_produit1+"';");
			affLigne_commande();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour afficher la table
	public static void affLigne_commande(){
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			ResultSet res = requete.executeQuery("SELECT * FROM `akniou1u_cpoa`.`Ligne_commande`");
			ArrayList<String> tLigne_com = new ArrayList<String>();
			while (res.next()){
				String ligne_com = new String();
				ligne_com = "["+res.getInt("id_commande")+", "+ res.getInt("id_produit")+ ", "+res.getInt("quantite")+", "+res.getDouble("tarif_unitaire")+"]";
				tLigne_com.add(ligne_com);
			}
			for(int i=0; i<tLigne_com.size(); i++) {
				System.out.println(tLigne_com.get(i));
			}
		}catch(SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}
	
	//M�thode qui va permettre la saisie clavier des attributs d'un �l�ment � ajouter
	public static void ajouterLigne_commande() {
		System.out.println("Ajout d'une ligne de commande: \n");
		
		System.out.println("id_commande: ");
		int id_commande = sc.nextInt();
		
		System.out.println("id_produit: ");
		int id_produit = sc.nextInt();
		
		System.out.println("quantite: ");
		int quantite = sc.nextInt();
		
		System.out.println("tarif_unitaire: ");
		double tarif_unitaire = Double.parseDouble(sc.next());

		tLigne_commande.ajouterLigne_commandeSQL(id_commande, id_produit, quantite, tarif_unitaire);
	}
	
	//M�thodes qui va permettre la saisie clavier des attributs d'un �l�ment � �diter
	public static void editLigne_commande() {
		System.out.println("Modifier d'une ligne de commande: \n");
		
		System.out.println("id_commande � modifier: ");
		int id_commande = sc.nextInt();
		
		System.out.println("id_produit � modifier: ");
		int id_produit1 = sc.nextInt();
		
		System.out.println("Modifier les �l�ments suivants: ");
		System.out.println("id_produit: ");
		int id_produit2 = sc.nextInt();
		
		System.out.println("quantite: ");
		int quantite = sc.nextInt();
		
		System.out.println("tarif_unitaire: ");
		double tarif_unitaire = Double.parseDouble(sc.next());
		
		tLigne_commande.editLigne_commandeSQL(id_commande, id_produit1, quantite, tarif_unitaire, id_produit2);
	}
	
	//M�thode qui va permettre la saisie clavier des attributs d'un �l�ment � supprimer
	public static void suppLigne_commande() {
		System.out.println("Suppression d'une ligne de commande: \n");
		
		System.out.println("id_commande: ");
		int id_commande = sc.nextInt();
		
		System.out.println("id_produit: ");
		int id_produit = sc.nextInt();


		tLigne_commande.suppLigne_commandeSQL(id_commande, id_produit);
	}
	
}
