package td2.dao;

public class MySQLDAOFactory extends DAOFactory{
    public CategorieDAO getCategorieDAO(){
        return MySQLCategorieDAO.getInstance();
    }

    public ClientDAO getClientDAO(){
        return MySQLClientDAO.getInstance();
    }

    public CommandeDAO getCommandeDAO(){
        return MySQLCommandeDAO.getInstance();
    }

    public ProduitDAO getProduitDAO(){
        return MySQLProduitDAO.getInstance();
    }
}