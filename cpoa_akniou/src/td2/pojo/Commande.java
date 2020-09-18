package td2.pojo;

import java.sql.Date;

public class Commande {

	//VARIABLES
	private int id;
	private Date date;
	private int idClient;
	
	//CONSTRUCTEUR
	public Commande(int id, Date date, int idClient) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	
	
}
