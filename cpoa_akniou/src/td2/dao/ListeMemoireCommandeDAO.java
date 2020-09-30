package td2.dao;

import java.util.*;
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

    public boolean insert(Commande objet) {
        objet.setId(1);
        while (this.donnees.contains(objet)) {
            objet.setId(objet.getId() + 1);
        }
        boolean ok = this.donnees.add(objet);
        return ok;
    }

	public boolean delete(Commande objet) {
		Commande supprime;
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

    public boolean update(Commande objet) {
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'une commande inexistante");
        } else {
            this.donnees.set(idx, objet);
        }
        return true;
    }

    public Commande getById(int id) {
        int idx = this.donnees.indexOf(this.donnees.get(id-1));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucune commande ne possede cet id");
        } else {
            return this.donnees.get(idx);
        }
    }

    public ArrayList<Commande> getAll(){
        return (ArrayList<Commande>) this.donnees;
    }
}