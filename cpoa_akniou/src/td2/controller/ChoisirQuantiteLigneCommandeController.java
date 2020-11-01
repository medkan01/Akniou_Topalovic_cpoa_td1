package td2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChoisirQuantiteLigneCommandeController {
    @FXML Button boutonValider;
    @FXML TextField saisieQuantite;
    @FXML Label labelErreur;
    private int quantite;
    
    @FXML
    public void setQuantite(){
        quantite = Integer.parseInt(this.saisieQuantite.getText().trim());
    }
    
    public void valider(){
        try{
            if(!isNumeric(this.saisieQuantite.getText().trim())){
                throw new IllegalArgumentException("Quantité incorrect");
            } else {
                setQuantite();
                Stage fenetre = (Stage) boutonValider.getScene().getWindow();
                fenetre.close();
            }
        } catch(Exception e) {
            this.labelErreur.setText(e.getMessage());
        }
    }
    
    public int getQuantite(){
        return quantite;
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
