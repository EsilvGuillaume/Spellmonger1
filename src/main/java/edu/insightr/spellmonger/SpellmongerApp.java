package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.util.*;

public class SpellmongerApp {
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);

    Map<String, Integer> playersLifePoints = new HashMap<>(2);
    Map<String, Integer> playersCreature = new HashMap<>(2);
    Map<String, Integer> playersDegats = new HashMap<>(2);
    List<String> cardPool = new ArrayList<>(70);
    List<String> discard = new ArrayList<>();
    List<String> creatures = new ArrayList<>(3);
    List<String> rituals = new ArrayList<>(2);


    public SpellmongerApp() {
        playersLifePoints.put("Alice", 20);
        playersLifePoints.put("Bob", 20);
        playersCreature.put("Alice", 0);
        playersCreature.put("Bob", 0);
        playersDegats.put("Alice", 0);
        playersDegats.put("Bob", 0);

        creatures.add("Eagle");
        creatures.add("Wolf");
        creatures.add("Bear");

        rituals.add("Curse");
        rituals.add("Blessing");

        for(int i = 0; i < 70; i++)
        {
            int max = creatures.size()+ rituals.size();
            Random rand = new Random();
            int nombreAleatoire = rand.nextInt(max);

            if(nombreAleatoire < creatures.size())
            {
                cardPool.add(creatures.get(nombreAleatoire));
            }
            else if(nombreAleatoire >= creatures.size())
            {
                cardPool.add(rituals.get(nombreAleatoire - creatures.size()));
            }

        }
    }

    public static void main(String[] args) {
        SpellmongerApp app = new SpellmongerApp();

        boolean onePlayerDead = false;
        String currentPlayer = "Alice";
        String opponent = "Bob";
        int currentCardNumber = 0;
        int roundCounter = 1;
        String winner = null;

        while (!onePlayerDead) {
            logger.info("\n");
            logger.info("***** ROUND " + roundCounter);

            app.drawACard(currentPlayer, opponent, currentCardNumber);

            logger.info(opponent + " has " + app.playersLifePoints.get(opponent).intValue() + " life points and " + currentPlayer + " has " + app.playersLifePoints.get(currentPlayer).intValue() + " life points ");

            if (app.playersLifePoints.get(currentPlayer).intValue() <= 0) {
                winner = opponent;
                onePlayerDead = true;
            }

            else if (app.playersLifePoints.get(opponent).intValue() <= 0) {
                winner = currentPlayer;
                onePlayerDead = true;
                }

            if ("Alice".equalsIgnoreCase(currentPlayer)) {
                currentPlayer = "Bob";
                opponent = "Alice";
            } else {
                currentPlayer = "Alice";
                opponent = "Bob";
            }
            currentCardNumber++;
            roundCounter++;
        }

        logger.info("\n");
        logger.info("******************************");
        logger.info("THE WINNER IS " + winner + " !!!");
        logger.info("******************************");


    }

    public void drawACard(String currentPlayer, String opponent, int currentCardNumber) {


        if (creatures.get(0).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer + " draw a " + creatures.get(0));
            playersCreature.put(currentPlayer, playersCreature.get(currentPlayer).intValue() + 1);
            playersDegats.put(currentPlayer, playersDegats.get(currentPlayer).intValue() + 1);
            int nbCreatures = playersCreature.get(currentPlayer).intValue();
            int degats = playersDegats.get(currentPlayer).intValue();
            if (nbCreatures > 0)
            {
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - degats));
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer + " attack and deal " + degats + " damages to its opponent");

            }

            cardPool.remove(currentCardNumber);

        }

        else if (creatures.get(1).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer + " draw a " + creatures.get(1));
            playersCreature.put(currentPlayer, playersCreature.get(currentPlayer).intValue() + 1);
            playersDegats.put(currentPlayer, playersDegats.get(currentPlayer).intValue() + 2);
            int nbCreatures = playersCreature.get(currentPlayer).intValue() ;
            int degats = playersDegats.get(currentPlayer).intValue();
            if (nbCreatures > 0)
            {
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - degats));
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer + " attack and deal " + degats + " damages to its opponent");

            }

            cardPool.remove(currentCardNumber);
        }

        else if (creatures.get(2).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer + " draw a " + creatures.get(2));
            playersCreature.put(currentPlayer, playersCreature.get(currentPlayer).intValue() + 1);
            playersDegats.put(currentPlayer, playersDegats.get(currentPlayer).intValue() + 3);
            int nbCreatures = playersCreature.get(currentPlayer).intValue();
            int degats = playersDegats.get(currentPlayer).intValue();
            if (nbCreatures > 0)
            {
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - degats));
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer + " attack and deal " + degats + " damages to its opponent");

            }

            cardPool.remove(currentCardNumber);
        }

        else if (rituals.get(0).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer + " draw a " + rituals.get(0));
            int nbCreatures = playersCreature.get(currentPlayer).intValue();
            int degats = playersDegats.get(currentPlayer).intValue();
            if (nbCreatures > 0) {
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - degats - 3));
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer + " attack and deal " + degats + " damages to its opponent");
                logger.info(currentPlayer + " cast a " + rituals.get(0) + " that deals 3 damages to " + opponent);
            }

            String card = cardPool.get(currentCardNumber);
            cardPool.remove(currentCardNumber);
            discard.add(card);

        }

        else if (rituals.get(1).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer + " draw a " + rituals.get(1));
            int nbCreatures = playersCreature.get(currentPlayer).intValue();
            int degats = playersDegats.get(currentPlayer).intValue();

            if (playersLifePoints.get(currentPlayer).intValue() <= 17)
                playersLifePoints.put(currentPlayer, (playersLifePoints.get(currentPlayer).intValue() + 3));
            else
                playersLifePoints.put(currentPlayer, 20);

            if (nbCreatures > 0) {
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - degats));
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer + " attack and deal " + degats + " damages to its opponent");
            }

            logger.info(currentPlayer + " cast a " + rituals.get(1) + " that restores 3 life point to " + currentPlayer);

            String card = cardPool.get(currentCardNumber);
            cardPool.remove(currentCardNumber);
            discard.add(card);
        }

    }

}
