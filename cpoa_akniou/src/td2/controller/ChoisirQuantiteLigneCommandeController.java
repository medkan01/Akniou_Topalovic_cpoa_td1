package td2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChoisirQuantiteLigneCommandeController {
    @FXML Button boutonValider;
    @FXML TextField saisieQuantite;
    @FXML Label labelErreur;
    
    @FXML
    public int ajouter(){
        return Integer.parseInt(saisieQuantite.getText().trim()); 
    }  
}
