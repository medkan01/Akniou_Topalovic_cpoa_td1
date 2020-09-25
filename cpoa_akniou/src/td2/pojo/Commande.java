package td2.pojo;

import java.time.LocalDate;

public class Commande {

	//VARIABLES
	private int id;
	private LocalDate date;
	private int idClient;
	
	//CONSTRUCTEUR
	public Commande(int id, LocalDate date, int idClient) {
		super();
		this.id = id;
		this.date = date;
		this.idClient = idClient;
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
}
