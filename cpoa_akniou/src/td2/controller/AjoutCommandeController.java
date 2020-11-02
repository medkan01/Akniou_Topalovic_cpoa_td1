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
import td2.controller.ChoisirQuantiteLigneCommandeController;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Produit;
import td2.pojo.ProduitSelectionne;
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
            //Ajout du produit dans la table des produits selectionnes
            this.tableProduitSelectionne.getItems().add(produitSelectionne);
            //Fermeture de la fenetre quantite apres recuperation des donnees necessaire
            quantiteStage.close();
            this.tableProduit.getSelectionModel().clearSelection();
            this.tableProduitSelectionne.getSelectionModel().clearSelection();
        } catch (Exception e) {
            e.printStackTrace();
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
            TableColumn<ProduitSelectionne, String> colNomProdLigneCom = new TableColumn<ProduitSelectionne, String>("Nom");
            TableColumn<ProduitSelectionne, String> colNomCategorieProdLigneCom = new TableColumn<ProduitSelectionne, String>("Categorie");
            TableColumn<ProduitSelectionne, Double> colTarifProdLigneCom = new TableColumn<ProduitSelectionne, Double>("Tarif unitaire");
            TableColumn<ProduitSelectionne, Integer> colQuantiteProdLigneCom = new TableColumn<ProduitSelectionne, Integer>("Quantite");
            //Tailles des colonnes
            colNomProdLigneCom.setPrefWidth(130);
            colNomCategorieProdLigneCom.setPrefWidth(130);
            colTarifProdLigneCom.setPrefWidth(79);
            colQuantiteProdLigneCom.setPrefWidth(79);
            //setResizable à l'etat faux
            colNomProdLigneCom.setResizable(false);
            colNomCategorieProdLigneCom.setResizable(false);
            colTarifProdLigneCom.setResizable(false);
            colQuantiteProdLigneCom.setResizable(false);
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
        this.tableProduit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonAjouterLigneCommande.setDisable(newValue == null); this.tableProduitSelectionne.getSelectionModel().clearSelection();});
        this.tableProduitSelectionne.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonSupprimerLigneCommande.setDisable(newValue == null); this.tableProduit.getSelectionModel().clearSelection();});
        //if(this.tableProduit.getSelectionModel().selectedItemProperty().get() != null) this.tableProduitSelectionne.getSelectionModel().select(-1);
    }
}