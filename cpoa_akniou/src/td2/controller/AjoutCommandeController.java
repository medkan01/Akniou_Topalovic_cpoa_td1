package td2.controller;

import java.net.URL;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import td2.pojo.ProduitSelectionne;
import td2.pojo.Categorie;
import td2.pojo.Client;

public class AjoutCommandeController{
    
    private DAOFactory daos;
    @FXML private ChoiceBox<Categorie> cbxCategorie;
    @FXML private ChoiceBox<Client> cbxClient;
    @FXML private AnchorPane panelFenetre;
    @FXML private VBox vBoxFenetre;
    @FXML private GridPane gridFenetre, gridTable, gridBoutonBas, gridBoutonHaut, gridLigneCommande;
    @FXML private Label labelResume;
    @FXML private Button boutonAjouterLigneCommande, boutonSupprimerLigneCommande, boutonSuppAll, boutonAjouterCommande, boutonAnnuler;
    @FXML private TableView<Produit> tableProduit;
    @FXML private TableView<ProduitSelectionne> tableProduitSelectionne;

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
            //Creation du produit selectionne
            Produit produit = this.tableProduit.getSelectionModel().getSelectedItem();
            String nomProduit = produit.getNom();
            String nomCategorie = daos.getCategorieDAO().getById(produit.getIdCategorie()).getTitre();
            double tarifUnitaire = produit.getTarif();
            int quantite = controller.getQuantite();
            ProduitSelectionne produitSelectionne = new ProduitSelectionne(nomProduit, nomCategorie, tarifUnitaire, quantite);
            if(this.tableProduitSelectionne.getItems().contains(produitSelectionne)){
                this.tableProduitSelectionne.getSelectionModel().select(produitSelectionne);
                produitSelectionne = this.tableProduitSelectionne.getSelectionModel().getSelectedItem();
                produitSelectionne.setQuantite(produitSelectionne.getQuantite()+quantite);
            } else{
                this.tableProduitSelectionne.getItems().add(produitSelectionne);
            }
            //Fermeture de la fenetre quantite apres recuperation des donnees necessaire
            quantiteStage.close();
            this.tableProduit.getSelectionModel().clearSelection();
            this.tableProduitSelectionne.getSelectionModel().clearSelection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void supprimer(){
        ProduitSelectionne produitSelectionne = this.tableProduitSelectionne.getSelectionModel().getSelectedItem();
        this.tableProduitSelectionne.getItems().remove(produitSelectionne);
    }

