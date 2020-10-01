package td2.vue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import td2.dao.DAOFactory;
import td2.dao.Persistance;
import td2.pojo.Commande;
import td2.pojo.LigneCommande;
import td2.pojo.Produit;

public class VueCommande {
    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    private static Scanner sc;

    public static String afficherTable(){
       ArrayList<Commande> liste = new ArrayList<Commande>();
       String afficher = "";
       try{
            liste = daos.getCommandeDAO().getAll();
       } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n" + sqle.getMessage());
       }
       for(int i = 0; i < liste.size(); i++){
            Commande objet = liste.get(i);
            afficher = afficher + objet.afficher();
            afficher = afficher + "\n----------------------\n";
       }
       return afficher;
    }

    public static void insert(){
        sc = new Scanner(System.in);
        System.out.println("\nVeuillez saisir les attributs de la categorie a modifier:\n");
        System.out.println("ID Client:");
        int idClient = sc.nextInt();
        System.out.println("ID Produit:");
        int idProduit = sc.nextInt();
        System.out.println("Quantite:");
        int quantite = sc.nextInt();
        System.out.println("Tarif unitaire:");
        double tarifUnitaire = sc.nextDouble();

        try {
            Produit produit = daos.getProduitDAO().getById(idProduit);
            LigneCommande ligneCommande = new LigneCommande(quantite, tarifUnitaire);
            Commande commande = new Commande(1, LocalDate.now(), idClient);
            commande.ajouterLigne(produit, ligneCommande);
            if(daos.getCommandeDAO().insert(commande)==true){
                System.out.println("Commande ajoutée avec succes");
            } else {
                System.out.println("Impossible d'ajouter cette commande");
            }
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL;\n" + sqle.getMessage());
        }
    }
    
    public static void update(){
        
    }

    public static void delete(){
        
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
                System.out.println("Commande: \n");
                System.out.println(afficherTable());
                selection();
                break;
            case 2:
                insert();
                selection();
                break;
            case 3:
                update();
                selection();
                break;
            case 4:
                delete();
                selection();
                break;
            case 5:
                System.out.println("Changement de persistance...");
                if (DAOFactory.getPersistanceActuelle() == "MySQL"){
                    daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
                } else {
                    daos = DAOFactory.getDAOFactory(Persistance.MySQL);
                }
                selection();
                break;
            case 6:
                System.out.println("Retour...");
                VuePrincipale.selection();
                break;
            default:
                System.out.println("Choix invalide, veuillez réessayer");
                selection();
                break;

        }
    }
}
