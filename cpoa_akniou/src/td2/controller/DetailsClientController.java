package td2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import td2.pojo.Client;

public class DetailsClientController {
    @FXML Button boutonTermine;
    @FXML Label labelId, labelNom, labelPrenom, labelIdentifiant, labelMotDePasse, labelNumero, labelRue, labelCP, labelVille, labelPays;

    @FXML
    public void setClient(Client obj) throws Exception{
        this.labelId.setText(String.valueOf(obj.getId()));
        this.labelNom.setText(obj.getNom());
        this.labelPrenom.setText(obj.getPrenom());
        this.labelIdentifiant.setText(obj.getIdentifiant());
        this.labelMotDePasse.setText(obj.getMotDePasse());
        this.labelNumero.setText(obj.getAdrNumero());
        this.labelCP.setText(obj.getAdrCodePostal());
        this.labelRue.setText(obj.getAdrVoie());
        this.labelVille.setText(obj.getAdrVille());
        this.labelPays.setText(obj.getAdrPays());
    }

    @FXML
    public void close(){
        Stage fenetre = (Stage) boutonTermine.getScene().getWindow();
        fenetre.close();
    }
}
