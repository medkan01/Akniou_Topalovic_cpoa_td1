package td2.controller;

import java.net.URL;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
import td2.pojo.Categorie;

public class AjoutCommandeController{
    
    private DAOFactory daos;
    @FXML private ChoiceBox<Categorie> cbxCategorie;
    @FXML private AnchorPane panelFenetre;
    @FXML private VBox vBoxFenetre;
    @FXML private GridPane gridFenetre, gridTable, gridBoutonBas, gridBoutonHaut, gridLigneCommande;
    @FXML private Label labelResume;
    @FXML private Button boutonAjouterLigneCommande, boutonSupprimerLigneCommande, boutonSuppAll, boutonAjouterCommande, boutonAnnuler;
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
        int quantite;
        try{
            URL fxmlURLQuantite = getClass().getResource("../javafx/ChoisirQuantiteLigneCommande.fxml");
            FXMLLoader fxmlLoaderQuantite = new FXMLLoader(fxmlURLQuantite);
            Node rootQuantite = fxmlLoaderQuantite.load();
            ChoisirQuantiteLigneCommandeController controller = fxmlLoaderQuantite.getController();
            Scene sceneQuantite = new Scene((AnchorPane) rootQuantite, 150, 105);
            quantiteStage.setScene(sceneQuantite);
            quantiteStage.setTitle("Choix quantité");
            quantiteStage.initModality(Modality.APPLICATION_MODAL);
            quantiteStage.setResizable(false);
            quantiteStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
            quantiteStage.showAndWait();
            quantite = controller.getQuantite();
        } catch (Exception e) {
            e.printStackTrace();
        }
       // Produit produit = this.tableProduit.getSelectionModel().getSelectedItem();

      // this.tableProduitSelectionne.getColumns().add();
    }

    @FXML 
    public void setDaos(String persistance){

        if(persistance.equals("En Ligne")){
            this.daos = DAOFactory.getDAOFactory(Persistance.MySQL);
        }
        else if(persistance.equals("Local")){
            this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        }
        try {
            //Premiere table contenant tous les produits
            //Creation des colonnes de la table contenant tous les produits
            TableColumn<Produit, String> colNomProd = new TableColumn<Produit, String>("Nom");
            TableColumn<Produit, Double> colTarifProd = new TableColumn<Produit, Double>("Tarif unitaire");
            //Taille des colonnes
            colNomProd.setPrefWidth(130);
            colTarifProd.setPrefWidth(98);
            //setResizable à l'etat faux
            colNomProd.setResizable(false);
            colTarifProd.setResizable(false);
            //Format du type des cellules pour chaque colonne
            colNomProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
            colTarifProd.setCellValueFactory(new PropertyValueFactory<Produit, Double>("tarif"));
            //Ajout des colonnes à la table
            this.tableProduit.getColumns().setAll(colNomProd, colTarifProd);
            //Remplissage de la table
            this.tableProduit.getItems().addAll(daos.getProduitDAO().getAll());

            //Deuxieme tables contenant les produits à ajouter à la commande
            //Creation des colonnes de la table contenant les produits à ajouter à la commande
            TableColumn<String, String> colNomProdLigneCom = new TableColumn<String, String>("Nom");
            TableColumn<String, String> colCategorieProdLigneCom = new TableColumn<String, String>("Categorie");
            TableColumn<String, Double> colTarifProdLigneCom = new TableColumn<String, Double>("Tarif unitaire");
            TableColumn<String, Integer> colQuantiteProdLigneCom = new TableColumn<String, Integer>("Quantite");
            //Tailles des colonnes
     
            colNomProdLigneCom.setPrefWidth(130);
            colCategorieProdLigneCom.setPrefWidth(130);
            colTarifProdLigneCom.setPrefWidth(79);
            colQuantiteProdLigneCom.setPrefWidth(79);
        
            //setResizable à l'etat faux
            colNomProdLigneCom.setResizable(false);
            colCategorieProdLigneCom.setResizable(false);
            colTarifProdLigneCom.setResizable(false);
            colQuantiteProdLigneCom.setResizable(false);


            this.tableProduitSelectionne.getColumns().setAll(colNomProdLigneCom, colCategorieProdLigneCom, colTarifProdLigneCom, colQuantiteProdLigneCom);

        } catch (Exception e) {
            this.labelResume.setText("erreur produit");
        }
        
        try {
            this.cbxCategorie.setItems(FXCollections.observableArrayList(daos.getCategorieDAO().getAll()));
        } catch (Exception e) {
            this.labelResume.setTextFill(Color.web("#FF0000"));
            this.labelResume.setText("erreur Categorie");
        }
        this.tableProduit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonAjouterLigneCommande.setDisable(newValue == null);});
        this.tableProduitSelectionne.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonSupprimerLigneCommande.setDisable(newValue == null);});
    }
}