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

    public static void insert() {
        sc = new Scanner(System.in);
        
        System.out.println("Veuillez saisir les attributs de la ligne à ajouter: ");
        System.out.println("ID Commande:");
        int idCommande = sc.nextInt();
    
        System.out.println("ID Produit:");
        int idProduit = sc.nextInt();
    
        System.out.println("Quantite: ");
        int quantite = sc.nextInt();
    
        System.out.println("Tarif unitaire:");
        double tarifUnitaire = sc.nextDouble();
    
        try {
            LigneCommande ligneCommande = new LigneCommande(quantite, tarifUnitaire);
            if(daos.getLigneCommandeDAO().insert(idCommande, idProduit, ligneCommande)) {
                System.out.println("Ligne ajoutée avec succes !");
            } else {
                throw new IllegalArgumentException("Impossible d'ajouter cette ligne de commande");
            }
    
        } catch(SQLException sqle) {
            System.out.println("Erreur:\n" + sqle);
        }
    }
    
    public static void delete() {
        sc = new Scanner(System.in);
        
        System.out.println("Veuillez saisir les attributs de la ligne à supprimer: ");
        System.out.println("ID Commande: ");
        int idCommande = sc.nextInt();
    
        System.out.println("ID Produit: ");
        int idProduit = sc.nextInt();
    
        try {
            if(daos.getLigneCommandeDAO().delete(idCommande, idProduit)){
                System.out.println("Ligne supprime avec succes");
            } else {
                throw new IllegalArgumentException("Impossible de supprimer cette ligne");
            }
        } catch(SQLException sqle) {
            System.out.println("Erreur:\n" + sqle);
        }
    }
    
    public static void update() {
        sc = new Scanner(System.in);
        
        System.out.println("Veuillez saisir les attributs de la ligne à modifier: ");
        System.out.println("ID Commande:");
        int idCommande = sc.nextInt();
    
        System.out.println("ID Produit:");
        int idProduit = sc.nextInt();
    
        System.out.println("Quantite: ");
        int quantite = sc.nextInt();
    
        System.out.println("Tarif unitaire:");
        double tarifUnitaire = sc.nextDouble();
    
        try {
            LigneCommande ligneCommande = new LigneCommande(quantite, tarifUnitaire);
            if(daos.getLigneCommandeDAO().update(idCommande, idProduit, ligneCommande)) {
                System.out.println("Ligne modifiee avec succes !");
            } else {
                throw new IllegalArgumentException("Impossible d'ajouter cette ligne de commande");
            }
    
        } catch(SQLException sqle) {
            System.out.println("Erreur:\n" + sqle);
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
                System.out.println("Ajout d'un element a la table:\n");
                insert();
                selection();
                break;
            case 3:
                System.out.println("Modification d'un element a la table:\n");
                update();
                selection();
                break;
            case 4:
                System.out.println("Suppression d'un element a la table:\n");
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
