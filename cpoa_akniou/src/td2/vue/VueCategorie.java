package td2.vue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import td2.dao.DAOFactory;
import td2.dao.Persistance;
import td2.pojo.Categorie;

public class VueCategorie {
    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    static Scanner sc = new Scanner(System.in);

    public static String afficherCategorie(Categorie categorie) {
        Categorie afficher = categorie;
        try {
            afficher = daos.getCategorieDAO().getById(categorie.getId());
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
        return afficher.toString();
    }

    public static String afficherTableCategorie(){
        ArrayList<Categorie> liste = new ArrayList<Categorie>();
        try{
            liste = daos.getCategorieDAO().getAll();
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
        String afficher="[";
        for(int i = 0; i < liste.size();i++){
            afficher = afficher+"("+afficherCategorie(liste.get(i))+")";
        }
        afficher = afficher + "]\n";
        return liste.toString();
    }

    public static void insert(){
        System.out.println("Veuillez saisir les attributs de la categorie a ajouter:\n");
        System.out.println("Titre: ");
        String titre = sc.next();
        System.out.println("Visuel: ");
        String visuel = sc.next();
        try{
            if (daos.getCategorieDAO().insert(new Categorie(5, titre, visuel)) == true){
                System.out.println("Categorie ajoutee avec succes");
            }
            else{
                System.out.println("Impossible d'ajouter cette categorie");
            }
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
    }
    
    public static void update(){
        System.out.println("Veuillez saisir les attributs de la categorie a modifier:\n");
        System.out.println("ID: ");
        int id = sc.nextInt();
        System.out.println("Titre: ");
        String titre = sc.nextLine();
        System.out.println("Visuel: ");
        String visuel = sc.nextLine();
        try{
            if(daos.getCategorieDAO().update(new Categorie(id,titre,visuel)) == true){
                System.out.println("Catégorie mise a jour avec succes");
            }
            else{
                System.out.println("Impossible de mettre a jour cette categorie");
            }
        } catch (SQLException sqle){
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
    }

    public static void delete(){
        System.out.println("Veuillez saisir l'id de la categorie a supprimer:\n");
        System.out.println("ID: ");
        int id = sc.nextInt();
        try{
            if(daos.getCategorieDAO().delete(new Categorie(id, "",""))==true){
                System.out.println("Categorie supprimee avec succes");
            }
            else{
                System.out.println("Impossible de supprimer cette categorie");
            }
        } catch (SQLException sqle){
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
    }
}