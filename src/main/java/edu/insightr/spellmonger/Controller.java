package edu.insightr.spellmonger;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import static edu.insightr.spellmonger.SpellmongerApp.app;

public class Controller extends Application {

    @Override
    public void start(final Stage primaryStage) {
        try {
            final URL url = getClass().getResource("/Board.fxml");
            final FXMLLoader fxmlLoader = new FXMLLoader(url);
            final AnchorPane root = fxmlLoader.load();
            final Scene scene = new Scene(root, 1050, 650);

            /*final Button draw1Button = new Button("drawl !");
            final URL buttonCSSURL = getClass().getResource("/buttonDesign");
            draw1Button.getStylesheets().add(buttonCSSURL.toExternalForm());*/

            /*Image draw_card = new Image(getClass().getResourceAsStream("/img/card-back.png"));
            Button draw1Button = new Button("lol", new ImageView(draw_card));
            HBox layout  = new HBox();
            layout.getChildren().addAll(draw1Button);*/

            scene.getStylesheets().add(getClass().getResource("/design").toExternalForm());
            primaryStage.setScene(scene);
        } catch (IOException ex) {
            System.err.println("Loading error: " + ex);
        }
        primaryStage.setTitle("Card Game");
        primaryStage.show();

    }@FXML
    private Label namePlayer1;

    @FXML
    private Label namePlayer2;

    @FXML
    private Button draw1Button;

    @FXML
    private Button draw2Button;

    @FXML
    private Label hpPlayer1;

    @FXML
    private Label hpPlayer2;

    @FXML
    private Label manaPlayer1;

    @FXML
    private Label manaPlayer2;


    public static void main(String[] args) {
        launch(args);
    }


    @FXML

    private void draw(ActionEvent event) {

        if(event.getSource()==draw1Button)
        {
        app.setCurrentPlayer(app.getPlayer1());
            app.setOpponent(app.getPlayer2());
        }
        else
        {
        app.setCurrentPlayer(app.getPlayer2());
            app.setOpponent(app.getPlayer1());
        }
        displayInitialPlayers(); // PUT SOMEWHERE ELSE
        refreshPlayerInfo(app.getCurrentPlayer(), app.getOpponent());
        app.drawACard(app.getCurrentPlayer(), app.getOpponent());
        app.endOfTurn(app.getCurrentPlayer(), app.getOpponent());
        app.setTmpPlayer(app.getCurrentPlayer());
        app.setCurrentPlayer(app.getOpponent());
        app.setOpponent(app.getTmpPlayer());
        if (app.getCurrentPlayer().equals(app.getPlayer1())) {
            draw1Button.setDisable(false);
            draw2Button.setDisable(true);
        } else if (app.getCurrentPlayer().equals(app.getPlayer2()))
        {
            draw1Button.setDisable(true);
            draw2Button.setDisable(false);
        }
        checkIfWinner();
        refreshPlayerInfo(app.getCurrentPlayer(), app.getOpponent());
    }

    private void refreshPlayerInfo(Player currPlayer, Player oppo){
        if (currPlayer == app.getPlayer1()){
            hpPlayer1.setText("Hp : "+Integer.toString(currPlayer.getHp()));
            manaPlayer1.setText("Energy : "+Integer.toString(currPlayer.getEnergy()));
            hpPlayer2.setText("Hp : "+Integer.toString(oppo.getHp()));
            manaPlayer2.setText("Energy : "+Integer.toString(oppo.getEnergy()));
        }
        else{
            hpPlayer2.setText("Hp : "+Integer.toString(currPlayer.getHp()));
            manaPlayer2.setText("Energy : "+Integer.toString(currPlayer.getEnergy()));
            hpPlayer1.setText("Hp : "+Integer.toString(oppo.getHp()));
            manaPlayer1.setText("Energy : "+Integer.toString(oppo.getEnergy()));
        }
    }

    private void checkIfWinner() {
        if (app.isOnePlayerDead()) {
            System.out.println("THE WINNER IS " + app.getWinner() + " !!!");
            System.exit(0);
        } else {
            System.out.println("*****ROUND " + app.getRoundCounter());
        }
    }

    public void displayInitialPlayers() {
        namePlayer1.setText(app.getPlayer1().getName());
        namePlayer2.setText(app.getPlayer2().getName());
    }

    @FXML
    private void draw2(ActionEvent event) {

    }

}