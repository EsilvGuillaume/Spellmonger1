package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*   Having 3 different creatures: eagle, wolf and bear
     Eagle deals 1 damage
     Wolf deals 2 damages
     Bear deals 3 damages

     Having 2 different rituals : curse and blessing
     Curse deals 3 damages
     Blessing restore 3 life points

     add a discard to the game*/


public class SpellmongerApp {
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);

    Map<String, Integer> playersLifePoints = new HashMap<>(2);
    Map<String, Integer> playersCreature = new HashMap<>(2);
    Map<String, int[]> playersCreatures = new HashMap<>(2);
    List<String> cardPool = new ArrayList<>(70);

    int[] creaArray = {0,0,0};
    int eagleDamage = 0;
    int wolfDamage = 0;
    int bearDamage = 0;
    int damageFromCreatures = 0;

    public SpellmongerApp() {
        playersLifePoints.put("Alice", 20);
        playersLifePoints.put("Bob", 20);
        playersCreature.put("Alice", 0);
        playersCreature.put("Bob", 0);
        playersCreatures.put("Alice", null);
        playersCreatures.put("Bob", null);

        int ritualMod = 3;

        for (int i = 0; i < 70; i++) {
            if (i % ritualMod == 0) {
                cardPool.add("Ritual");
            }
            if (i % ritualMod != 0) {
                cardPool.add("Creature");
            }

            if (ritualMod == 3) {
                ritualMod = 2;
            } else {
                ritualMod = 3;
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
            if (app.playersLifePoints.get(opponent).intValue() <= 0) {
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

        if ("Creature".equalsIgnoreCase(cardPool.get(currentCardNumber))) {
            logger.info(currentPlayer + " draw a Creature");
            drawACreature(currentPlayer);
            playersCreature.put(currentPlayer, playersCreature.get(currentPlayer).intValue() + 1);

            logger.info("Eagle number = "+playersCreatures.get(currentPlayer)[0]);
            logger.info("Wolf number = "+playersCreatures.get(currentPlayer)[1]);
            logger.info("Bear number = "+playersCreatures.get(currentPlayer)[2]);
            eagleDamage = playersCreatures.get(currentPlayer)[0];
            wolfDamage = playersCreatures.get(currentPlayer)[1]*2;
            bearDamage = playersCreatures.get(currentPlayer)[2]*3;
            damageFromCreatures = eagleDamage+wolfDamage+bearDamage;

            int nbCreatures = playersCreature.get(currentPlayer).intValue();
            if (nbCreatures > 0) {
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - damageFromCreatures));
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer + " attack and deal " + damageFromCreatures + " damages to its opponent");
            }
        }
        if ("Ritual".equalsIgnoreCase(cardPool.get(currentCardNumber))) {
            logger.info(currentPlayer + " draw a Ritual");
            int nbCreatures = playersCreature.get(currentPlayer).intValue();
            if (nbCreatures > 0) {
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - damageFromCreatures - 3));
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer + " attack and deal " + damageFromCreatures + " damages to its opponent");
            }
            logger.info(currentPlayer + " cast a ritual that deals 3 damages to " + opponent);
        }
    }

    public void drawACreature(String player){

            Random rand = new Random();
            int randomNum = rand.nextInt((3 - 1) + 1) + 1;

            creaArray = playersCreatures.get(player);
            if (creaArray == null){
                creaArray = new int[] { 0, 0, 0 };
            }

            if (randomNum == 1) {
                creaArray[0]++;
            }
            else if (randomNum == 2){
                creaArray[1]++;
            }
            else{
                creaArray[2]++;
            }

            playersCreatures.put(player, creaArray);

    }

}
