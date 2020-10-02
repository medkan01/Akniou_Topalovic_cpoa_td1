package td2.dao.daolistememoire;

import java.util.ArrayList;
import java.util.List;
import td2.dao.daofactory.ClientDAO;
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

    private ListeMemoireClientDAO() {
        donnees = new ArrayList<Client>();
        this.donnees.add(new Client(1, "Laroche", "Pierre", "laroche5@ul.fr", "toto", "12", "Rue des etudiants", "57000", "Metz", "France"));
		this.donnees.add(new Client(2, "akniou", "medkan", "larocqdqzdhe5@ul.fr", "toqzdto", "12", "Rue dqzdes etudiants", "570zd00", "Metz", "France"));
    }
    
    public boolean insert(Client objet){
		objet.setId(this.donnees.get(donnees.size()-1).getId()+1);
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