package td2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AjoutProduitController {

    @FXML
    private Label labelResumeProduit;
    
    public void CreerProduit(){
        this.labelResumeProduit.setText("ok");
    }

}
