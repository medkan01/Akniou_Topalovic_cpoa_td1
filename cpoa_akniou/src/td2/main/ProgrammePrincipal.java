package td2.main;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Commande;
import td2.pojo.LigneCommande;
import td2.pojo.Produit;
import td2.vue.VuePrincipale;
public class ProgrammePrincipal {

    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);

    public static void main(String args[]) {
        try{
            Commande commande = daos.getCommandeDAO().getById(1);
            HashMap<Produit, LigneCommande> liste = daos.getLigneCommandeDAO().getAll(commande.getId()); 
            
        } catch (SQLException sqle) {
            System.out.println("Erreur:\n"+sqle);
        }
    }
}