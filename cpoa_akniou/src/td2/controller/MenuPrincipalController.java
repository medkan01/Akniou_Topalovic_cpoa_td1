package td2.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Categorie;
import td2.pojo.Client;
import td2.pojo.Commande;
import td2.pojo.Produit;

public class MenuPrincipalController implements Initializable{
    
    private DAOFactory daos;
    @FXML private Button boutonCategories;
    @FXML private Button boutonClients;
    @FXML private Button boutonCommandes;
    @FXML private Button boutonProduits;
    @FXML private TableView<Categorie> tableCategorie;
    @FXML private TableView<Client> tableClient;
    @FXML private TableView<Commande> tableCommande;
    @FXML private TableView<Produit> tableProduit;
    @FXML private RadioMenuItem online;
    @FXML private RadioMenuItem offline;
    @FXML private RadioMenuItem nightMode;
    @FXML private Button boutonDetails;
    @FXML private Button boutonAjouter;
    @FXML private Button boutonModifier;
    @FXML private Button boutonSupprimer;
    @FXML private AnchorPane affichageTableau;
    @FXML private Pane panelBoutonInteraction;

    public void initialize(URL location, ResourceBundle resources) {
        Stage connexionStage = new Stage();
        if((!this.online.isSelected())&&(!this.offline.isSelected())){
            try{
                URL fxmlURLConnexion=getClass().getResource("../javafx/Accueil.fxml");
                FXMLLoader fxmlLoaderConnexion = new FXMLLoader(fxmlURLConnexion);
                Node rootConnexion = fxmlLoaderConnexion.load();
                AccueilController controller = fxmlLoaderConnexion.getController();
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 980, 650);
            //    sceneConnexion.getStylesheets().add(getClass().getResource("../javafx/css/themeSombre.css").toExternalForm());
                connexionStage.setScene(sceneConnexion);
                connexionStage.setTitle("Connexion");
                connexionStage.initModality(Modality.APPLICATION_MODAL);
                connexionStage.setResizable(false);
                connexionStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                connexionStage.showAndWait();
                daos=controller.getDaos();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if(daos.getPersistanceActuelle().equals("MySQL")){
                this.online.setSelected(true);
                this.offline.setSelected(false);
            }
            else if(daos.getPersistanceActuelle().equals("ListeMemoire")){
                this.offline.setSelected(true);
                this.online.setSelected(false);
            }
        }
        
    }
    @FXML 
    public void setInstanceOnline(){
        Stage connexionStage = new Stage();
        try{
            URL fxmlURLConnexion=getClass().getResource("../javafx/Connexion.fxml");
            FXMLLoader fxmlLoaderConnexion = new FXMLLoader(fxmlURLConnexion);
            Node rootConnexion = fxmlLoaderConnexion.load();
            ConnexionController controller = fxmlLoaderConnexion.getController();
            Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 505, 315);
            connexionStage.setScene(sceneConnexion);
            connexionStage.setTitle("Connexion");
            connexionStage.initModality(Modality.APPLICATION_MODAL);
            connexionStage.setResizable(false);
            connexionStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
            connexionStage.showAndWait();

            if(controller.seConnecter()){
                this.daos = DAOFactory.getDAOFactory(Persistance.MySQL);
                this.affichageTableau.getChildren().clear();
                this.panelBoutonInteraction.setDisable(true);
           }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML 
    public void setInstanceOffline(){
        this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        this.affichageTableau.getChildren().clear();
        this.panelBoutonInteraction.setDisable(true);
    }

    @FXML
    public void afficherCategories() throws SQLException{
        
        tableCategorie = new TableView<Categorie>();
        this.tableCategorie.setMinSize(870, 620);

        TableColumn<Categorie, String> colTitre = new TableColumn<>("Titre");
        colTitre.setCellValueFactory(new PropertyValueFactory<Categorie, String>("titre"));

        TableColumn<Categorie, String> colVisuel = new TableColumn<>("Visuel");
        colVisuel.setCellValueFactory(new PropertyValueFactory<Categorie, String>("visuel"));

        this.tableCategorie.getColumns().setAll(colTitre,colVisuel);

        this.tableCategorie.getItems().addAll(daos.getCategorieDAO().getAll());

        this.affichageTableau.getChildren().clear();
        this.affichageTableau.getChildren().addAll(tableCategorie);

        this.panelBoutonInteraction.setDisable(false);
    }

    @FXML
    public void afficherClients() throws SQLException {

        tableClient = new TableView<Client>();
        this.tableClient.setMinSize(870, 620);
        
        TableColumn<Client, String> colNom = new TableColumn<>("Nom");
        colNom.setPrefWidth(100);
        colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));

