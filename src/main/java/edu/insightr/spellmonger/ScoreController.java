package edu.insightr.spellmonger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ScoreController implements Initializable,ControlledScreen{
    ScreenController myController;

    @FXML
    public TableView table;
    public TableColumn LoginColumn,NbPlayColumn,ScoreColumn;
    private final ObservableList<Joueur> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Map<String, Joueur> map =  Tools.readFileToMap(System.getProperty("user.dir")+"/src/main/resources/score.json");
        for(String s: map.keySet()) {
            data.add(map.get(s));
        }
        LoginColumn.setCellValueFactory(new PropertyValueFactory<>("Login"));
        NbPlayColumn.setCellValueFactory(new PropertyValueFactory<>("NbPlay"));
        ScoreColumn.setCellValueFactory(new PropertyValueFactory<>("PercentageScore"));
        table.setItems(data);
    }

    public void setScreenParent(ScreenController screenParent){
        myController = screenParent;
    }

    public void play(){
      //  myController.loadScreen(Main.Play_ID,Main.Play_FILE);
      //  myController.setScreen(Main.Play_ID);
    }
    public void backToMenu(){
        myController.setScreen(Main.Menu_ID);
    }
}