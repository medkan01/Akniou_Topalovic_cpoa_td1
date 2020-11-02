package td2.dao.daolistememoire;

import java.time.LocalDate;
import java.util.*;
import td2.dao.daofactory.CommandeDAO;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.*;

public class ListeMemoireCommandeDAO implements CommandeDAO {

    private static ListeMemoireCommandeDAO instance;
    private List<Commande> donnees;
    DAOFactory daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);

    public static ListeMemoireCommandeDAO getInstance() {
        if (instance == null) {
            instance = new ListeMemoireCommandeDAO();
        }
        return instance;
    }

    private ListeMemoireCommandeDAO() {
        try{
            donnees = new ArrayList<Commande>();
            Produit p1 = daos.getProduitDAO().getById(1);
            Produit p2 = daos.getProduitDAO().getById(2);
            LigneCommande l1 = new LigneCommande(2, p1.getTarif());
            LigneCommande l2 = new LigneCommande(3, p2.getTarif());
            Commande commande1 = new Commande(1, LocalDate.now(), 1);
            commande1.ajouterLigne(p1, l1);
            commande1.ajouterLigne(p2, l2);
            this.donnees.add(commande1);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

	}

    public boolean insert(Commande objet){
		objet.setId(this.donnees.get(donnees.size()-1).getId()+1);
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	public boolean delete(Commande objet) {
        if(objet.getId()<=0) throw new IllegalArgumentException("ID Incorrect");
		Commande supprime;
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		return objet.equals(supprime);
	}

    public boolean update(Commande objet){
        if(objet.getId()<=0) throw new IllegalArgumentException("ID Incorrect");
        this.donnees.set(this.donnees.indexOf(getById(objet.getId())), objet);
        return true;
    }

    public Commande getById(int id) {
        if(id<=0) throw new IllegalArgumentException("ID Incorrect");
        for (int i = 0; i<this.donnees.size();i++){
            if(this.donnees.get(i).getId()==id){
                return this.donnees.get(i);
            }
        }
		throw new IllegalArgumentException("Aucune produit ne possède cet identifiant");
    }

    public ArrayList<Commande> getAll(){
        return (ArrayList<Commande>) this.donnees;
    }

    public ArrayList<Commande> getByIdClient(int idClient){
        ArrayList<Commande> listeAll = this.getAll();
        ArrayList<Commande> listeCommande = new ArrayList<Commande>();

        for(int i=0;i<listeAll.size();i++){
            if(listeAll.get(i).getIdClient() == idClient){
                listeCommande.add(listeAll.get(i));
            }
        }
        return listeCommande;
    }
}