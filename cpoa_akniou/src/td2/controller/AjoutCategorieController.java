package td2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private DAOFactory daos;
    
    @FXML
    public boolean creerCategorie(){
        try{
            String titre = saisieTitre.getText();
            this.labelResume.setText("");;
            Categorie categorie = new Categorie(1, titre, titre+".png");
            if (daos.getCategorieDAO().insert(categorie) == true){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout catégorie");
                alert.setHeaderText("Catégorie "+this.saisieTitre.getText()+" ajoutée avec succès !");
                alert.showAndWait();
                this.saisieTitre.clear();
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

    @FXML 
    public void setDaos(String persistance){
        if(persistance.equals("En Ligne")){
            this.daos = DAOFactory.getDAOFactory(Persistance.MySQL);
        }
        else if(persistance.equals("Local")){
            this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        }
    }
}