    @FXML
    public void ajouterClient(){
        Stage ajoutStage = new Stage();
        try {
            URL fxmlURLAjouterClient = getClass().getResource("../javafx/AjoutClient.fxml");
            FXMLLoader fxmlLoaderAjouterClient = new FXMLLoader(fxmlURLAjouterClient);
            Node rootAjouterClient = fxmlLoaderAjouterClient.load();
            AjoutClientController controller = fxmlLoaderAjouterClient.getController();
            controller.setDaos(DAOFactory.getPersistanceActuelle());
            Scene sceneAjouterClient = new Scene((AnchorPane) rootAjouterClient, 800, 500);
            sceneAjouterClient.getStylesheets().add(getClass().getResource("../javafx/css/themeClaire.css").toExternalForm());
            ajoutStage.setScene(sceneAjouterClient);
            ajoutStage.setTitle("Ajout client");
            ajoutStage.initModality(Modality.APPLICATION_MODAL);
            ajoutStage.setResizable(false);
            ajoutStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
            ajoutStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try { 
            this.cbxClient.setItems(FXCollections.observableArrayList(daos.getClientDAO().getAll()));
        } catch (Exception e) {
            this.labelResume.setTextFill(Color.web("#FF0000"));
            this.labelResume.setText("erreur Client");
        }
    }

    @FXML
    public void ajouterCommande(){

    }

    @FXML
    public void annuler(){
        Stage fenetre = (Stage) boutonAnnuler.getScene().getWindow();
        fenetre.close();
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
            TableColumn<Produit, Double> colTarifProd = new TableColumn<Produit, Double>("Tarif");
            //Taille des colonnes
            colNomProd.setPrefWidth(130);
            colTarifProd.setPrefWidth(90);
            //Format du type des cellules pour chaque colonne
            colNomProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
            colTarifProd.setCellValueFactory(new PropertyValueFactory<Produit, Double>("tarif"));
            //Ajout des colonnes à la table
            this.tableProduit.getColumns().setAll(colNomProd, colTarifProd);
            //Remplissage de la table
            this.tableProduit.getItems().addAll(daos.getProduitDAO().getAll());

            //Deuxieme tables contenant les produits à ajouter à la commande
            //Creation des colonnes de la table contenant les produits à ajouter à la commande
            TableColumn<ProduitSelectionne, String> colNomProdLigneCom = new TableColumn<ProduitSelectionne, String>("Nom");
            TableColumn<ProduitSelectionne, String> colNomCategorieProdLigneCom = new TableColumn<ProduitSelectionne, String>("Categorie");
            TableColumn<ProduitSelectionne, Double> colTarifProdLigneCom = new TableColumn<ProduitSelectionne, Double>("Tarif");
            TableColumn<ProduitSelectionne, Integer> colQuantiteProdLigneCom = new TableColumn<ProduitSelectionne, Integer>("Quantité");
            //Tailles des colonnes
            colNomProdLigneCom.setPrefWidth(128);
            colNomCategorieProdLigneCom.setPrefWidth(128);
            colTarifProdLigneCom.setPrefWidth(77);
            colQuantiteProdLigneCom.setPrefWidth(77);
            //Format du type des cellules pour chaque colonne
            colNomProdLigneCom.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, String>("nomProduit"));
            colNomCategorieProdLigneCom.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, String>("nomCategorie"));
            colTarifProdLigneCom.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, Double>("tarifUnitaire"));
            colQuantiteProdLigneCom.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, Integer>("quantite"));
            //Ajout des colonnes à la table contenant les produits à ajouter à la commande
            this.tableProduitSelectionne.getColumns().setAll(colNomProdLigneCom, colNomCategorieProdLigneCom, colTarifProdLigneCom, colQuantiteProdLigneCom);

        } catch (Exception e) {
            this.labelResume.setText("erreur produit");
        }
        
        try {
            this.cbxCategorie.setItems(FXCollections.observableArrayList(daos.getCategorieDAO().getAll()));
        } catch (Exception e) {
            this.labelResume.setTextFill(Color.web("#FF0000"));
            this.labelResume.setText("erreur Categorie");
        }

        try {
            this.cbxClient.setItems(FXCollections.observableArrayList(daos.getClientDAO().getAll()));
        } catch (Exception e) {
            String erreur = "Erreur lors de la recherche de tous les clients";
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur Client");
            alert.setHeaderText("Veuillez vérifier que vous êtes bien connecté à la base de données");
            alert.setContentText(erreur);alert.showAndWait();
        }
        
        this.cbxCategorie.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.tableProduit.getItems().clear();try{this.tableProduit.getItems().addAll(daos.getProduitDAO().getAllByCategorie(this.cbxCategorie.getSelectionModel().getSelectedItem().getId()));}catch(Exception e){System.out.print(e.getMessage());}});
        this.tableProduit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonAjouterLigneCommande.setDisable(newValue == null); this.tableProduitSelectionne.getSelectionModel().select(-1); this.tableProduitSelectionne.getSelectionModel().clearSelection();});
        this.tableProduitSelectionne.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonSupprimerLigneCommande.setDisable(newValue == null); this.tableProduitSelectionne.getSelectionModel().select(-1); this.tableProduit.getSelectionModel().clearSelection();});
        
    }
}