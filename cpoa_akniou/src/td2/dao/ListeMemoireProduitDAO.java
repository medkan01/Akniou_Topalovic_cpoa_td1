package td2.dao;

import java.util.ArrayList;
import java.util.List;
import td2.pojo.Produit;

public class ListeMemoireProduitDAO implements ProduitDAO {

    private static ListeMemoireProduitDAO instance;
    private List<Produit> donnees;

    public static ListeMemoireProduitDAO getInstance(){
        if (instance == null){
            instance = new ListeMemoireProduitDAO();
        }
        return instance;
    }

    public boolean insert(Produit objet){
        objet.setId(1);
        while(this.donnees.contains(objet)){
            objet.setId(objet.getId()+1);
        }
        boolean ok = this.donnees.add(objet);
        return ok;
    }

	public boolean delete(Produit objet) {
		Produit supprime;
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un produit inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

    public boolean update(Produit objet){
        int idx = objet.getId()-1;
        if (idx == -1){
            throw new IllegalArgumentException("Tentative de modification d'un produit inexistant");
        }
        else{
            this.donnees.set(idx, objet);
        }
        return true;
    }

	public Produit getById(int id) {
		int idx = this.donnees.indexOf(this.donnees.get(id-1));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune produit ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

    public ArrayList<Produit> getAll(){
        return (ArrayList<Produit>) this.donnees;
    }
}