package td2.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Categorie;
import td2.pojo.Produit;

public class AjoutProduitController implements Initializable {

    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    @FXML private ChoiceBox<Categorie> cbxCategorie;
    @FXML private Button boutonCreer;
    @FXML private Label labelResumeProduit;
    @FXML private TextField saisieNom,saisieTarif;
    @FXML private TextArea saisieDescription;

    @FXML
    public boolean creerProduit(){
        try{
            String nom = saisieNom.getText();
            String description = saisieDescription.getText();
            Double tarif = 0.0;

            if (saisieTarif.getText().isEmpty()) {
                throw new IllegalArgumentException("La case tarif est vide");
            } else {
                tarif = Double.parseDouble(saisieTarif.getText().trim());
            }

            Categorie categorie = this.cbxCategorie.getValue();
            tarif = Double.parseDouble(this.saisieTarif.getText().trim());
            if(categorie == null){
                throw new IllegalArgumentException("Aucune catégorie selectionnée");
            }
            Produit p = new Produit(0, nom, description, tarif, "", categorie.getId());
            if (daos.getProduitDAO().insert(p) == true){
                labelResumeProduit.setTextFill(Color.web("#000000"));
                labelResumeProduit.setText(p.toString());
            }
            else{
               throw new IllegalArgumentException("Impossible d'ajouter le produit");
            }
        } catch (Exception e){
            labelResumeProduit.setTextFill(Color.web("#FF0000"));
            labelResumeProduit.setText(e.getMessage());
        }

        return true;
    }

    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.cbxCategorie.setItems(FXCollections.observableArrayList(daos.getCategorieDAO().getAll()));
        } catch (Exception e) {
            this.labelResumeProduit.setText("erreur Categorie");
        }
    }
}
