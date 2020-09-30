package td2.vue;

import td2.pojo.*;
import java.sql.SQLException;
import java.util.*;
import td2.dao.*;

public class VueProduit {
    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    private static Scanner sc = new Scanner(System.in);
    private static Scanner scanLn = new Scanner(System.in);
    
    public static String afficherTableProduit(){
        ArrayList<Produit> liste = new ArrayList<Produit>();
        String afficher = "";
        try{
            liste = daos.getProduitDAO().getAll();
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
        for(int i = 0; i < liste.size();i++){
            afficher = afficher+afficherProduit(liste.get(i));
        }
        return afficher;
    }

    public static void insert(){
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
        System.out.println("Veuillez saisir les attributs du produit a modifier:\n");

        System.out.println("ID:");
        int id = sc.nextInt();

        System.out.println("Nom: ");
        Scanner scan = new Scanner(System.in);
        String nom = scan.nextLine();

        System.out.println("Description: ");
        scan.reset();
        String description = scan.nextLine();

        System.out.println("Tarif: ");
        double tarif = sc.nextDouble();

        System.out.println("Visuel: ");
        String visuel = sc.next();

        System.out.println("ID Categorie: ");
        int idCategorie = sc.nextInt();

        scan.close();
        try{
            if(daos.getProduitDAO().update(new Produit(id,nom, description, tarif, visuel, idCategorie)) == true){
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
        System.out.println("Veuillez saisir l'id de la Produit a supprimer:\n");
        System.out.println("ID: ");
        int id = sc.nextInt();
        try{
            if(daos.getProduitDAO().delete(new Produit(id, "","",0,"",0))==true){
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
        System.out.println("Que souhaitez-vous faire dans cette table: ");
        System.out.println("1. Afficher la table");
        System.out.println("2. Ajouter a la table");
        System.out.println("3. Modifier un element de la table");
        System.out.println("4. Supprimer un element de la table");
        System.out.println("5. Retour");
        int choix = 0;
        choix = sc.nextInt();
        switch(choix){
            case 1:
                System.out.println("Produit: \n");
                System.out.println(afficherTableProduit());
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
