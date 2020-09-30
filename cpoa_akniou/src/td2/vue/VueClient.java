package td2.vue;

import java.sql.SQLException;
import java.util.*;
import td2.dao.*;
import td2.pojo.*;

public class VueClient {
    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    private static Scanner sc = new Scanner(System.in);
    private static Scanner scanLn = new Scanner(System.in);

    public static String afficherTableClient(){
        ArrayList<Client> liste = new ArrayList<Client>();
        String afficher = "";
        try{
            liste = daos.getClientDAO().getAll();
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
        for(int i = 0; i < liste.size();i++){
            afficher = afficher+afficherClient(liste.get(i));
        }
        return afficher;
    }

    public static void insert(){
        System.out.println("Veuillez saisir les attributs du client a ajouter:\n");

        System.out.println("Nom: ");
        String nom = sc.next();

        System.out.println("Prenom: ");
        String prenom = sc.next();

        System.out.println("Identifiant: ");
        String identifiant = sc.next();

        System.out.println("Mot de passe: ");
        String motDePasse = sc.next();

        System.out.println("Adresse Numero: ");
        String adrNumero = sc.next();

        System.out.println("Adresse Voie: ");
        scanLn.reset();
        String adrVoie = scanLn.nextLine();

        System.out.println("Adresse Code Postal: ");
        String adrCodePostal = sc.next();

        System.out.println("Adresse Ville: ");
        String adrVille = sc.next();

        System.out.println("Adresse Pays: ");
        String adrPays = sc.next();

        try{
            if (daos.getClientDAO().insert(new Client(1, nom, prenom,identifiant,motDePasse,adrNumero,adrVoie,adrCodePostal,adrVille,adrPays)) == true){
                System.out.println("Client ajoute avec succes");
            }
            else{
                System.out.println("Impossible d'ajouter ce client");
            }
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
    }
    
    public static void update(){
        System.out.println("Veuillez saisir les attributs du client a modifier:\n");

        System.out.println("ID:");
        int id = sc.nextInt();

        System.out.println("Nom: ");
        String nom = sc.next();

        System.out.println("Prenom: ");
        String prenom = sc.next();

        System.out.println("Identifiant: ");
        String identifiant = sc.next();

        System.out.println("Mot de passe: ");
        String motDePasse = sc.next();

        System.out.println("Adresse Numero: ");
        String adrNumero = sc.next();

        System.out.println("Adresse Voie: ");
        Scanner scan = new Scanner(System.in);
        String adrVoie = scan.nextLine();

        System.out.println("Adresse Code Postal: ");
        String adrCodePostal = sc.next();

        System.out.println("Adresse Ville: ");
        String adrVille = sc.next();

        System.out.println("Adresse Pays: ");
        String adrPays = sc.next();
        
        scan.close();
        try{
            if (daos.getClientDAO().update(new Client(id, nom, prenom,identifiant,motDePasse,adrNumero,adrVoie,adrCodePostal,adrVille,adrPays)) == true){
                System.out.println("Client mis a jour avec succes");
            }
            else{
                System.out.println("Impossible de modifier ce client");
            }
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
    }

    public static void delete(){
        System.out.println("Veuillez saisir l'id du client a supprimer:\n");
        System.out.println("ID: ");
        int id = sc.nextInt();
        try{
            if(daos.getClientDAO().delete(new Client(id, "","","","","","","","",""))==true){
                System.out.println("Client supprime avec succes");
            }
            else{
                System.out.println("Impossible de supprimer ce client");
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
        System.out.println("5. Changer de persistance");
        System.out.println("6. Retour\n");
        System.out.println("Persistance actuelle: "+ DAOFactory.getPersistanceActuelle()+"\n");
        

        int choix = sc.nextInt();
        switch(choix){
            case 1:
                System.out.println("Client: \n");
                System.out.println(afficherTableClient());
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
