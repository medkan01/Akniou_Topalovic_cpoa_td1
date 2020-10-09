package td2.pojo;

public class LigneCommande {
	private int quantite;
	private double tarifUnitaire;
	
	public LigneCommande(int quantite, double tarifUnitaire) {
		this.setQuantite(quantite);
		this.setTarifUnitaire(tarifUnitaire);
	}
	
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public double getTarifUnitaire() {
		return tarifUnitaire;
	}
	public void setTarifUnitaire(double tarifUnitaire) {
		this.tarifUnitaire = tarifUnitaire;
	}
}
