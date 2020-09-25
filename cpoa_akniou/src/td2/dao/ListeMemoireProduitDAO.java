package td2.dao;

import java.util.List;
import td2.pojo.Produit;

public class ListeMemoireProduitDAO {

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

    public boolean delete(Produit objet){
        boolean ok;
        int idx = this.donnees.indexOf(objet);
        if (idx == -1){
			throw new IllegalArgumentException("Tentative de suppression d'un produit inexistant");
        }
        else{
            ok = this.donnees.remove(objet);
        }
        return ok;
    }

    public boolean update(Produit objet){
        int idx = this.donnees.indexOf(objet);
        if (idx == -1){
            throw new IllegalArgumentException("Tentative de modification d'un produit inexistant");
        }
        else{
            this.donnees.set(idx, objet);
        }
        return true;
    }

    public Produit getById(int id) {
        int idx = this.donnees.indexOf(new Produit(id, "", "", 0, "", 0));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun produit ne possede cet id");
        }
        else {
            return this.donnees.get(idx);
        }
    }
}