package td2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnexionController {
    @FXML TextField saisieUtilisateur;
    @FXML PasswordField saisieMotDePasse;
    @FXML Label labelErreur;
    @FXML Button bouttonConnexion;
    @FXML Button bouttonAnnuler;

    @FXML
    public boolean seConnecter(){
    if((this.saisieUtilisateur.getText().equals("admin"))&&(this.saisieMotDePasse.getText().equals("motdepasse")))
    {
        Stage fenetre = (Stage) bouttonAnnuler.getScene().getWindow();
        fenetre.close();
        return true;
    }
    else{
        this.labelErreur.setText("Utilisateur ou mot de passe incorrect veuillez réessayer");
    }
    return false;
    }

    @FXML
    public void Retour(){
        Stage fenetre = (Stage) bouttonAnnuler.getScene().getWindow();
        fenetre.close();
    }
}
