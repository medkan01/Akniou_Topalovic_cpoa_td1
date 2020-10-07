package td2.vue;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
import td2.pojo.LigneCommande;
import td2.pojo.Produit;
import td2.pojo.Commande;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;

public class VueLigneCommande{
    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    private static Scanner sc;

    public static String afficherTable(){

        sc = new Scanner(System.in);

        System.out.println("Entrez l'ID de la commande:\n");
        int id = sc.nextInt();
        String afficher = "ID de la commande affichée: " + id + "\n";
        try {
            Commande commande = daos.getCommandeDAO().getById(id);
            HashMap<Produit, LigneCommande> liste = daos.getLigneCommandeDAO().getAll(commande.getId());
            commande.setLigneCommande(liste);
            afficher = commande.afficher();
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n" + sqle.getMessage());
        }
        return afficher;
    }

    public static void selection(){
        sc = new Scanner(System.in);
        System.out.println("Que souhaitez-vous faire dans cette table: ");
        System.out.println("1. Afficher la table");
        System.out.println("2. Ajouter a la table");
        System.out.println("3. Modifier un element de la table");
        System.out.println("4. Supprimer un element de la table");
        System.out.println("5. Changer de persistance");
        System.out.println("6. Retour\n");
        System.out.println("Persistance actuelle: "+ DAOFactory.getPersistanceActuelle()+"\n");

        int choix = sc.nextInt();
        switch(choix){
            case 1:
                System.out.println("Affichage des lignes d'une commande:\n");
                System.out.println(afficherTable());
                selection();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
}
