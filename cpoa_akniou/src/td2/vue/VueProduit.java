package td2.vue;

import td2.pojo.*;
import java.sql.SQLException;
import java.util.*;
import td2.dao.*;

public class VueProduit {
    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    static Scanner sc = new Scanner(System.in);

    public static String afficherProduit(Produit produit) {
        Produit afficher = produit;
        try {
            afficher = daos.getProduitDAO().getById(produit.getId());
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
        return afficher.toString();
    }

    public static String afficherTableProduit(){
        ArrayList<Produit> liste = new ArrayList<Produit>();
        try{
            liste = daos.getProduitDAO().getAll();
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
        String afficher="[";
        for(int i = 0; i < liste.size();i++){
            afficher = afficher+"("+afficherProduit(liste.get(i))+")";
        }
        afficher = afficher + "]\n";
        return liste.toString();
    }

    public static void insert(){
        System.out.println("Veuillez saisir les attributs du produit a ajouter:\n");

        System.out.println("Nom: ");
        String nom = sc.next();

        System.out.println("Description: ");
        String description = sc.nextLine();

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
        String nom = sc.next();

        System.out.println("Description: ");
        String description = sc.nextLine();

        System.out.println("Tarif: ");
        double tarif = sc.nextDouble();

        System.out.println("Visuel: ");
        String visuel = sc.next();

        System.out.println("ID Categorie: ");
        int idCategorie = sc.nextInt();

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
}
