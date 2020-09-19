package td2.pojo;

public class Client {

	//VARIABLES
	private int id;
	private String nom;
	private String prenom;
	private String identifiant;
	private String motDePasse;
	private String adrNumero;
	private String adrVoie;
	private String adrCodePostal;
	private String adrVille;
	private String adrPays;
	
	//CONSTRUCTEUR
	public Client(int id, String nom, String prenom, String identifiant, String motDePasse, String adrNumero, String adrVoie,
			String adrCodePostal, String adrVille, String adrPays) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.adrNumero = adrNumero;
		this.adrVoie = adrVoie;
		this.adrCodePostal = adrCodePostal;
		this.adrVille = adrVille;
		this.adrPays = adrPays;
	}

	//ACCESSEURS
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
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getAdrNumero() {
		return adrNumero;
	}

	public void setAdrNumero(String adrNumero) {
		this.adrNumero = adrNumero;
	}

	public String getAdrVoie() {
		return adrVoie;
	}

	public void setAdrVoie(String adrVoie) {
		this.adrVoie = adrVoie;
	}

	public String getAdrCodePostal() {
		return adrCodePostal;
	}

	public void setAdrCodePostal(String adrCodePostal) {
		this.adrCodePostal = adrCodePostal;
	}

	public String getAdrVille() {
		return adrVille;
	}

	public void setAdrVille(String adrVille) {
		this.adrVille = adrVille;
	}

	public String getAdrPays() {
		return adrPays;
	}

	public void setAdrPays(String adrPays) {
		this.adrPays = adrPays;
	}
	
	
	
	
}
