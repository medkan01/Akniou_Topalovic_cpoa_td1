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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
    @FXML private TabPane tabPane;
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

    public void initialize(URL location, ResourceBundle resources) {
        Stage connexionStage = new Stage();
        if((!this.online.isSelected())&&(!this.offline.isSelected())){
            try{
                URL fxmlURLConnexion=getClass().getResource("../javafx/Accueil.fxml");
                FXMLLoader fxmlLoaderConnexion = new FXMLLoader(fxmlURLConnexion);
                Node rootConnexion = fxmlLoaderConnexion.load();
                AccueilController controller = fxmlLoaderConnexion.getController();
                Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 980, 650);
                connexionStage.setScene(sceneConnexion);
                connexionStage.setTitle("Connexion");
                connexionStage.initModality(Modality.APPLICATION_MODAL);
                connexionStage.setResizable(false);
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
            connexionStage.showAndWait();

            if(controller.seConnecter()){
                this.daos = DAOFactory.getDAOFactory(Persistance.MySQL);
                this.tabPane.getTabs().clear();
           }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML 
    public void setInstanceOffline(){
        this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        this.tabPane.getTabs().clear();
    }

    @FXML
    public void afficherCategories() throws SQLException{
        
        Tab tabCategorie = new Tab("Catégories", tableCategorie = new TableView<Categorie>());
        this.tabPane.getTabs().add(tabCategorie);

        TableColumn<Categorie, String> colTitre = new TableColumn<>("Titre");
        colTitre.setCellValueFactory(new PropertyValueFactory<Categorie, String>("titre"));

        TableColumn<Categorie, String> colVisuel = new TableColumn<>("Visuel");
        colVisuel.setCellValueFactory(new PropertyValueFactory<Categorie, String>("visuel"));

        this.tableCategorie.getColumns().setAll(colTitre,colVisuel);

        this.tableCategorie.getItems().addAll(daos.getCategorieDAO().getAll());

    }

    @FXML
    public void afficherClients() throws SQLException {

        Tab tabClient = new Tab("Clients", tableClient = new TableView<Client>());
        this.tabPane.getTabs().add(tabClient);
        
        TableColumn<Client, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));

        TableColumn<Client, String> colPrenom = new TableColumn<>("Prenom");
        colPrenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));

        TableColumn<Client, String> colAdrNumero = new TableColumn<>("Numéro");
        colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("adrNumero"));
        
        TableColumn<Client, String> colAdrVoie = new TableColumn<>("Voie");
        colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("adrVoie"));

        TableColumn<Client, String> colAdrCodePostal = new TableColumn<>("CP");
        colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("adrCodePostal"));

        TableColumn<Client, String> colAdrVille = new TableColumn<>("Ville");
        colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("adrVille"));

        TableColumn<Client, String> colAdrPays = new TableColumn<>("Pays");
        colNom.setCellValueFactory(new PropertyValueFactory<Client, String>("adrPays"));

        this.tableClient.getColumns().setAll(colNom, colPrenom, colAdrNumero, colAdrVoie, colAdrCodePostal, colAdrVille, colAdrPays);

        this.tableClient.getItems().addAll(daos.getClientDAO().getAll());
    }

    @FXML
    public void afficherCommandes() throws SQLException {
        
        Tab tabCommande = new Tab("Commandes", tableCommande = new TableView<Commande>());
        this.tabPane.getTabs().add(tabCommande);
        
        TableColumn<Produit, String> colTarif = new TableColumn<>("Tarif");
        colTarif.setCellValueFactory(new PropertyValueFactory<Produit, String>("tarif"));

        TableColumn<Produit,String> colLibelle = new TableColumn<>("Nom");
        colLibelle.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));

        TableColumn<Produit, String> colDescription = new TableColumn<>("Description");
        colDescription.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));

        this.tableProduit.getColumns().setAll(colTarif,colLibelle,colDescription);

        this.tableProduit.getItems().addAll(daos.getProduitDAO().getAll());
    }

        
    @FXML
    public void afficherProduits() throws SQLException {
        
        Tab tabProduit = new Tab("Produits", tableProduit = new TableView<Produit>());
        this.tabPane.getTabs().add(tabProduit);
        
        TableColumn<Produit, String> colTarif = new TableColumn<>("Tarif");
        colTarif.setCellValueFactory(new PropertyValueFactory<Produit, String>("tarif"));

        TableColumn<Produit,String> colLibelle = new TableColumn<>("Nom");
        colLibelle.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));

        TableColumn<Produit, String> colDescription = new TableColumn<>("Description");
        colDescription.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));

        this.tableProduit.getColumns().setAll(colTarif,colLibelle,colDescription);

        this.tableProduit.getItems().addAll(daos.getProduitDAO().getAll());
    }

}
