package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.util.*;

public class SpellmongerApp {

    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);

    List<String> cardPool = new ArrayList<>(70);
    List<String> discard = new ArrayList<>();

    //List<String> creatures = new ArrayList<>(3);
    //List<String> rituals = new ArrayList<>(2);

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

        /*creatures.add("Eagle");
        creatures.add("Wolf");
        creatures.add("Bear");

        rituals.add("Curse");
        rituals.add("Blessing");*/

        /*for(int i = 0; i < 70; i++)
        {
            int max = creatures.size()+ rituals.size();
            Random rand = new Random();
            int randNumber = rand.nextInt(max);

            if(randNumber < creatures.size())
            {
                cardPool.add(creatures.get(randNumber));
            }
            else if(randNumber >= creatures.size())
            {
                cardPool.add(rituals.get(randNumber - creatures.size()));
            }

        }*/
    }

    public static void main(String[] args) {
        SpellmongerApp app = new SpellmongerApp();

        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");

        currentPlayer = player1;
        opponent = player2;

        //List<Creature> player1Creatures = Creature.getPlayerCreatures(player1.getName());
        //List<Creature> player2Creatures = Creature.getPlayerCreatures(player2.getName());

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
    }

    public void drawACard(Player currentPlayer, Player opponent) {

        //Creature.displayGroupOfCrea(Creature.allCreatures);

        Card nextCard = new Bear("no more card ?", "none"); // null, infinite loop
        //System.out.println("deck size: "+currentPlayer.getDeckInfo().getDeck().size());
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

        //System.out.println("********call of playerCreaOnBard 2");
        playerCreaOnBoard = Creature.getPlayerCreaOnBoard(currentPlayer);

        allCreaOnBoard = Creature.getPlayerCreaOnBoard(currentPlayer);
        for(Creature crea : Creature.getPlayerCreaOnBoard(opponent))
        {
            allCreaOnBoard.add(crea);
        }

        //System.out.println(currentPlayer.getName()+" crea on board : "+playerCreaOnBoard);

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


        /*if (creatures.get(0).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer.getName() + " draw a " + creatures.get(0));

            currentPlayer.setNumberOfCreaOnBoard(currentPlayer.getNumberOfCreaOnBoard() + 1);
            int degats = Creature.getCreaDamageForPlayer(currentPlayer);
            if (currentPlayer.getNumberOfCreaOnBoard() > 0)
            {
                opponent.setHp(opponent.getHp() - degats);
                logger.info("The " + currentPlayer.getNumberOfCreaOnBoard() + " creatures of " + currentPlayer.getName() + " attack and deal " + degats + " damages to its opponent");

            }

            cardPool.remove(currentCardNumber);

        }

        else if (creatures.get(1).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer.getName() + " draw a " + creatures.get(1));
            currentPlayer.setNumberOfCreaOnBoard(currentPlayer.getNumberOfCreaOnBoard() + 1);
            int degats = Creature.getCreaDamageForPlayer(currentPlayer);
            if (currentPlayer.getNumberOfCreaOnBoard() > 0)
            {
                opponent.setHp(opponent.getHp() - degats);
                logger.info("The " + currentPlayer.getNumberOfCreaOnBoard() + " creatures of " + currentPlayer.getName() + " attack and deal " + degats + " damages to its opponent");

            }

            cardPool.remove(currentCardNumber);
        }

        else if (creatures.get(2).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer.getName() + " draw a " + creatures.get(2));
            currentPlayer.setNumberOfCreaOnBoard(currentPlayer.getNumberOfCreaOnBoard() + 1);
            int degats = Creature.getCreaDamageForPlayer(currentPlayer);
            if (currentPlayer.getNumberOfCreaOnBoard() > 0)
            {
                opponent.setHp(opponent.getHp() - degats);
                logger.info("The " + currentPlayer.getNumberOfCreaOnBoard() + " creatures of " + currentPlayer.getName() + " attack and deal " + degats + " damages to its opponent");

            }

            cardPool.remove(currentCardNumber);
        }

        else if (rituals.get(0).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer.getName() + " draw a " + rituals.get(0));
            int degats = Creature.getCreaDamageForPlayer(currentPlayer);
            if (currentPlayer.getNumberOfCreaOnBoard() > 0) {
                opponent.setHp(opponent.getHp() - degats - 3);
                logger.info("The " + currentPlayer.getNumberOfCreaOnBoard() + " creatures of " + currentPlayer.getName() + " attack and deal " + degats + " damages to its opponent");
                logger.info(currentPlayer.getName() + " cast a " + rituals.get(0) + " that deals 3 damages to " + opponent.getName());
            }

            String card = cardPool.get(currentCardNumber);
            cardPool.remove(currentCardNumber);
            discard.add(card);

        }

        else if (rituals.get(1).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer.getName() + " draw a " + rituals.get(1));
            int degats = Creature.getCreaDamageForPlayer(currentPlayer);

            if (currentPlayer.getHp() <= 17)
                currentPlayer.setHp(currentPlayer.getHp() + 3);
            else
                currentPlayer.setHp(20);

            if (currentPlayer.getNumberOfCreaOnBoard() > 0) {
                opponent.setHp(opponent.getHp() - degats);
                logger.info("The " + currentPlayer.getNumberOfCreaOnBoard() + " creatures of " + currentPlayer.getName() + " attack and deal " + degats + " damages to its opponent");
            }

            logger.info(currentPlayer.getName() + " cast a " + rituals.get(1) + " that restores 3 life point to " + currentPlayer.getName());

            String card = cardPool.get(currentCardNumber);
            cardPool.remove(currentCardNumber);
            discard.add(card);
        }*/

        /*String card = cardPool.get(currentCardNumber);
        cardPool.remove(currentCardNumber);
        discard.add(card);*/

    }

}