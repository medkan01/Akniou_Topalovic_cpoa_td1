package td2.controller.autresController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import td2.controller.ajoutController.AjoutCategorieController;
import td2.controller.ajoutController.AjoutClientController;
import td2.controller.ajoutController.AjoutCommandeController;
import td2.controller.ajoutController.AjoutProduitController;
import td2.controller.detailsController.DetailsCategorieController;
import td2.controller.detailsController.DetailsClientController;
import td2.controller.detailsController.DetailsProduitController;
import td2.controller.modifierController.ModifierCategorieController;
import td2.controller.modifierController.ModifierClientController;
import td2.controller.modifierController.ModifierProduitController;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Categorie;
import td2.pojo.Client;
import td2.pojo.Commande;
import td2.pojo.Produit;

public class MenuPrincipalController implements Initializable {

    private DAOFactory daos;
    @FXML private Button boutonCategories, boutonClients, boutonCommandes,boutonProduits, boutonDetails, boutonAjouter, boutonModifier, boutonSupprimer;
    @FXML private RadioMenuItem online, offline, nightMode, lightMode;
    @FXML private TableView<Categorie> tableCategorie;
    @FXML private TableView<Client> tableClient;
    @FXML private TableView<Commande> tableCommande;
    @FXML private TableView<Produit> tableProduit;
    @FXML private AnchorPane affichageTableau;
    @FXML private Pane panelBoutonInteraction;
    @FXML private Label labelInstance;

    public void initialize(URL location, ResourceBundle resources) {
        Stage connexionStage = new Stage();
        if ((!this.online.isSelected()) && (!this.offline.isSelected())) {
            try {
                URL fxmlURLConnexion = getClass().getResource("../javafx/Accueil.fxml");
                FXMLLoader fxmlLoaderConnexion = new FXMLLoader(fxmlURLConnexion);
                Node rootConnexion = fxmlLoaderConnexion.load();
                AccueilController controller = fxmlLoaderConnexion.getController();
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 980, 650);
                // sceneConnexion.getStylesheets().add(getClass().getResource("../javafx/css/themeSombre.css").toExternalForm());
                connexionStage.setScene(sceneConnexion);
                connexionStage.setTitle("Connexion");
                connexionStage.initModality(Modality.APPLICATION_MODAL);
                connexionStage.setResizable(false);
                connexionStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                connexionStage.showAndWait();
                daos = controller.getDaos();

               
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (DAOFactory.getPersistanceActuelle().equals("En Ligne")) {
                this.online.setSelected(true);
                this.offline.setSelected(false);
            } else if (DAOFactory.getPersistanceActuelle().equals("Local")) {
                this.offline.setSelected(true);
                this.online.setSelected(false);
            }
            this.labelInstance.setText(DAOFactory.getPersistanceActuelle());
        }
    }

