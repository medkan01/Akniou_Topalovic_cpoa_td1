package td2.controller;

import java.net.URL;
import java.sql.SQLException;
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

    @FXML private ChoiceBox<Categorie> cbxCategorie;
    @FXML private Button boutonCreer;
    @FXML private Label labelResumeProduit;
    @FXML private TextField textNom;
    @FXML private TextArea textDescription;
    @FXML private TextField textTarif;

    @FXML
    public void creerProduit() {
        String erreur = "";
        String nom = textNom.getText().trim();
        String description = textDescription.getText().trim();
        Double tarif = 0.0;
        Categorie categorie = this.cbxCategorie.getValue();

       
        if(nom.isEmpty()){
            erreur = erreur + "le nom est vide, ";
        }

        if(description.isEmpty()){
            erreur = erreur + "la description est vide, ";
        }

        if(this.textTarif.getText().trim().isEmpty()){
            erreur = erreur + "le tarif est vide, ";
        }
        else{
            try{
                tarif = Double.parseDouble(this.textTarif.getText().trim());
                if(tarif == 0){
                    erreur = erreur + "le tarif ne peut pas être égal à 0, ";
                }
            }catch (NumberFormatException e){
                erreur = erreur + "tarif incorrect, ";
            }
        }

        if(categorie == null){
            erreur = erreur + "veuillez selectionner une categorie.";
        }
        
        if(erreur.isEmpty()){
            Produit p = new Produit(0, nom, description, tarif, "", categorie.getId());
            DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
            try{
                if (daos.getProduitDAO().insert(p) == true){
                    labelResumeProduit.setTextFill(Color.web("#000000"));
                    labelResumeProduit.setText(p.toString());
                }
                else{
                   erreur = "Impossible d'ajouter ce produit : \n";
                }
            } catch (SQLException sqle) {
               erreur = erreur + "Message d'erreur SQL:\n"+sqle.getMessage();
            }
        }
        else{
            labelResumeProduit.setText(erreur);
            labelResumeProduit.setTextFill(Color.web("#ff0000"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DAOFactory dao = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        try {
            this.cbxCategorie.setItems(FXCollections.observableArrayList(dao.getCategorieDAO().getAll()));
        } catch (Exception e) {
            this.labelResumeProduit.setText("erreur Categorie");
        }
    }
}
