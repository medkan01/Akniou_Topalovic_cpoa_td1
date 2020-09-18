package TD1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class tCategorie{
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
	public static void ajouterCategorieSQL(int no_cat, String titre, String visuel) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("INSERT INTO `akniou1u_cpoa`.`Categorie` (`id_categorie`, `titre`, `visuel`) VALUES ('"+no_cat+"', '"+titre+"', '"+visuel+"');");
			affCategorie();
			requete.close();
			laConnexion.close();
		} catch (SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n" + sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour supprimer un �l�ment de la table, la table s'affiche une fois la modification effectu�e
	public static void suppCategorieSQL(int no_cat) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `akniou1u_cpoa`.`Categorie` WHERE `Categorie`.`id_categorie` = "+no_cat+"");
			affCategorie();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour editer un �l�ment de la table, la table s'affiche une fois la modification effectu�e
	public static void editCategorieSQL(int no_cat, String titre, String visuel) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("UPDATE `akniou1u_cpoa`.`Categorie` SET `titre` = '"+titre+"', `visuel` = '"+visuel+"' WHERE `Categorie`.`id_categorie` = "+no_cat+";");
			affCategorie();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}
	
	//Requ�te SQL pour afficher la table
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
			System.out.println("Il y a un probl�me avec la base de donn�e: \n"+sqle.getMessage());
		}
	}

	//M�thode qui va permettre la saisie clavier des attributs d'un �l�ment � ajouter
	@SuppressWarnings("resource")
	public static void ajouterCategorie() {
		System.out.println("Ajout d'une cat�gorie: \n");
		
		System.out.println("id_categorie: ");
		int id_categorie = sc.nextInt();
		
		System.out.println("titre: ");
		String titre = new Scanner(System.in).nextLine();
		
		System.out.println("visuel: ");
		String visuel = new Scanner(System.in).nextLine();
		
		tCategorie.ajouterCategorieSQL(id_categorie, titre, visuel);
	}

	//M�thodes qui va permettre la saisie clavier des attributs d'un �l�ment � �diter
	@SuppressWarnings("resource")
	public static void editCategorie() {
		System.out.println("Modification d'une cat�gorie: \n");
		
		System.out.println("id_categorie � modifier: ");
		int id_categorie = sc.nextInt();
		
		System.out.println("Modifier les �l�ments suivants: ");
		System.out.println("titre: ");
		String titre =  new Scanner(System.in).nextLine();

		System.out.println("visuel: ");
		String visuel =  new Scanner(System.in).nextLine();
		
		tCategorie.editCategorieSQL(id_categorie, titre, visuel);
	}
	
	//M�thode qui va permettre la saisie clavier des attributs d'un �l�ment � supprimer
	public static void suppCategorie() {
		System.out.println("Suppression d'une cat�gorie: \n");
		
		System.out.println("id_categorie: ");
		int id_categorie = sc.nextInt();
		
		tCategorie.suppCategorieSQL(id_categorie);
	}
}
