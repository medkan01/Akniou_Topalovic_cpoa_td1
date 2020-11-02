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
	public Client(int id, String nom, String prenom, String identifiant, String motDePasse, String adrNumero, String adrVoie, String adrCodePostal, String adrVille, String adrPays) {
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setIdentifiant(identifiant);
		this.setMotDePasse(motDePasse);
		this.setAdrNumero(adrNumero);
		this.setAdrVoie(adrVoie);
		this.setAdrCodePostal(adrCodePostal);
		this.setAdrVille(adrVille);
		this.setAdrPays(adrPays);
	}

	//ACCESSEURS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		nom = nom.trim();
		if(nom=="") {
			throw new IllegalArgumentException("Le nom saisie est vide");
		} else {
			this.nom = nom;
		}
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		prenom = prenom.trim();
		if(prenom=="") {
			throw new IllegalArgumentException("Le prenom saisie est vide");
		} else {
			this.prenom = prenom;
		}
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		identifiant = identifiant.trim();
		if(identifiant=="") {
			throw new IllegalArgumentException("L'identifiant saisie est vide");
		} else {
			this.identifiant = identifiant;
		}
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		motDePasse= motDePasse.trim();
		if(motDePasse=="") {
			throw new IllegalArgumentException("Le mot de passe saisie est vide");
		} else {
			this.motDePasse = motDePasse;
		}
	}

	public String getAdrNumero() {
		return adrNumero;
	}

	public void setAdrNumero(String adrNumero) {
		adrNumero= adrNumero.trim();
		if(adrNumero=="") {
			throw new IllegalArgumentException("Le numero d'adresse saisie est vide");
		} else {
			this.adrNumero = adrNumero;
		}
	}

	public String getAdrVoie() {
		return adrVoie;
	}

	public void setAdrVoie(String adrVoie) {
		adrVoie= adrVoie.trim();
		if(adrVoie=="") {
			throw new IllegalArgumentException("La voie saisie est vide");
		} else {
			this.adrVoie = adrVoie;
		}
	}

	public String getAdrCodePostal() {
		return adrCodePostal;
	}

	public void setAdrCodePostal(String adrCodePostal) {
		adrCodePostal= adrCodePostal.trim();
		if(adrCodePostal=="") {
			throw new IllegalArgumentException("Le code postal saisie est vide");
		} else {
			this.adrCodePostal = adrCodePostal;
		}
	}

	public String getAdrVille() {
		return adrVille;
	}

	public void setAdrVille(String adrVille) {
		adrVille= adrVille.trim();
		if(adrVille=="") {
			throw new IllegalArgumentException("La ville saisie est vide");
		} else {
			this.adrVille = adrVille;
		}
	}

	public String getAdrPays() {
		return adrPays;
	}

	public void setAdrPays(String adrPays) {
		adrPays= adrPays.trim();
		if(adrPays=="") {
			throw new IllegalArgumentException("Le pays saisie est vide");
		} else {
			this.adrPays = adrPays;
		}
	}


	@Override
	public String toString() {
		return id + " - " + nom.toUpperCase() + " - " + prenom.substring(0, 1)+ prenom.substring(1);
	}

}
