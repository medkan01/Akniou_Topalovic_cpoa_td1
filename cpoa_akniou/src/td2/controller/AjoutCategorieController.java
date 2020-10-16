package td2.controller;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import td2.dao.daofactory.*;
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
    public boolean creerProduit(){
        try{
            String titre = saisieTitre.getText();

            Categorie categorie = new Categorie(1, titre, "");
            if (daos.getCategorieDAO().insert(categorie) == true){
                labelResume.setTextFill(Color.web("#000000"));
                labelResume.setText(categorie.toString());
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
