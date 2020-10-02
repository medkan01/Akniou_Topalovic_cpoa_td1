package td2.dao.daolistememoire;

import java.util.ArrayList;
import td2.dao.daofactory.CategorieDAO;
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
        this.donnees.set(this.donnees.indexOf(getById(objet.getId())), objet);
        return true;
    }
    
	public Categorie getById(int id) {
        for (int i = 0; i<this.donnees.size();i++){
            if(this.donnees.get(i).getId()==id){
                return this.donnees.get(i);
            }
        }
		throw new IllegalArgumentException("Aucune produit ne possède cet identifiant");
    }

    public ArrayList<Categorie> getAll(){
        return (ArrayList<Categorie>) donnees;
    }
}