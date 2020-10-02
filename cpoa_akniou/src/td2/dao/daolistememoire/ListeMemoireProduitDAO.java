package td2.dao.daolistememoire;

import java.util.ArrayList;
import java.util.List;
import td2.dao.daofactory.ProduitDAO;
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
		objet.setId(this.donnees.get(donnees.size()-1).getId()+1);
		boolean ok = this.donnees.add(objet);
		return ok;
	}
    
    private ListeMemoireProduitDAO() {
        donnees = new ArrayList<Produit>();
        this.donnees.add(new Produit(1, "Sonic te claque", "Sonic te claque parce qu'il est mechant", 25.2, "sonic.png", 1));
		this.donnees.add(new Produit(2, "MarioGros", "Il a grossi", 25.2, "mario.png", 1));
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
        this.donnees.set(this.donnees.indexOf(getById(objet.getId())), objet);
        return true;
    }

	public Produit getById(int id) {
        for (int i = 0; i<this.donnees.size();i++){
            if(this.donnees.get(i).getId()==id){
                return this.donnees.get(i);
            }
        }
		throw new IllegalArgumentException("Aucun produit ne possède cet identifiant");
	}

    public ArrayList<Produit> getAll(){
        return (ArrayList<Produit>) this.donnees;
    }
}