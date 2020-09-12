package cpoa_akniou;
import java.util.Scanner;

public class Gestion {
	private static Scanner sc = new Scanner(System.in);
	
	public static void action() {
		System.out.println("Que souhaitez vous faire: \n");
		System.out.println("1. Afficher une table");
		System.out.println("2. Ajouter des éléments à une table");
		System.out.println("3. Supprimer un élément d'une table");
		System.out.println("4. Modifier un élément d'une table");
		System.out.println("Choix n°: ");
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
	
	public static void choixAffTable() {
		System.out.println("Quelle table souhaitez vous afficher: \n");
		System.out.println("1. Catégorie");
		System.out.println("2. Client");
		System.out.println("3. Commande");
		System.out.println("4. Ligne_commande");
		System.out.println("5. Produit");
		System.out.println("6. Retour");
		System.out.println("Choix n°: ");
		int choix = sc.nextInt();
		
		switch (choix) {
		case 1:
			System.out.println("Affichage de la table Categorie: \n");
			tCategorie.affCategorie();
			Gestion.action();
		case 2:
			System.out.println("Affichage de la table Client: \n");
			tClient.affClient();
			Gestion.action();
		case 3:
			System.out.println("Affichage de la table Commande: \n");
			tCommande.affCommande();
			Gestion.action();
		case 4:
			System.out.println("Affichage de la table Ligne_commande: \n");
			tLigne_commande.affLigne_commande();
			Gestion.action();
		case 5:
			System.out.println("Affichage de la table Produit: \n");
			tProduit.affProduit();
			Gestion.action();
		case 6:
			System.out.println("Retour");
			Gestion.action();
		
		}
		
	}
	
	public static void choixAddTable() {
		
	}
	
	public static void choixSuppTable() {
	
	}
	
	public static void choixEditTable() {
	
	}
}
