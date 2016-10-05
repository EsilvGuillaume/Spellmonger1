package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.util.*;

public class SpellmongerApp {

    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);

    List<String> cardPool = new ArrayList<>(70);
    List<String> discard = new ArrayList<>();

    static boolean onePlayerDead = false;
    static Player currentPlayer;
    static Player opponent;
    static Player tmpPlayer;

    static int currentCardNumber = 0;
    static int roundCounter = 1;
    static String winner = null;

    static List<Creature> playerCreaOnBoard = new ArrayList<>();
    static List<Creature> allCreaOnBoard = new ArrayList<>();


    public SpellmongerApp() {

    }

    public static void main(String[] args) {
        SpellmongerApp app = new SpellmongerApp();

        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");

        currentPlayer = player1;
        opponent = player2;

        while (!onePlayerDead) {

            logger.info("\n");
            logger.info("***** ROUND " + roundCounter);

            app.drawACard(currentPlayer, opponent); //, currentCardNumber);

            app.endOfTurn(currentPlayer, opponent);//, player1, player2);

            tmpPlayer = currentPlayer;
            currentPlayer = opponent;
            opponent = tmpPlayer;

        }

        logger.info("\n");
        logger.info("******************************");
        logger.info("THE WINNER IS " + winner + " !!!");
        logger.info("******************************");

    }

    public void endOfTurn(Player currentPlayer, Player opponent){ //, Player player1, Player player2){
        logger.info(opponent.getName() + " has " + opponent.getHp() + " life points and " + currentPlayer.getName() + " has " + currentPlayer.getHp() + " life points ");

        if (currentPlayer.getHp() <= 0) {
            winner = opponent.getName();
            onePlayerDead = true;
        }
        else if (opponent.getHp() <= 0) {
            winner = currentPlayer.getName();
            onePlayerDead = true;
        } 
        else{
            currentPlayer.setEnergy(currentPlayer.getEnergy() + 1);
            opponent.setEnergy(opponent.getEnergy() + 1);
        }

        currentCardNumber++;
        roundCounter++;

        if(roundCounter > 500){
            onePlayerDead = true;
            winner = "Time - It's a draw";
        }
    }

    public void drawACard(Player currentPlayer, Player opponent) {

        Card nextCard = currentPlayer.getDeckInfo().getDeck().get(0);

        for(Card card : currentPlayer.getDeckInfo().getDeck())
        {
            card.setOwner(currentPlayer.getDeckInfo().getDeckOwner());

            if (card.isDraw() == false){
                nextCard = card;
                currentPlayer.getDeckInfo().getDeck().remove(nextCard);
                break;
            }
        }

        if(nextCard != null) {
            System.out.println(currentPlayer.getName() + " draws " + nextCard.getName());
            nextCard.draw(); // to be discarded
        }

        if(nextCard instanceof Creature){
            ((Creature) nextCard).attack(opponent);
        }
        else if(nextCard instanceof Rituol){
            if(((Rituol) nextCard).isBonus()){
                ((Rituol) nextCard).play(currentPlayer);
            }
            else{
                ((Rituol) nextCard).play(opponent);
            }
        }

        playerCreaOnBoard = Creature.getPlayerCreaOnBoard(currentPlayer);

        allCreaOnBoard = Creature.getPlayerCreaOnBoard(currentPlayer);
        for(Creature crea : Creature.getPlayerCreaOnBoard(opponent))
        {
            allCreaOnBoard.add(crea);
        }

        System.out.println("********All the creatures on the board :");
        Creature.displayGroupOfCrea(allCreaOnBoard);

        if(playerCreaOnBoard != null) {
            playerCreaOnBoard.remove(nextCard);

            for (Card card : playerCreaOnBoard) {
                if (card instanceof Creature) {
                    ((Creature) card).attack(opponent);
                } else if (card instanceof Rituol) {
                    if (((Rituol) card).isBonus()) {
                        ((Rituol) card).play(currentPlayer);
                    } else {
                        ((Rituol) card).play(opponent);
                    }
                }
            }
        }

    }

}