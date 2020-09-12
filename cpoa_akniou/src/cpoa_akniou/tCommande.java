package cpoa_akniou;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class tCommande {
	public static void ajouterCommande(int id_commande, String date, int id_client) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("INSERT INTO `akniou1u_cpoa`.`Commande` (`id_commande`, `date_commande`, `id_client`) VALUES ('"+id_commande+"', '"+date+"', '"+id_client+"');");
			affCommande();
			requete.close();
			laConnexion.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}
	
	public static void suppCommande(int id_commande) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `akniou1u_cpoa`.`Commande` WHERE `Commande`.`id_commande` = "+id_commande+"");
			affCommande();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Pb select"+sqle.getMessage());
		}
	}
	
	public static void editCommande(int id_commande, String date_commande, int id_client) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("UPDATE `akniou1u_cpoa`.`Commande` SET `date_commande` = '"+date_commande+"', `id_client` = '"+id_client+"' WHERE `Commande`.`id_commande` = "+id_commande+";");
			affCommande();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Pb select"+sqle.getMessage());
		}
	}
	
	public static void affCommande(){
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			ResultSet res = requete.executeQuery("SELECT * FROM `akniou1u_cpoa`.`Commande`");
			ArrayList<String> tCom = new ArrayList<String>();
			while (res.next()){
				String com = new String();
				com = "["+res.getInt("id_commande")+", "+ res.getString("date_commande")+ ", "+res.getInt("id_client")+"]";
				tCom.add(com);
			}
			for(int i=0; i<tCom.size(); i++) {
				System.out.println(tCom.get(i));
			}
		}catch(SQLException sqle) {
			System.out.println("Pb select"+sqle.getMessage());
		}
	}
}
