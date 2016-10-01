package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.util.*;

public class SpellmongerApp {
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);

    //Map<String, Integer> playersLifePoints = new HashMap<>(2);
    Map<String, Integer> playersCreature = new HashMap<>(2);
    Map<String, Integer> playersDegats = new HashMap<>(2);

    List<String> cardPool = new ArrayList<>(70);
    List<String> discard = new ArrayList<>();
    List<String> creatures = new ArrayList<>(3);
    List<String> rituals = new ArrayList<>(2);


    public SpellmongerApp() {

        /*playersLifePoints.put("Alice", 20);
        playersLifePoints.put("Bob", 20);*/
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

        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");

        List<Creature> player1Creatures = Creature.getPlayerCreatures(player1.getName());
        List<Creature> player2Creatures = Creature.getPlayerCreatures(player2.getName());

        boolean onePlayerDead = false;
        Player currentPlayer = player1;
        Player opponent = player2;

        int currentCardNumber = 0;
        int roundCounter = 1;
        String winner = null;

        while (!onePlayerDead) {
            logger.info("\n");
            logger.info("***** ROUND " + roundCounter);

            app.drawACard(currentPlayer, opponent, currentCardNumber);

            logger.info(opponent.getName() + " has " + opponent.getHp() + " life points and " + currentPlayer.getName() + " has " + currentPlayer.getHp() + " life points ");

            if (currentPlayer.getHp() <= 0) {
                winner = opponent.getName();
                onePlayerDead = true;
            }

            else if (opponent.getHp() <= 0) {
                winner = currentPlayer.getName();
                onePlayerDead = true;
            }

            if (currentPlayer.equals(player1)) {
                currentPlayer = player2;
                opponent = player1;
            } else {
                currentPlayer = player1;
                opponent = player2;
            }
            currentCardNumber++;
            roundCounter++;
        }

        logger.info("\n");
        logger.info("******************************");
        logger.info("THE WINNER IS " + winner + " !!!");
        logger.info("******************************");


    }

    public void drawACard(Player currentPlayer, Player opponent, int currentCardNumber) {


        if (creatures.get(0).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer.getName() + " draw a " + creatures.get(0));

            playersCreature.put(currentPlayer.getName(), playersCreature.get(currentPlayer.getName()).intValue() + 1);
            playersDegats.put(currentPlayer.getName(), playersDegats.get(currentPlayer.getName()).intValue() + 1);
            int nbCreatures = playersCreature.get(currentPlayer.getName()).intValue();
            int degats = playersDegats.get(currentPlayer.getName()).intValue();
            if (nbCreatures > 0)
            {
                opponent.setHp(opponent.getHp() - degats);
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer.getName() + " attack and deal " + degats + " damages to its opponent");

            }

            cardPool.remove(currentCardNumber);

        }

        else if (creatures.get(1).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer.getName() + " draw a " + creatures.get(1));
            playersCreature.put(currentPlayer.getName(), playersCreature.get(currentPlayer.getName()).intValue() + 1);
            playersDegats.put(currentPlayer.getName(), playersDegats.get(currentPlayer.getName()).intValue() + 2);
            int nbCreatures = playersCreature.get(currentPlayer.getName()).intValue() ;
            int degats = playersDegats.get(currentPlayer.getName()).intValue();
            if (nbCreatures > 0)
            {
                opponent.setHp(opponent.getHp() - degats);
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer.getName() + " attack and deal " + degats + " damages to its opponent");

            }

            cardPool.remove(currentCardNumber);
        }

        else if (creatures.get(2).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer.getName() + " draw a " + creatures.get(2));
            playersCreature.put(currentPlayer.getName(), playersCreature.get(currentPlayer.getName()).intValue() + 1);
            playersDegats.put(currentPlayer.getName(), playersDegats.get(currentPlayer.getName()).intValue() + 3);
            int nbCreatures = playersCreature.get(currentPlayer.getName()).intValue();
            int degats = playersDegats.get(currentPlayer.getName()).intValue();
            if (nbCreatures > 0)
            {
                opponent.setHp(opponent.getHp() - degats);
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer.getName() + " attack and deal " + degats + " damages to its opponent");

            }

            cardPool.remove(currentCardNumber);
        }

        else if (rituals.get(0).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer.getName() + " draw a " + rituals.get(0));
            int nbCreatures = playersCreature.get(currentPlayer.getName()).intValue();
            int degats = playersDegats.get(currentPlayer.getName()).intValue();
            if (nbCreatures > 0) {
                opponent.setHp(opponent.getHp() - degats - 3);
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer.getName() + " attack and deal " + degats + " damages to its opponent");
                logger.info(currentPlayer.getName() + " cast a " + rituals.get(0) + " that deals 3 damages to " + opponent.getName());
            }

            String card = cardPool.get(currentCardNumber);
            cardPool.remove(currentCardNumber);
            discard.add(card);

        }

        else if (rituals.get(1).equalsIgnoreCase(cardPool.get(currentCardNumber)))
        {
            logger.info(currentPlayer.getName() + " draw a " + rituals.get(1));
            int nbCreatures = playersCreature.get(currentPlayer.getName()).intValue();
            int degats = playersDegats.get(currentPlayer.getName()).intValue();

            if (currentPlayer.getHp() <= 17)
                currentPlayer.setHp(currentPlayer.getHp() + 3);
            else
                currentPlayer.setHp(20);

            if (nbCreatures > 0) {
                opponent.setHp(opponent.getHp() - degats);
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer.getName() + " attack and deal " + degats + " damages to its opponent");
            }

            logger.info(currentPlayer.getName() + " cast a " + rituals.get(1) + " that restores 3 life point to " + currentPlayer.getName());

            String card = cardPool.get(currentCardNumber);
            cardPool.remove(currentCardNumber);
            discard.add(card);
        }

    }

}