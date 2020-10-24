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
import td2.pojo.Produit;

public class MenuPrincipalController implements Initializable{
    
    static DAOFactory daos;
    @FXML private Button boutonAFficher;
    @FXML private TabPane tabPane;
    @FXML private TableView<Produit> tableCategorie;
    @FXML private TableView<Produit> tableClient;
    @FXML private TableView<Produit> tableCommande;
    @FXML private TableView<Produit> tableProduit;
    @FXML private RadioMenuItem online;
    @FXML private RadioMenuItem offline;
    @FXML private RadioMenuItem nightMode;

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
        }
        
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
