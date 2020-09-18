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
	
}
