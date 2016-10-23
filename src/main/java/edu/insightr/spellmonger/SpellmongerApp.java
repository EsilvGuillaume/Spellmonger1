package edu.insightr.spellmonger;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SpellmongerApp {

    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    static SpellmongerApp app = new SpellmongerApp();
    private static String igMsg = "Play !";
    private Player player1;
    private Player player2;
    private boolean onePlayerDead = false;
    private Player currentPlayer;
    private Player opponent;
    private Player tmpPlayer;
    private int currentCardNumber = 0;
    private int roundCounter = 1;
    private String winner = null;
    private List<Creature> playerCreaOnBoard = new ArrayList<>();
    private List<Creature> allCreaOnBoard = new ArrayList<>();

    public static void main(String[] args) {

        app.setPlayer1(new Player("Alice"));
        app.setPlayer2(new Player("Bob"));
        app.setCurrentPlayer(app.getPlayer1());
        app.setOpponent(app.getPlayer2());
        app.drawFirstTwoCards();

        Controller ctrl = new Controller();
        ctrl.main(args);

    }

    public static String getIgMsg() {
        return igMsg;
    }

    public static void setIgMsg(String igMsg) {
        SpellmongerApp.igMsg = igMsg;
    }

    public void drawFirstTwoCards() {
        currentPlayer.getDeckInfo().getDeck().get(0).draw(currentPlayer);
        currentPlayer.getDeckInfo().getDeck().get(1).draw(currentPlayer);
        opponent.getDeckInfo().getDeck().get(0).draw(opponent);
        opponent.getDeckInfo().getDeck().get(1).draw(opponent);
    }

    public void endOfTurn(Player currentPlayer, Player opponent) { //, Player player1, Player player2){
        logger.info(opponent.getName() + " has " + opponent.getHp() + " life points and " + currentPlayer.getName() + " has " + currentPlayer.getHp() + " life points ");

        if (currentPlayer.getHp() <= 0) {
            setWinner(opponent.getName());
            setOnePlayerDead(true);
        } else if (opponent.getHp() <= 0) {
            setWinner(currentPlayer.getName());
            setOnePlayerDead(true);
        } else {
            currentPlayer.setEnergy(currentPlayer.getEnergy() + 1);
            opponent.setEnergy(opponent.getEnergy() + 1);
        }

        setCurrentCardNumber(getCurrentCardNumber() + 1);
        setRoundCounter(getRoundCounter() + 1);

        if (getRoundCounter() > 500) {
            setOnePlayerDead(true);
            setWinner("Time Limit - It's a draw");
        }
    }

    public void drawACard(Player currentPlayer, Player opponent) {

        igMsg = "";

        verifyVaultOverclock(currentPlayer);

        Card nextCard = currentPlayer.getDeckInfo().getDeck().get(0);

        for (Card card : currentPlayer.getDeckInfo().getDeck()) {
            card.setOwner(currentPlayer.getDeckInfo().getDeckOwner());

            if (card.isDraw() == false) {
                nextCard = card;
                currentPlayer.getDeckInfo().getDeck().remove(nextCard);
                break;
            }
        }

        if (nextCard != null) {
            System.out.println(currentPlayer.getName() + " draws " + nextCard.getName());
            nextCard.draw(currentPlayer);
        }

        displayCardInHand(currentPlayer);
        igMsg = currentPlayer.getName()+", choose a card to play";
    }

        //askToPlay(); // (also plays card)

        /*setPlayerCreaOnBoard(Creature.getPlayerCreaOnBoard(currentPlayer));

        setAllCreaOnBoard(Creature.getPlayerCreaOnBoard(currentPlayer));
        for (Creature crea : Creature.getPlayerCreaOnBoard(opponent)) {
            getAllCreaOnBoard().add(crea);
        }

        System.out.println("********All the creatures on the board :");
        Creature.displayGroupOfCrea(getAllCreaOnBoard());

        if (getPlayerCreaOnBoard() != null) {
            //getPlayerCreaOnBoard().remove(nextCard);

            for (Card card : getPlayerCreaOnBoard()) {
                if (card instanceof Creature) {
                    ((Creature) card).attack(opponent);
                }
            }
        }
    }*/

    void cardPlayed(){
        setPlayerCreaOnBoard(Creature.getPlayerCreaOnBoard(currentPlayer));

        setAllCreaOnBoard(Creature.getPlayerCreaOnBoard(currentPlayer));
        for (Creature crea : Creature.getPlayerCreaOnBoard(opponent)) {
            getAllCreaOnBoard().add(crea);
        }

        System.out.println("********All the creatures on the board :");
        Creature.displayGroupOfCrea(getAllCreaOnBoard());

        if (getPlayerCreaOnBoard() != null) {
            //getPlayerCreaOnBoard().remove(nextCard);

            for (Card card : getPlayerCreaOnBoard()) {
                if (card instanceof Creature) {
                    ((Creature) card).attack(opponent);
                }
            }
        }
        //checkIfWinner();
    }

    void checkIfWinner() {
        if (app.isOnePlayerDead()) {
            System.out.println("THE WINNER IS " + app.getWinner() + " !!!");
            System.exit(0);
        } else {
            System.out.println("*****ROUND " + app.getRoundCounter());
        }
    }

    void displayCardInHand(Player player) {
        System.out.println(player.getName() + "'s cards in hand :");
        int i = 1;
        for (Card card : player.getHand()) {
            System.out.println(i + "]" + card.getName() + " (" + card.getCost() + ")"); // hash = "+card.getIdCode());
            i++;
        }
    }

    /*void askToPlay() {
        Scanner reader = new Scanner(System.in);
        Card cardToPlay = null;
        int cardToPlayNumber = 0;
        do {
            System.out.println("What card do you play ? If none, enter 0");
            cardToPlayNumber = reader.nextInt();
            if (cardToPlayNumber == 0) {
                setIgMsg(currentPlayer.getName() + " doesn't play any card.");
                break;
            } else if (cardToPlayNumber > 0 && cardToPlayNumber - 1 < currentPlayer.getHand().size()) {
                cardToPlay = currentPlayer.getHand().get(cardToPlayNumber - 1);
                //playCard(cardToPlay, currentPlayer, opponent);
                if (playCard(cardToPlay, currentPlayer, opponent))
                    break; // remove if we can play several cards per turn
            } else {
                System.out.println("This card number is not valid.");
            }
        } while (cardToPlayNumber != 0 || cardToPlay != null); // == null if several cards can be played each turn
    }*/

     boolean playCard(Card card, Player currentPlayer, Player opponent) {
        if (card.getCost() <= currentPlayer.getEnergy()) {
            if (card instanceof Creature) {
                ((Creature) card).setPlayed(1);
                //put on board?
                ((Creature) card).setPutOnBoard(true);
                //((Creature) card).attack(opponent); //creature will attack only once at the end of turn
            } else if (card instanceof Rituol) {
                if (card instanceof EnergyDrain) {
                    ((EnergyDrain) card).play(currentPlayer, opponent);
                } else {
                    if (((Rituol) card).isBonus()) {
                        ((Rituol) card).play(currentPlayer);
                    } else {
                        ((Rituol) card).play(opponent);
                    }
                }
            } else if (card instanceof Enchantment) {
                ((Enchantment) card).play(currentPlayer);
            }
            currentPlayer.setEnergy(currentPlayer.getEnergy() - card.getCost());
            currentPlayer.getHand().remove(card);
            if (!(card instanceof Creature)) {
                currentPlayer.getDiscard().add(card);
            }

            cardPlayed();

            return true;
        } else {
            System.out.println(card.getName() + " cost is too high to be played !");
            setIgMsg(card.getName()+"'s cost is too high to be played !");
            return false;
        }
    }

    static void verifyVaultOverclock(Player currentPlayer) {
        if (currentPlayer.isVaultOverclocking()) {
            int randNumber = ThreadLocalRandom.current().nextInt(1, 101);
            if (randNumber > 35) {
                currentPlayer.setEnergy(currentPlayer.getEnergy() + 1);
                setIgMsg(currentPlayer.getName() + " gain 1 extra energy thanks to his overclock of energy");
            } else {
                currentPlayer.setVaultOverclocking(false);
                System.out.println(currentPlayer.getName() + " loses his overclock of energy");
                setIgMsg(currentPlayer.getName() + " loses his overclock of energy");
            }
        }
    }

    String getCardImageName(Card card){
        String cardName = "";
        if (card.getName() == "bear") {
            cardName = ("bear-card.jpg");
        }
        if (card.getName() == "eagle") {
            cardName = ("eagle-card.jpg");
        }
        if (card.getName() == "wolf") {
            cardName = ("wolf-card.jpg");
        }
        if (card.getName() == "fox") {
            cardName = ("fox-card.jpg");
        }
        if (card.getName() == "curse") {
            cardName = ("curse-card.jpg");
        }
        if (card.getName() == "blessing") {
            cardName = ("blessing-card.jpg");
        }
        if (card.getName() == "energy drain") {
            cardName = ("energy-drain-card.jpg");
        }
        if (card.getName() == "vault overclocking") {
            cardName = ("overclock-card.jpg");
        }
        return cardName;
    }

    ArrayList<String> getNamesFromCardList(List<Card> cardList) {

        ArrayList<String> cardNames = new ArrayList<String>();

        for (int i = 0; i < cardList.size(); i++) {
            cardNames.add(getCardImageName(cardList.get(i)));
        }
        return cardNames;
    }

    ArrayList<String[]> getNamesFromCreaList(List<Creature> creaOnBoard) {

        ArrayList<String[]> creaNamesAndOwner = new ArrayList<String[]>();
        String cardName = "";

        for (int i = 0; i < creaOnBoard.size(); i++) {
            if (creaOnBoard.get(i).isPutOnBoard() && creaOnBoard.get(i).isAlive()) {
                if (creaOnBoard.get(i).getName() == "bear") {
                    cardName = ("bear-card.jpg");
                }
                if (creaOnBoard.get(i).getName() == "eagle") {
                    cardName = ("eagle-card.jpg");
                }
                if (creaOnBoard.get(i).getName() == "wolf") {
                    cardName = ("wolf-card.jpg");
                }
                if (creaOnBoard.get(i).getName() == "fox") {
                    cardName = ("fox-card.jpg");
                }
                String[] strToAdd = {cardName, creaOnBoard.get(i).getOwner()};
                creaNamesAndOwner.add(strToAdd);
            }
        }
        return creaNamesAndOwner;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public boolean isOnePlayerDead() {
        return onePlayerDead;
    }

    public void setOnePlayerDead(boolean onePlayerDead) {
        this.onePlayerDead = onePlayerDead;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getTmpPlayer() {
        return tmpPlayer;
    }

    public void setTmpPlayer(Player tmpPlayer) {
        this.tmpPlayer = tmpPlayer;
    }

    public int getCurrentCardNumber() {
        return currentCardNumber;
    }

    public void setCurrentCardNumber(int currentCardNumber) {
        this.currentCardNumber = currentCardNumber;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<Creature> getPlayerCreaOnBoard() {
        return playerCreaOnBoard;
    }

    public void setPlayerCreaOnBoard(List<Creature> playerCreaOnBoard) {
        this.playerCreaOnBoard = playerCreaOnBoard;
    }

    public List<Creature> getAllCreaOnBoard() {
        return allCreaOnBoard;
    }

    public void setAllCreaOnBoard(List<Creature> allCreaOnBoard) {
        this.allCreaOnBoard = allCreaOnBoard;
    }

}