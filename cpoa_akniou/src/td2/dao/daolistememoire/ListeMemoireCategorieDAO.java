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
        if(objet.getId()<=0) throw new IllegalArgumentException("ID Incorrect");
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
        if(objet.getId()<=0) throw new IllegalArgumentException("ID Incorrect");
        this.donnees.set(this.donnees.indexOf(getById(objet.getId())), objet);
        return true;
    }
    
	public Categorie getById(int id) {
        if(id<=0) throw new IllegalArgumentException("ID Incorrect");
        for (int i = 0; i<this.donnees.size();i++){
            if(this.donnees.get(i).getId()==id){
                return this.donnees.get(i);
            }
        }
		throw new IllegalArgumentException("Aucune categorie ne possède cet identifiant");
    }

    public Categorie getCategorieByProduit(int idProduit) {
        if(idProduit<=0) throw new IllegalArgumentException("ID Incorrect");
        int idCategorie;
        for (int i = 0; i<this.donnees.size();i++){
            if(this.donnees.get(i).getId()==idProduit){
                idCategorie = this.donnees.get(i).getId();             
                return getById(idCategorie);
            }
        }
        throw new IllegalArgumentException("Aucun produit ne possède cet identifiant");
    }

    public ArrayList<Categorie> getAll(){
        return (ArrayList<Categorie>) donnees;
    }
}