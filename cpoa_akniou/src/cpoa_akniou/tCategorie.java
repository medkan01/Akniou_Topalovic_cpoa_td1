package cpoa_akniou;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class tCategorie{
	
	public static void ajouterCategorie(int no_cat, String titre, String visuel) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("INSERT INTO `akniou1u_cpoa`.`Categorie` (`id_categorie`, `titre`, `visuel`) VALUES ('"+no_cat+"', '"+titre+"', '"+visuel+"');");
			ResultSet res = requete.executeQuery("SELECT * FROM `akniou1u_cpoa`.`Categorie`");
			affCategorie(res);
			requete.close();
			laConnexion.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}
	
	public static void suppCategorie(int no_cat) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `akniou1u_cpoa`.`Categorie` WHERE `Categorie`.`id_categorie` = "+no_cat+"");
			ResultSet res = requete.executeQuery("SELECT * FROM `akniou1u_cpoa`.`Categorie`");
			affCategorie(res);
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Pb select"+sqle.getMessage());
		}
	}
	
	public static void editCategorie(int no_cat, String titre, String visuel) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("UPDATE `akniou1u_cpoa`.`Categorie` SET `titre` = '"+titre+"', `visuel` = '"+visuel+"' WHERE `Categorie`.`id_categorie` = "+no_cat+";");
			ResultSet res = requete.executeQuery("SELECT * FROM `akniou1u_cpoa`.`Categorie`");
			affCategorie(res);
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Pb select"+sqle.getMessage());
		}
	}
	
	private static void affCategorie(ResultSet res){
		try {
			ArrayList<String> tCat = new ArrayList<String>();
			while (res.next()){
				String cat = new String();
				cat = "('"+res.getInt("id_categorie")+"','"+res.getString("titre")+"','"+res.getString("visuel")+"')";
				tCat.add(cat);
			}
			System.out.println(tCat.toString());
		}catch(SQLException sqle) {
			System.out.println("Pb select"+sqle.getMessage());
		}
	}
}