    @FXML
    public void setInstanceOnline() {
        Stage connexionStage = new Stage();
        try {
            URL fxmlURLConnexion = getClass().getResource("../javafx/Connexion.fxml");
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

            if (controller.seConnecter()) {
                this.daos = DAOFactory.getDAOFactory(Persistance.MySQL);
                this.affichageTableau.getChildren().clear();
                this.boutonAjouter.setDisable(true);
                this.boutonDetails.setDisable(true);
                this.boutonModifier.setDisable(true);
                this.boutonSupprimer.setDisable(true);
                this.labelInstance.setText(DAOFactory.getPersistanceActuelle());
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void setInstanceOffline() {
        this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        this.affichageTableau.getChildren().clear();
        this.labelInstance.setText(DAOFactory.getPersistanceActuelle());
    }

    @FXML
    public void afficherCategorie() throws SQLException {
        this.affichageTableau.getChildren().clear();
        this.boutonAjouter.setDisable(false);
        this.boutonDetails.setDisable(true);
        this.boutonModifier.setDisable(true);
        this.boutonSupprimer.setDisable(true);
        tableCategorie = new TableView<Categorie>();
        this.tableCategorie.setMinSize(870, 620);
        TableColumn<Categorie, String> colTitre = new TableColumn<>("Titre");
        colTitre.setCellValueFactory(new PropertyValueFactory<Categorie, String>("titre"));
        TableColumn<Categorie, String> colVisuel = new TableColumn<>("Visuel");
        colVisuel.setCellValueFactory(new PropertyValueFactory<Categorie, String>("visuel"));
        this.tableCategorie.getColumns().setAll(colTitre, colVisuel);
        this.tableCategorie.getItems().addAll(daos.getCategorieDAO().getAll());
        this.tableCategorie.requestLayout();
        this.affichageTableau.getChildren().addAll(tableCategorie);
        this.tableCategorie.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonDetails.setDisable(newValue == null);this.boutonModifier.setDisable(newValue == null);this.boutonSupprimer.setDisable(newValue == null);});
    }

    @FXML
    public void afficherClients() throws SQLException {
        this.affichageTableau.getChildren().clear();
        this.boutonAjouter.setDisable(false);
        this.boutonDetails.setDisable(true);
        this.boutonModifier.setDisable(true);
        this.boutonSupprimer.setDisable(true);
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
        this.tableClient.getColumns().setAll(colNom, colPrenom, colIdentifiant, colAdrNumero, colAdrVoie,colAdrCodePostal, colAdrVille, colAdrPays);
        this.tableClient.getItems().addAll(daos.getClientDAO().getAll());
        this.tableClient.requestLayout();
        this.affichageTableau.getChildren().addAll(tableClient);
        this.tableClient.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonDetails.setDisable(newValue == null);this.boutonModifier.setDisable(newValue == null);this.boutonSupprimer.setDisable(newValue == null);});
    }

    @FXML
    public void afficherCommandes() throws SQLException {
        this.affichageTableau.getChildren().clear();
        this.boutonAjouter.setDisable(false);
        this.boutonDetails.setDisable(true);
        this.boutonModifier.setDisable(true);
        this.boutonSupprimer.setDisable(true);
        tableCommande = new TableView<Commande>();
        this.tableCommande.setMinSize(870, 620);
        TableColumn<Commande, Integer> colIdCommande = new TableColumn<>("ID Commande");
        colIdCommande.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("id"));
        TableColumn<Commande, String> colDate = new TableColumn<>("Date");
        colDate.setCellValueFactory(new PropertyValueFactory<Commande, String>("date"));
        TableColumn<Commande, Integer> colIdClient = new TableColumn<>("ID Client");
        colIdClient.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("idClient"));
        this.tableCommande.getColumns().setAll(colIdCommande, colDate, colIdClient);
        this.tableCommande.getItems().addAll(daos.getCommandeDAO().getAll());
        this.tableCommande.requestLayout();;
        this.affichageTableau.getChildren().addAll(tableCommande);
        this.tableCommande.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonDetails.setDisable(newValue == null);this.boutonModifier.setDisable(newValue == null);this.boutonSupprimer.setDisable(newValue == null);});

    }

    @FXML
    public void afficherProduits() throws SQLException {
        this.affichageTableau.getChildren().clear();
        this.boutonAjouter.setDisable(false);
        this.boutonDetails.setDisable(true);
        this.boutonModifier.setDisable(true);
        this.boutonSupprimer.setDisable(true);
        tableProduit = new TableView<Produit>();
        this.tableProduit.setMinSize(870, 620);
        TableColumn<Produit, String> colTarif = new TableColumn<>("Tarif");
        colTarif.setPrefWidth(50);
        colTarif.setCellValueFactory(new PropertyValueFactory<Produit, String>("tarif"));
        TableColumn<Produit, String> colLibelle = new TableColumn<>("Nom");
        colLibelle.setPrefWidth(120);
        colLibelle.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        TableColumn<Produit, String> colDescription = new TableColumn<>("Description");
        colDescription.setPrefWidth(700);
        colDescription.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        this.tableProduit.getColumns().setAll(colTarif, colLibelle, colDescription);
        this.tableProduit.getItems().addAll(daos.getProduitDAO().getAll());
        this.tableProduit.requestLayout();
        this.affichageTableau.getChildren().addAll(tableProduit);
        this.tableProduit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonDetails.setDisable(newValue == null);this.boutonModifier.setDisable(newValue == null);this.boutonSupprimer.setDisable(newValue == null);});
    }

    @FXML
    public void ajouter() {
        Stage ajoutStage = new Stage();
        if (this.affichageTableau.getChildren().contains(tableCategorie)) {
            try {
                URL fxmlURLAjouterCategorie = getClass().getResource("../javafx/AjoutCategorie.fxml");
                FXMLLoader fxmlLoaderAjouterCategorie = new FXMLLoader(fxmlURLAjouterCategorie);
                Node rootConnexion = fxmlLoaderAjouterCategorie.load();
                AjoutCategorieController controller = fxmlLoaderAjouterCategorie.getController();
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 550, 180);
                ajoutStage.setScene(sceneConnexion);
                ajoutStage.setTitle("Ajout Categorie");
                ajoutStage.initModality(Modality.APPLICATION_MODAL);
                ajoutStage.setResizable(false);
                ajoutStage.getIcons()
                        .add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                ajoutStage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.affichageTableau.getChildren().clear();
            try {
                afficherCategorie();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (this.affichageTableau.getChildren().contains(tableClient)) {
            try {
                URL fxmlURLAjouterClient = getClass().getResource("../javafx/AjoutClient.fxml");
                FXMLLoader fxmlLoaderAjouterClient = new FXMLLoader(fxmlURLAjouterClient);
                Node rootConnexion = fxmlLoaderAjouterClient.load();
                AjoutClientController controller = fxmlLoaderAjouterClient.getController();
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 800, 500);
                ajoutStage.setScene(sceneConnexion);
                ajoutStage.setTitle("Ajout client");
                ajoutStage.initModality(Modality.APPLICATION_MODAL);
                ajoutStage.setResizable(false);
                ajoutStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                ajoutStage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.affichageTableau.getChildren().clear();
            try {
                afficherClients();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (this.affichageTableau.getChildren().contains(tableCommande)) {
            try {
                URL fxmlURLAjouterCommande = getClass().getResource("../javafx/AjoutCommande.fxml");
                FXMLLoader fxmlLoaderAjouterCommande = new FXMLLoader(fxmlURLAjouterCommande);
                Node rootConnexion = fxmlLoaderAjouterCommande.load();
                AjoutCommandeController controller = fxmlLoaderAjouterCommande.getController();
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 900, 500);
                ajoutStage.setScene(sceneConnexion);
                ajoutStage.setTitle("Ajout Commande");
                ajoutStage.initModality(Modality.APPLICATION_MODAL);
                ajoutStage.setResizable(false);
                ajoutStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                ajoutStage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (this.affichageTableau.getChildren().contains(tableProduit)) {
            try {
                URL fxmlURLAjouterProduit = getClass().getResource("../javafx/AjoutProduit.fxml");
                FXMLLoader fxmlLoaderAjouterProduit = new FXMLLoader(fxmlURLAjouterProduit);
                Node rootConnexion = fxmlLoaderAjouterProduit.load();
                AjoutProduitController controller = fxmlLoaderAjouterProduit.getController();
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 680, 550);
                ajoutStage.setScene(sceneConnexion);
                ajoutStage.setTitle("Ajout Produit");
                ajoutStage.initModality(Modality.APPLICATION_MODAL);
                ajoutStage.setResizable(false);
                ajoutStage.getIcons()
                        .add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                ajoutStage.showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
            }

            this.affichageTableau.getChildren().clear();
            try {
                afficherProduits();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void details() {
        Stage detailsStage = new Stage();
        try{
            if(this.affichageTableau.getChildren().contains(tableCategorie)){
                Categorie categorie = this.tableCategorie.getSelectionModel().getSelectedItem();
                URL fxmlURLDetailsCategorie = getClass().getResource("../javafx/DetailsCategorie.fxml");
                FXMLLoader fxmlLoaderDetailsCategorie = new FXMLLoader(fxmlURLDetailsCategorie);
                Node rootdetailsCategorie = fxmlLoaderDetailsCategorie.load();
                DetailsCategorieController controller = fxmlLoaderDetailsCategorie.getController();
                controller.setCategorie(categorie);
                Scene sceneDetailsCategorie = new Scene((AnchorPane) rootdetailsCategorie, 430, 220);
                detailsStage.setScene(sceneDetailsCategorie);
                detailsStage.setTitle("Details categorie");
                detailsStage.initModality(Modality.APPLICATION_MODAL);
                detailsStage.setResizable(false);
                detailsStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                detailsStage.showAndWait();
            } else if (this.affichageTableau.getChildren().contains(tableClient)) {
                Client client = this.tableClient.getSelectionModel().getSelectedItem();
                URL fxmlURLdetailsClient = getClass().getResource("../javafx/DetailsClient.fxml");
                FXMLLoader fxmlLoaderdetailsClient = new FXMLLoader(fxmlURLdetailsClient);
                Node rootdetailsClient = fxmlLoaderdetailsClient.load();
                DetailsClientController controller = fxmlLoaderdetailsClient.getController();
                controller.setClient(client);
                Scene sceneDetailsClient = new Scene((AnchorPane) rootdetailsClient, 285, 410);
                detailsStage.setScene(sceneDetailsClient);
                detailsStage.setTitle("Details Client");
                detailsStage.initModality(Modality.APPLICATION_MODAL);
                detailsStage.setResizable(false);
                detailsStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                detailsStage.showAndWait();
           
        }  else if (this.affichageTableau.getChildren().contains(tableCommande)) {


        }  else if (this.affichageTableau.getChildren().contains(tableProduit)){
            Produit produit = this.tableProduit.getSelectionModel().getSelectedItem();
            URL fxmlURLdetailsProduit = getClass().getResource("../javafx/DetailsProduit.fxml");
            FXMLLoader fxmlLoaderdetailsProduit = new FXMLLoader(fxmlURLdetailsProduit);
            Node rootdetailsProduit = fxmlLoaderdetailsProduit.load();
            DetailsProduitController controller = fxmlLoaderdetailsProduit.getController();
            controller.setDaos(DAOFactory.getPersistanceActuelle());
            controller.setProduit(produit);
            Scene sceneDetailsProduit = new Scene((AnchorPane) rootdetailsProduit, 595, 280);
            detailsStage.setScene(sceneDetailsProduit);
            detailsStage.setTitle("Details Produit");
            detailsStage.initModality(Modality.APPLICATION_MODAL);
            detailsStage.setResizable(false);
            detailsStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
            detailsStage.showAndWait();
        }

    }catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

    public void modifier() {
        Stage modifierStage = new Stage();
        try {
            if (this.affichageTableau.getChildren().contains(tableCategorie)) {
                Categorie categorie = this.tableCategorie.getSelectionModel().getSelectedItem();

                // Creation et affichage de la fenetre
                URL fxmlURLModifierCategorie = getClass().getResource("../javafx/ModifierCategorie.fxml");
                FXMLLoader fxmlLoaderModiferCategorie = new FXMLLoader(fxmlURLModifierCategorie);
                Node rootConnexion = fxmlLoaderModiferCategorie.load();
                ModifierCategorieController controller = fxmlLoaderModiferCategorie.getController();
                controller.setCategorie(categorie);
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 420, 145);
                modifierStage.setScene(sceneConnexion);
                modifierStage.setTitle("Modifier categorie");
                modifierStage.initModality(Modality.APPLICATION_MODAL);
                modifierStage.setResizable(false);
                modifierStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                modifierStage.showAndWait();
                this.affichageTableau.getChildren().clear();
                afficherCategorie();
            } else if (this.affichageTableau.getChildren().contains(tableClient)) {
                Client client = this.tableClient.getSelectionModel().getSelectedItem();

                // Creation et affichage de la fenetre
                URL fxmlURLModifierClient = getClass().getResource("../javafx/ModifierClient.fxml");
                FXMLLoader fxmlLoaderModifierClient = new FXMLLoader(fxmlURLModifierClient);
                Node rootConnexion = fxmlLoaderModifierClient.load();
                ModifierClientController controller = fxmlLoaderModifierClient.getController();
                controller.setClient(client);
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 800, 500);
                modifierStage.setScene(sceneConnexion);
                modifierStage.setTitle("Modifier client");
                modifierStage.initModality(Modality.APPLICATION_MODAL);
                modifierStage.setResizable(false);
                modifierStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                modifierStage.showAndWait();
                this.affichageTableau.getChildren().clear();
                afficherClients();
              }  else if (this.affichageTableau.getChildren().contains(tableCommande)) {
                  try{

                  } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            
              }  else if (this.affichageTableau.getChildren().contains(tableProduit)) {
                Produit produit = this.tableProduit.getSelectionModel().getSelectedItem();

                // Creation et affichage de la fenetre
                URL fxmlURLModifierProduit = getClass().getResource("../javafx/ModifierProduit.fxml");
                FXMLLoader fxmlLoaderModifierProduit = new FXMLLoader(fxmlURLModifierProduit);
                Node rootConnexion = fxmlLoaderModifierProduit.load();
                ModifierProduitController controller = fxmlLoaderModifierProduit.getController();
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                controller.setProduit(produit);
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 680, 550);
                modifierStage.setScene(sceneConnexion);
                modifierStage.setTitle("Modifier produit");
                modifierStage.initModality(Modality.APPLICATION_MODAL);
                modifierStage.setResizable(false);
                modifierStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                modifierStage.showAndWait();
                this.affichageTableau.getChildren().clear();
                afficherProduits();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void supprimer() {
        try {
            if (this.affichageTableau.getChildren().contains(tableCategorie)) {
                // Récuperation des données de l'item séléctionné puis suppression
                Categorie categorie = this.tableCategorie.getSelectionModel().getSelectedItem();
                daos.getCategorieDAO().delete(categorie);
                this.affichageTableau.getChildren().clear();
                afficherCategorie();
            } else if (this.affichageTableau.getChildren().contains(tableClient)) {
                // Récuperation des données de l'item séléctionné puis suppression
                Client client = this.tableClient.getSelectionModel().getSelectedItem();
                daos.getClientDAO().delete(client);
                this.affichageTableau.getChildren().clear();
                afficherClients();
            } else if (this.affichageTableau.getChildren().contains(tableCommande)) {
                // Récuperation des données de l'item séléctionné puis suppression
                Commande commande = this.tableCommande.getSelectionModel().getSelectedItem();
                daos.getCommandeDAO().delete(commande);
                this.affichageTableau.getChildren().clear();
                afficherCommandes();
            } else if (this.affichageTableau.getChildren().contains(tableProduit)) {
                // Récuperation des données de l'item séléctionné puis suppression
                Produit produit = this.tableProduit.getSelectionModel().getSelectedItem();
                daos.getProduitDAO().delete(produit);
                this.affichageTableau.getChildren().clear();
                afficherProduits();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}