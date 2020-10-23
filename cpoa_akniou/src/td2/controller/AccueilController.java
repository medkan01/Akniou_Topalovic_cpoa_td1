package td2.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;

public class AccueilController {
    @FXML private Button boutonConnexion;
    @FXML private Button boutonLocal;
    @FXML private RadioMenuItem online;
    @FXML private RadioMenuItem offline;
    @FXML private RadioMenuItem nightMode;
    static DAOFactory daos;

    @FXML
    public void connexion() {
            try {
                java.net.URL fxmlURL=getClass().getResource("../javafx/Connexion.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
                Node root = fxmlLoader.load();
                Scene scene = new Scene((AnchorPane) root, 505, 290);

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Person");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.setScene(scene);

            } catch (Exception e) {
                e.printStackTrace();
            }
        this.online.setSelected(true);
        daos = DAOFactory.getDAOFactory(Persistance.MySQL);
        }
        
  /*    https://code.makery.ch/fr/library/javafx-tutorial/part3/      */
    @FXML
    public void localHost(){
        this.offline.setSelected(true);
        daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
    }

}
