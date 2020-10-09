package td2.main;

import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.vue.VuePrincipale;
public class ProgrammePrincipal {

    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    public static void main(String args[]) {
        VuePrincipale.selection();
    }
}