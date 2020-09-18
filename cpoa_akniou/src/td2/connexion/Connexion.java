package td2.connexion;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
abstract public class Connexion {
	
	public static Connection creeConnexion() {
		String url ="jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr";
		url += "?serverTimezone=Europe/Paris";
		String login = "akniou1u_appli";
		String pwd = "31900870";
		Connection maConnexion = null;
		try {
			maConnexion = DriverManager.getConnection(url, login, pwd);
		} catch (SQLException sqle) {
			System.out.println("Erreur connexion" + sqle.getMessage());
		}
		return maConnexion;
	}
}
