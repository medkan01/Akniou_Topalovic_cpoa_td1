package td2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Categorie;
import td2.pojo.Commande;
import td2.pojo.LigneCommande;
import td2.pojo.Produit;
import td2.pojo.ProduitSelectionne;

public class DetailsCommandeController{

    private DAOFactory daos;
    @FXML private TableView<ProduitSelectionne> tableProduitSelectionne;
    @FXML private Label labelIdCommande, labelDateCommande, labelIdClient;
    @FXML private Button boutonTerminer;


	public void setCommande(Commande obj){
        try{
            Commande commande = obj;
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
            this.labelIdCommande.setText(String.valueOf(commande.getIdClient()));
            this.labelDateCommande.setText(String.valueOf(commande.getDate()));
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
           //Deuxieme tables contenant les produits à ajouter à la commande
            //Creation des colonnes de la table contenant les produits à ajouter à la commande
            TableColumn<ProduitSelectionne, String> colNomProdLigneCom = new TableColumn<ProduitSelectionne, String>("Nom");
            TableColumn<ProduitSelectionne, String> colNomCategorieProdLigneCom = new TableColumn<ProduitSelectionne, String>("Categorie");
            TableColumn<ProduitSelectionne, Double> colTarifProdLigneCom = new TableColumn<ProduitSelectionne, Double>("Tarif");
            TableColumn<ProduitSelectionne, Integer> colQuantiteProdLigneCom = new TableColumn<ProduitSelectionne, Integer>("Qte");
            TableColumn<ProduitSelectionne, Integer> colIdProduit = new TableColumn<ProduitSelectionne, Integer>("idProduit");
            //Tailles des colonnes
            colNomProdLigneCom.setPrefWidth(120);
            colNomCategorieProdLigneCom.setPrefWidth(120);
            colTarifProdLigneCom.setPrefWidth(70);
            colQuantiteProdLigneCom.setPrefWidth(60);
            colIdProduit.setVisible(false);
            //setResizable = false
            colNomProdLigneCom.setResizable(false);
            colNomCategorieProdLigneCom.setResizable(false);
            colTarifProdLigneCom.setResizable(false);
            colQuantiteProdLigneCom.setResizable(false);
            //Format du type des cellules pour chaque colonne
            colIdProduit.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, Integer>("idProduit"));
            colNomProdLigneCom.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, String>("nomProduit"));
            colNomCategorieProdLigneCom.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, String>("nomCategorie"));
            colTarifProdLigneCom.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, Double>("tarifUnitaire"));
            colQuantiteProdLigneCom.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, Integer>("quantite"));
            //Ajout des colonnes à la table contenant les produits à ajouter à la commande
            ArrayList<TableColumn<ProduitSelectionne,?>> colTableProduitSelectionne = new ArrayList<TableColumn<ProduitSelectionne,?>>();
            colTableProduitSelectionne.add(colIdProduit);
            colTableProduitSelectionne.add(colNomProdLigneCom);
            colTableProduitSelectionne.add(colNomCategorieProdLigneCom);
            colTableProduitSelectionne.add(colTarifProdLigneCom);
            colTableProduitSelectionne.add(colQuantiteProdLigneCom);
            this.tableProduitSelectionne.getColumns().setAll(colTableProduitSelectionne);

    }

    @FXML
    public void close(){
		Stage fenetre = (Stage) this.boutonTerminer.getScene().getWindow();
        fenetre.close();
    }
}   
