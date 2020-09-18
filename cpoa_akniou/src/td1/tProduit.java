package td1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class tProduit {
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
	public static void ajouterProduitSQL(int id_produit, String nom, String description, double tarif, String visuel, int id_categorie) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("INSERT INTO `akniou1u_cpoa`.`Produit` (`id_produit`, `nom`, `description`, `tarif`, `visuel`, `id_categorie`) VALUES ('"+id_produit+"', '"+nom+"', '"+description+"','"+tarif+"','"+visuel+"','"+id_categorie+"');");
			affProduit();
			requete.close();
			laConnexion.close();
		} catch (SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n" + sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour supprimer un �l�ment de la table, la table s'affiche une fois la modification effectu�e
	public static void suppProduitSQL(int id_produit) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `akniou1u_cpoa`.`Produit` WHERE `Produit`.`id_produit` = "+id_produit);
			affProduit();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour editer un �l�ment de la table, la table s'affiche une fois la modification effectu�e
	public static void editProduitSQL(int id_produit, String nom, String description, double tarif, String visuel, int id_categorie) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("UPDATE `akniou1u_cpoa`.`Produit` SET `nom` = '"+nom+"', `description` = '"+description+"', `tarif` = '"+tarif+"' WHERE `Produit`.`id_produit` = '"+id_produit+"';");
			affProduit();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour afficher la table
	public static void affProduit(){
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			ResultSet res = requete.executeQuery("SELECT * FROM `akniou1u_cpoa`.`Produit`");
			ArrayList<String> tProd = new ArrayList<String>();
			while (res.next()){
				String prod = new String();
				prod = "["+res.getInt("id_produit")+", "+ res.getString("nom")+ ", "+res.getString("description")+", "+res.getDouble("tarif")+", "+res.getString("visuel")+", "+res.getInt("id_categorie")+"]";
				tProd.add(prod);
			}
			for(int i=0; i<tProd.size(); i++) {
				System.out.println(tProd.get(i));
			}
		}catch(SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}
	
	//M�thode qui va permettre la saisie clavier des attributs d'un �l�ment � ajouter
	@SuppressWarnings("resource")
	public static void ajouterProduit() {
		System.out.println("Ajout d'un produit: \n");
		
		System.out.println("id_produit: ");
		int id_produit = sc.nextInt();
		
		System.out.println("nom: ");
		String nom = new Scanner(System.in).nextLine();
		
		System.out.println("description");
		String description =  new Scanner(System.in).nextLine();
				
		System.out.println("tarif: ");
		double tarif = Double.parseDouble(sc.next());
		
		System.out.println("visuel: ");
		String visuel =  new Scanner(System.in).nextLine();
		
		System.out.println("id_categorie: ");
		int id_categorie = sc.nextInt();
		
		tProduit.ajouterProduitSQL(id_produit, nom, description, tarif, visuel, id_categorie);
	}
	
	//M�thodes qui va permettre la saisie clavier des attributs d'un �l�ment � �diter
	@SuppressWarnings("resource")
	public static void editProduit() {
		System.out.println("Modification d'un produit: \n");
		
		System.out.println("id_produit � modifier: ");
		int id_produit = sc.nextInt();
		
		System.out.println("Modifier les �l�ments suivants: ");
		System.out.println("nom: ");
		String nom = new Scanner(System.in).nextLine();
		
		System.out.println("description");
		String description =  new Scanner(System.in).nextLine();
				
		System.out.println("tarif: ");
		double tarif = Double.parseDouble(sc.next());
		
		System.out.println("visuel: ");
		String visuel =  new Scanner(System.in).nextLine();
		
		System.out.println("id_categorie: ");
		int id_categorie = sc.nextInt();
		
		tProduit.editProduitSQL(id_produit, nom, description, tarif, visuel, id_categorie);
	}
	
	//M�thode qui va permettre la saisie clavier des attributs d'un �l�ment � supprimer
	public static void suppProduit() {
		System.out.println("Suppression d'un produit: \n");
		
		System.out.println("id_produit: ");
		int id_produit = sc.nextInt();
		
		tProduit.suppProduitSQL(id_produit);;
	}
}
