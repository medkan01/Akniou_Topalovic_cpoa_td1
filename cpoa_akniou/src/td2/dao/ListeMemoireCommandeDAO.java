package td2.dao;

import java.util.List;
import td2.pojo.Commande;

public class ListeMemoireCommandeDAO {

    private static ListeMemoireCommandeDAO instance;
    private List<Commande> donnees;

    public static ListeMemoireCommandeDAO getInstance(){
        if (instance == null){
            instance = new ListeMemoireCommandeDAO();
        }
        return instance;
    }

    public boolean insert(Commande objet){
        objet.setId(1);
        while(this.donnees.contains(objet)){
            objet.setId(objet.getId()+1);
        }
        boolean ok = this.donnees.add(objet);
        return ok;
    }

    public boolean delete(Commande objet){
        boolean ok;
        int idx = this.donnees.indexOf(objet);
        if (idx == -1){
			throw new IllegalArgumentException("Tentative de suppression d'une Commande inexistante");
        }
        else{
            ok = this.donnees.remove(objet);
        }
        return ok;
    }

    public boolean update(Commande objet){
        int idx = this.donnees.indexOf(objet);
        if (idx == -1){
            throw new IllegalArgumentException("Tentative de modification d'un catégorie inexistante");
        }
        else{
            this.donnees.set(idx, objet);
        }
        return true;
    }
}