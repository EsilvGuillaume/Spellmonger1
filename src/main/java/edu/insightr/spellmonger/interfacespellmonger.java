
package edu.insightr.spellmonger;

/**
 * Created by aissaoui on 08/10/2016.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class interfacespellmonger extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) {

        window=primaryStage;
        window.setTitle("Spellmonger1");

        GridPane grid=new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel=new Label("Username");
        GridPane.setConstraints(nameLabel,0,0);

        TextField nameInput=new TextField("Bob");
        GridPane.setConstraints(nameInput,1,0);

        Label passLabel=new Label("Password");
        GridPane.setConstraints(nameLabel,0,1);

        TextField passInput=new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput,1,1);

        Button loginButton=new Button("Log In");
        GridPane.setConstraints(loginButton,1,2);

        grid.getChildren().addAll(nameLabel,nameInput,passLabel,passInput,loginButton);

        Scene scene=new Scene(grid,300,300);
        window.setScene(scene);

        //button login
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GridPane gridPane = new GridPane();

                Button button1 = new Button("Curse");
                Button button2 = new Button("Blessing");
                Button button3 = new Button("Eagle");
                Button button4 = new Button("Wolf");
                Button button5 = new Button("Bear");


                gridPane.add(button1, 0, 0, 1, 1);
                gridPane.add(button2, 1, 0, 1, 1);
                gridPane.add(button3, 2, 0, 1, 1);
                gridPane.add(button4, 0, 1, 1, 1);
                gridPane.add(button5, 1, 1, 1, 1);

                Scene scene1 = new Scene(gridPane, 240, 100);
                primaryStage.setScene(scene1);
                primaryStage.show();

            }
        });


        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
