package td2.pojo;

public class Categorie {
	
	//VARIABLES
	private int id;
	private String titre;
	private String visuel;
	
	// CONSTRUCTEUR
	public Categorie(int id, String titre, String visuel) {
		super();
		this.id = id;
		this.titre = titre;
		this.visuel = visuel;
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
		this.titre = titre;
	}

	public String getVisuel() {
		return visuel;
	}

	public void setVisuel(String visuel) {
		this.visuel = visuel;
	}

	
	
}
