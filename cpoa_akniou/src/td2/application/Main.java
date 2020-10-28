package td2.application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application{

    @Override
    public void start(Stage primaryStage) {
        try {
            URL fxmlURL=getClass().getResource("../javafx/MenuPrincipal.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Scene scene = new Scene((AnchorPane) root, 980, 650);
            //scene.getStylesheets().add(getClass().getResource("../javafx/css/themeSombre.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("EZGestion");
            primaryStage.show();
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image(getClass().getResource("../javafx/images/iconLogo.png").toExternalForm()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
