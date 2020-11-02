package td2.controller.detailsController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import td2.pojo.Categorie;

public class DetailsCategorieController {
    @FXML private Label labelId, labelTitre;
    @FXML private ImageView imageCategorie;
    @FXML private Button boutonTermine;

    @FXML
    public void setCategorie(Categorie obj) throws Exception{
        this.labelId.setText(String.valueOf(obj.getId()));
        this.labelTitre.setText(obj.getTitre());
        try{
        Image image = new Image(getClass().getResource("../javafx/images/"+obj.getVisuel()).toExternalForm());
        this.imageCategorie.setImage(image);
        } catch(Exception e){
            Image image = new Image(getClass().getResource("../javafx/images/null.png").toExternalForm());
            this.imageCategorie.setImage(image);
        }
        
    }

    @FXML
    public void close(){
        Stage fenetre = (Stage) boutonTermine.getScene().getWindow();
        fenetre.close();
    }
}
