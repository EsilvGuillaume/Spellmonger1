package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.util.*;

public class SpellmongerApp {
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);

    Map<String, Integer> playersLifePoints = new HashMap<>(2);
    Map<String, Integer> playersCreature = new HashMap<>(2);
    Map<String, Integer> playersDegats = new HashMap<>(2);
    Map<String, Integer> playerEnergyPoint = new HashMap<>(2);
    //List<String> cardPool = new ArrayList<>(70);
    Deck deck_j1 = new Deck(70);
    Deck deck_j2 = new Deck(70);
    //List<String> discard = new ArrayList<>(70);
    Discard discard = new Discard(70);
    //List<String> creatures = new ArrayList<>(3);
    //List<String> rituals = new ArrayList<>(2);
    List<Creature> creatures = new ArrayList<Creature>();
    List<Rituel> rituels = new ArrayList<Rituel>();

    public SpellmongerApp() {
        String alice = "Alice";
        playersLifePoints.put(alice, 20);
        String bob = "Bob";
        playersLifePoints.put(bob, 20);
        playersCreature.put(alice, 0);
        playersCreature.put(bob, 0);
        playersDegats.put(alice, 0);
        playersDegats.put(bob, 0);
        playerEnergyPoint.put(alice, 0);
        playerEnergyPoint.put(bob, 0);

        Creature eagle = new Creature("Eagle", 1, "Flying");
        Creature wolf = new Creature("Wolf", 1, "Beast");
        Creature bear = new Creature("Bear", 1, "Beast");
        creatures.add(eagle);
        creatures.add(wolf);
        creatures.add(bear);
        //creatures.add("Eagle");
        //creatures.add("Wolf");
        //creatures.add("Bear");

        Rituel curse = new Rituel("Curse", 0, 3);
        Rituel blessing = new Rituel("Blessing", 3, 0);
        Rituel energy_drain = new Rituel("Energy_Drain", 2, 2);
        rituels.add(curse);
        rituels.add(blessing);
        rituels.add(energy_drain);
        //rituals.add("Curse");
        //rituals.add("Blessing");

        /*for(int i = 0; i < 70; i++)
        {
            int max = creatures.size()+ rituels.size();
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

        }*/

        deck_j1.constituionDeck(creatures,rituels);
        deck_j2.constituionDeck(creatures,rituels);
    }

    public void drawACard(String currentPlayer, String opponent, int currentCardNumber, Deck currentPlayerDeck) {


        /*if (creatures.get(0).equalsIgnoreCase(cardPool.get(currentCardNumber)))
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
            //discard.add("vide");
            discard.ajouter_A_La_Discard();

        }*/
        if (creatures.get(0).getNom().equalsIgnoreCase(currentPlayerDeck.cartePioche(currentCardNumber).getNom()))
        {
            logger.info(currentPlayer + " draw a " + creatures.get(0).getNom());
            playersCreature.put(currentPlayer, playersCreature.get(currentPlayer).intValue() + 1);
            playersDegats.put(currentPlayer, playersDegats.get(currentPlayer).intValue() + 1);
            int nbCreatures = playersCreature.get(currentPlayer).intValue();
            int degats = playersDegats.get(currentPlayer).intValue();
            if (nbCreatures > 0)
            {
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - degats));
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer + " attack and deal " + degats + " damages to its opponent");

            }

            playerEnergyPoint.put(currentPlayer, playerEnergyPoint.get(currentPlayer).intValue() + 1);
            playerEnergyPoint.put(opponent, playerEnergyPoint.get(opponent).intValue() + 1);
            currentPlayerDeck.removeFromDeck(currentPlayerDeck.cartePioche(currentCardNumber));
            discard.ajouter_A_La_Discard(null);

        }

        else if (creatures.get(1).getNom().equalsIgnoreCase(currentPlayerDeck.cartePioche(currentCardNumber).getNom()))
        {
            logger.info(currentPlayer + " draw a " + creatures.get(1).getNom());
            playersCreature.put(currentPlayer, playersCreature.get(currentPlayer).intValue() + 1);
            playersDegats.put(currentPlayer, playersDegats.get(currentPlayer).intValue() + 2);
            int nbCreatures = playersCreature.get(currentPlayer).intValue() ;
            int degats = playersDegats.get(currentPlayer).intValue();
            if (nbCreatures > 0)
            {
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - degats));
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer + " attack and deal " + degats + " damages to its opponent");

            }
            playerEnergyPoint.put(currentPlayer, playerEnergyPoint.get(currentPlayer).intValue() + 1);
            playerEnergyPoint.put(opponent, playerEnergyPoint.get(opponent).intValue() + 1);
            currentPlayerDeck.removeFromDeck(currentPlayerDeck.cartePioche(currentCardNumber));
            discard.ajouter_A_La_Discard(null);
        }

        else if (creatures.get(2).getNom().equalsIgnoreCase(currentPlayerDeck.cartePioche(currentCardNumber).getNom()))
        {
            logger.info(currentPlayer + " draw a " + creatures.get(2).getNom());
            playersCreature.put(currentPlayer, playersCreature.get(currentPlayer).intValue() + 1);
            playersDegats.put(currentPlayer, playersDegats.get(currentPlayer).intValue() + 3);
            int nbCreatures = playersCreature.get(currentPlayer).intValue();
            int degats = playersDegats.get(currentPlayer).intValue();
            if (nbCreatures > 0)
            {
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - degats));
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer + " attack and deal " + degats + " damages to its opponent");

            }
            playerEnergyPoint.put(currentPlayer, playerEnergyPoint.get(currentPlayer).intValue() + 1);
            playerEnergyPoint.put(opponent, playerEnergyPoint.get(opponent).intValue() + 1);
            currentPlayerDeck.removeFromDeck(currentPlayerDeck.cartePioche(currentCardNumber));
            discard.ajouter_A_La_Discard(null);
        }

        else if (rituels.get(0).getNom().equalsIgnoreCase(currentPlayerDeck.cartePioche(currentCardNumber).getNom()))
        {
            logger.info(currentPlayer + " draw a " + rituels.get(0).getNom());
            int nbCreatures = playersCreature.get(currentPlayer).intValue();
            int degats = playersDegats.get(currentPlayer).intValue();
            if (nbCreatures > 0) {
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - degats - 3));
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer + " attack and deal " + degats + " damages to its opponent");
                logger.info(currentPlayer + " cast a " + rituels.get(0).getNom() + " that deals 3 damages to " + opponent);
            }
            playerEnergyPoint.put(currentPlayer, playerEnergyPoint.get(currentPlayer).intValue() + 1);
            playerEnergyPoint.put(opponent, playerEnergyPoint.get(opponent).intValue() + 1);
            Carte card = currentPlayerDeck.cartePioche(currentCardNumber);
            currentPlayerDeck.removeFromDeck(currentPlayerDeck.cartePioche(currentCardNumber));
            discard.ajouter_A_La_Discard(card);

        }

        else if (rituels.get(1).getNom().equalsIgnoreCase(currentPlayerDeck.cartePioche(currentCardNumber).getNom()))
        {
            logger.info(currentPlayer + " draw a " + rituels.get(1).getNom());
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

            logger.info(currentPlayer + " cast a " + rituels.get(1).getNom() + " that restores 3 life point to " + currentPlayer);
            playerEnergyPoint.put(currentPlayer, playerEnergyPoint.get(currentPlayer).intValue() + 1);
            playerEnergyPoint.put(opponent, playerEnergyPoint.get(opponent).intValue() + 1);
            Carte card = currentPlayerDeck.cartePioche(currentCardNumber);
            currentPlayerDeck.removeFromDeck(currentPlayerDeck.cartePioche(currentCardNumber));
            discard.ajouter_A_La_Discard(card);
        }

        else if (rituels.get(2).getNom().equalsIgnoreCase(currentPlayerDeck.cartePioche(currentCardNumber).getNom()))
        {
            logger.info(currentPlayer + " draw a " + rituels.get(2).getNom());
            int nbCreatures = playersCreature.get(currentPlayer).intValue();
            int degats = playersDegats.get(currentPlayer).intValue();

            if (playersLifePoints.get(currentPlayer).intValue() <= 18)
            {
                playersLifePoints.put(currentPlayer, (playersLifePoints.get(currentPlayer).intValue() + 2));
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - 2));
            }
            else
            {
                playersLifePoints.put(currentPlayer, 20);
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - 2));
            }

            if (nbCreatures > 0) {
                playersLifePoints.put(opponent, (playersLifePoints.get(opponent).intValue() - degats));
                logger.info("The " + nbCreatures + " creatures of " + currentPlayer + " attack and deal " + degats + " damages to its opponent");
            }

            logger.info(currentPlayer + " cast a " + rituels.get(2).getNom() + " that drains 2 life point to " + opponent + " and restores 2 life point to " + currentPlayer);
            playerEnergyPoint.put(currentPlayer, playerEnergyPoint.get(currentPlayer).intValue() + 1);
            playerEnergyPoint.put(opponent, playerEnergyPoint.get(opponent).intValue() + 1);
            Carte card = currentPlayerDeck.cartePioche(currentCardNumber);
            currentPlayerDeck.removeFromDeck(currentPlayerDeck.cartePioche(currentCardNumber));
            discard.ajouter_A_La_Discard(card);
        }

        /*else if (creatures.get(1).equalsIgnoreCase(cardPool.get(currentCardNumber)))
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
            playerEnergyPoint.put(currentPlayer, playerEnergyPoint.get(currentPlayer).intValue() + 1);
            cardPool.remove(currentCardNumber);
            discard.add("vide");
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
            playerEnergyPoint.put(currentPlayer, playerEnergyPoint.get(currentPlayer).intValue() + 1);
            cardPool.remove(currentCardNumber);
            discard.add("vide");
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
            playerEnergyPoint.put(currentPlayer, playerEnergyPoint.get(currentPlayer).intValue() + 1);
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
            playerEnergyPoint.put(currentPlayer, playerEnergyPoint.get(currentPlayer).intValue() + 1);
            String card = cardPool.get(currentCardNumber);
            cardPool.remove(currentCardNumber);
            discard.add(card);
        }*/

    }

}
