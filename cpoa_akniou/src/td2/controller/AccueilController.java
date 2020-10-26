package td2.controller;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;

public class AccueilController extends Stage{
    
    private DAOFactory daos;
    @FXML private Button boutonConnexion;
    @FXML private Button boutonLocal;
    @FXML private Button boutonRetour;
    @FXML private Label labelConnecter;
    

    public DAOFactory getDaos() {
        return this.daos;
    }

    public void setDaos(DAOFactory daos) {
        this.daos = daos;
    }

    @FXML
    public void connexion() {
        Stage connexionStage = new Stage();
        try{
            URL fxmlURLConnexion=getClass().getResource("../javafx/Connexion.fxml");
            FXMLLoader fxmlLoaderConnexion = new FXMLLoader(fxmlURLConnexion);
            Node rootConnexion = fxmlLoaderConnexion.load();
            ConnexionController controller = fxmlLoaderConnexion.getController();
            Scene sceneConnexion = new Scene((AnchorPane) rootConnexion, 505, 315);
            connexionStage.setScene(sceneConnexion);
            connexionStage.setTitle("Connexion");
            connexionStage.initModality(Modality.APPLICATION_MODAL);
            connexionStage.setResizable(false);
            connexionStage.showAndWait();

            if(controller.seConnecter()){
                this.daos = DAOFactory.getDAOFactory(Persistance.MySQL);
                this.labelConnecter.setText("Vous êtes connecté");
                this.boutonConnexion.setVisible(false);
                this.boutonRetour.setVisible(true);
           }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        }
        
    @FXML
    public void localHost(){
        this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        Stage fenetre = (Stage) boutonRetour.getScene().getWindow();
        fenetre.close();
    }

    @FXML
    public void RetourMenuPrincipal() {
        Stage fenetre = (Stage) boutonRetour.getScene().getWindow();
        fenetre.close();
    }
}
