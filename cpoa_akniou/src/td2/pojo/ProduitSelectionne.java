package td2.pojo;

public class ProduitSelectionne {

    private String nomProduit, nomCategorie;
    private double tarifUnitaire;
    private int quantite, idProduit;

    public ProduitSelectionne(int idProduit, String nomProduit, String nomCategorie, double tarifUnitaire, int quantite) {
        this.setIdProduit(idProduit);
        this.setNomProduit(nomProduit);
        this.setNomCategorie(nomCategorie);
        this.setTarifUnitaire(tarifUnitaire);
        this.setQuantite(quantite);
    }

    private void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdProduit() {
        return this.idProduit;
    }

    public void setNomProduit(String nomProduit) {
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
        if(quantite==0){
            throw new IllegalArgumentException("la quantite saisie est egale a 0");
        } else{
            this.quantite = quantite;
        }
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
