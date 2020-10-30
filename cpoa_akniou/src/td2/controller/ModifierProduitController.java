package td2.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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

public class ModifierProduitController {
    private DAOFactory daos;
    @FXML private ChoiceBox<Categorie> cbxCategorie;
    @FXML private Button boutonValider;
    @FXML private Label labelResumeProduit;
    @FXML private TextField saisieNom,saisieTarif;
    @FXML private TextArea saisieDescription;
    int id;

    @FXML
    public boolean creerProduit(){
        try{
            String nom = saisieNom.getText();
            String description = saisieDescription.getText();
            Double tarif = 0.0;

            if (saisieTarif.getText().isEmpty()) {
                throw new IllegalArgumentException("La case tarif est vide");
            } else if(!isNumeric(this.saisieTarif.getText().trim())) {
                throw new IllegalArgumentException("Le tarif saisie est incorrect");
            } else {
                tarif = Double.parseDouble(this.saisieTarif.getText().trim());
            }

            Categorie categorie = this.cbxCategorie.getValue();
            tarif = Double.parseDouble(this.saisieTarif.getText().trim());
            if(categorie == null){
                throw new IllegalArgumentException("Aucune catégorie selectionnée");
            }
            Produit p = new Produit(id, nom, description, tarif, "", categorie.getId());
            if (daos.getProduitDAO().update(p) == true){
                this.labelResumeProduit.setTextFill(Color.web("#000000"));
                this.labelResumeProduit.setText(p.toString());
            }
            else{
               throw new IllegalArgumentException("Impossible d'ajouter le produit");
            }
        } catch (Exception e){
            this.labelResumeProduit.setTextFill(Color.web("#FF0000"));
            this.labelResumeProduit.setText(e.getMessage());
        }
        return true;
    }

    @FXML
    public void setProduit(Produit obj) throws Exception{
        this.id = obj.getId();
        this.saisieNom.setText(obj.getNom());
        this.saisieDescription.setText(obj.getDescription());
        this.saisieTarif.setText(String.valueOf(obj.getTarif()));
        this.cbxCategorie.setValue(daos.getCategorieDAO().getById(obj.getIdCategorie()));
    }

    @FXML 
    public void setDaos(String persistance){

        if(persistance.equals("MySQL")){
            this.daos = DAOFactory.getDAOFactory(Persistance.MySQL);
        }
        else if(persistance.equals("ListeMemoire")){
            this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        }

        try {
            this.cbxCategorie.setItems(FXCollections.observableArrayList(daos.getCategorieDAO().getAll()));
        } catch (Exception e) {
            this.labelResumeProduit.setTextFill(Color.web("#FF0000"));
            this.labelResumeProduit.setText("erreur Categorie");
        }
    }

    private static boolean isNumeric(String str){
        try {
            Double.parseDouble(str);
            return true;

        } catch(NumberFormatException e) {
            return false;
        }
    }
}
