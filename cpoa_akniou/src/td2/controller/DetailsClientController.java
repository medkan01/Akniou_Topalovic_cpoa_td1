package td2.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Client;
import td2.pojo.Commande;

public class DetailsClientController {

    private DAOFactory daos;
    Client client;
    @FXML Button boutonTerminer, boutonDetailsCommande;
    @FXML Label labelId, labelNom, labelPrenom, labelIdentifiant, labelMotDePasse, labelNumero, labelRue, labelCP, labelVille, labelPays;
    @FXML TableView<Commande> tableCommande;

    @FXML
    public void setClient(Client obj) throws Exception{
        client = obj;
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
        Stage fenetre = (Stage) boutonTerminer.getScene().getWindow();
        fenetre.close();
    }

    @FXML 
    public void setDaos(String persistance){
        try{  
            if(persistance.equals("En Ligne")){
                this.daos = DAOFactory.getDAOFactory(Persistance.MySQL);
            }
            else if(persistance.equals("Local")){
                this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
            }
            this.afficheCommandeClient();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        this.tableCommande.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.boutonDetailsCommande.setDisable(newValue == null);
        });
        
    }

    @FXML
    public void afficherDetailsCommande(){
        Stage detailsStage = new Stage();
        try{    
            Commande commande = this.tableCommande.getSelectionModel().getSelectedItem();
            URL fxmlURLDetailsCommande = getClass().getResource("../javafx/DetailsCommande.fxml");
            FXMLLoader fxmlLoaderDetailsCommande = new FXMLLoader(fxmlURLDetailsCommande);
            Node rootdetailsCommande = fxmlLoaderDetailsCommande.load();
            DetailsCommandeController controller = fxmlLoaderDetailsCommande.getController();
            controller.setDaos(DAOFactory.getPersistanceActuelle());
            controller.setCommande(commande);
            Scene sceneDetailsCommande = new Scene((AnchorPane) rootdetailsCommande, 600, 400);
            sceneDetailsCommande.getStylesheets().add(getClass().getResource("../javafx/css/themeClaire.css").toExternalForm()); 
            detailsStage.setScene(sceneDetailsCommande);
            detailsStage.setTitle("Details Commande");
            detailsStage.initModality(Modality.APPLICATION_MODAL);
            detailsStage.setResizable(false);
            detailsStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
            detailsStage.showAndWait();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void afficheCommandeClient(){
        try{
            //Table contenant toutes les commandes du client
            //Creation des colonnes de la table contenant toutes les commandes du client
            TableColumn<Commande, Integer> colId = new TableColumn<Commande, Integer>("ID");
            TableColumn<Commande, LocalDate> colDate = new TableColumn<Commande, LocalDate>("Date");
            //Taille des colonnes
            colId.setPrefWidth(40);
            colDate.setPrefWidth(167);
            //setResizable à l'etat faux
            colId.setResizable(false);
            colDate.setResizable(false);
            //Format du type des cellules pour chaque colonne
            colId.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("id"));
            colDate.setCellValueFactory(new PropertyValueFactory<Commande, LocalDate>("date"));
            //Ajout des colonnes à la table
            ArrayList<TableColumn<Commande,?>> colTableCommande = new ArrayList<TableColumn<Commande,?>>();
            colTableCommande.add(colId);
            colTableCommande.add(colDate);
            this.tableCommande.getColumns().setAll(colTableCommande);
            //Remplissage de la table
            this.tableCommande.getItems().addAll(daos.getCommandeDAO().getByIdClient(client.getId()));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

