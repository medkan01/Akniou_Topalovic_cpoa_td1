package td2.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Table;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Categorie;

public class ModifierCategorieController {
    
    @FXML private Button boutonValider, boutonAnnuler;
    @FXML private TextField saisieTitre, saisieVisuel;
    @FXML private TableView<Categorie> tableCategorie;
    String dao = DAOFactory.getPersistanceActuelle();
    DAOFactory daos = DAOFactory.getDAOFactory(Persistance.dao);
    
    public void modifierCategorie() {
        try {
            
        } catch(Exception e) {

        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        try {

            // Table de tous les produits
            TableColumn<Categorie, String> colTitre = new TableColumn<Categorie, String>("Titre");
            TableColumn<Categorie, String> colVisuel = new TableColumn<Categorie, String>("Visuel");

            colTitre.setCellValueFactory(new PropertyValueFactory<Categorie, String>("titre"));
            colVisuel.setCellValueFactory(new PropertyValueFactory<Categorie, String>("visuel"));

            tableCategorie.getColumns().setAll(colTitre, colVisuel);
			tableCatogorie.getItems().addAll(daos.getProduitDAO().getAll());
        } catch(Exception e) {
            
        }
}
