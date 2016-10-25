package edu.insightr.spellmonger;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//import java.awt.*;
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

            Image cursorImage = new Image("img/cursor-basic.png");
            scene.setCursor(new ImageCursor(cursorImage));

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


    @FXML private Rectangle player1Box, player2Box;

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
    private ProgressBar TimeToPlay;
    @FXML
    private GridPane boardG1, boardG2;

    @FXML private ImageView currentCard, discard1, discard2;

    public static void main(String[] args) {
        launch(args);
    }

    public void initialize() {
        refreshHand(app.getPlayer1());
        refreshHand(app.getPlayer2());
        refreshPlayerInfo(app.getCurrentPlayer(), app.getOpponent());
        displayInitialPlayers();
    }

    protected void addCursorEffect(Node node){
        Image imageCursorExit = new Image("img/cursor-basic.png");
        Image imageCursorEnter = new Image("img/cursor-hover.png");

        node.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                (node.getScene()).setCursor(new ImageCursor(imageCursorEnter));
            }
        });

        node.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                (node.getScene()).setCursor(new ImageCursor(imageCursorExit));
            }
        });
    }

    private void refreshBoard(List<Creature> creaOnBoard) {
        displayBoard(creaOnBoard);
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
        app.verifyVaultOverclock(app.getOpponent());
        if(app.getCurrentPlayer().equals(app.getPlayer1())) {
            player2Box.setFill(Color.rgb(173, 237, 125));
            player1Box.setFill(Color.rgb(255, 240, 175));
        }
        else{
            player1Box.setFill(Color.rgb(173, 237, 125));
            player2Box.setFill(Color.rgb(255, 240, 175));
        }
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
        if (app.getCurrentPlayer().equals(app.getPlayer1())) {
            hand2.setDisable(true);
            hand1.setDisable(false);
        } else {
            hand1.setDisable(true);
            hand2.setDisable(false);
        }
        refreshPlayerInfo(app.getCurrentPlayer(), app.getOpponent());
        resfreshIGMsg();
        app.checkIfWinner();
    }


    void refreshDiscard(){
        if(app.getCurrentPlayer().getDiscard().size() > 0) {
            Card lastDiscard = (Card) app.getCurrentPlayer().getDiscard().get(app.getCurrentPlayer().getDiscard().size() - 1);
            //String nameOfImage = app.getCardImageName(lastDiscard);
            //Image image0 = new Image(getClass().getResourceAsStream("/img/"+nameOfImage));
            Image image = lastDiscard.getImg();
            ImageView pic = new ImageView();
            if(app.getCurrentPlayer().equals(app.getPlayer1())){
                discard1.setImage(image);
                pic = discard1;
            }
           else{
                discard2.setImage(image);
                pic = discard2;
            }
            pic.hoverProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    refreshCurrCard(image);
                }
            });
            addCursorEffect(pic);
        }
        if(app.getOpponent().getDiscard().size() > 0) {
            Card lastDiscard = (Card) app.getOpponent().getDiscard().get(app.getOpponent().getDiscard().size() - 1);
            //String nameOfImage = app.getCardImageName(lastDiscard);
            //Image image0 = new Image(getClass().getResourceAsStream("/img/"+nameOfImage));
            Image image = lastDiscard.getImg();
            ImageView pic = new ImageView();
            if(app.getOpponent().equals(app.getPlayer1())){
                discard1.setImage(image);
                pic = discard1;
            }
            else{
                discard2.setImage(image);
                pic = discard2;
            }
            pic.hoverProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    refreshCurrCard(image);
                }
            });
            addCursorEffect(pic);
        }
    }

    void goPlayCard(Card card, Player currentPlayer, Player opponent){
            if (card.getCost() <= currentPlayer.getEnergy()) {
                setIgMsg ("");
                app.playCard(card, currentPlayer, opponent);
                //ArrayList<String[]> creaNames = app.getNamesFromCreaList(app.getAllCreaOnBoard());
                refreshBoard(app.getAllCreaOnBoard());
                removecardhand(card, currentPlayer);
                refreshHand(currentPlayer);
                turnEnded();
            }
            else {
                app.setIgMsg("You have not enough energy,\n choose another card");
                resfreshIGMsg();
            }
            refreshDiscard();
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

    boolean checkcardhand (Card card, Player currentPlayer) {
        for (int i=0;i<currentPlayer.getHand().size();i++)
        {
            if (card == currentPlayer.getHand().get(i)) {

                return true;

            }
        }
        return false;
    }

    @FXML
    public void refreshCurrCard(Image img) {
        currentCard.setImage(img);
    }

    void resfreshIGMsg() {
        gameMsg.setText(app.getIgMsg());
    }
    void resfreshIGMsgintoapp()
    {
        gameMsg.setText(SpellmongerApp.getIgMsg());
    }

    private void refreshHand(Player currPlayer) {

        //ArrayList<String> handCards = app.getNamesFromCardList(currPlayer.getHand());
        //ArrayList<String> handCardsbis = app.getNamesFromCardList(app.getOpponent().getHand());
        int j = 0;
        //Image[] images = new Image[handCards.size()];
        //ImageView[] pics = new ImageView[handCards.size()];
        //Image[] imagesbis = new Image[handCardsbis.size()];
        //ImageView[] picsbis = new ImageView[handCardsbis.size()];
        Image img;
        ImageView pic;

        if (currPlayer.equals(app.getPlayer1())) {

            hand1.setContent(null);
            handP1 = new HBox();

            for (int i = 0; i < currPlayer.getHand().size(); i++) {

                img = currPlayer.getHand().get(i).getImg();
                pic = new ImageView(img);
                final Image img0 = img;

                int index = i;

                pic.hoverProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        refreshCurrCard(img0);
                    }
                });

                addCursorEffect(pic);

                pic.setOnMouseClicked(e ->
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

                pic.setFitWidth(110);
                pic.setFitHeight(130);
                pic.setImage(img);

                handG1.add(pic, j, 0);
                handP1.getChildren().add(pic);

                GridPane.setMargin(pic, new Insets(2, 2, 2, 2));
                hand1.setContent(handP1);
            }

        } else {

            hand2.setContent(null);
            handP2 = new HBox();

            for (int i = 0; i < currPlayer.getHand().size(); i++) {

                //images[i] = new Image(getClass().getResourceAsStream("/img/" + handCards.get(i)));
                //pics[i] = new ImageView(images[i]);
                img = currPlayer.getHand().get(i).getImg();
                pic = new ImageView(img);
                final Image img0 = img;

                int index = i;

                pic.hoverProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        refreshCurrCard(img0);
                    }
                });

                addCursorEffect(pic);

                pic.setOnMouseClicked(e ->
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

                pic.setFitWidth(110);
                pic.setFitHeight(130);
                pic.setImage(img);

                handG2.add(pic, j, 0);
                handP2.getChildren().add(pic);

                GridPane.setMargin(pic, new Insets(2, 2, 2, 2));
                hand2.setContent(handP2);

            }

        }
    }

    private void refreshHand0(Player currPlayer) {

        //ArrayList<String> handCards = app.getNamesFromCardList(currPlayer.getHand());
        //ArrayList<String> handCardsbis = app.getNamesFromCardList(app.getOpponent().getHand());
        int j = 0;
        //Image[] images = new Image[handCards.size()];
        //ImageView[] pics = new ImageView[handCards.size()];
        //Image[] imagesbis = new Image[handCardsbis.size()];
        //ImageView[] picsbis = new ImageView[handCardsbis.size()];
        Image img0;
        ImageView pic;

        if (currPlayer.equals(app.getPlayer1())) {

            hand1.setContent(null);
            handP1 = new HBox();

            for (int i = 0; i < app.getCurrentPlayer().getHand().size(); i++) {

                img0 = app.getCurrentPlayer().getHand().get(i).getImg();
                pic = new ImageView(img0);
                final Image img = img0;

                int index = i;

                pic.hoverProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        refreshCurrCard(img);
                    }
                });

                addCursorEffect(pic);

                pic.setOnMouseClicked(e ->
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

                pic.setFitWidth(110);
                pic.setFitHeight(130);
                pic.setImage(img);

                handG1.add(pic, j, 0);
                handP1.getChildren().add(pic);

                GridPane.setMargin(pic, new Insets(2, 2, 2, 2));
                hand1.setContent(handP1);
            }

        } else {

            hand2.setContent(null);
            handP2 = new HBox();

            for (int i = 0; i < app.getCurrentPlayer().getHand().size(); i++) {

                img0 = app.getCurrentPlayer().getHand().get(i).getImg();
                pic = new ImageView(img0);
                final Image img = img0;

                int index = i;

                pic.hoverProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        refreshCurrCard(img);
                    }
                });

                addCursorEffect(pic);

                pic.setOnMouseClicked(e ->
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

                pic.setFitWidth(110);
                pic.setFitHeight(130);
                pic.setImage(img);

                handG2.add(pic, j, 0);
                handP2.getChildren().add(pic);

                GridPane.setMargin(pic, new Insets(2, 2, 2, 2));
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

    public void displayBoard(List<Creature> creaOnBoard) {

        board1.setContent(null);
        board2.setContent(null);
        boardP1 = new HBox();
        boardP2 = new HBox();

        int imageCol = 0;
        //Image[] images = new Image[cardNames.size()];
        //ImageView[] pics = new ImageView[cardNames.size()];
        Image img;
        ImageView pic;

        for (int i = 0; i < creaOnBoard.size(); i++) {

            img = creaOnBoard.get(i).getImg();
            pic = new ImageView(img);

            if (app.getCurrentPlayer().equals(app.getPlayer1())) {
                if (app.getCurrentPlayer().getName().equals(creaOnBoard.get(i).getOwner())) {
                    displayB1(pic, img, imageCol);
                } else {
                    displayB2(pic, img, imageCol);
                }
            } else if (app.getCurrentPlayer().equals(app.getPlayer2())) {
                if (app.getCurrentPlayer().getName().equals(creaOnBoard.get(i).getOwner())) {
                    displayB2(pic, img, imageCol);
                } else {
                    displayB1(pic, img, imageCol);
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

        pic.hoverProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                refreshCurrCard(img);
            }
        });

        addCursorEffect(pic);

        boardG1.add(pic, j, 0);
        boardP1.getChildren().add(pic);

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

        pic.hoverProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                refreshCurrCard(img);
            }
        });

        addCursorEffect(pic);

        boardG2.add(pic, j, 0);
        boardP2.getChildren().add(pic);

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
