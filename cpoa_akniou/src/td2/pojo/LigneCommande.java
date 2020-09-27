package td2.pojo;

public class LigneCommande {
	private int quantite;
	private double tarifUnitaire;
	
	public LigneCommande(int quantite, double tarifUnitaire) {
		this.quantite = quantite;
		this.tarifUnitaire = tarifUnitaire;
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

	@Override
	public String toString() {
		return
			" quantite='" + quantite + "'" +
			", tarifUnitaire='" + tarifUnitaire + "'"
			;
	}

}
