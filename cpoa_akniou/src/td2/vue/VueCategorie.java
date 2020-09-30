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
        Categorie element = categorie;
        try {
            element = daos.getCategorieDAO().getById(categorie.getId());
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
        String afficher = "["+element.getId()+", "+element.getTitre()+", "+element.getVisuel()+"]\n";
        return afficher;
    }

    public static String afficherTableCategorie(){
        ArrayList<Categorie> liste = new ArrayList<Categorie>();
        String afficher = "";
        try{
            liste = daos.getCategorieDAO().getAll();
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
        for(int i = 0; i < liste.size();i++){
            afficher = afficher+afficherCategorie(liste.get(i));
        }
        return afficher;
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
        String titre = sc.next();
        System.out.println("Visuel: ");
        String visuel = sc.next();
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

    public static void selection(){
        System.out.println("Que souhaitez-vous faire dans cette table: ");
        System.out.println("1. Afficher la table");
        System.out.println("2. Ajouter a la table");
        System.out.println("3. Modifier un element de la table");
        System.out.println("4. Supprimer un element de la table");
        System.out.println("5. Retour");

        int choix = sc.nextInt();
        choix = 0;
        choix = sc.nextInt();
        switch(choix){
            case 1:
                System.out.println("Categorie: \n");
                System.out.println(afficherTableCategorie());
                selection();
                break;
            case 2:
                insert();
                selection();
                break;
            case 3:
                update();
                break;
            case 4:
                delete();
                selection();
                break;
            case 5:
                System.out.println("Retour...");
                break;
            default:
                System.out.println("Choix invalide, veuillez réessayer");
                selection();
                break;

        }
    }
}