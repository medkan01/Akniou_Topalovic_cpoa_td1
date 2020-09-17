package cpoa_akniou;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class tLigne_commande {
	private static Scanner sc = new Scanner(System.in);
	
	/* 
	 * Il est possible de réduire le nombre de méthodes en fusionnant
	 * les méthodes des requetes SQL avec les méthodes qui vont gérer
	 * les entrées clavier afin d'ajouter, de supprimer, ou d'editer
	 * les éléments de la table. On a choisi de les séparer pour
	 * "clarifier" le code et ne pas surcharger visuellement une
	 * méthode. 
	*/
	
	//Requête SQL pour ajouter un élément de la table, la table s'affiche une fois la modification effectuée
	public static void ajouterLigne_commandeSQL(int id_commande, int id_produit, int quantite, double tarif_unitaire) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("INSERT INTO `akniou1u_cpoa`.`Ligne_commande` (`id_commande`, `id_produit`, `quantite`, `tarif_unitaire`) VALUES ('"+id_commande+"', '"+id_produit+"', '"+quantite+"','"+tarif_unitaire+"');");
			affLigne_commande();
			requete.close();
			laConnexion.close();
		} catch (SQLException sqle) {
			System.out.println("Il y a un problème avec la base de donnée: \n" + sqle.getMessage());
		}
	}
	
	//Requête SQL pour supprimer un élément de la table, la table s'affiche une fois la modification effectuée
	public static void suppLigne_commandeSQL(int id_commande, int id_produit) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `akniou1u_cpoa`.`Ligne_commande` WHERE `Ligne_commande`.`id_commande` = "+id_commande+" AND `id_produit` = "+id_produit);
			affLigne_commande();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Il y a un problème avec la base de donnée: \n"+sqle.getMessage());
		}
	}
	
	//Requête SQL pour editer un élément de la table, la table s'affiche une fois la modification effectuée
	public static void editLigne_commandeSQL(int id_commande, int id_produit1, int quantite, double tarif_unitaire, int id_produit2) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("UPDATE `akniou1u_cpoa`.`Ligne_commande` SET `id_produit` = '"+id_produit2+"', `quantite` = '"+quantite+"', `tarif_unitaire` = '"+tarif_unitaire+"' WHERE `Ligne_commande`.`id_commande` = '"+id_commande+"' AND `id_produit` = '"+id_produit1+"';");
			affLigne_commande();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Il y a un problème avec la base de donnée: \n"+sqle.getMessage());
		}
	}
	
	//Requête SQL pour afficher la table
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
			System.out.println("Il y a un problème avec la base de donnée: \n"+sqle.getMessage());
		}
	}
	
	//Méthode qui va permettre la saisie clavier des attributs d'un élément à ajouter
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
	
	//Méthodes qui va permettre la saisie clavier des attributs d'un élément à éditer
	public static void editLigne_commande() {
		System.out.println("Modifier d'une ligne de commande: \n");
		
		System.out.println("id_commande à modifier: ");
		int id_commande = sc.nextInt();
		
		System.out.println("id_produit à modifier: ");
		int id_produit1 = sc.nextInt();
		
		System.out.println("Modifier les éléments suivants: ");
		System.out.println("id_produit: ");
		int id_produit2 = sc.nextInt();
		
		System.out.println("quantite: ");
		int quantite = sc.nextInt();
		
		System.out.println("tarif_unitaire: ");
		double tarif_unitaire = Double.parseDouble(sc.next());
		
		tLigne_commande.editLigne_commandeSQL(id_commande, id_produit1, quantite, tarif_unitaire, id_produit2);
	}
	
	//Méthode qui va permettre la saisie clavier des attributs d'un élément à supprimer
	public static void suppLigne_commande() {
		System.out.println("Suppression d'une ligne de commande: \n");
		
		System.out.println("id_commande: ");
		int id_commande = sc.nextInt();
		
		System.out.println("id_produit: ");
		int id_produit = sc.nextInt();


		tLigne_commande.suppLigne_commandeSQL(id_commande, id_produit);
	}
	
}
