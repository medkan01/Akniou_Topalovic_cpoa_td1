/*
package td2.dao;

import java.util.List;
import td2.pojo.LigneCommande;

public class ListeMemoireLigneCommandeDAO implements LigneCommandeDAO{

    private static ListeMemoireLigneCommandeDAO instance;
    private List<LigneCommande> donnees;

    public static ListeMemoireLigneCommandeDAO getInstance(){
        if (instance == null){
            instance = new ListeMemoireLigneCommandeDAO();
        }
        return instance;
    }

    public boolean insert(LigneCommande objet){
        objet.setIdCommande(1);
        while(this.donnees.contains(objet)){
            objet.setIdCommande(objet.getIdCommande()+1);
        }
        boolean ok = this.donnees.add(objet);
        return ok;
    }

    public boolean delete(LigneCommande objet){
        boolean ok;
        int idx = this.donnees.indexOf(objet);
        if (idx == -1){
			throw new IllegalArgumentException("Tentative de suppression d'une ligne de commande inexistante");
        }
        else{
            ok = this.donnees.remove(objet);
        }
        return ok;
    }

    public boolean update(LigneCommande objet){
        int idx = this.donnees.indexOf(objet);
        if (idx == -1){
            throw new IllegalArgumentException("Tentative de modification d'une ligne de commande inexistante");
        }
        else{
            this.donnees.set(idx, objet);
        }
        return true;
    }
}
*/