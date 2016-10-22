package edu.insightr.spellmonger;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;

import static edu.insightr.spellmonger.SpellmongerApp.app;
import static edu.insightr.spellmonger.SpellmongerApp.setIgMsg;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Controller extends Application {

    @Override
    public void start(final Stage primaryStage) {
        try {
            final URL url = getClass().getResource("/Board.fxml");
            final FXMLLoader fxmlLoader = new FXMLLoader(url);
            final AnchorPane root = fxmlLoader.load();
            final Scene scene = new Scene(root, 1050, 650);

            scene.getStylesheets().add(getClass().getResource("/design").toExternalForm());
            primaryStage.setScene(scene);
        } catch (IOException ex) {
            System.err.println("Loading error: " + ex);
        }
        primaryStage.setTitle("Card Game");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    @FXML
    private Label namePlayer1, namePlayer2;

    @FXML
    private Button draw1Button, draw2Button;

    @FXML
    private Label hpPlayer1, hpPlayer2, manaPlayer1, manaPlayer2;

    @FXML
    private ScrollPane hand1, hand2;

    @FXML
    private HBox handP1, handP2;

    @FXML
    private GridPane handG1, handG2;

    @FXML
    private Label gameMsg;

    @FXML
    private ScrollPane board1, board2;

    @FXML
    private HBox boardP1, boardP2;

    @FXML
    private GridPane boardG1, boardG2;

    public static void main(String[] args) {
        launch(args);
    }

    private void refreshBoard(ArrayList<String[]> cardNames) {
        displayBoard(cardNames);
    }

    @FXML
    private void draw(ActionEvent event) {
        resfreshIGMsg();
        if (event.getSource() == draw1Button) {
            app.setCurrentPlayer(app.getPlayer1());
            app.setOpponent(app.getPlayer2());
            hand2.setDisable(true);
            hand1.setDisable(false);
        } else {
            app.setCurrentPlayer(app.getPlayer2());
            app.setOpponent(app.getPlayer1());
            hand1.setDisable(true);
            hand2.setDisable(false);
        }
        displayInitialPlayers(); // PUT SOMEWHERE ELSE
        refreshPlayerInfo(app.getCurrentPlayer(), app.getOpponent());
        if (app.getCurrentPlayer().getHand().size()<5) {
            app.drawACard(app.getCurrentPlayer(), app.getOpponent());

            draw1Button.setDisable(true);
            draw2Button.setDisable(true);

            refreshPlayerInfo(app.getCurrentPlayer(), app.getOpponent());
            refreshHand(app.getCurrentPlayer());
            resfreshIGMsg();
        }
        else
        {
            setIgMsg("You have already five cards, play one card if you want");
            draw1Button.setDisable(true);
            draw2Button.setDisable(true);

            refreshPlayerInfo(app.getCurrentPlayer(), app.getOpponent());
            refreshHand(app.getCurrentPlayer());
            resfreshIGMsg();
        }
    }

    @FXML
    void playNoCard(){
        app.cardPlayed();
        turnEnded();
    }

    void turnEnded(){
        app.endOfTurn(app.getCurrentPlayer(), app.getOpponent());
        app.setTmpPlayer(app.getCurrentPlayer());
        app.setCurrentPlayer(app.getOpponent());
        app.setOpponent(app.getTmpPlayer());
        if (app.getCurrentPlayer().equals(app.getPlayer1())) {
            draw1Button.setDisable(false);
            draw2Button.setDisable(true);
            setIgMsg(app.getIgMsg()+"\nEnd of turn "+ app.getOpponent().getName());
        } else if (app.getCurrentPlayer().equals(app.getPlayer2())) {
            draw1Button.setDisable(true);
            draw2Button.setDisable(false);
            setIgMsg(app.getIgMsg()+"\nEnd of turn "+ app.getOpponent().getName());
        }
        app.setIgMsg(app.getIgMsg()+"\n" +app.getCurrentPlayer().getName()+" to draw");
        refreshPlayerInfo(app.getCurrentPlayer(), app.getOpponent());
        resfreshIGMsg();
        app.checkIfWinner();
    }

    void goPlayCard(Card card, Player currentPlayer, Player opponent){
            if (card.getCost() <= currentPlayer.getEnergy()) {
                setIgMsg ("");
                app.playCard(card, currentPlayer, opponent);
                ArrayList<String[]> creaNames = app.getNamesFromCreaList(app.getAllCreaOnBoard());
                refreshBoard(creaNames);
                removecardhand(card, currentPlayer);
                refreshHand(currentPlayer);
                turnEnded();
            }
            else {
                app.setIgMsg("You have not enough energy,\n choose an other card");
                resfreshIGMsg();
            }
        }




    void removecardhand (Card card, Player currentPlayer)
    {
            for (int i=0;i<currentPlayer.getHand().size();i++)
            {
                if (card == currentPlayer.getHand().get(i))
                {
                    currentPlayer.getHand().remove(i);
                }
            }
    }

    boolean checkcardhand (Card card, Player currentPlayer)
    {
        for (int i=0;i<currentPlayer.getHand().size();i++)
        {
            if (card == currentPlayer.getHand().get(i)) {

                return true;

            }
        }
        return false;
    }

    void resfreshIGMsg() {
        gameMsg.setText(app.getIgMsg());
    }
    void resfreshIGMsgintoapp()
    {
        gameMsg.setText(SpellmongerApp.getIgMsg());
    }

    private void refreshHand(Player currPlayer) {

        ArrayList<String> handCards = app.getNamesFromCardList(app.getCurrentPlayer().getHand());

        int j = 0;
        Image[] images = new Image[handCards.size()];
        ImageView[] pics = new ImageView[handCards.size()];

        if (currPlayer.equals(app.getPlayer1())) {

            hand1.setContent(null);
            handP1 = new HBox();

            for (int i = 0; i < handCards.size(); i++) {

                images[i] = new Image(getClass().getResourceAsStream("/img/" + handCards.get(i)));
                pics[i] = new ImageView(images[i]);

                int index = i;
                pics[i].setOnMouseClicked(e ->
                        goPlayCard(app.getCurrentPlayer().getHand().get(index), app.getCurrentPlayer(), app.getOpponent())
                );

                hand1.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                hand1.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

                handG1.setAlignment(Pos.CENTER);
                handG1.setPadding(new Insets(5, 5, 5, 5));
                handG1.setHgap(20);
                handP1.setPadding(new Insets(14, 2, 2, 14));
                handP1.setSpacing(20);

                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setFillWidth(true);
                columnConstraints.setHgrow(Priority.ALWAYS);
                handG1.getColumnConstraints().add(columnConstraints);

                pics[i].setFitWidth(110);
                pics[i].setFitHeight(130);
                pics[i].setImage(images[i]);

                handG1.add(pics[i], j, 0);
                handP1.getChildren().add(pics[i]);

                GridPane.setMargin(pics[i], new Insets(2, 2, 2, 2));
                hand1.setContent(handP1);

            }

        } else {

            hand2.setContent(null);
            handP2 = new HBox();

            for (int i = 0; i < handCards.size(); i++) {

                images[i] = new Image(getClass().getResourceAsStream("/img/" + handCards.get(i)));
                pics[i] = new ImageView(images[i]);

                int index = i;
                pics[i].setOnMouseClicked(e ->
                        goPlayCard(app.getCurrentPlayer().getHand().get(index), app.getCurrentPlayer(), app.getOpponent())
                );

                hand2.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                hand2.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

                handG2.setAlignment(Pos.CENTER);
                handG2.setPadding(new Insets(5, 5, 5, 5));
                handG2.setHgap(20);
                handP2.setPadding(new Insets(14, 2, 2, 14));
                handP2.setSpacing(20);

                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setFillWidth(true);
                columnConstraints.setHgrow(Priority.ALWAYS);
                handG2.getColumnConstraints().add(columnConstraints);

                pics[i].setFitWidth(110);
                pics[i].setFitHeight(130);
                pics[i].setImage(images[i]);

                handG2.add(pics[i], j, 0);
                handP2.getChildren().add(pics[i]);

                GridPane.setMargin(pics[i], new Insets(2, 2, 2, 2));
                hand2.setContent(handP2);

            }

        }
    }

    private void refreshPlayerInfo(Player currPlayer, Player oppo) {
        if (currPlayer == app.getPlayer1()) {
            hpPlayer1.setText("Hp : " + Integer.toString(currPlayer.getHp()));
            manaPlayer1.setText("Energy : " + Integer.toString(currPlayer.getEnergy()));
            hpPlayer2.setText("Hp : " + Integer.toString(oppo.getHp()));
            manaPlayer2.setText("Energy : " + Integer.toString(oppo.getEnergy()));
        } else {
            hpPlayer2.setText("Hp : " + Integer.toString(currPlayer.getHp()));
            manaPlayer2.setText("Energy : " + Integer.toString(currPlayer.getEnergy()));
            hpPlayer1.setText("Hp : " + Integer.toString(oppo.getHp()));
            manaPlayer1.setText("Energy : " + Integer.toString(oppo.getEnergy()));
        }
    }

    public void displayBoard(ArrayList<String[]> cardNames) {

        board1.setContent(null);
        board2.setContent(null);
        boardP1 = new HBox();
        boardP2 = new HBox();

        int imageCol = 0;
        Image[] images = new Image[cardNames.size()];
        ImageView[] pics = new ImageView[cardNames.size()];

        for (int i = 0; i < cardNames.size(); i++) {

            images[i] = new Image(getClass().getResourceAsStream("/img/" + cardNames.get(i)[0]));
            pics[i] = new ImageView(images[i]);

            if (app.getCurrentPlayer().equals(app.getPlayer1())) {
                if (app.getCurrentPlayer().getName().equals(cardNames.get(i)[1])) {
                    displayB1(pics[i], images[i], imageCol);
                } else {
                    displayB2(pics[i], images[i], imageCol);
                }
            } else if (app.getCurrentPlayer().equals(app.getPlayer2())) {
                if (app.getCurrentPlayer().getName().equals(cardNames.get(i)[1])) {
                    displayB2(pics[i], images[i], imageCol);
                } else {
                    displayB1(pics[i], images[i], imageCol);
                }
            }
        }
    }

    private void displayB1(ImageView pic, Image img, int j) {

        board1.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        board1.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        boardG1.setAlignment(Pos.CENTER);
        boardG1.setPadding(new Insets(5, 5, 5, 5));
        boardG1.setHgap(20);
        boardP1.setPadding(new Insets(14, 2, 2, 14));
        boardP1.setSpacing(20);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS);
        boardG1.getColumnConstraints().add(columnConstraints);

        pic.setFitWidth(110);
        pic.setFitHeight(130);
        pic.setImage(img);

        boardG1.add(pic, j, 0);
        boardP1.getChildren().add(pic);

        /*System.out.println("BOARD1 ScrollPane GET CONTENT :"+board1.getContent());
        System.out.println("BOARDG1 GridPane GET CHILDREND :"+boardG1.getChildren());
        System.out.println("BOARDP1 HBox GET CHILDREND :"+boardP1.getChildren());*/

        GridPane.setMargin(pic, new Insets(2, 2, 2, 2));
        board1.setContent(boardP1);
    }

    private void displayB2(ImageView pic, Image img, int j) {

        board2.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        board2.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        boardG2.setAlignment(Pos.CENTER);
        boardG2.setPadding(new Insets(5, 5, 5, 5));
        boardG2.setHgap(20);
        boardP2.setPadding(new Insets(14, 2, 2, 14));
        boardP2.setSpacing(20);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS);
        boardG2.getColumnConstraints().add(columnConstraints);

        pic.setFitWidth(110);
        pic.setFitHeight(130);
        pic.setImage(img);

        boardG2.add(pic, j, 0);
        boardP2.getChildren().add(pic);

        /*System.out.println("BOARD2 ScrollPane GET CONTENT :"+board2.getContent());
        System.out.println("BOARDG2 GridPane GET CHILDREND :"+boardG2.getChildren());
        System.out.println("BOARDP2 HBox GET CHILDREND :"+boardP2.getChildren());*/

        GridPane.setMargin(pic, new Insets(2, 2, 2, 2));
        board2.setContent(boardP2);
    }

    private void displayB02(ImageView pic, Image img, int j) {
        GridPane gridpane = new GridPane();
        board2.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        board2.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        gridpane.setAlignment(Pos.CENTER);
        gridpane.setPadding(new Insets(5, 5, 5, 5));
        gridpane.setHgap(20);

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().add(columnConstraints);

        board2.setContent(gridpane);

        pic.setFitWidth(110);
        pic.setFitHeight(120);

        pic.setImage(img);
        VBox vb = new VBox();
        vb.getChildren().addAll(pic);

        gridpane.add(vb, j, 0);
        GridPane.setMargin(pic, new Insets(2, 2, 2, 2));
    }

    /*private void checkIfWinner() {
        if (app.isOnePlayerDead()) {
            System.out.println("THE WINNER IS " + app.getWinner() + " !!!");
            System.exit(0);
        } else {
            System.out.println("*****ROUND " + app.getRoundCounter());
        }
    }*/

    private void displayInitialPlayers() {
        namePlayer1.setText(app.getPlayer1().getName());
        namePlayer2.setText(app.getPlayer2().getName());
    }

}
