
package td2.dao;

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

	public boolean insert(Commande commande) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"INSERT INTO akniou1u.Ligne_commande (id_commande, id_produit, quantite, tarif_unitaire) VALUES (?, ?, ?, ?);",Statement.RETURN_GENERATED_KEYS);
		ArrayList<Integer> Tk = new ArrayList<Integer>();
		ArrayList<LigneCommande> Tv = new ArrayList<LigneCommande>();
		commande.getKeys(Tk);
		commande.getValues(Tv);
		int nbligne = 0 ;
		int key ;
		LigneCommande values;
		for(int i = 0 ; i < Tk.size(); i++){
			key = Tk.get(i);
			values = Tv.get(i);
			requete.setInt(1, commande.getId());
			requete.setInt(2, key);
			requete.setInt(2, values.getQuantite());
			requete.setDouble(3, values.getTarifUnitaire());
		nbligne = requete.executeUpdate();
	}
		ResultSet res = requete.getGeneratedKeys();
		if (res.next()) {
			int cle = res.getInt(1);
			commande.setId(cle);
		}	
		requete.close();
		return nbligne != 0;
	}
	
	
	public boolean delete(Commande commande,int id_produit) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"DELETE FROM akniou1u.Ligne_commande WHERE id_commande = ? AND id_produit=?");
		int nbLignes = 0;
		ArrayList<Integer> Tk = new ArrayList<Integer>();
		commande.getKeys(Tk);
		if(Tk.contains(id_produit)){	
			requete.setInt(1, commande.getId());
			requete.setInt(2, Tk.get(Tk.indexOf(id_produit)));	
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
		ArrayList<Integer> Tk = new ArrayList<Integer>();
		ArrayList<LigneCommande> Tv = new ArrayList<LigneCommande>();
		commande.getKeys(Tk);
		commande.getValues(Tv);
		int nbLignes = 0;
		if(Tk.contains(id_produit)){	
			requete.setInt(1, commande.getId());
			requete.setInt(2, Tk.get(Tk.indexOf(id_produit)));
			requete.setInt(3, Tv.get(Tk.indexOf(id_produit)).getQuantite());
			requete.setDouble(4, Tv.get(Tk.indexOf(id_produit)).getTarifUnitaire());
			nbLignes = requete.executeUpdate();
		}else{
			throw new IllegalArgumentException("Ce produit ne se trouve pas dans la commande");
		}
		requete.close();
		return nbLignes == 1;
	}
}
