package td2.pojo;

public class Client {

	//VARIABLES
	private int id;
	private String nom;
	private String prenom;
	private String identifiant;
	private String mdp;
	private int adrNumero;
	private String adrVoie;
	private int adrCp;
	private String adrVille;
	private String adrPays;
	
	//CONSTRUCTEUR
	public Client(int id, String nom, String prenom, String identifiant, String mdp, int adrNumero, String adrVoie,
			int adrCp, String adrVille, String adrPays) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
		this.mdp = mdp;
		this.adrNumero = adrNumero;
		this.adrVoie = adrVoie;
		this.adrCp = adrCp;
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

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getAdrNumero() {
		return adrNumero;
	}

	public void setAdrNumero(int adrNumero) {
		this.adrNumero = adrNumero;
	}

	public String getAdrVoie() {
		return adrVoie;
	}

	public void setAdrVoie(String adrVoie) {
		this.adrVoie = adrVoie;
	}

	public int getAdrCp() {
		return adrCp;
	}

	public void setAdrCp(int adrCp) {
		this.adrCp = adrCp;
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
