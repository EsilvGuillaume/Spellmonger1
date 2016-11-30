package edu.insightr.spellmonger;

import edu.insightr.spellmonger.model.Player;
import edu.insightr.spellmonger.model.SpellmongerApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class MenuController implements Initializable, ControlledScreen {

    public static SpellmongerApp app = new SpellmongerApp();

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
        app.initPlayer(Login.getText(), "Opponent");
        app.setCurrentPlayer(app.getPlayer1());
        app.setOpponent(app.getPlayer2());
        app.drawFirstTwoCards();
        Stage pstage = new Stage();
        Controller ctrl = new Controller();
        ctrl.start(pstage);
    }

}
