package td2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Produit;

public class DetailsProduitController {

    private DAOFactory daos;
    @FXML private Button boutonTermine;
    @FXML private Label labelId, labelNom, labelDescription, labelCategorie, labelTarif;
    @FXML private ImageView imageProduit;
    
    @FXML
    public void setProduit(Produit obj) throws Exception{
        this.labelId.setText(String.valueOf(obj.getId()));
        this.labelNom.setText(obj.getNom());
        this.labelDescription.setText(obj.getDescription());
        this.labelTarif.setText(String.valueOf(obj.getTarif()));
        this.labelCategorie.setText(daos.getCategorieDAO().getCategorieByProduit(obj.getId()).getTitre());
        try{
            Image image = new Image(getClass().getResource("../javafx/images/"+obj.getVisuel()).toExternalForm());
            this.imageProduit.setImage(image);
        } catch(Exception e){
            Image image = new Image(getClass().getResource("../javafx/images/null.png").toExternalForm());
            this.imageProduit.setImage(image);
        }
        
        
    }

    @FXML
    public void close(){
        Stage fenetre = (Stage) boutonTermine.getScene().getWindow();
        fenetre.close();
    }

    @FXML 
    public void setDaos(String persistance){

        if(persistance.equals("En Ligne")){
            this.daos = DAOFactory.getDAOFactory(Persistance.MySQL);
        }
        else if(persistance.equals("Local")){
            this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        }
    }
}
