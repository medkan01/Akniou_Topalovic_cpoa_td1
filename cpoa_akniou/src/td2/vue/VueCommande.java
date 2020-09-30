package td2.vue;

import java.util.Scanner;

import td2.dao.DAOFactory;
import td2.dao.Persistance;
import td2.pojo.Commande;

public class VueCommande {
    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    static Scanner sc = new Scanner(System.in);

    public static String afficherCommande(Commande commande){
      //  Commande element = commande;
        return null;
        //try{
        //   element = daos.get
      //  } catch 
    }
}
