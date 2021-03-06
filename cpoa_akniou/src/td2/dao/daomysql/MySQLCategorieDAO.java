package td2.dao.daomysql;

import java.sql.*;
import java.util.ArrayList;
import td2.dao.daofactory.CategorieDAO;
import td2.pojo.Categorie;

public class MySQLCategorieDAO implements CategorieDAO{

	private static MySQLCategorieDAO instance;

	public static MySQLCategorieDAO getInstance() {
		if (instance == null){
			instance = new MySQLCategorieDAO();
		}
		return instance;
	}

	public boolean insert(Categorie categorie) throws SQLException {
		Connection c = Connexion.getInstance().getMaConnexion();
		PreparedStatement requete = c.prepareStatement(
		"INSERT INTO akniou1u_cpoa.Categorie (titre, visuel) VALUES (?, ?);",Statement.RETURN_GENERATED_KEYS);
			requete.setString(1, categorie.getTitre());
			requete.setString(2, categorie.getVisuel());
		int nbligne = requete.executeUpdate();
		ResultSet res = requete.getGeneratedKeys();
		if (res.next()) {
			int cle = res.getInt(1);
			categorie.setId(cle);
		}
		requete.close();
		return nbligne == 1;
	}

	public boolean delete(Categorie categorie) throws SQLException {
		Connection c = Connexion.getInstance().getMaConnexion();
		if(categorie.getId()<=0) throw new IllegalArgumentException("ID Incorrect");
		PreparedStatement requete = c.prepareStatement(
			"DELETE FROM akniou1u_cpoa.Categorie WHERE Categorie.id_categorie = ?;");
				requete.setInt(1, categorie.getId());
		int nbLignes = requete.executeUpdate();
		requete.close();
		if(nbLignes != 1) throw new IllegalArgumentException("ID Incorrect");
		else return true;
	}
	
	public boolean update(Categorie categorie) throws SQLException {
		Connection c = Connexion.getInstance().getMaConnexion();
		if(categorie.getId()<=0) throw new IllegalArgumentException("ID Incorrect");
		PreparedStatement requete = c.prepareStatement(
			"UPDATE akniou1u_cpoa.Categorie SET titre = ?, visuel = ? WHERE Categorie.id_categorie = ?;");
				requete.setString(1, categorie.getTitre());
				requete.setString(2, categorie.getVisuel());
				requete.setInt(3, categorie.getId());
		int nbLignes = requete.executeUpdate();
		requete.close();
		if(nbLignes != 1) throw new IllegalArgumentException("ID Incorrect");
		else return true;
	}

	public Categorie getById(int id) throws SQLException{
		if(id<=0) throw new IllegalArgumentException();
		Connection c = Connexion.getInstance().getMaConnexion();
		Statement requete = c.createStatement();
		requete.executeQuery("SELECT * FROM akniou1u_cpoa.Categorie WHERE id_categorie ='"+id+"';");
		ResultSet res = requete.getResultSet();
		res.next();
		Categorie categorieRes = new Categorie(
			res.getInt("id_categorie"),
			res.getString("titre"),
			res.getString("visuel"));
		return categorieRes;
	}

	public Categorie getCategorieByProduit(int idProduit) throws SQLException{
		if(idProduit<=0) throw new IllegalArgumentException("ID Incorrect");
		Connection c = Connexion.getInstance().getMaConnexion();
		Statement requete = c.createStatement();
		requete.executeQuery("SELECT Categorie.id_categorie, Categorie.titre, Categorie.visuel FROM akniou1u_cpoa.Categorie, akniou1u_cpoa.Produit  WHERE Produit.id_produit = "+idProduit+" AND akniou1u_cpoa.Categorie.id_categorie =  akniou1u_cpoa.Produit.id_categorie");
		ResultSet res = requete.getResultSet();
		res.next();
		Categorie categorie = new Categorie(res.getInt("id_categorie"),res.getString("titre"),res.getString("visuel"));

		return categorie;
	}
	public ArrayList<Categorie> getAll() throws SQLException{
		Connection c = Connexion.getInstance().getMaConnexion();
		Statement requete = c.createStatement();
		requete.executeQuery("SELECT * FROM akniou1u_cpoa.Categorie");
		ResultSet res = requete.getResultSet();
		ArrayList<Categorie> liste = new ArrayList<Categorie>();
		while (res.next()) {
			Categorie categorie = new Categorie(res.getInt("id_categorie"),res.getString("titre"),res.getString("visuel"));
			liste.add(categorie);
		}
		return liste;
	}
}
