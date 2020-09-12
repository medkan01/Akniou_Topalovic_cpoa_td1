package cpoa_akniou;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class tLigne_commande {
	public static void ajouterLigne_commande(int id_commande, int id_produit, int quantite, double tarif_unitaire) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("INSERT INTO `akniou1u_cpoa`.`Ligne_commande` (`id_commande`, `id_produit`, `quantite`, `tarif_unitaire`) VALUES ('"+id_commande+"', '"+id_produit+"', '"+quantite+"','"+tarif_unitaire+"');");
			affLigne_commande();
			requete.close();
			laConnexion.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}
	
	public static void suppLigne_commande(int id_commande, int id_produit) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("DELETE FROM `akniou1u_cpoa`.`Ligne_commande` WHERE `Commande`.`id_commande` = "+id_commande+" AND `id_produit` = "+id_produit);
			affLigne_commande();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Pb select"+sqle.getMessage());
		}
	}
	
	public static void editLigne_commande(int id_commande, int id_produit, int quantite, double tarif_unitaire) {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			requete.executeUpdate("UPDATE `akniou1u_cpoa`.`Commande` SET `id_produit` = '"+id_produit+"', `quantite` = '"+quantite+"', `tarif_unitaire` = '"+tarif_unitaire+" WHERE `Commande`.`id_commande` = "+id_commande+" AND `id_produit` = "+id_produit+";");
			affLigne_commande();
			requete.close();
			laConnexion.close();
		} catch(SQLException sqle) {
			System.out.println("Pb select"+sqle.getMessage());
		}
	}
	
	public static void affLigne_commande(){
		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement requete = laConnexion.createStatement();
			ResultSet res = requete.executeQuery("SELECT * FROM `akniou1u_cpoa`.`Ligne_commande`");
			ArrayList<String> tLigne_com = new ArrayList<String>();
			while (res.next()){
				String ligne_com = new String();
				ligne_com = "["+res.getInt("id_commande")+", "+ res.getInt("id_produit")+ ", "+res.getInt("quantite")+", "+res.getDouble("tarif_unitaire")+"]";
				tLigne_com.add(ligne_com);
			}
			for(int i=0; i<tLigne_com.size(); i++) {
				System.out.println(tLigne_com.get(i));
			}
		}catch(SQLException sqle) {
			System.out.println("Pb select"+sqle.getMessage());
		}
	}
}
