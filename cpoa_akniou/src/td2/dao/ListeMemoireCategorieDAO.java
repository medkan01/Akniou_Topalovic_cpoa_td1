package td2.dao;

import java.util.ArrayList;
import td2.pojo.Categorie;

public class ListeMemoireCategorieDAO implements CategorieDAO{

    private static ListeMemoireCategorieDAO instance;
    private ArrayList<Categorie> donnees;

    public static ListeMemoireCategorieDAO getInstance(){
        if (instance == null){
            instance = new ListeMemoireCategorieDAO();
        }
        return instance;
    }

    private ListeMemoireCategorieDAO() {
        donnees = new ArrayList<Categorie>();
        this.donnees.add(new Categorie(1, "Pulls", "pulls.png"));
		this.donnees.add(new Categorie(2, "Bonnets", "bonnets.png"));
	}

    public boolean insert(Categorie objet){
		objet.setId(this.donnees.get(donnees.size()-1).getId()+1);
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	public boolean delete(Categorie objet) {
		Categorie supprime;
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une categorie inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

    public boolean update(Categorie objet){
        int idx = objet.getId()-1;
        if (idx == -1){
            throw new IllegalArgumentException("Tentative de modification d'une catégorie inexistante");
        }
        else{
            this.donnees.set(idx, objet);
        }
        return true;
    }
    
	public Categorie getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(this.donnees.get(id-1));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune categorie ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

    public ArrayList<Categorie> getAll(){
        return (ArrayList<Categorie>) donnees;
    }
}