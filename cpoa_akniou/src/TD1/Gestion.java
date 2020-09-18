package TD1;
import java.util.Scanner;

public class Gestion {
	private static Scanner sc = new Scanner(System.in);
	
	//Menu principal, avec le choix des differentes actions possibles
	public static void action() {
		System.out.println("\nQue souhaitez vous faire: \n");
		System.out.println("1. Afficher une table");
		System.out.println("2. Ajouter des �l�ments � une table");
		System.out.println("3. Supprimer un element d'une table");
		System.out.println("4. Modifier un �l�ment d'une table");
		System.out.println("Choix n�: ");
		int choix = sc.nextInt();
		
		switch (choix) {
			case 1:
				choixAffTable();
				break;
			case 2:	
				choixAddTable();
				break;
			case 3: 
				choixSuppTable();
				break;
			case 4: 
				choixEditTable();
				break;
		}
	}
	
	//Choix de la table � afficher
	public static void choixAffTable() {
		System.out.println("\nQuelle table souhaitez vous afficher: \n");
		System.out.println("1. Cat�gorie");
		System.out.println("2. Client");
		System.out.println("3. Commande");
		System.out.println("4. Ligne_commande");
		System.out.println("5. Produit");
		System.out.println("6. Retour");
		System.out.println("Choix n�: ");
		int choix = sc.nextInt();
		
		switch (choix) {
		case 1:
			System.out.println("\nAffichage de la table Categorie: \n");
			tCategorie.affCategorie();
			Gestion.action();
		case 2:
			System.out.println("\nAffichage de la table Client: \n");
			tClient.affClient();
			Gestion.action();
		case 3:
			System.out.println("\nAffichage de la table Commande: \n");
			tCommande.affCommande();
			Gestion.action();
		case 4:
			System.out.println("\nAffichage de la table Ligne_commande: \n");
			tLigne_commande.affLigne_commande();
			Gestion.action();
		case 5:
			System.out.println("\nAffichage de la table Produit: \n");
			tProduit.affProduit();
			Gestion.action();
		case 6:
			System.out.println("\nRetour");
			Gestion.action();
		
		}
		
	}
	
	//Choix de la table dans laquelle on va ajouter un �l�ment
	public static void choixAddTable() {
		System.out.println("\nDans quelle table souhaitez vous ajouter un �l�ment: \n");
		System.out.println("1. Cat�gorie");
		System.out.println("2. Client");
		System.out.println("3. Commande");
		System.out.println("4. Ligne_commande");
		System.out.println("5. Produit");
		System.out.println("6. Retour");
		System.out.println("Choix n�: ");
		int choix = sc.nextInt();
		
		switch (choix) {
		case 1:
			tCategorie.affCategorie();
			tCategorie.ajouterCategorie();
			Gestion.action();
		case 2:
			tClient.affClient();
			tClient.ajouterClient();
			Gestion.action();
		case 3:
			tCommande.affCommande();
			tCommande.ajouterCommande();
			Gestion.action();
		case 4:
			tLigne_commande.affLigne_commande();
			tLigne_commande.ajouterLigne_commande();
			Gestion.action();
		case 5:
			tProduit.affProduit();
			tProduit.ajouterProduit();
			Gestion.action();
		case 6:
			System.out.println("Retour");
			Gestion.action();
		}
	}
	
	//Choix de la table dans laquelle on va supprimer un �l�ment
	public static void choixSuppTable() {
		System.out.println("\nDans quelle table souhaitez vous supprimer un �l�ment: \n");
		System.out.println("1. Cat�gorie");
		System.out.println("2. Client");
		System.out.println("3. Commande");
		System.out.println("4. Ligne_commande");
		System.out.println("5. Produit");
		System.out.println("6. Retour");
		System.out.println("Choix n�: ");
		int choix = sc.nextInt();
		
		switch (choix) {
		case 1:
			tCategorie.affCategorie();
			tCategorie.suppCategorie();
			Gestion.action();
		case 2:
			tClient.affClient();
			tClient.suppClient();
			Gestion.action();
		case 3:
			tCommande.affCommande();
			tCommande.suppCommande();
			Gestion.action();
		case 4:
			tLigne_commande.affLigne_commande();
			tLigne_commande.suppLigne_commande();
			Gestion.action();
		case 5:
			tProduit.affProduit();
			tProduit.suppProduit();
			Gestion.action();
		case 6:
			System.out.println("Retour");
			Gestion.action();
		}
	}
	
	//Choix de la table dans laquelle on va editer un �l�ment
	public static void choixEditTable() {
		System.out.println("\nDans quelle table souhaitez vous modifier un �l�ment: \n");
		System.out.println("1. Cat�gorie");
		System.out.println("2. Client");
		System.out.println("3. Commande");
		System.out.println("4. Ligne_commande");
		System.out.println("5. Produit");
		System.out.println("6. Retour");
		System.out.println("Choix n�: ");
		int choix = sc.nextInt();
		
		switch (choix) {
		case 1:
			tCategorie.affCategorie();
			tCategorie.editCategorie();
			Gestion.action();
		case 2:
			tClient.affClient();
			tClient.editClient();
			Gestion.action();
		case 3:
			tCommande.affCommande();
			tCommande.editCommande();
			Gestion.action();
		case 4:
			tLigne_commande.affLigne_commande();
			tLigne_commande.editLigne_commande();
			Gestion.action();
		case 5:
			tProduit.affProduit();
			tProduit.editProduit();
			Gestion.action();
		case 6:
			System.out.println("Retour");
			Gestion.action();
		}
	}
}
