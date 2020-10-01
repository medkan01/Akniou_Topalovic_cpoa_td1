package td2.vue;

import td2.pojo.*;
import java.sql.SQLException;
import java.util.*;
import td2.dao.*;

public class VueProduit {
    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    static Scanner scanLn;
    static Scanner sc;
    
    public static String afficherTable(){
        ArrayList<Produit> liste = new ArrayList<Produit>();
        String afficher = "";
        try{
            liste = daos.getProduitDAO().getAll();
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
        for(int i = 0; i < liste.size();i++){
            Produit objet = liste.get(i);
            afficher=afficher+"["+objet.getId()+", "+objet.getNom()+", "+objet.getDescription()+", "+objet.getTarif()+", "+objet.getVisuel()+", "+objet.getIdCategorie()+"]\n";
        }
        return afficher;
    }

    public static void insert(){
        sc = new Scanner(System.in);
        scanLn = new Scanner(System.in);

        System.out.println("Veuillez saisir les attributs du produit a ajouter:\n");

        System.out.println("Nom: ");
        scanLn.reset();
        String nom = scanLn.nextLine();

        System.out.println("Description: ");
        scanLn.reset();
        String description = scanLn.nextLine();

        System.out.println("Tarif: ");
        double tarif = sc.nextDouble();

        System.out.println("Visuel: ");
        String visuel = sc.next();

        System.out.println("ID Categorie: ");
        int idCategorie = sc.nextInt();
        try{
            if (daos.getProduitDAO().insert(new Produit(1, nom, description, tarif, visuel, idCategorie)) == true){
                System.out.println("Produit ajoute avec succes");
            }
            else{
                System.out.println("Impossible d'ajouter ce produit");
            }
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
    }
    
    public static void update(){
        sc = new Scanner(System.in);
        scanLn = new Scanner(System.in);

        System.out.println("Veuillez saisir les attributs du produit a modifier:\n");

        System.out.println("ID:");
        int id = sc.nextInt();

        System.out.println("Nom: ");
        String nom = scanLn.nextLine();

        System.out.println("Description: ");
        scanLn.reset();
        String description = scanLn.nextLine();

        System.out.println("Tarif: ");
        double tarif = sc.nextDouble();

        System.out.println("Visuel: ");
        String visuel = sc.next();

        System.out.println("ID Categorie: ");
        int idCategorie = sc.nextInt();
        try{
            Produit modifier = daos.getProduitDAO().getById(id);
            if(daos.getProduitDAO().update(new Produit(modifier.getId(),nom, description, tarif, visuel, idCategorie)) == true){
                System.out.println("Produit mis a jour avec succes");
            }
            else{
                System.out.println("Impossible de mettre a jour ce produit");
            }
        } catch (SQLException sqle){
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
    }

    public static void delete(){
        sc = new Scanner(System.in);
        System.out.println("Veuillez saisir l'id de la Produit a supprimer:\n");
        System.out.println("ID: ");
        int id = sc.nextInt();
        try{
            Produit supprimer = daos.getProduitDAO().getById(id);
            if(daos.getProduitDAO().delete(supprimer)==true){
                System.out.println("Produit supprime avec succes");
            }
            else{
                System.out.println("Impossible de supprimer ce produit");
            }
        } catch (SQLException sqle){
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
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
                System.out.println("Produit: \n");
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
