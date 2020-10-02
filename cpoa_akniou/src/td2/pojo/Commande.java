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

	public HashMap<Produit, LigneCommande> getLigneCommande(int idCommande){
		return this.lignes;
	}

	
	// METHODES
	public void ajouterLigne(Produit p, LigneCommande l) {
		if (lignes.containsKey(p)) {
			throw new IllegalArgumentException("Produit deja existant dans la liste");
		} else {
			lignes.put(p, l);
		}
	}

	public void supprimerLigne(Produit p, LigneCommande l) {
		if (lignes.containsKey(p) == false) {
			throw new IllegalArgumentException("Produit n'existe pas dans la liste");
		} else {
			lignes.remove(p);
		}
	}

	public void modifierLigne(Produit p, LigneCommande l) {
		if (lignes.containsKey(p) == false) {
			throw new IllegalArgumentException("Produit n'existe pas dans la liste");
		} else {
			lignes.put(p, l);
		}
	}

	public String afficher() {
		Iterator<Entry<Produit, LigneCommande>> it = lignes.entrySet().iterator();
		String afficher = "";
		while(it.hasNext()){
			Map.Entry<Produit, LigneCommande> l = (Map.Entry<Produit, LigneCommande>) it.next();
			afficher = afficher + "["+this.id+", "+this.date+", "+this.idClient+", Produit: "+l.getKey()+", Ligne Commande: "+l.getValue()+"\n";
		}
		return afficher;
	}
	
}
