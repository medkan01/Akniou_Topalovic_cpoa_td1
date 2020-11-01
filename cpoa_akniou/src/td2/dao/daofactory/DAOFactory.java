package td2.dao.daofactory;

public abstract class DAOFactory {

    private static String persistanceActuelle;
    
    public static DAOFactory getDAOFactory(Persistance cible){
        DAOFactory daoF = null;
        switch (cible){
            case MySQL:
                daoF = new MySQLDAOFactory();
                persistanceActuelle = "En Ligne";
                break;
            case ListeMemoire:
                daoF = new ListeMemoireDAOFactory();
                persistanceActuelle = "Local";
                break;
        }
        return daoF;
    }
    
    public static String getPersistanceActuelle(){
        return persistanceActuelle;
    }

    public abstract CategorieDAO getCategorieDAO();
    public abstract ClientDAO getClientDAO();
    public abstract CommandeDAO getCommandeDAO();
    public abstract ProduitDAO getProduitDAO();
    public abstract LigneCommandeDAO getLigneCommandeDAO();
}
