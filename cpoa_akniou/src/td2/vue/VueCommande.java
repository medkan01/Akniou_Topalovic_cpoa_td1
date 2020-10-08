package td2.vue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Commande;

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
            afficher = afficher + "[ID Commande: "+objet.getId()+", Date: "+objet.getDate().toString()+", ID Client: "+objet.getIdClient()+"]\n";
       }
       return afficher;
    }

    public static void insert(){
        sc = new Scanner(System.in);
        System.out.println("\nVeuillez saisir les attributs de la commande a ajouter:\n");
        System.out.println("ID Client:");
        int idClient = sc.nextInt();

        try {
            Commande commande = new Commande(1, LocalDate.now(), idClient);
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
        sc = new Scanner(System.in);
        System.out.println("\nVeuillez saisir les attributs de la commande a modifier:\n");
        System.out.println("ID Commande: ");
        int idCommande = -1;
        do{
            idCommande = sc.nextInt();
            if(idCommande<=0) System.out.println("L'ID saisie n'est pas valide, veuillez saisir un ID strictement positif");
        } while (idCommande<=0);
        
        System.out.println("ID Client: ");
        int idClient = -1;
        do{
            idClient = sc.nextInt();
            if(idClient<=0) System.out.println("L'ID saisie n'est pas valide, veuillez saisir un ID strictement positif");
        } while (idClient<=0);
        

        try {
            Commande commande = new Commande(idCommande, LocalDate.now(), idClient);
            if (daos.getCommandeDAO().update(commande)){
                System.out.println("Commande ajoutee avec succes");
            } else {
                System.out.println("Impossible d'ajouter cette commande");
            }
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n" + sqle.getMessage());
        }
    }

    public static void delete(){
        sc = new Scanner(System.in);
        System.out.println("\nVeuillez saisir les attributs de la commande a modifier:\n");
        System.out.println("ID: ");
        int idCommande = -1;
        do{
            idCommande = sc.nextInt();
            if(idCommande<=0) System.out.println("L'ID saisie n'est pas valide, veuillez saisir un ID strictement positif");
        } while (idCommande<=0);
        

        try {
            Commande commande = daos.getCommandeDAO().getById(idCommande);
            if (daos.getCommandeDAO().delete(commande)){
                System.out.println("Commande supprimee avec succes");
            } else {
                System.out.println("Impossible d'ajouter cette commande");
            }
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n" + sqle.getMessage());
        }
    }

    public static void selection(){
        sc = new Scanner(System.in);
        System.out.println("Que souhaitez-vous faire dans cette table: ");
        System.out.println("1. Afficher la table");
        System.out.println("2. Ajouter a la table");
        System.out.println("3. Modifier un element de la table");
        System.out.println("4. Supprimer un element de la table");
        System.out.println("5. Changer de persistance");
        System.out.println("6. Acceder aux lignes de commandes");
        System.out.println("7. Retour\n");
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
                System.out.println("Table LigneCommande:\n");
                VueLigneCommande.selection();
                break;
            case 7:
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
