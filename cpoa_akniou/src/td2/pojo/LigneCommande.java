package td2.pojo;

public class LigneCommande {
	private int idCommande;
	private int idProduit;
	private int quantite;
	private double tarifUnitaire;
	
	public LigneCommande(int idCommande, int idProduit, int quantite, double tarifUnitaire) {
		this.idCommande = idCommande;
		this.idProduit = idProduit;
		this.quantite = quantite;
		this.tarifUnitaire = tarifUnitaire;
	}
	
	public int getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}
	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
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
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof LigneCommande)) {
			return false;
		}
		LigneCommande ligneCommande = (LigneCommande) o;
		return idCommande == ligneCommande.idCommande && idProduit == ligneCommande.idProduit && quantite == ligneCommande.quantite && tarifUnitaire == ligneCommande.tarifUnitaire;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCommande, idProduit, quantite, tarifUnitaire);
	}


}
