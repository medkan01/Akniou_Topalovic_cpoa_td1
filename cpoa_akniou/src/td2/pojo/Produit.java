package td2.pojo;

public class Produit {
	private int id;
	private String nom;
	private String description;
	private double tarif;
	private String visuel;
	private int idCategorie;
	
	public Produit(int id, String nom, String description, double tarif, String visuel, int idCategorie) {
		this.setId(id);
		this.setNom(nom);
		this.setDescription(description);
		this.setTarif(tarif);
		this.setVisuel(visuel);
		this.setIdCategorie(idCategorie);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		if (nom.trim().isEmpty()){
			throw new IllegalArgumentException("Le nom est vide. ");
		} else {
			this.nom = nom.trim();
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description.trim().isEmpty()){
			throw new IllegalArgumentException("La description est vide. ");
		} else {
			this.description = description.trim();
		}
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		if (tarif <= 0){
			throw new IllegalArgumentException("Le tarif doit être supérieur à 0");
		} else {
			this.tarif = tarif;
		}
	}

	public String getVisuel() {
		return visuel;
	}

	public void setVisuel(String visuel) {
		this.visuel = visuel;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String toString() {
		return "le produit: " +	this.nom + " à été correctement ajouté pour un montant de " + this.tarif + " avec comme description: " + this.description ;
	}
	
	
}
