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
        this.donnees.add(new Client(2, "Akniou", "Mehdi", "mehdiakniou@gmail.com", "toto", "12", "Rue des etudiants", "57000", "Metz", "France"));
        this.donnees.add(new Client(3, "Topalovic", "Ajdin", "ajdintpc@gmail.com", "toto", "12", "Rue des etudiants", "57000", "Hagondange", "France"));
    }
    
    public boolean insert(Client objet){
		objet.setId(this.donnees.get(donnees.size()-1).getId()+1);
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	public boolean delete(Client objet) {
        if(objet.getId()<=0) throw new IllegalArgumentException("ID Incorrect");
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
        if(objet.getId()<=0) throw new IllegalArgumentException("ID Incorrect");
        this.donnees.set(this.donnees.indexOf(getById(objet.getId())), objet);
        return true;
    }

    public Client getById(int id) {
        if(id<=0) throw new IllegalArgumentException("ID Incorrect");
        for (int i = 0; i<this.donnees.size();i++){
            if(this.donnees.get(i).getId()==id){
                return this.donnees.get(i);
            }
        }
		throw new IllegalArgumentException("Aucun client ne possède cet identifiant");
    }

    public ArrayList<Client> getAll(){
        return (ArrayList<Client>) this.donnees;
    }
}