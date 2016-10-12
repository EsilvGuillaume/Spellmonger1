package edu.insightr.spellmonger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class Controller extends Application {

    @Override
    public void start(final Stage primaryStage) {
        try {
            // Localisation du fichier FXML.
            final URL url = getClass().getResource("/Board.fxml");
            // Création du loader.
            final FXMLLoader fxmlLoader = new FXMLLoader(url);
            // Chargement du FXML.
            final AnchorPane root = (AnchorPane) fxmlLoader.load();
            // Création de la scène.
            final Scene scene = new Scene(root, 1050, 650);
            primaryStage.setScene(scene);
        } catch (IOException ex) {
            System.err.println("Loarding error: " + ex);
        }
        primaryStage.setTitle("Test FXML");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
