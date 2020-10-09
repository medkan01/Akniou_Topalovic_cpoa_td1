
package td2.dao.daomysql;

import java.sql.*;
import java.util.ArrayList;
import td2.dao.daofactory.CommandeDAO;
import td2.pojo.Commande;
import td2.pojo.LigneCommande;
import td2.pojo.Produit;

public class MySQLCommandeDAO implements CommandeDAO{

	private static MySQLCommandeDAO instance;

	public static MySQLCommandeDAO getInstance(){
		if (instance == null){
			instance = new MySQLCommandeDAO();
		}
		return instance;
	}

	public boolean insert(Commande commande) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"INSERT INTO akniou1u_cpoa.Commande (date_commande, id_client) VALUES (?, ?)",Statement.RETURN_GENERATED_KEYS);
			requete.setDate(1,  java.sql.Date.valueOf(commande.getDate()));
			requete.setInt(2, commande.getIdClient());
		int nbligne = requete.executeUpdate();
		ResultSet res = requete.getGeneratedKeys();
		if (res.next()) {
			int cle = res.getInt(1);
			commande.setId(cle);
		}
		requete.close();
		return nbligne == 1;
	}
	
	public boolean delete(Commande commande) throws SQLException{
		if(commande.getId()<=0) throw new IllegalArgumentException("ID Incorrect");
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"DELETE FROM akniou1u_cpoa.Commande WHERE id_commande=?;", Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, commande.getId());
		int nbLignes = requete.executeUpdate();
		requete.close();
		if(nbLignes != 1) throw new IllegalArgumentException("ID Incorrect");
		else return true;
	}
	
	public boolean update(Commande commande) throws SQLException{
		if(commande.getId()<=0) throw new IllegalArgumentException("ID Incorrect");
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"UPDATE akniou1u_cpoa.Commande SET date_commande=?, id_client=? WHERE id_commande=?;");
			requete.setDate(1, java.sql.Date.valueOf(commande.getDate()));
			requete.setInt(2, commande.getIdClient());
			requete.setInt(3, commande.getId());
		int nbLignes = requete.executeUpdate();
		requete.close();
		if(nbLignes != 1) throw new IllegalArgumentException("ID Incorrect");
		else return true;
	}

	public Commande getById(int id) throws SQLException {
		if(id<=0) throw new IllegalArgumentException("ID Incorrect");
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
			"SELECT * FROM akniou1u_cpoa.Commande WHERE id_commande = ?;");
				requete.setInt(1, id);
		requete.executeQuery();
		ResultSet res = requete.getResultSet();
		res.next();
		Commande commande = new Commande(
			res.getInt("id_commande"),
			res.getDate("date_commande").toLocalDate(),
			res.getInt("id_client"));
		return commande;
	}

	public ArrayList<Commande> getAll() throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		Statement requete = c.createStatement();
		requete.executeQuery("SELECT Commande.id_commande, date_commande, Commande.id_client, Ligne_commande.id_produit, "+
		"quantite, tarif_unitaire, nom, description, tarif, visuel, id_categorie FROM akniou1u_cpoa.Commande, akniou1u_cpoa.Ligne_commande, akniou1u_cpoa.Produit "+
		"WHERE Commande.id_commande = Ligne_commande.id_commande AND Produit.id_produit = Ligne_commande.id_produit;");
		ResultSet res = requete.getResultSet();
		ArrayList<Commande> liste = new ArrayList<Commande>();
		while (res.next()) {
			Commande commande = new Commande(res.getInt("id_commande"),res.getDate("date_commande").toLocalDate(),res.getInt("id_client"));
			
			Produit produit = new Produit(res.getInt("Ligne_commande.id_produit"), res.getString("nom"), res.getString("description"), res.getDouble("tarif"), res.getString("visuel"), res.getInt("id_categorie"));
			LigneCommande ligneCommande = new LigneCommande(res.getInt("quantite"), res.getDouble("tarif_unitaire"));
			commande.ajouterLigne(produit, ligneCommande);
			liste.add(commande);
		}
		return liste;
	}

}

