package td2.dao;

import java.util.ArrayList;
import java.util.List;
import td2.pojo.Categorie;

public class ListeMemoireCategorieDAO implements CategorieDAO{

    private static ListeMemoireCategorieDAO instance;
    private List<Categorie> donnees;

    public static ListeMemoireCategorieDAO getInstance(){
        if (instance == null){
            instance = new ListeMemoireCategorieDAO();
        }
        return instance;
    }

    public boolean insert(Categorie objet){
        objet.setId(1);
        while(this.donnees.contains(objet)){
            objet.setId(objet.getId()+1);
        }
        boolean ok = this.donnees.add(objet);
        return ok;
    }

    public boolean delete(Categorie objet){
        boolean ok;
        int idx = this.donnees.indexOf(objet);
        if (idx == -1){
			throw new IllegalArgumentException("Tentative de suppression d'une categorie inexistante");
        }
        else{
            ok = this.donnees.remove(objet);
        }
        return ok;
    }

    public boolean update(Categorie objet){
        int idx = this.donnees.indexOf(objet);
        if (idx == -1){
            throw new IllegalArgumentException("Tentative de modification d'une catégorie inexistante");
        }
        else{
            this.donnees.set(idx, objet);
        }
        return true;
    }

    public Categorie getById(int id) {
        int idx = this.donnees.indexOf(new Categorie(id, "", ""));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucune categorie ne possede cet id");
        }
        else {
            return this.donnees.get(idx);
        }
    }

    public ArrayList<Categorie> getAll(){
        return (ArrayList<Categorie>) this.donnees;
    }
}