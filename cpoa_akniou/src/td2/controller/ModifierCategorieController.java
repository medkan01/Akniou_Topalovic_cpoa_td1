package td2.controller;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Categorie;

public class ModifierCategorieController{

    @FXML private Button boutonValider, boutonAnnuler;
    @FXML private TextField saisieTitre, saisieVisuel;
    @FXML private TableView<Categorie> tableCategorie;
    private DAOFactory daos;
    private int id;
    private String titre;
    private String visuel;

    public boolean confirmation(){
        String msg = "êtes vous sûr de vouloir modifier la catégorie ?";
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(titre);
        alert.setTitle("Confirmation");
        alert.setContentText(msg);
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == null) {
            return false;
         } else if (option.get() == ButtonType.OK) {
            return true;
         } else if (option.get() == ButtonType.CANCEL) {
            return false;
         } else{
             return false;
         }
    }

    public void modifierCategorie() {
        try {
            this.titre = saisieTitre.getText();
            this.visuel = saisieVisuel.getText();
            if(confirmation())
            daos.getCategorieDAO().update(new Categorie(id, titre, visuel));
            Stage fenetre = (Stage) boutonValider.getScene().getWindow();
            fenetre.close();
        } catch (Exception e) {
            String erreur = "Il y a une erreur dans la saisie";
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erreur");
            alert.setTitle(e.getMessage());
            alert.setContentText(erreur);
            alert.showAndWait();
        }
    }

    @FXML
    public void annuler(){
        Stage fenetre = (Stage) boutonAnnuler.getScene().getWindow();
        fenetre.close();
    }

    public void setCategorie(Categorie obj) throws Exception{
        this.id = obj.getId();
        this.saisieTitre.setText(obj.getTitre());
        this.saisieVisuel.setText(obj.getVisuel());
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
