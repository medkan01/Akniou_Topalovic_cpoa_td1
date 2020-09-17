package cpoa_akniou;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class tCategorie{
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
	public static void ajouterCategorieSQL(int no_cat, String titre, String visuel) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("INSERT INTO `akniou1u_cpoa`.`Categorie` (`id_categorie`, `titre`, `visuel`) VALUES ('"+no_cat+"', '"+titre+"', '"+visuel+"');");
			affCategorie();
			requete.close();
			laConnexion.close();
		} catch (SQLException sqle) {
			System.out.println("Il y a un problème avec la base de donnée: \n" + sqle.getMessage());
		}
	}
	
	//Requête SQL pour supprimer un élément de la table, la table s'affiche une fois la modification effectuée
	public static void suppCategorieSQL(int no_cat) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `akniou1u_cpoa`.`Categorie` WHERE `Categorie`.`id_categorie` = "+no_cat+"");
			affCategorie();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Il y a un problème avec la base de donnée: \n"+sqle.getMessage());
		}
	}
	
	//Requête SQL pour editer un élément de la table, la table s'affiche une fois la modification effectuée
	public static void editCategorieSQL(int no_cat, String titre, String visuel) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("UPDATE `akniou1u_cpoa`.`Categorie` SET `titre` = '"+titre+"', `visuel` = '"+visuel+"' WHERE `Categorie`.`id_categorie` = "+no_cat+";");
			affCategorie();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Il y a un problème avec la base de donnée: \n"+sqle.getMessage());
		}
	}
	
	//Requête SQL pour afficher la table
	public static void affCategorie(){
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			ResultSet res = requete.executeQuery("SELECT * FROM `akniou1u_cpoa`.`Categorie`");
			ArrayList<String> tCat = new ArrayList<String>();
			while (res.next()){
				String cat = new String();
				cat = "["+res.getInt("id_categorie")+", "+ res.getString("titre")+ ", "+res.getString("visuel")+"]";
				tCat.add(cat);
			}
			for(int i=0; i<tCat.size(); i++) {
				System.out.println(tCat.get(i));
			}
		}catch(SQLException sqle) {
			System.out.println("Il y a un problème avec la base de donnée: \n"+sqle.getMessage());
		}
	}

	//Méthode qui va permettre la saisie clavier des attributs d'un élément à ajouter
	@SuppressWarnings("resource")
	public static void ajouterCategorie() {
		System.out.println("Ajout d'une catégorie: \n");
		
		System.out.println("id_categorie: ");
		int id_categorie = sc.nextInt();
		
		System.out.println("titre: ");
		String titre = new Scanner(System.in).nextLine();
		
		System.out.println("visuel: ");
		String visuel = new Scanner(System.in).nextLine();
		
		tCategorie.ajouterCategorieSQL(id_categorie, titre, visuel);
	}

	//Méthodes qui va permettre la saisie clavier des attributs d'un élément à éditer
	@SuppressWarnings("resource")
	public static void editCategorie() {
		System.out.println("Modification d'une catégorie: \n");
		
		System.out.println("id_categorie à modifier: ");
		int id_categorie = sc.nextInt();
		
		System.out.println("Modifier les éléments suivants: ");
		System.out.println("titre: ");
		String titre =  new Scanner(System.in).nextLine();

		System.out.println("visuel: ");
		String visuel =  new Scanner(System.in).nextLine();
		
		tCategorie.editCategorieSQL(id_categorie, titre, visuel);
	}
	
	//Méthode qui va permettre la saisie clavier des attributs d'un élément à supprimer
	public static void suppCategorie() {
		System.out.println("Suppression d'une catégorie: \n");
		
		System.out.println("id_categorie: ");
		int id_categorie = sc.nextInt();
		
		tCategorie.suppCategorieSQL(id_categorie);
	}
}
