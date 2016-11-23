package edu.insightr.spellmonger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static String Menu_ID = "MENU";
    public static String Menu_FILE = "/menu.fxml";

    public static String Score_ID = "SCORE";
    public static String Score_FILE = "/score.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {

        ScreenController mainContainer = new ScreenController();
        System.out.println(Menu_FILE) ;
        mainContainer.loadScreen(Menu_ID,Menu_FILE);

        mainContainer.setScreen(Menu_ID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        primaryStage.setTitle("SpellMonger");
        Scene scene = new Scene(root, 800, 700);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }
}
