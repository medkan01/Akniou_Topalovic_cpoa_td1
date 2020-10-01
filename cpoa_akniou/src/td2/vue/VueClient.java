package td2.vue;

import java.sql.SQLException;
import java.util.*;
import td2.dao.*;
import td2.pojo.*;

public class VueClient {
    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    private static Scanner sc;
    private static Scanner scanLn;

    public static String afficherTable(){
        ArrayList<Client> liste = new ArrayList<Client>();
        String afficher = "";
        try{
            liste = daos.getClientDAO().getAll();
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
        for(int i = 0; i < liste.size();i++){
            Client cat = liste.get(i);
            afficher=afficher+"["
                +cat.getId()+", "
                +cat.getNom()+", "
                +cat.getPrenom()+", "
                +cat.getId()+", "
                +cat.getIdentifiant()+", "
                +cat.getMotDePasse()+", "
                +cat.getAdrNumero()+", "
                +cat.getAdrVoie()+", "
                +cat.getAdrCodePostal()+", "
                +cat.getAdrVille()+", "
                +cat.getAdrPays()+"]\n";
        }
        return afficher;
    }

    public static void insert(){
        sc = new Scanner(System.in);
        scanLn = new Scanner(System.in);
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
                System.out.println("\nClient ajoute avec succes\n");
            }
            else{
                System.out.println("\nImpossible d'ajouter ce client\n");
            }
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
    }
    
    public static void update(){
        sc = new Scanner(System.in);
        scanLn = new Scanner(System.in);
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
        String adrVoie = scanLn.nextLine();

        System.out.println("Adresse Code Postal: ");
        String adrCodePostal = sc.next();

        System.out.println("Adresse Ville: ");
        String adrVille = sc.next();

        System.out.println("Adresse Pays: ");
        String adrPays = sc.next();
        
        try{
            Client modifier = daos.getClientDAO().getById(id);
            if (daos.getClientDAO().update(new Client(modifier.getId(), nom, prenom,identifiant,motDePasse,adrNumero,adrVoie,adrCodePostal,adrVille,adrPays)) == true){
                System.out.println("\nClient mis a jour avec succes\n");
            }
            else{
                System.out.println("\nImpossible de modifier ce client\n");
            }
        } catch (SQLException sqle) {
            System.out.println("Message d'erreur SQL:\n"+sqle.getMessage());
        }
    }

    public static void delete(){
        sc = new Scanner(System.in);
        System.out.println("Veuillez saisir l'id du client a supprimer:\n");
        System.out.println("ID: ");
        int id = sc.nextInt();
        try{
            Client supprimer = daos.getClientDAO().getById(id);
            if(daos.getClientDAO().delete(supprimer)==true){
                System.out.println("\nClient supprime avec succes\n");
            }
            else{
                System.out.println("\nImpossible de supprimer ce client\n");
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
                System.out.println("Client: \n");
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
