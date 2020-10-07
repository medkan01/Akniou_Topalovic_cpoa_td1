package td2.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Commande {

	// VARIABLES
	private int id;
	private LocalDate date;
	private int idClient;
	private HashMap<Produit, LigneCommande> lignes;

	// CONSTRUCTEUR
	public Commande(int id, LocalDate date, int idClient) {
		this.id = id;
		this.date = date;
		this.idClient = idClient;
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

	public void getKeys(ArrayList<Integer> T) {
		Iterator<Entry<Produit, LigneCommande>> it = lignes.entrySet().iterator();
		Produit cle;
		while(it.hasNext()){
		Map.Entry<Produit, LigneCommande> l = (Map.Entry<Produit, LigneCommande>) it.next(); 
		cle = l.getKey();
		T.add(cle.getId());
		}
	}

	public void getValues(ArrayList<LigneCommande> T) {
		Iterator<Entry<Produit, LigneCommande>> it = lignes.entrySet().iterator();
		while(it.hasNext()){
		Map.Entry<Produit, LigneCommande> l = (Map.Entry<Produit, LigneCommande>) it.next(); 
		T.add(l.getValue());
		}
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
		Iterator<Entry<Produit, LigneCommande>> it = lignes.entrySet().iterator();
		String afficher = "";
		Map.Entry<Produit, LigneCommande> l = it.next();
		while(it.hasNext()){
			afficher = afficher + "["+this.id+", "+this.date+", "+this.idClient+", Produit: "+l.getKey()+", Ligne Commande: "+l.getValue()+"\n";
			l = (Map.Entry<Produit, LigneCommande>) it.next();
		}
		return afficher;
	}
	
}
