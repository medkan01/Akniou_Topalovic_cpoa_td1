package td2.dao.daolistememoire;

import java.util.ArrayList;
import java.util.List;

import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Commande;
import td2.pojo.LigneCommande;
import td2.pojo.Produit;

public class ListeMemoireLigneCommandeDAO {
    private ListeMemoireCommandeDAO commandeInstance = ListeMemoireCommandeDAO.getInstance();
    private ListeMemoireProduitDAO produitInstance = ListeMemoireProduitDAO.getInstance();
    private static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
    private static ListeMemoireLigneCommandeDAO instance;
    private List<Commande> donnees;
    
	public static ListeMemoireLigneCommandeDAO getInstance(){
		if (instance == null){
			instance = new ListeMemoireLigneCommandeDAO();
		}
		return instance;
    }
    
    private ListeMemoireLigneCommandeDAO(){
        donnees = new ArrayList<Commande>();
        donnees = commandeInstance.getAll();
    }

    public boolean insert(int idCommande, int idProduit, LigneCommande objet){
        Commande commande = commandeInstance.getById(idCommande);
        Produit produit= produitInstance.getById(idProduit);
        commande.ajouterLigne(produit, objet);
        commandeInstance.update(commande);
        return true;
    }

    public boolean update(int idCommande, int idProduit, LigneCommande objet){
        Commande commande = commandeInstance.getById(idCommande);
        Produit produit= produitInstance.getById(idProduit);
        commande.modifierLigne(produit, objet);
        commandeInstance.update(commande);
        return true;
    }
    public boolean delete(int idCommande, int idProduit){
        Commande commande = commandeInstance.getById(idCommande);
        Produit produit= produitInstance.getById(idProduit);
        commande.supprimerLigne(produit);
        commandeInstance.update(commande);
        return true;
    }

}