package td2.vue;

import java.util.Scanner;

import td2.dao.DAOFactory;
import td2.dao.Persistance;

public class VuePrincipale {
    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    private static Scanner sc = new Scanner(System.in);

    public static void selection(){
        System.out.println("Quelle table souhaitez vous consulter:\n");
        System.out.println("1. Categorie");
        System.out.println("2. Client");
        System.out.println("3. Commande");
        System.out.println("4. Produit");
        System.out.println("5. Fermer le programme\n");

        int choix = sc.nextInt();
        switch(choix){
            case 1:
                System.out.println("Table Categorie:\n");
                VueCategorie.selection();
                break;
            case 2:
                System.out.println("Table Client:\n");
                VueClient.selection();
                break;               
            case 3:
                System.out.println("Table Commande:\n");
                VueCommande.selection();
                break;
            case 4:
                System.out.println("Table Produit:\n");
                VueProduit.selection();
                break;
            case 5:
                System.out.println("A bientot !\n");
                break;
            default:
                System.out.println("Choix inconnu, veuillez reessayer:\n");
                selection();
                break;
        }
    }


}