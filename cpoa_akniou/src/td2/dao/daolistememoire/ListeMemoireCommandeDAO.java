package td2.dao.daolistememoire;

import java.time.LocalDate;
import java.util.*;
import td2.dao.daofactory.CommandeDAO;
import td2.pojo.*;

public class ListeMemoireCommandeDAO implements CommandeDAO {

    private static ListeMemoireCommandeDAO instance;
    private List<Commande> donnees;

    public static ListeMemoireCommandeDAO getInstance() {
        if (instance == null) {
            instance = new ListeMemoireCommandeDAO();
        }
        return instance;
    }

    private ListeMemoireCommandeDAO() {
        donnees = new ArrayList<Commande>();
        Produit p1 = new Produit(2, "Pomme","Pomme verte",1,"Pomme.jpg",3);
        Produit p2 = new Produit(3, "Pommmme","Pomme verte",1,"Pomme.jpg",3);
        LigneCommande l1 = new LigneCommande(2, 1);
        LigneCommande l2 = new LigneCommande(3, 1);
        Commande commande1 = new Commande(1, LocalDate.now(), 1);
        Commande commande2 = new Commande(2, LocalDate.now(), 3);
        commande1.ajouterLigne(p1, l1);
        commande2.ajouterLigne(p2, l2);
        this.donnees.add(commande1);
        this.donnees.add(commande2);
	}

    public boolean insert(Commande objet){
		objet.setId(this.donnees.get(donnees.size()-1).getId()+1);
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	public boolean delete(Commande objet) {
        if(objet.getId()<=0) return false;
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
        if(objet.getId()<=0) return false;
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
}