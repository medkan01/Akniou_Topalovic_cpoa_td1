package td2.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Client;

public class ModifierClientController implements Initializable {
    
    @FXML private Button boutonSuivant, boutonPrecedent, boutonAnnuler;
    @FXML private TextField saisieNom, saisiePrenom, saisieEmail, saisieMotDePasse, saisieNumero, saisieVoie, saisieCodePostal, saisieVille, saisiePays;
    @FXML private AnchorPane panelNom, panelEmail, panelAdresse, panelActuel;
    @FXML private Label labelResume;
    int id;
    private DAOFactory daos;

    @FXML
    public void creerClient() {
        try {
            String nom = this.saisieNom.getText();
            String prenom = this.saisiePrenom.getText();
            String numero = this.saisieNumero.getText();
            String voie = this.saisieVoie.getText();
            String codePostal = this.saisieCodePostal.getText();
            String ville = this.saisieVille.getText();
            String pays = this.saisiePays.getText();
            String email = this.saisieEmail.getText();
            String motDePasse = this.saisieMotDePasse.getText();

            Client client = new Client(id,nom,prenom,email,motDePasse,numero,voie,codePostal,ville,pays);

            if(this.daos.getClientDAO().update(client) == true) {
                this.labelResume.setTextFill(Color.web("#52D044"));
                this.labelResume.setText("Le client a ete ajoute avec succes");
            }
            else{
               throw new IllegalArgumentException("Impossible d'ajouter ce client");
            }

        } catch(Exception e) {
            labelResume.setTextFill(Color.web("#FF0000"));
            labelResume.setText(e.getMessage());
        }
    }

    @FXML
    public void panelSuivant() {
        if(panelActuel == panelNom) {
            panelNom.setVisible(false);
            panelAdresse.setVisible(true);
            boutonPrecedent.setDisable(false);
            panelActuel = panelAdresse;
        } else if(panelActuel == panelAdresse) {
            panelAdresse.setVisible(false);
            panelEmail.setVisible(true);
            boutonSuivant.setText("Valider");
            panelActuel = panelEmail;
        } else if(panelActuel == panelEmail) {
            creerClient();
            Stage fenetre = (Stage) boutonSuivant.getScene().getWindow();
            fenetre.close();
        }
    }

    @FXML
    public void panelPrecedent() {
        if(panelActuel == panelAdresse) {
            panelAdresse.setVisible(false);
            boutonPrecedent.setDisable(true);
            panelNom.setVisible(true);
            panelActuel = panelNom;
        } else if(panelActuel == panelEmail) {
            panelEmail.setVisible(false);
            boutonSuivant.setText("Suivant");
            panelAdresse.setVisible(true);
            panelActuel = panelAdresse;
        }
    }

    @FXML
    public void annuler(){
        Stage fenetre = (Stage) boutonAnnuler.getScene().getWindow();
        fenetre.close();
    }

    @FXML
    public void setClient(Client obj) throws Exception{
        this.id = obj.getId();
        this.saisieNom.setText(obj.getNom());
        this.saisiePrenom.setText(obj.getPrenom());
        this.saisieNumero.setText(obj.getAdrNumero());
        this.saisieVoie.setText(obj.getAdrVoie());
        this.saisieCodePostal.setText(obj.getAdrCodePostal());
        this.saisieVille.setText(obj.getAdrVille());
        this.saisiePays.setText(obj.getAdrPays());
        this.saisieEmail.setText(obj.getIdentifiant());
        this.saisieMotDePasse.setText(obj.getMotDePasse());
    }

    @FXML 
    public void setDaos(String persistance){

        if(persistance.equals("MySQL")){
            this.daos = DAOFactory.getDAOFactory(Persistance.MySQL);
        }
        else if(persistance.equals("ListeMemoire")){
            this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        }
    }

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        panelNom.setVisible(true);
        panelActuel = panelNom;
        boutonPrecedent.setDisable(true);
	}
}
