package edu.insightr.spellmonger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MenuController implements Initializable,ControlledScreen{
    ScreenController myController;

    @FXML
    public TextField Login;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setScreenParent(ScreenController screenParent){
        myController = screenParent;
    }


    public void play(){

        myController.addData("NamePlayer", Login.getText());
    }

    public void viewScore(){
        myController.loadScreen(Main.Score_ID,Main.Score_FILE);
        myController.setScreen(Main.Score_ID);
    }

}
