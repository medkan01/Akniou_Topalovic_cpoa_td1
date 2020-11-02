package td2.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class MenuPrincipalController implements Initializable {

    private String css="themeClaire";
    private DAOFactory daos;
    @FXML private Button boutonCategories, boutonClients, boutonCommandes,boutonProduits, boutonDetails, boutonAjouter, boutonModifier, boutonSupprimer;
    @FXML private RadioMenuItem online, offline, nightMode, lightMode;
    @FXML private TableView<Categorie> tableCategorie;
    @FXML private TableView<Client> tableClient;
    @FXML private TableView<Commande> tableCommande;
    @FXML private TableView<Produit> tableProduit;
    @FXML private ChoiceBox<String> cbxChoixRecherche;
    @FXML private AnchorPane affichageTableau;
    @FXML private Pane panelBoutonInteraction;
    @FXML private Label labelInstance;
    @FXML private TextField saisieRecherche;

    public void initialize(URL location, ResourceBundle resources) {
        Stage connexionStage = new Stage();
        if ((!this.online.isSelected()) && (!this.offline.isSelected())) {
            try {
                URL fxmlURLConnexion = getClass().getResource("../javafx/Accueil.fxml");
                FXMLLoader fxmlLoaderConnexion = new FXMLLoader(fxmlURLConnexion);
                Node rootConnexion = fxmlLoaderConnexion.load();
                AccueilController controller = fxmlLoaderConnexion.getController();
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 980, 650);
                sceneConnexion.getStylesheets().add(getClass().getResource("../javafx/css/"+this.css+".css").toExternalForm());
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
    public void modeNuit(){
        this.css = "themeSombre";
    }

    @FXML
    public void modeJour(){
        this.css = "themeClaire";
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
            sceneConnexion.getStylesheets().add(getClass().getResource("../javafx/css/"+this.css+".css").toExternalForm());
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
                this.cbxChoixRecherche.setDisable(true);
                this.saisieRecherche.setVisible(false);
                this.labelInstance.setText(DAOFactory.getPersistanceActuelle());
            }   else{
                this.offline.setSelected(true);
                this.online.setSelected(false);
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
        this.boutonAjouter.setDisable(true);
        this.boutonDetails.setDisable(true);
        this.boutonModifier.setDisable(true);
        this.boutonSupprimer.setDisable(true);
        this.cbxChoixRecherche.setDisable(true);
        this.saisieRecherche.setVisible(false);
    }

    @FXML
    public void afficherCategorie() throws SQLException {
        this.affichageTableau.getChildren().clear();
        this.boutonAjouter.setDisable(false);
        this.cbxChoixRecherche.setDisable(false);
        this.boutonDetails.setDisable(true);
        this.boutonModifier.setDisable(true);
        this.boutonSupprimer.setDisable(true);
        this.saisieRecherche.setVisible(false);
        this.saisieRecherche.setText("");
        tableCategorie = new TableView<Categorie>();
        this.tableCategorie.setMinSize(870, 620);
        TableColumn<Categorie, String> colTitre = new TableColumn<>("Titre");
        colTitre.setCellValueFactory(new PropertyValueFactory<Categorie, String>("titre"));
        TableColumn<Categorie, String> colVisuel = new TableColumn<>("Visuel");
        colVisuel.setCellValueFactory(new PropertyValueFactory<Categorie, String>("visuel"));
        ArrayList<TableColumn<Categorie,?>> colTableCategorie = new ArrayList<TableColumn<Categorie,?>>();
        colTableCategorie.add(colTitre);
        colTableCategorie.add(colVisuel);
        this.tableCategorie.getItems().addAll(daos.getCategorieDAO().getAll());
        this.tableCategorie.requestLayout();
        this.affichageTableau.getChildren().addAll(tableCategorie);
        this.cbxChoixRecherche.getItems().clear();
        this.cbxChoixRecherche.getItems().addAll("Titre","Visuel");
        this.cbxChoixRecherche.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.saisieRecherche.setVisible(!(newValue == null));});
        this.tableCategorie.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonDetails.setDisable(newValue == null);this.boutonModifier.setDisable(newValue == null);this.boutonSupprimer.setDisable(newValue == null);});

        
        FilteredList<Categorie> filtreCategorie = new FilteredList(this.tableCategorie.getItems(), p -> true);
        tableCategorie.setItems(filtreCategorie);
        tableCategorie.getColumns().addAll(colTitre, colVisuel);

        saisieRecherche.setOnKeyReleased(keyEvent ->
        {
            switch (cbxChoixRecherche.getValue())//Switch on choiceBox value
            {
                case "Titre":
                filtreCategorie.setPredicate(p -> p.getTitre().toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;
                case "Visuel":
                filtreCategorie.setPredicate(p -> p.getVisuel().toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;
            }
        });
    }

    @FXML
    public void afficherClients() throws SQLException {
        this.affichageTableau.getChildren().clear();
        this.boutonAjouter.setDisable(false);
        this.cbxChoixRecherche.setDisable(false);
        this.boutonDetails.setDisable(true);
        this.boutonModifier.setDisable(true);
        this.boutonSupprimer.setDisable(true);
        this.saisieRecherche.setVisible(false);
        this.saisieRecherche.setText("");
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
        colAdrNumero.setPrefWidth(40);
        colAdrNumero.setCellValueFactory(new PropertyValueFactory<Client, String>("adrNumero"));
        TableColumn<Client, String> colAdrVoie = new TableColumn<>("Voie");
        colAdrVoie.setPrefWidth(140);
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
        ArrayList<TableColumn<Client,?>> colTableClient = new ArrayList<TableColumn<Client,?>>();
        colTableClient.add(colNom);
        colTableClient.add(colPrenom);
        colTableClient.add(colIdentifiant);
        colTableClient.add(colAdrNumero);
        colTableClient.add(colAdrVoie);
        colTableClient.add(colAdrCodePostal);
        colTableClient.add(colAdrVille);
        colTableClient.add(colAdrPays);
        this.tableClient.getItems().addAll(daos.getClientDAO().getAll());
        this.tableClient.requestLayout();
        this.affichageTableau.getChildren().addAll(tableClient);
        this.cbxChoixRecherche.getItems().clear();
        this.cbxChoixRecherche.getItems().addAll("Nom","Prenom","E-Mail","Numéro d'adresse", "Rue","Code Postal","Ville","Pays");
        this.cbxChoixRecherche.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.saisieRecherche.setVisible(!(newValue == null));});
        this.tableClient.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonDetails.setDisable(newValue == null);this.boutonModifier.setDisable(newValue == null);this.boutonSupprimer.setDisable(newValue == null);});

        FilteredList<Client> filtreClient = new FilteredList(this.tableClient.getItems(), p -> true);
        tableClient.setItems(filtreClient);
        tableClient.getColumns().addAll(colNom, colPrenom, colIdentifiant, colAdrNumero, colAdrVoie, colAdrCodePostal, colAdrVille, colAdrPays);

        saisieRecherche.setOnKeyReleased(keyEvent ->
        {
            switch (cbxChoixRecherche.getValue())//Switch on choiceBox value
            {
                case "Nom":
                filtreClient.setPredicate(p -> p.getNom().toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;
                case "Prenom":
                filtreClient.setPredicate(p -> p.getPrenom().toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;
                case "E-Mail":
                filtreClient.setPredicate(p -> p.getIdentifiant().toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;
                case "Numéro d'adresse":
                filtreClient.setPredicate(p -> p.getAdrNumero().toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;
                case "Rue":
                filtreClient.setPredicate(p -> p.getAdrVoie().toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;
                case "Code Postal":
                filtreClient.setPredicate(p -> p.getAdrCodePostal().toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;
                case "Ville":
                filtreClient.setPredicate(p -> p.getAdrVille().toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;
                case "Pays":
                filtreClient.setPredicate(p -> p.getAdrPays().toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                break;

            }
        });
    }

    @FXML
    public void afficherCommandes() throws SQLException {
        this.affichageTableau.getChildren().clear();
        this.boutonAjouter.setDisable(false);
        this.cbxChoixRecherche.setDisable(false);
        this.boutonDetails.setDisable(true);
        this.boutonModifier.setDisable(true);
        this.boutonSupprimer.setDisable(true);
        this.saisieRecherche.setVisible(false);
        this.saisieRecherche.setText("");
        tableCommande = new TableView<Commande>();
        this.tableCommande.setMinSize(870, 620);
        TableColumn<Commande, Integer> colIdCommande = new TableColumn<>("ID Commande");
        colIdCommande.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("id"));
        colIdCommande.setPrefWidth(200);
        TableColumn<Commande, String> colDate = new TableColumn<>("Date");
        colDate.setCellValueFactory(new PropertyValueFactory<Commande, String>("date"));
        colDate.setPrefWidth(200);
        TableColumn<Commande, Integer> colIdClient = new TableColumn<>("ID Client");
        colIdClient.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("idClient"));
        colIdClient.setPrefWidth(200);
        ArrayList<TableColumn<Commande,?>> colTableCommande = new ArrayList<TableColumn<Commande,?>>();
        colTableCommande.add(colIdCommande);
        colTableCommande.add(colDate);
        colTableCommande.add(colIdClient);
        this.tableCommande.getItems().addAll(daos.getCommandeDAO().getAll());
        this.tableCommande.requestLayout();;
        this.affichageTableau.getChildren().addAll(tableCommande);
        this.cbxChoixRecherche.getItems().clear();
        this.cbxChoixRecherche.getItems().addAll("IdCommande","Date","IdClient");
        this.cbxChoixRecherche.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.saisieRecherche.setVisible(!(newValue == null));});
        this.tableCommande.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonDetails.setDisable(newValue == null);this.boutonModifier.setDisable(newValue == null);this.boutonSupprimer.setDisable(newValue == null);});

        FilteredList<Commande> filtreCommande = new FilteredList(this.tableCommande.getItems(), p -> true);
        tableCommande.setItems(filtreCommande);
        tableCommande.getColumns().addAll(colIdCommande, colDate, colIdClient);

        saisieRecherche.setOnKeyReleased(keyEvent ->
        {
            switch (cbxChoixRecherche.getValue())//Switch on choiceBox value
            {
                case "IdCommande":
                filtreCommande.setPredicate(p -> String.valueOf(p.getId()).toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;
                case "Date":
                filtreCommande.setPredicate(p -> String.valueOf(p.getDate()).toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;
                case "IdClient":
                filtreCommande.setPredicate(p -> String.valueOf(p.getIdClient()).toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;

            }
        });
    }

    @FXML
    public void afficherProduits() throws SQLException {
        this.affichageTableau.getChildren().clear();
        this.boutonAjouter.setDisable(false);
        this.cbxChoixRecherche.setDisable(false);
        this.boutonDetails.setDisable(true);
        this.boutonModifier.setDisable(true);
        this.boutonSupprimer.setDisable(true);
        this.saisieRecherche.setVisible(false);
        this.saisieRecherche.setText("");
        tableProduit = new TableView<Produit>();
        this.tableProduit.setMinSize(870, 620);
        TableColumn<Produit, String> colTarif = new TableColumn<>("Tarif");
        colTarif.setPrefWidth(75);
        colTarif.setCellValueFactory(new PropertyValueFactory<Produit, String>("tarif"));
        TableColumn<Produit, String> colLibelle = new TableColumn<>("Nom");
        colLibelle.setPrefWidth(120);
        colLibelle.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        TableColumn<Produit, String> colDescription = new TableColumn<>("Description");
        colDescription.setPrefWidth(675);
        colDescription.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        ArrayList<TableColumn<Produit,?>> colTableProduit = new ArrayList<TableColumn<Produit,?>>();
        colTableProduit.add(colTarif);
        colTableProduit.add(colLibelle);
        colTableProduit.add(colDescription);
        this.tableProduit.getItems().addAll(daos.getProduitDAO().getAll());
        this.tableProduit.requestLayout();
        this.affichageTableau.getChildren().addAll(tableProduit);
        this.cbxChoixRecherche.getItems().clear();
        this.cbxChoixRecherche.getItems().addAll("Tarif","Nom","Description");
        this.cbxChoixRecherche.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.saisieRecherche.setVisible(!(newValue == null));});
        this.tableProduit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonDetails.setDisable(newValue == null);this.boutonModifier.setDisable(newValue == null);this.boutonSupprimer.setDisable(newValue == null);});

        FilteredList<Produit> filtreProduit = new FilteredList(this.tableProduit.getItems(), p -> true);
        tableProduit.setItems(filtreProduit);
        tableProduit.getColumns().addAll(colTarif, colLibelle, colDescription);

        saisieRecherche.setOnKeyReleased(keyEvent ->
        {
            switch (cbxChoixRecherche.getValue())//Switch on choiceBox value
            {
                case "Tarif":
                filtreProduit.setPredicate(p -> String.valueOf(p.getTarif()).toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;
                case "Nom":
                filtreProduit.setPredicate(p -> p.getNom().toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;
                case "Description":
                filtreProduit.setPredicate(p -> p.getDescription().toLowerCase().contains(saisieRecherche.getText().toLowerCase().trim()));
                    break;

            }
        });
    }

    @FXML
    public void ajouter() {
        Stage ajoutStage = new Stage();
        if (this.affichageTableau.getChildren().contains(tableCategorie)) {
            try {
                URL fxmlURLAjouterCategorie = getClass().getResource("../javafx/AjoutCategorie.fxml");
                FXMLLoader fxmlLoaderAjouterCategorie = new FXMLLoader(fxmlURLAjouterCategorie);
                Node rootAjouterCategorie = fxmlLoaderAjouterCategorie.load();
                AjoutCategorieController controller = fxmlLoaderAjouterCategorie.getController();
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                Scene sceneAjouterCategorie = new Scene((AnchorPane) rootAjouterCategorie, 550, 180);
                sceneAjouterCategorie.getStylesheets().add(getClass().getResource("../javafx/css/"+this.css+".css").toExternalForm());
                ajoutStage.setScene(sceneAjouterCategorie);
                ajoutStage.setTitle("Ajout Categorie");
                ajoutStage.initModality(Modality.APPLICATION_MODAL);
                ajoutStage.setResizable(false);
                ajoutStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
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
                Node rootAjouterClient = fxmlLoaderAjouterClient.load();
                AjoutClientController controller = fxmlLoaderAjouterClient.getController();
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                Scene sceneAjouterClient = new Scene((AnchorPane) rootAjouterClient, 800, 500);
                sceneAjouterClient.getStylesheets().add(getClass().getResource("../javafx/css/"+this.css+".css").toExternalForm());
                ajoutStage.setScene(sceneAjouterClient);
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
                Node rootAjouterCommande = fxmlLoaderAjouterCommande.load();
                AjoutCommandeController controller = fxmlLoaderAjouterCommande.getController();
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                Scene sceneAjouterCommande = new Scene((AnchorPane) rootAjouterCommande, 900, 500);
                sceneAjouterCommande.getStylesheets().add(getClass().getResource("../javafx/css/"+this.css+".css").toExternalForm());
                ajoutStage.setScene(sceneAjouterCommande);
                ajoutStage.setTitle("Ajout Commande");
                ajoutStage.initModality(Modality.APPLICATION_MODAL);
                ajoutStage.setResizable(false);
                ajoutStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                ajoutStage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            this.affichageTableau.getChildren().clear();
            try {
                afficherCommandes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (this.affichageTableau.getChildren().contains(tableProduit)) {
            try {
                URL fxmlURLAjouterProduit = getClass().getResource("../javafx/AjoutProduit.fxml");
                FXMLLoader fxmlLoaderAjouterProduit = new FXMLLoader(fxmlURLAjouterProduit);
                Node rootAjouterProduit = fxmlLoaderAjouterProduit.load();
                AjoutProduitController controller = fxmlLoaderAjouterProduit.getController();
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                Scene sceneAjouterProduit = new Scene((AnchorPane) rootAjouterProduit, 680, 450);
                sceneAjouterProduit.getStylesheets().add(getClass().getResource("../javafx/css/"+this.css+".css").toExternalForm());
                ajoutStage.setScene(sceneAjouterProduit);
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

    @FXML
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
                sceneDetailsCategorie.getStylesheets().add(getClass().getResource("../javafx/css/"+this.css+".css").toExternalForm());
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
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                Scene sceneDetailsClient = new Scene((AnchorPane) rootdetailsClient, 500, 410);
                sceneDetailsClient.getStylesheets().add(getClass().getResource("../javafx/css/"+this.css+".css").toExternalForm());
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
            sceneDetailsProduit.getStylesheets().add(getClass().getResource("../javafx/css/"+this.css+".css").toExternalForm());
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

    @FXML
    public void modifier() {
        Stage modifierStage = new Stage();
        try {
            if (this.affichageTableau.getChildren().contains(tableCategorie)) {
                Categorie categorie = this.tableCategorie.getSelectionModel().getSelectedItem();

                // Creation et affichage de la fenetre
                URL fxmlURLModifierCategorie = getClass().getResource("../javafx/ModifierCategorie.fxml");
                FXMLLoader fxmlLoaderModiferCategorie = new FXMLLoader(fxmlURLModifierCategorie);
                Node rootModifierCategorie = fxmlLoaderModiferCategorie.load();
                ModifierCategorieController controller = fxmlLoaderModiferCategorie.getController();
                controller.setCategorie(categorie);
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                Scene sceneModifierCategorie = new Scene((AnchorPane) rootModifierCategorie, 420, 145);
                sceneModifierCategorie.getStylesheets().add(getClass().getResource("../javafx/css/"+this.css+".css").toExternalForm());
                modifierStage.setScene(sceneModifierCategorie);
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
                Node rootModifierClient = fxmlLoaderModifierClient.load();
                ModifierClientController controller = fxmlLoaderModifierClient.getController();
                controller.setClient(client);
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                Scene sceneModifierClient = new Scene((AnchorPane) rootModifierClient, 800, 500);
                sceneModifierClient.getStylesheets().add(getClass().getResource("../javafx/css/"+this.css+".css").toExternalForm());
                modifierStage.setScene(sceneModifierClient);
                modifierStage.setTitle("Modifier client");
                modifierStage.initModality(Modality.APPLICATION_MODAL);
                modifierStage.setResizable(false);
                modifierStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                modifierStage.showAndWait();
                this.affichageTableau.getChildren().clear();
                afficherClients();
              }  else if (this.affichageTableau.getChildren().contains(tableCommande)) {
                  try{
                    Commande commande = this.tableCommande.getSelectionModel().getSelectedItem();
                    // Creation et affichage de la fenetre
                    URL fxmlURLModifierCommande = getClass().getResource("../javafx/ModifierCommande.fxml");
                    FXMLLoader fxmlLoaderModifierCommande = new FXMLLoader(fxmlURLModifierCommande);
                    Node rootModifierCommande = fxmlLoaderModifierCommande.load();
                    ModifierCommandeController controller = fxmlLoaderModifierCommande.getController();
                    controller.setDaos(DAOFactory.getPersistanceActuelle());
                    controller.setCommande(commande);
                    Scene sceneModifierProduit = new Scene((AnchorPane) rootModifierCommande, 900, 500);
                    sceneModifierProduit.getStylesheets().add(getClass().getResource("../javafx/css/themeClaire.css").toExternalForm());
                    modifierStage.setScene(sceneModifierProduit);
                    modifierStage.setTitle("Modifier produit");
                    modifierStage.initModality(Modality.APPLICATION_MODAL);
                    modifierStage.setResizable(false);
                    modifierStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
                    modifierStage.showAndWait();
                    this.affichageTableau.getChildren().clear();
                    afficherCommandes();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            
              }  else if (this.affichageTableau.getChildren().contains(tableProduit)) {
                Produit produit = this.tableProduit.getSelectionModel().getSelectedItem();

                // Creation et affichage de la fenetre
                URL fxmlURLModifierProduit = getClass().getResource("../javafx/ModifierProduit.fxml");
                FXMLLoader fxmlLoaderModifierProduit = new FXMLLoader(fxmlURLModifierProduit);
                Node rootModifierProduit = fxmlLoaderModifierProduit.load();
                ModifierProduitController controller = fxmlLoaderModifierProduit.getController();
                controller.setDaos(DAOFactory.getPersistanceActuelle());
                controller.setProduit(produit);
                Scene sceneModifierProduit = new Scene((AnchorPane) rootModifierProduit, 680, 550);
                sceneModifierProduit.getStylesheets().add(getClass().getResource("../javafx/css/"+this.css+".css").toExternalForm());
                modifierStage.setScene(sceneModifierProduit);
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

    @FXML
    public void supprimer() {
        try {
            if (this.affichageTableau.getChildren().contains(tableCategorie)) {
                // Récuperation des données de l'item séléctionné puis suppression
                Categorie categorie = this.tableCategorie.getSelectionModel().getSelectedItem();
                if(confirmation()){
                daos.getCategorieDAO().delete(categorie);
                this.affichageTableau.getChildren().clear();
                afficherCategorie();
                }
            } else if (this.affichageTableau.getChildren().contains(tableClient)) {
                // Récuperation des données de l'item séléctionné puis suppression
                Client client = this.tableClient.getSelectionModel().getSelectedItem();
                if(confirmation()){
                daos.getClientDAO().delete(client);
                this.affichageTableau.getChildren().clear();
                afficherClients();
                }
            } else if (this.affichageTableau.getChildren().contains(tableCommande)) {
                // Récuperation des données de l'item séléctionné puis suppression
                Commande commande = this.tableCommande.getSelectionModel().getSelectedItem();
                if(confirmation()){
                daos.getCommandeDAO().delete(commande);
                this.affichageTableau.getChildren().clear();
                afficherCommandes();
                }
            } else if (this.affichageTableau.getChildren().contains(tableProduit)) {
                // Récuperation des données de l'item séléctionné puis suppression
                Produit produit = this.tableProduit.getSelectionModel().getSelectedItem();
                if(confirmation()){
                daos.getProduitDAO().delete(produit);
                this.affichageTableau.getChildren().clear();
                afficherProduits();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

        public boolean confirmation(){
            String msg = "êtes vous sûr de vouloir supprimer cette ligne ?";
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Suppression");
            alert.setTitle("Confirmation");
            alert.setContentText(msg);
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == null) {
                return false;
             } else if (option.get() == ButtonType.OK) {
                return true;
             } else if (option.get() == ButtonType.CANCEL) {
                return false;
             } else{
                 return false;
             }
        }
    }