package td2.dao;

public class ListeMemoireDAOFactory extends DAOFactory{
    public CategorieDAO getCategorieDAO(){
        return ListeMemoireCategorieDAO.getInstance();
    }

    public ClientDAO getClientDAO(){
        return ListeMemoireClientDAO.getInstance();
    }

    public CommandeDAO getCommandeDAO(){
        return ListeMemoireCommandeDAO.getInstance();
    }

    public ProduitDAO getProduitDAO(){
        return ListeMemoireProduitDAO.getInstance();
    }
}
