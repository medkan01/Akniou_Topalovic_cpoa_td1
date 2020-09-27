package td2.pojo;

import java.time.LocalDate;
import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;

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

	public void supprimerLigne(Produit p, LigneCommande l){
		if (lignes.containsKey(p) == false){
			throw new IllegalArgumentException("Produit n'existe pas dans la liste");
		}
		else{
			lignes.remove(p);
		}
	}

	public void ajouterModifier(Produit p, LigneCommande l){
		if (lignes.containsKey(p) == false){
			throw new IllegalArgumentException("Produit n'existe pas dans la liste");
		}
		else{
			lignes.put(p,l);
		}
	}

	public void afficher(){
		Iterator it = lignes.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry l = (Map.Entry) it.next();
			System.out.println("id : "+this.id+"\nDate : "+this.date+"\nClient : "+this.idClient+"\nProduit : "+l.getKey()+"\nLigne Commande : "+l.getValue());
		}
	}
}
