
package td2.dao.daomysql;

import java.sql.*;
import java.util.ArrayList;
import td2.connexion.*;
import td2.pojo.Commande;
import td2.pojo.LigneCommande;

public class MySQLLigneCommandeDAO{

	private static MySQLLigneCommandeDAO instance;

	public static MySQLLigneCommandeDAO getInstance(){
		if (instance == null){
			instance = new MySQLLigneCommandeDAO();
		}
		return instance;
	}

	public boolean insert(int idProduit,int idCommande,LigneCommande ligne) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"INSERT INTO akniou1u.Ligne_commande (id_commande, id_produit, quantite, tarif_unitaire) VALUES (?, ?, ?, ?);");
			requete.setInt(1, idCommande);
			requete.setInt(2, idProduit);
			requete.setInt(3, ligne.getQuantite());
			requete.setDouble(4, ligne.getTarifUnitaire());
		int nbligne = requete.executeUpdate();
		requete.close();
		return nbligne == 1;
	}
	
	
	public boolean delete(Commande commande,int id_produit) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"DELETE FROM akniou1u.Ligne_commande WHERE id_commande = ? AND id_produit=?");
		int nbLignes = 0;
		ArrayList<Integer> tKey = new ArrayList<Integer>();
		commande.getKeys(tKey);
		if(tKey.contains(id_produit)){	
			requete.setInt(1, commande.getId());
			requete.setInt(2, tKey.get(tKey.indexOf(id_produit)));	
			nbLignes = requete.executeUpdate();
		}else{
			throw new IllegalArgumentException("Ce produit ne se trouve pas dans la commande");
		}
		requete.close();
		return nbLignes == 1;
	}
	
	public boolean update(Commande commande,int id_produit) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"UPDATE akniou1u.Ligne_commande SET id_commande = ? ,id_produit = ?, quantite= ?, tarif_unitaire=? WHERE id_commande=? AND id_produit = ?;");
		ArrayList<Integer> tKey = new ArrayList<Integer>();
		ArrayList<LigneCommande> tValue = new ArrayList<LigneCommande>();
		commande.getKeys(tKey);
		commande.getValues(tValue);
		int nbLignes = 0;
		if(tKey.contains(id_produit)){	
			requete.setInt(1, commande.getId());
			requete.setInt(2, tKey.get(tKey.indexOf(id_produit)));
			requete.setInt(3, tValue.get(tKey.indexOf(id_produit)).getQuantite());
			requete.setDouble(4, tValue.get(tKey.indexOf(id_produit)).getTarifUnitaire());
			nbLignes = requete.executeUpdate();
		}else{
			throw new IllegalArgumentException("Ce produit ne se trouve pas dans la commande");
		}
		requete.close();
		return nbLignes == 1;
	}
}