        TableColumn<Client, String> colPrenom = new TableColumn<>("Prenom");
        colPrenom.setPrefWidth(100);
        colPrenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));

        TableColumn<Client, String> colIdentifiant = new TableColumn<>("E-mail");
        colIdentifiant.setPrefWidth(200);
        colIdentifiant.setCellValueFactory(new PropertyValueFactory<Client, String>("identifiant"));

        TableColumn<Client, String> colAdrNumero = new TableColumn<>("N°");
        colAdrNumero.setPrefWidth(30);
        colAdrNumero.setCellValueFactory(new PropertyValueFactory<Client, String>("adrNumero"));
        
        TableColumn<Client, String> colAdrVoie = new TableColumn<>("Voie");
        colAdrVoie.setPrefWidth(150);
        colAdrVoie.setCellValueFactory(new PropertyValueFactory<Client, String>("adrVoie"));

        TableColumn<Client, String> colAdrCodePostal = new TableColumn<>("CP");
        colAdrCodePostal.setPrefWidth(40);
        colAdrCodePostal.setCellValueFactory(new PropertyValueFactory<Client, String>("adrCodePostal"));

        TableColumn<Client, String> colAdrVille = new TableColumn<>("Ville");
        colAdrVille.setPrefWidth(75);
        colAdrVille.setCellValueFactory(new PropertyValueFactory<Client, String>("adrVille"));

        TableColumn<Client, String> colAdrPays = new TableColumn<>("Pays");
        colAdrPays.setPrefWidth(75);
        colAdrPays.setCellValueFactory(new PropertyValueFactory<Client, String>("adrPays"));

        this.tableClient.getColumns().setAll(colNom, colPrenom, colIdentifiant, colAdrNumero, colAdrVoie, colAdrCodePostal, colAdrVille, colAdrPays);

        this.tableClient.getItems().addAll(daos.getClientDAO().getAll());

        this.affichageTableau.getChildren().clear();
        this.affichageTableau.getChildren().addAll(tableClient);

        this.panelBoutonInteraction.setDisable(false);
    }

    @FXML
    public void afficherCommandes() throws SQLException {

        tableCommande = new TableView<Commande>();
        this.tableCommande.setMinSize(870, 620);
        
        TableColumn<Commande, Integer> colIdCommande = new TableColumn<>("ID Commande");
        colIdCommande.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("id"));

        TableColumn<Commande,String> colDate = new TableColumn<>("Date");
        colDate.setCellValueFactory(new PropertyValueFactory<Commande, String>("date"));

        TableColumn<Commande, Integer> colIdClient = new TableColumn<>("ID Client");
        colIdClient.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("idClient"));

        this.tableCommande.getColumns().setAll(colIdCommande,colDate,colIdClient);

        this.tableCommande.getItems().addAll(daos.getCommandeDAO().getAll());

        this.affichageTableau.getChildren().clear();
        this.affichageTableau.getChildren().addAll(tableCommande);

        this.panelBoutonInteraction.setDisable(false);
    }

        
    @FXML
    public void afficherProduits() throws SQLException {

        tableProduit = new TableView<Produit>();
        this.tableProduit.setMinSize(870, 620);

        TableColumn<Produit, String> colTarif = new TableColumn<>("Tarif");
        colTarif.setPrefWidth(50);
        colTarif.setCellValueFactory(new PropertyValueFactory<Produit, String>("tarif"));

        TableColumn<Produit,String> colLibelle = new TableColumn<>("Nom");
        colLibelle.setPrefWidth(120);
        colLibelle.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));

        TableColumn<Produit, String> colDescription = new TableColumn<>("Description");
        colDescription.setPrefWidth(700);
        colDescription.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));

        this.tableProduit.getColumns().setAll(colTarif,colLibelle,colDescription);

        this.tableProduit.getItems().addAll(daos.getProduitDAO().getAll());

        this.affichageTableau.getChildren().clear();
        this.affichageTableau.getChildren().addAll(tableProduit);

        this.panelBoutonInteraction.setDisable(false);
    }

    @FXML
    public void Ajouter(){
        Stage ajoutStage = new Stage();
        if(this.affichageTableau.getChildren().contains(tableCategorie))
        {
            try{
            URL fxmlURLConnexion=getClass().getResource("../javafx/AjoutCategorie.fxml");
            FXMLLoader fxmlLoaderConnexion = new FXMLLoader(fxmlURLConnexion);
            Node rootConnexion = fxmlLoaderConnexion.load();
           // AccueilController controller = fxmlLoaderConnexion.getController();
            Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 420, 430);
            ajoutStage.setScene(sceneConnexion);
            ajoutStage.setTitle("Ajout Categorie");
            ajoutStage.initModality(Modality.APPLICATION_MODAL);
            ajoutStage.setResizable(false);
            ajoutStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
            ajoutStage.showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        }
        else if(this.affichageTableau.getChildren().contains(tableClient))
        {
            try{
                URL fxmlURLConnexion=getClass().getResource("../javafx/AjoutClient.fxml");
                FXMLLoader fxmlLoaderConnexion = new FXMLLoader(fxmlURLConnexion);
                Node rootConnexion = fxmlLoaderConnexion.load();
               // AccueilController controller = fxmlLoaderConnexion.getController();
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 420, 430);
                ajoutStage.setScene(sceneConnexion);
                ajoutStage.setTitle("Ajout Categorie");
                ajoutStage.initModality(Modality.APPLICATION_MODAL);
                ajoutStage.setResizable(false);
                ajoutStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                ajoutStage.showAndWait();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
       /* else if(this.affichageTableau.getChildren().contains(tableCommande))
        {
            try{
                URL fxmlURLConnexion=getClass().getResource("../javafx/AjoutCommande.fxml");
                FXMLLoader fxmlLoaderConnexion = new FXMLLoader(fxmlURLConnexion);
                Node rootConnexion = fxmlLoaderConnexion.load();
               // AccueilController controller = fxmlLoaderConnexion.getController();
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 420, 430);
                ajoutStage.setScene(sceneConnexion);
                ajoutStage.setTitle("Ajout Categorie");
                ajoutStage.initModality(Modality.APPLICATION_MODAL);
                ajoutStage.setResizable(false);
                ajoutStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                ajoutStage.showAndWait();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        else if(this.affichageTableau.getChildren().contains(tableProduit)){
            try{
                URL fxmlURLConnexion=getClass().getResource("../javafx/AjoutProduit.fxml");
                FXMLLoader fxmlLoaderConnexion = new FXMLLoader(fxmlURLConnexion);
                Node rootConnexion = fxmlLoaderConnexion.load();
                AjoutProduitController controller = fxmlLoaderConnexion.getController();
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 680, 550);
                controller.setDaos(daos.getPersistanceActuelle());
                ajoutStage.setScene(sceneConnexion);
                ajoutStage.setTitle("Ajout Produit");
                ajoutStage.initModality(Modality.APPLICATION_MODAL);
                ajoutStage.setResizable(false);
                ajoutStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                ajoutStage.showAndWait();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
