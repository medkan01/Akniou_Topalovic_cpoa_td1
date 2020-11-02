package td2.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Categorie;
import td2.pojo.Commande;
import td2.pojo.LigneCommande;
import td2.pojo.Produit;
import td2.pojo.ProduitSelectionne;

public class DetailsCommandeController{
    
    private int idCom;
    private LocalDate date;
    private DAOFactory daos;
    @FXML private TableView<ProduitSelectionne> tableProduitSelectionne;
    @FXML private Label labelIdCommande, labelDateCommande, labelIdClient;

	public void setCommande(Commande obj){
        try{
            Commande commande = obj;
            this.idCom = commande.getId();
            this.date = commande.getDate();
            HashMap<Produit, LigneCommande> listeLigneCommande = commande.getLigneCommande();
            ArrayList<ProduitSelectionne> listeProduitSelectionnes = new ArrayList<ProduitSelectionne>();
            Set<Produit> keys = listeLigneCommande.keySet();
            ArrayList<Produit> listeProduitLigneCommande = new ArrayList<Produit>(keys);
			for(int i = 0; i<listeProduitLigneCommande.size();i++){
                Produit produit = listeProduitLigneCommande.get(i);
                Categorie categorie = daos.getCategorieDAO().getById(produit.getIdCategorie());
                LigneCommande ligneCommande = listeLigneCommande.get(produit);
                ProduitSelectionne produitSelectionne = new ProduitSelectionne(produit.getId(), produit.getNom(), categorie.getTitre(), ligneCommande.getTarifUnitaire(), ligneCommande.getQuantite());
                listeProduitSelectionnes.add(produitSelectionne);
            }
            this.tableProduitSelectionne.getItems().addAll(listeProduitSelectionnes);
            this.labelIdClient.setText(String.valueOf(commande.getIdClient()));
        } catch(Exception e){
            String erreur = "Erreur";
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Impossible de récupérer les lignes de commande");
            alert.setHeaderText(e.getMessage());
            alert.setContentText(erreur);
            alert.showAndWait();
            }
        }

        @FXML 
    public void setDaos(String persistance){

        if(persistance.equals("En Ligne")){
            this.daos = DAOFactory.getDAOFactory(Persistance.MySQL);
        }
        else if(persistance.equals("Local")){
            this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        }

    }
}
