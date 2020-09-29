package td2.dao;

public abstract class DAOFactory {
    public static DAOFactory getDAOFactory(Persistance cible){
        DAOFactory daoF = null;
        switch (cible){
            case MySQL:
                daoF = new MySQLDAOFactory();
                break;
            case ListeMemoire:
                daoF = new ListeMemoireDAOFactory();
                break;
        }
        return daoF;
    }
    
    public abstract CategorieDAO getCategorieDAO();
    public abstract ClientDAO getClientDAO();
    public abstract CommandeDAO getCommandeDAO();
    public abstract ProduitDAO getProduitDAO();
}
