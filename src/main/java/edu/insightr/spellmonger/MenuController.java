package edu.insightr.spellmonger;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class MenuController implements Initializable, ControlledScreen {

    static SpellmongerApp app = new SpellmongerApp();

    ScreenController myController;

    @FXML
    public TextField Login;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent;
    }

    public void play() {
        System.out.println("Play is pressed");
        myController.addData("NamePlayer", Login.getText());
        launchGame();
    }

    public void viewScore() {
        myController.loadScreen(Main.Score_ID, Main.Score_FILE);
        myController.setScreen(Main.Score_ID);
    }

    public void launchGame() {
        app = null;
        System.gc();
        Creature.allCreatures = new ArrayList<>();
        app = new SpellmongerApp();

        app.setPlayer1(new Player(Login.getText()));
        app.setPlayer2(new Player("Opponent"));
        app.setCurrentPlayer(app.getPlayer1());
        app.setOpponent(app.getPlayer2());
        app.drawFirstTwoCards();
        Stage pstage = new Stage();
        Controller ctrl = new Controller();
        ctrl.start(pstage);
    }

}
