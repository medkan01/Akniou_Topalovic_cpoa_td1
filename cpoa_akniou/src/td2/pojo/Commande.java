package td2.pojo;

import java.time.LocalDate;
import java.util.HashMap;

public class Commande {

	//VARIABLES
	private int id;
	private LocalDate date;
	private int idClient;
	private HashMap<Produit, LigneCommande> lignes; 
	
	//CONSTRUCTEUR
	public Commande(int id, LocalDate date, int idClient) {
		this.id = id;
		this.date = date;
		this.idClient = idClient;
		lignes = new HashMap<Produit, LigneCommande>();
	}

	//ACCESSEURS
	public HashMap<Produit,LigneCommande> getLignes() {
		return this.lignes;
	}

	public void setLignes(HashMap<Produit,LigneCommande> lignes) {
		this.lignes = lignes;
	}

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

	public void ajouterLigne(Produit p, LigneCommande l){
		if (lignes.containsKey(p)){
			throw new IllegalArgumentException("Produit deja existant dans la liste");
		}
		else{
			lignes.put(p,l);
		}
	}
}
