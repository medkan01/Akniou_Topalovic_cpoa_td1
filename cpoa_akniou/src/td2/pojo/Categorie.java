package td2.pojo;

public class Categorie {
	
	//VARIABLES
	private int id;
	private String titre;
	private String visuel;
	
	// CONSTRUCTEUR
	public Categorie(int id, String titre, String visuel) {
		this.setId(id);
		this.setTitre(titre);
		this.setVisuel(visuel);;
	}
	
	//ACCESSEURS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		titre = titre.trim();
		if(titre == "") {
			throw new IllegalArgumentException("Le titre saisie est vide");
		} else {
			this.titre=titre;
		}
	}

	public String getVisuel() {
		return visuel;
	}

	public void setVisuel(String visuel) {
		visuel = visuel.trim();
		if(visuel=="") {
			throw new IllegalArgumentException("Le visuel saisie est vide");
		} else {
			this.visuel = visuel;
		}
	}

	@Override
	public String toString() {
		return titre 
	;
	}
	
}
