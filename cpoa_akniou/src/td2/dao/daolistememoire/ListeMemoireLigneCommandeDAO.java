package td2.dao.daolistememoire;

import td2.dao.daomysql.MySQLLigneCommandeDAO;
import td2.pojo.LigneCommande;

public class ListeMemoireLigneCommandeDAO {
    
    private static MySQLLigneCommandeDAO instance;

	public static MySQLLigneCommandeDAO getInstance(){
		if (instance == null){
			instance = new MySQLLigneCommandeDAO();
		}
		return instance;
    }
    
    private ListeMemoireLigneCommandeDAO(){

    }

    public boolean insert(int idProduit, int idCommande, LigneCommande objet){
        

    }
}