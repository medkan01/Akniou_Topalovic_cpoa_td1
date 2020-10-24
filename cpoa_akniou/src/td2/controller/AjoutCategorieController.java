package td2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Categorie;

public class AjoutCategorieController {
    @FXML private Button boutonCreer;
    @FXML private Label labelTitre, labelResume;
    @FXML private VBox vBoxFenetreCategorie;
    @FXML private AnchorPane panelFenetreCategorie;
    @FXML private TextField saisieTitre;
    @FXML private GridPane gridPaneResume, gridPaneSaisie;
    static DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MySQL);
    
    @FXML
    public boolean creerCategorie(){
        try{
            String titre = saisieTitre.getText();
            this.labelResume.setText("");;
            Categorie categorie = new Categorie(1, titre, titre+".png");
            if (daos.getCategorieDAO().insert(categorie) == true){
                labelResume.setTextFill(Color.web("#000000"));
                labelResume.setText("La categorie "+ categorie.toString() + " a ete ajoutee avec succes");
            }
            else{
               throw new IllegalArgumentException("Impossible d'ajouter la categorie");
            }
        } catch (Exception e){
            labelResume.setTextFill(Color.web("#FF0000"));
            labelResume.setText(e.getMessage());
        }

        return true;
    }
}
