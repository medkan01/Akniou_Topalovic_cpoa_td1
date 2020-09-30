package td2.dao;

import java.util.ArrayList;
import java.util.List;
import td2.pojo.Client;

public class ListeMemoireClientDAO implements ClientDAO{

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

	public boolean delete(Client objet) {
		Client supprime;
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un client inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

    public boolean update(Client objet){
        int idx = objet.getId()-1;
        if (idx == -1){
            throw new IllegalArgumentException("Tentative de modification d'un client inexistant");
        }
        else{
            this.donnees.set(idx, objet);
        }
        return true;
    }

    public Client getById(int id) {
        int idx = this.donnees.indexOf(this.donnees.get(id-1));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucun client ne possede cet id");
        }
        else {
            return this.donnees.get(idx);
        }
    }
    public ArrayList<Client> getAll(){
        return (ArrayList<Client>) this.donnees;
    }
}