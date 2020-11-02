package td2.pojo;

public class ProduitSelectionne {

    private String nomProduit, nomCategorie;
    private double tarifUnitaire;
    private int quantite;

    public ProduitSelectionne(String nomProduit, String nomCategorie, double tarifUnitaire, int quantite) {
        this.setNomProduit(nomProduit);
        this.setNomCategorie(nomCategorie);
        this.setTarifUnitaire(tarifUnitaire);
        this.setQuantite(quantite);
    }

    public void setNomProduit(String nomProduit){
        if(nomProduit.trim() == "") {
            throw new IllegalArgumentException("Le nom du produit saisie est vide");
        } else {
            this.nomProduit = nomProduit.trim();
        }
    }

    public void setNomCategorie(String nomCategorie) {
        if(nomCategorie.trim() == "") {
            throw new IllegalArgumentException("Le nom du produit saisie est vide");
        } else {
            this.nomCategorie = nomCategorie.trim();
        }
    }

    public void setTarifUnitaire(double tarifUnitaire) {
        this.tarifUnitaire = tarifUnitaire;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomProduit() {
        return this.nomProduit;
    }

    public String getNomCategorie() {
        return this.nomCategorie;
    }

    public double getTarifUnitaire() {
        return this.tarifUnitaire;
    }

    public int getQuantite() {
        return this.quantite;
    }

}
