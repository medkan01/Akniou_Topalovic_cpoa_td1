package td2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Categorie;

public class ModifierCategorieController{

    @FXML private Button boutonValider, boutonAnnuler;
    @FXML private TextField saisieTitre, saisieVisuel;
    @FXML private TableView<Categorie> tableCategorie;
    @FXML private Label labelResume;
    private DAOFactory daos;
    private int id;

    public void modifierCategorie() {
        try {
            String titre = saisieTitre.getText();
            String visuel = saisieVisuel.getText();
            if(daos.getCategorieDAO().update(new Categorie(id, titre, visuel))){
                labelResume.setTextFill(Color.web("#52D044"));
                labelResume.setText("Categorie ajoutée avec succés !");
            }
        } catch (Exception e) {
            labelResume.setTextFill(Color.web("#FF0000"));
            labelResume.setText(e.getMessage());
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

        if(persistance.equals("MySQL")){
            this.daos = DAOFactory.getDAOFactory(Persistance.MySQL);
        }
        else if(persistance.equals("ListeMemoire")){
            this.daos = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
        }
    }
}
