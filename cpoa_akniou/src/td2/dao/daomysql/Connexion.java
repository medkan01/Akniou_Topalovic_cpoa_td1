package td2.dao.daomysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private Connection maConnexion;
	public static Connexion instance;
	private Connexion(){
		creeConnexion();
	}
	private void creeConnexion() {
		String url ="jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr";
		url += "?serverTimezone=Europe/Paris";
		String login = "akniou1u_appli";
		String pwd = "31900870";
		try {
			maConnexion = DriverManager.getConnection(url, login, pwd);
		} catch (SQLException sqle) {
			System.out.println("Erreur connexion" + sqle.getMessage());
		}
	}

	public static Connexion getInstance(){
		if (instance == null){
			instance = new Connexion();
		}
		return instance;
	}

	public Connection getMaConnexion()throws SQLException{
		if (this.maConnexion.isClosed()){
			creeConnexion();
		}
		return this.maConnexion;
	}

}
