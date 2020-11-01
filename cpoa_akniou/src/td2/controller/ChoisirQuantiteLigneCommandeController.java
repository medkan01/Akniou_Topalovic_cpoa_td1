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
        if(!isNumeric(this.saisieQuantite.getText().trim()))
            throw new IllegalArgumentException("Le tarif saisie est incorrect");
        else
             return Integer.parseInt(this.saisieQuantite.getText().trim());
    }  

    
    private static boolean isNumeric(String str){
        try {
            Integer.parseInt(str.trim());
            return true;

        } catch(NumberFormatException e) {
            return false;
        }
    }
}
