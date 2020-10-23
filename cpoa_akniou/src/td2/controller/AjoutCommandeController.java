package td2.controller;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import td2.dao.daofactory.*;
import td2.pojo.*;

public class AjoutCommandeController {
    
    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    @FXML private AnchorPane panelFenetre;
    @FXML private VBox vBoxFenetre;
    @FXML private GridPane gridFenetre, gridTable, gridBoutonBas, gridBoutonHaut, gridLigneCommande;
    @FXML private Label labelResume;

    @FXML private Button boutonAjouterCommande, boutonAjouterLigneCommande, boutonAnnuler, boutonSuppAll, boutonSuppLigneCommande;
    @FXML private TableView<Produit> tableProduit;
    @FXML private TableView<String> tableProduitSelectionne;

    @FXML
    public boolean creerCommande(){
        try {

        } catch(Exception e) {
            labelResume.setTextFill(Color.web("#FF0000"));
            labelResume.setText(e.getMessage());
        }

        return true;
    }

    public void initialize(URL location, ResourceBundle resources) {
        try {

            // Table de tous les produits
            TableColumn<Produit, String> colNomProd = new TableColumn<Produit, String>("Nom");
            TableColumn<Produit, Integer> colCategorieProd = new TableColumn<Produit, Integer>("ID Categorie");
            TableColumn<Produit, Double> colTarifProd = new TableColumn<Produit, Double>("Tarif unitaire");

            colNomProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
            colCategorieProd.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("idCategorie"));
            colTarifProd.setCellValueFactory(new PropertyValueFactory<Produit, Double>("tarif"));

            this.tableProduit.getColumns().setAll(colNomProd, colCategorieProd, colTarifProd);

            this.tableProduit.getItems().addAll(daos.getProduitDAO().getAll());

            //Table des produits a ajouter
            
            TableColumn<String, String> colNomProdLigneCom = new TableColumn<String, String>("Nom");
            TableColumn<String, Integer> colCategorieProdLigneCom = new TableColumn<String, Integer>("ID Categorie");
            TableColumn<String, Double> colTarifProdLigneCom = new TableColumn<String, Double>("Tarif unitaire");
            TableColumn<String, Integer> colQuantiteProdLigneCom = new TableColumn<String, Integer>("Quantite");

            this.tableProduitSelectionne.getColumns().setAll(colNomProdLigneCom, colCategorieProdLigneCom, colTarifProdLigneCom, colQuantiteProdLigneCom);

        } catch (Exception e) {
            this.labelResume.setText("erreur produit");
        }
    }
}
