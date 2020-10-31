package td2.dao.daomysql;

import java.sql.*;
import java.util.ArrayList;

import td2.dao.daofactory.ProduitDAO;
import td2.pojo.Produit;
import td2.pojo.Categorie;

public class MySQLProduitDAO implements ProduitDAO {

	private static MySQLProduitDAO instance;

	public static MySQLProduitDAO getInstance(){
		if (instance == null){
			instance = new MySQLProduitDAO();
		}
		return instance;
	}

	public boolean insert(Produit produit) throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"INSERT INTO akniou1u_cpoa.Produit (nom, description, tarif, visuel, id_categorie) VALUES (?, ?, ?, ?, ?);",Statement.RETURN_GENERATED_KEYS);
			requete.setString(1, produit.getNom());
			requete.setString(2, produit.getDescription());
			requete.setDouble(3, produit.getTarif());
			requete.setString(4, produit.getVisuel());
			requete.setInt(5, produit.getIdCategorie());
		int nblignes = requete.executeUpdate();
		ResultSet res = requete.getGeneratedKeys();
		if (res.next()) {
			int cle = res.getInt(1);
			produit.setId(cle);
		}
		requete.close();
		return nblignes == 1;
	}
	
	public boolean delete(Produit produit) throws SQLException{
		if(produit.getId()<=0) throw new IllegalArgumentException("ID Incorrect");
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"DELETE FROM akniou1u_cpoa.Produit WHERE id_produit = ?;", Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, produit.getId());
		int nbLignes = requete.executeUpdate();
		requete.close();
		if(nbLignes != 1) throw new IllegalArgumentException("ID Incorrect");
		else return true;
	}
	
	public boolean update(Produit produit) throws SQLException{
		if(produit.getId()<=0) throw new IllegalArgumentException("ID Incorrect");
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"UPDATE akniou1u_cpoa.Produit SET nom = ?, description= ?, tarif=?, visuel=?, id_categorie=? WHERE id_produit = ?;");
			requete.setString(1, produit.getNom());
			requete.setString(2, produit.getDescription());
			requete.setDouble(3, produit.getTarif());
			requete.setString(4, produit.getVisuel());
			requete.setInt(5, produit.getIdCategorie());
			requete.setInt(6, produit.getId());
		int nbLignes = requete.executeUpdate();
		requete.close();
		if(nbLignes != 1) throw new IllegalArgumentException("ID Incorrect");
		else return true;
	}

	public Produit getById(int id) throws SQLException {
		if(id<=0) throw new IllegalArgumentException("ID Incorrect");
		Connection c = Connexion.getInstance().getMaConnexion();
		Statement requete = c.createStatement();
		requete.executeQuery("SELECT * FROM akniou1u_cpoa.Produit WHERE id_produit = "+id+";");
		ResultSet res = requete.getResultSet();
		res.next();
		Produit produit = new Produit(
			res.getInt("id_produit"),
			res.getString("nom"),
			res.getString("description"),
			res.getDouble("tarif"),
			res.getString("visuel"),
			res.getInt("id_categorie"));
		return produit;
	}

	public ArrayList<Produit> getAllByCategorie(int idProduit) throws SQLException{
		if(idProduit<=0) throw new IllegalArgumentException("ID Incorrect");
		Connection c = Connexion.getInstance().getMaConnexion();
		Statement requete = c.createStatement();
		requete.executeQuery("SELECT Produit.id_produit, Produit.nom, Produit.description, Produit.tarif, Produit.visuel, Produit.id_categorie FROM akniou1u_cpoa.Categorie, akniou1u_cpoa.Produit  WHERE Produit.id_categorie = "+idProduit+"AND akniou1u_cpoa.Produit.id_categorie =  akniou1u_cpoa.Categorie.id_categorie");
		ResultSet res = requete.getResultSet();
		ArrayList<Produit> liste = new ArrayList<Produit>();
		while (res.next()){
			Produit produit = new Produit(res.getInt("id_produit"),res.getString("nom"),res.getString("description"),res.getLong("tarif"),res.getString("visuel"),res.getInt("id_categorie"));
			liste.add(produit);
		}
		return liste;
	}

	public ArrayList<Produit> getAll() throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		Statement requete = c.createStatement();
		requete.executeQuery("SELECT * FROM akniou1u_cpoa.Produit;");
		ResultSet res = requete.getResultSet();
		ArrayList<Produit> liste = new ArrayList<Produit>();
		while (res.next()) {
			Produit produit = new Produit(res.getInt("id_produit"),res.getString("nom"),res.getString("description"),res.getDouble("tarif"),res.getString("visuel"),res.getInt("id_categorie"));
			liste.add(produit);
		}
		return liste;
	}
	
}
