package td2.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import td2.dao.daofactory.DAOFactory;
import td2.dao.daofactory.Persistance;
import td2.pojo.Produit;
import td2.pojo.ProduitSelectionne;
import td2.pojo.Categorie;
import td2.pojo.Client;
import td2.pojo.Commande;
import td2.pojo.LigneCommande;

public class ModifierCommandeController {
        
    private int idCom;
    private LocalDate date;
    private DAOFactory daos;
    @FXML private ChoiceBox<Categorie> cbxCategorie;
    @FXML private ChoiceBox<Client> cbxClients;
    @FXML private AnchorPane panelFenetre;
    @FXML private VBox vBoxFenetre;
    @FXML private GridPane gridFenetre, gridTable, gridBoutonBas, gridBoutonHaut, gridLigneCommande;
    @FXML private Button boutonAjouterLigneCommande, boutonSupprimerLigneCommande, boutonToutSupprimer, boutonModifierCommande, boutonAnnuler;
    @FXML private TableView<Produit> tableProduit;
    @FXML private TableView<ProduitSelectionne> tableProduitSelectionne;
    @FXML private Button boutonAfficherTousLesProduits;

    @FXML
    public void ajouter(){
        Stage quantiteStage = new Stage();
        try{
            URL fxmlURLQuantite = getClass().getResource("../javafx/ChoisirQuantiteLigneCommande.fxml");
            FXMLLoader fxmlLoaderQuantite = new FXMLLoader(fxmlURLQuantite);
            Node rootQuantite = fxmlLoaderQuantite.load();
            ChoisirQuantiteLigneCommandeController controller = fxmlLoaderQuantite.getController();
            Scene sceneQuantite = new Scene((AnchorPane) rootQuantite, 150, 105);
            quantiteStage.setScene(sceneQuantite);
            quantiteStage.setTitle("Choix quantité");
            quantiteStage.initModality(Modality.APPLICATION_MODAL);
            quantiteStage.setResizable(false);
            quantiteStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
            quantiteStage.showAndWait();
            //Creation du produit selectionne
            Produit produit = this.tableProduit.getSelectionModel().getSelectedItem();
            int idProduit = produit.getId();
            String nomProduit = produit.getNom();
            String nomCategorie = daos.getCategorieDAO().getById(produit.getIdCategorie()).getTitre();
            double tarifUnitaire = produit.getTarif();
            int quantite = controller.getQuantite();
            ProduitSelectionne produitSelectionne = new ProduitSelectionne(idProduit,nomProduit, nomCategorie, tarifUnitaire, quantite);
            if(contient(produit.getId(),this.tableProduitSelectionne)){
                this.tableProduitSelectionne.getSelectionModel().select(position(produit.getId(),this.tableProduitSelectionne));
                produitSelectionne = this.tableProduitSelectionne.getSelectionModel().getSelectedItem();
                int nouvelleQuantite = quantite + produitSelectionne.getQuantite();
                produitSelectionne = new ProduitSelectionne(idProduit, nomProduit, nomCategorie, tarifUnitaire, nouvelleQuantite);
                this.tableProduitSelectionne.getItems().remove(position(produit.getId(),this.tableProduitSelectionne));
                this.tableProduitSelectionne.getItems().add(produitSelectionne);
            } else{
                this.tableProduitSelectionne.getItems().add(produitSelectionne);
            }
            //Fermeture de la fenetre quantite apres recuperation des donnees necessaire
            quantiteStage.close();
            this.tableProduit.getSelectionModel().clearSelection();
            this.tableProduitSelectionne.getSelectionModel().clearSelection();
            if(!this.tableProduitSelectionne.getItems().isEmpty()){
                this.boutonSupprimerLigneCommande.setDisable(false);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(this.tableProduitSelectionne.getItems().size()!=0){
            this.boutonToutSupprimer.setDisable(false);
        }
    }
    @FXML
    public void supprimer(){
        ProduitSelectionne produitSelectionne = this.tableProduitSelectionne.getSelectionModel().getSelectedItem();
        this.tableProduitSelectionne.getItems().remove(produitSelectionne);
    }

    public boolean contient(int idProduit, TableView<ProduitSelectionne> tableProduitSelectionne){
        for(int i = 0 ; i < tableProduitSelectionne.getItems().size(); i++){
            tableProduitSelectionne.getSelectionModel().select(i);
            if(idProduit == tableProduitSelectionne.getSelectionModel().getSelectedItem().getIdProduit()){
                return true;
            }
        }
        return false;
    }

    public int position(int idProduit, TableView<ProduitSelectionne> tableProduitSelectionne){
        for(int i = 0 ; i < tableProduitSelectionne.getItems().size(); i++){
            tableProduitSelectionne.getSelectionModel().select(i);
            if(idProduit == tableProduitSelectionne.getSelectionModel().getSelectedItem().getIdProduit()){
                return i;
            }
        }
        return -1;
    }

    @FXML
    public void ajouterClient(){
        Stage ajoutStage = new Stage();
        try {
            URL fxmlURLAjouterClient = getClass().getResource("../javafx/AjoutClient.fxml");
            FXMLLoader fxmlLoaderAjouterClient = new FXMLLoader(fxmlURLAjouterClient);
            Node rootAjouterClient = fxmlLoaderAjouterClient.load();
            AjoutClientController controller = fxmlLoaderAjouterClient.getController();
            controller.setDaos(DAOFactory.getPersistanceActuelle());
            Scene sceneAjouterClient = new Scene((AnchorPane) rootAjouterClient, 800, 500);
            sceneAjouterClient.getStylesheets().add(getClass().getResource("../javafx/css/themeClaire.css").toExternalForm());
            ajoutStage.setScene(sceneAjouterClient);
            ajoutStage.setTitle("Ajout client");
            ajoutStage.initModality(Modality.APPLICATION_MODAL);
            ajoutStage.setResizable(false);
            ajoutStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
            ajoutStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try { 
            this.cbxClients.getItems().clear();
            this.cbxClients.setItems(FXCollections.observableArrayList(daos.getClientDAO().getAll()));
        } catch (Exception e) {
           
        }
    }

    @FXML
    public void afficherTousLesProduits(){
        this.cbxCategorie.getSelectionModel().select(-1);
        this.tableProduit.getItems().clear();
        this.boutonAfficherTousLesProduits.setDisable(true);
        try{
        this.tableProduit.getItems().addAll(daos.getProduitDAO().getAll());
    }catch(Exception e){
            String erreur = "Erreur";
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur Ajout Commande");
            alert.setHeaderText(e.getMessage());
            alert.setContentText(erreur);
            alert.showAndWait();
    }
    }

    @FXML
    public boolean modifierCommande(){
        int idClient = -1;
        try {
            idClient = this.cbxClients.getSelectionModel().getSelectedItem().getId();
            if(idClient==-1) throw new Exception ("");
        } catch (Exception e) {
            String erreur = "Erreur";
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur Ajout Commande");
            alert.setHeaderText("Veuillez sélectionner un Client.");
            alert.setContentText(erreur);
            alert.showAndWait();
            return false;
        }
        ObservableList<ProduitSelectionne> liste = this.tableProduitSelectionne.getItems();
        try{
            Commande commande = new Commande(this.idCom, this.date, idClient);
            for(int i = 0; i<liste.size(); i++){
                ProduitSelectionne produitSelectionne = liste.get(i);
                LigneCommande ligneCommande = new LigneCommande(liste.get(i).getQuantite(),liste.get(i).getTarifUnitaire()); 
                Produit produit = daos.getProduitDAO().getById(produitSelectionne.getIdProduit());
                commande.ajouterLigne(produit, ligneCommande);
                daos.getLigneCommandeDAO().update(commande.getId(),liste.get(i).getIdProduit(), ligneCommande);
            }
            daos.getCommandeDAO().update(commande);
        } catch(Exception e){
            String erreur = "Erreur";
            System.out.println(e.getMessage());
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur Ajout Commande");
            alert.setHeaderText(e.getMessage());
            alert.setContentText(erreur);
            alert.showAndWait();
            return false;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout commande");
        alert.setHeaderText("Resultat:");
        alert.setContentText("Commande modifiée avec succès !");
        alert.showAndWait();
        return true;
    }
    
    @FXML
    public void toutSupprimer(){
        this.tableProduitSelectionne.getItems().clear();
        this.boutonToutSupprimer.setDisable(true);
    }

    @FXML
    public void annuler(){
        Stage fenetre = (Stage) boutonAnnuler.getScene().getWindow();
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
        try {
            //Premiere table contenant tous les produits
            //Creation des colonnes de la table contenant tous les produits
            TableColumn<Produit, String> colNomProd = new TableColumn<Produit, String>("Nom");
            TableColumn<Produit, Double> colTarifProd = new TableColumn<Produit, Double>("Tarif");
            //Taille des colonnes
            colNomProd.setPrefWidth(130);
            colTarifProd.setPrefWidth(90);
            //Format du type des cellules pour chaque colonne
            colNomProd.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
            colTarifProd.setCellValueFactory(new PropertyValueFactory<Produit, Double>("tarif"));
            //Ajout des colonnes à la table
            ArrayList<TableColumn<Produit,?>> colTableProduit = new ArrayList<TableColumn<Produit,?>>();
            colTableProduit.add(colNomProd);
            colTableProduit.add(colTarifProd);
            this.tableProduit.getColumns().setAll(colTableProduit);
            //Remplissage de la table
            this.tableProduit.getItems().addAll(daos.getProduitDAO().getAll());

            //Deuxieme tables contenant les produits à ajouter à la commande
            //Creation des colonnes de la table contenant les produits à ajouter à la commande
            TableColumn<ProduitSelectionne, String> colNomProdLigneCom = new TableColumn<ProduitSelectionne, String>("Nom");
            TableColumn<ProduitSelectionne, String> colNomCategorieProdLigneCom = new TableColumn<ProduitSelectionne, String>("Categorie");
            TableColumn<ProduitSelectionne, Double> colTarifProdLigneCom = new TableColumn<ProduitSelectionne, Double>("Tarif");
            TableColumn<ProduitSelectionne, Integer> colQuantiteProdLigneCom = new TableColumn<ProduitSelectionne, Integer>("Quantité");
            TableColumn<ProduitSelectionne, Integer> colIdProduit = new TableColumn<ProduitSelectionne, Integer>("idProduit");
            //Tailles des colonnes
            colNomProdLigneCom.setPrefWidth(128);
            colNomCategorieProdLigneCom.setPrefWidth(128);
            colTarifProdLigneCom.setPrefWidth(77);
            colQuantiteProdLigneCom.setPrefWidth(77);
            colIdProduit.setVisible(false);
            //setResizable = false
            colNomProdLigneCom.setResizable(false);
            colNomCategorieProdLigneCom.setResizable(false);
            colTarifProdLigneCom.setResizable(false);
            colQuantiteProdLigneCom.setResizable(false);
            //Format du type des cellules pour chaque colonne
            colIdProduit.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, Integer>("idProduit"));
            colNomProdLigneCom.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, String>("nomProduit"));
            colNomCategorieProdLigneCom.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, String>("nomCategorie"));
            colTarifProdLigneCom.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, Double>("tarifUnitaire"));
            colQuantiteProdLigneCom.setCellValueFactory(new PropertyValueFactory<ProduitSelectionne, Integer>("quantite"));
            //Ajout des colonnes à la table contenant les produits à ajouter à la commande
            ArrayList<TableColumn<ProduitSelectionne,?>> colTableProduitSelectionne = new ArrayList<TableColumn<ProduitSelectionne,?>>();
            colTableProduitSelectionne.add(colIdProduit);
            colTableProduitSelectionne.add(colNomProdLigneCom);
            colTableProduitSelectionne.add(colNomCategorieProdLigneCom);
            colTableProduitSelectionne.add(colTarifProdLigneCom);
            colTableProduitSelectionne.add(colQuantiteProdLigneCom);
            this.tableProduitSelectionne.getColumns().setAll(colTableProduitSelectionne);
            this.cbxCategorie.setItems(FXCollections.observableArrayList(daos.getCategorieDAO().getAll()));
            this.cbxClients.setItems(FXCollections.observableArrayList(daos.getClientDAO().getAll()));
        } catch (Exception e) {
            String erreur = "Erreur";
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur Ajout Commande");
            alert.setHeaderText(e.getMessage());
            alert.setContentText(erreur);
            alert.showAndWait();
        }
        this.cbxCategorie.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonAfficherTousLesProduits.setDisable(false);this.tableProduit.getItems().clear();try{this.tableProduit.getItems().addAll(daos.getProduitDAO().getAllByCategorie(this.cbxCategorie.getSelectionModel().getSelectedItem().getId()));}catch(Exception e){System.out.print(e.getMessage());}});
        this.tableProduit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonAjouterLigneCommande.setDisable(false); this.tableProduitSelectionne.getSelectionModel().clearSelection();});
        this.tableProduitSelectionne.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {this.boutonSupprimerLigneCommande.setDisable(newValue == null); this.tableProduitSelectionne.getSelectionModel().select(-1); this.tableProduit.getSelectionModel().clearSelection();});
    }

    public void setCommande(Commande obj){
        try{
            Commande commande = obj;
            this.idCom = commande.getId();
            this.date = commande.getDate();
            HashMap<Produit, LigneCommande> listeLigneCommande = commande.getLigneCommande();
            ArrayList<ProduitSelectionne> listeProduitSelectionnes = new ArrayList<ProduitSelectionne>();
            Set<Produit> keys = listeLigneCommande.keySet();
            ArrayList<Produit> listeProduitLigneCommande = new ArrayList<Produit>(keys);
            for(int i = 0; i<listeProduitLigneCommande.size();i++){
                Produit produit = listeProduitLigneCommande.get(i);
                Categorie categorie = daos.getCategorieDAO().getById(produit.getIdCategorie());
                LigneCommande ligneCommande = listeLigneCommande.get(produit);
                ProduitSelectionne produitSelectionne = new ProduitSelectionne(produit.getId(), produit.getNom(), categorie.getTitre(), ligneCommande.getTarifUnitaire(), ligneCommande.getQuantite());
                listeProduitSelectionnes.add(produitSelectionne);
            }
            this.tableProduitSelectionne.getItems().addAll(listeProduitSelectionnes);
            this.cbxClients.setValue(daos.getClientDAO().getById(commande.getIdClient()));
        } catch(Exception e){
            String erreur = "Erreur";
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Impossible de récupérer les lignes de commande");
            alert.setHeaderText(e.getMessage());
            alert.setContentText(erreur);
            alert.showAndWait();
        }
    }
}
