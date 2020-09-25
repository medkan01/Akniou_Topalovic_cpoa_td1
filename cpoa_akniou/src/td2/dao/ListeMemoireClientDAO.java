package td2.dao;

import java.util.List;
import td2.pojo.Client;

public class ListeMemoireClientDAO {

    private static ListeMemoireClientDAO instance;
    private List<Client> donnees;

    public static ListeMemoireClientDAO getInstance(){
        if (instance == null){
            instance = new ListeMemoireClientDAO();
        }
        return instance;
    }

    public boolean insert(Client objet){
        objet.setId(1);
        while(this.donnees.contains(objet)){
            objet.setId(objet.getId()+1);
        }
        boolean ok = this.donnees.add(objet);
        return ok;
    }

    public boolean delete(Client objet){
        boolean ok;
        int idx = this.donnees.indexOf(objet);
        if (idx == -1){
			throw new IllegalArgumentException("Tentative de suppression d'une Client inexistante");
        }
        else{
            ok = this.donnees.remove(objet);
        }
        return ok;
    }

    public boolean update(Client objet){
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