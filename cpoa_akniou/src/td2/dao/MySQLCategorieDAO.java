package td2.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import td2.connexion.Connexion;
import td2.pojo.Categorie;

public class MySQLCategorieDAO {
	public static void insert(Categorie categorie) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("INSERT INTO `akniou1u_cpoa`.`Categorie` (`titre`, `visuel`) VALUES ('"+categorie.getTitre()+"', '"+categorie.getVisuel()+"');");
		requete.close();
		laConnexion.close();
	}
	
	public static void delete(Categorie categorie) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("DELETE FROM `akniou1u_cpoa`.`Categorie` WHERE `Categorie`.`id_categorie` = "+categorie.getId());
		requete.close();
		laConnexion.close();
	}
	
	public static void update(Categorie categorie) throws SQLException {
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		requete.executeUpdate("UPDATE `akniou1u_cpoa`.`Categorie` SET `titre` = '"+categorie.getTitre()+"', `visuel` = '"+categorie.getVisuel()+"' WHERE `Categorie`.`id_categorie` = "+categorie.getId()+";");
		requete.close();
		laConnexion.close();
	}
}
