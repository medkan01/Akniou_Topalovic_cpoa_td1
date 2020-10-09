package td2.pojo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Commande {

	// VARIABLES
	private int id;
	private LocalDate date;
	private int idClient;
	private HashMap<Produit, LigneCommande> lignes;

	// CONSTRUCTEUR
	public Commande(int id, LocalDate date, int idClient) {
		this.setId(id);
		this.setDate(date);
		this.setIdClient(idClient);
		lignes = new HashMap<Produit, LigneCommande>();
	}

	// ACCESSEURS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public HashMap<Produit, LigneCommande> getLigneCommande(){
		return this.lignes;
	}

	public void setLigneCommande(HashMap<Produit, LigneCommande> ligne){
		this.lignes = ligne;
	}
	
	// METHODES
	public void ajouterLigne(Produit produit, LigneCommande ligne) {
		if (lignes.containsKey(produit)) {
			throw new IllegalArgumentException("Produit deja existant dans la liste");
		} else {
			lignes.put(produit, ligne);
		}
	}

	public void supprimerLigne(Produit produit) {
		if (lignes.containsKey(produit) == false) {
			throw new IllegalArgumentException("Produit n'existe pas dans la liste");
		} else {
			lignes.remove(produit);
		}
	}

	public void modifierLigne(Produit produit, LigneCommande ligne) {
		if (lignes.containsKey(produit) == false) {
			throw new IllegalArgumentException("Produit n'existe pas dans la liste");
		} else {
			lignes.replace(produit, ligne);
		}
	}

	public String afficher() {
		String afficher = "";
		for(Map.Entry<Produit, LigneCommande> p : this.lignes.entrySet()){
			afficher = afficher + "[ id produit: "+p.getKey().getId() + ", quantite: " + this.lignes.get(p.getKey()).getQuantite()
					+ ", tarif unitaire: " + this.lignes.get(p.getKey()).getTarifUnitaire() + "] \n";
		}
		return afficher;
	}
	
}