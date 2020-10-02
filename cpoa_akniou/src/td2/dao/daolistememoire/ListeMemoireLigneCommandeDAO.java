package td2.dao.daolistememoire;

import td2.pojo.LigneCommande;

public class ListeMemoireLigneCommandeDAO {
    
    private static ListeMemoireLigneCommandeDAO instance;

	public static ListeMemoireLigneCommandeDAO getInstance(){
		if (instance == null){
			instance = new ListeMemoireLigneCommandeDAO();
		}
		return instance;
    }
    
    private ListeMemoireLigneCommandeDAO(){

    }

    public boolean insert(int idProduit, int idCommande, LigneCommande objet){
        

    }
}