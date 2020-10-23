package td2.controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Produit;

public class MenuPrincipalController {
    @FXML private Button boutonAFficher;
    @FXML private TabPane tabPane;
    @FXML private TableView<Produit> tableCategorie;
    @FXML private TableView<Produit> tableClient;
    @FXML private TableView<Produit> tableCommande;
    @FXML private TableView<Produit> tableProduit;

    @FXML
    public void afficherProduits() throws SQLException {
        
        Tab tabProduit = new Tab("Produits", tableProduit);

        this.tabPane.getTabs().add(tabProduit);

        TableColumn<Produit,String> colLibelle = new TableColumn<>("Nom");
        colLibelle.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        TableColumn<Produit, String> colTarif = new TableColumn<>("Tarif");
        colTarif.setCellValueFactory(new PropertyValueFactory<Produit, String>("tarif"));

        this.tableProduit.getColumns().setAll(colLibelle,colTarif);

        this.tableProduit.getItems().addAll(DAOFactory.getDAOFactory(Persistance.MySQL).getProduitDAO().getAll());
    }
}
