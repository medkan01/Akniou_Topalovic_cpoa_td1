package td2.controller;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Produit;

public class AjoutCommandeController{
    
    static DAOFactory daos;
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

    @FXML
    public void ajouter(){
        Stage quantiteStage = new Stage();
        try{
        URL fxmlURLQuantite = getClass().getResource("../javafx/ChoisirQuantiteLigneCommande.fxml");
        FXMLLoader fxmlLoaderConnexion = new FXMLLoader(fxmlURLQuantite);
        Node rootQuantite = fxmlLoaderConnexion.load();
        Scene sceneConnexion = new Scene((AnchorPane) rootQuantite, 150, 105);
        quantiteStage.setScene(sceneConnexion);
        quantiteStage.setTitle("Choix quantité");
        quantiteStage.initModality(Modality.APPLICATION_MODAL);
        quantiteStage.setResizable(false);
        quantiteStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
        quantiteStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML 
    public void setDaos(String persistance){

        if(persistance.equals("MySQL")){
            this.daos = DAOFactory.getDAOFactory(Persistance.MySQL);
        }
        else if(persistance.equals("ListeMemoire")){
            this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        }
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