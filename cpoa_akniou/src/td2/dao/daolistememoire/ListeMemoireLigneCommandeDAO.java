package td2.dao.daolistememoire;

import java.util.HashMap;

import td2.dao.daofactory.LigneCommandeDAO;
import td2.pojo.Commande;
import td2.pojo.LigneCommande;
import td2.pojo.Produit;

public class ListeMemoireLigneCommandeDAO implements LigneCommandeDAO {
    private ListeMemoireCommandeDAO commandeInstance = ListeMemoireCommandeDAO.getInstance();
    private ListeMemoireProduitDAO produitInstance = ListeMemoireProduitDAO.getInstance();
    private static ListeMemoireLigneCommandeDAO instance;
    
	public static ListeMemoireLigneCommandeDAO getInstance(){
		if (instance == null){
			instance = new ListeMemoireLigneCommandeDAO();
		}
		return instance;
    }

    public boolean insert(int idCommande, int idProduit, LigneCommande objet){
        Commande commande = commandeInstance.getById(idCommande);
        Produit produit= produitInstance.getById(idProduit);
        commande.ajouterLigne(produit, objet);
        commandeInstance.update(commande);
        return true;
    }

    public boolean update(int idCommande, int idProduit, LigneCommande objet){
        if((idCommande<=0) || (idProduit<=0)) throw new IllegalArgumentException("IDs Incorrects");
        Commande commande = commandeInstance.getById(idCommande);
        Produit produit= produitInstance.getById(idProduit);
        commande.modifierLigne(produit, objet);
        commandeInstance.update(commande);
        return true;
    }
    public boolean delete(int idCommande, int idProduit){
        if((idCommande<=0) || (idProduit<=0)) throw new IllegalArgumentException("IDs Incorrects");
        Commande commande = commandeInstance.getById(idCommande);
        Produit produit= produitInstance.getById(idProduit);
        commande.supprimerLigne(produit);
        commandeInstance.update(commande);
        return true;
    }

    public HashMap<Produit, LigneCommande> getAll(int idCommande){
        Commande commande = commandeInstance.getById(idCommande);
        return commande.getLigneCommande();

    }

    public HashMap<Produit, LigneCommande> getLigneCommandeByIdClient(int idClient){
        
    }
}