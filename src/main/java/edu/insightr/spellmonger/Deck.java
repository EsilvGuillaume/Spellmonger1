package edu.insightr.spellmonger;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Guillaume on 26/09/2016.
 */
public class Deck {

    Random randomGenerator;

    private String deckOwner; // type Player?
    private int size;
    private List<Card> deck = new ArrayList<Card>(); //

    Deck(int size, String playerName) {
        this.size = size;
        this.deck = createDeck(size, playerName);
        this.setDeckOwner(playerName);
    }

    public void addToList(List<Card> list, Card card, int dupli) {
        for (int i = 0; i < dupli; i++) {
            card.setOwner(deckOwner);
            list.add(card);
        }
    }

    public List<Card> createDeck(int size, String playerName) {

        List<Card> possibleCards = new ArrayList<>();
        Bear bearTest = new Bear("bear", playerName);
        Eagle eagleTest = new Eagle("eagle", playerName);
        Wolf wolfTest = new Wolf("wolf", playerName);
        Curse curseTest = new Curse("curse");
        Blessing blessingTest = new Blessing("blessing");
        EnergyDrain energyDrainTest = new EnergyDrain("energyDrain");

        int creatureNumber = (int) (size * 0.75);
        int rituolNumber = (int) (size * 0.25);

        int uniqueCreaNumber = (int) (creatureNumber / 3);
        int uniqueRituolNumber = (int) (rituolNumber / 3);

        int cardMissingNumber = size - (uniqueCreaNumber * 3 + uniqueRituolNumber * 3);

        addToList(possibleCards, energyDrainTest, uniqueRituolNumber);
        addToList(possibleCards, blessingTest, uniqueRituolNumber);
        addToList(possibleCards, curseTest, uniqueRituolNumber);
        addToList(possibleCards, wolfTest, uniqueCreaNumber);
        addToList(possibleCards, eagleTest, uniqueCreaNumber);
        addToList(possibleCards, bearTest, uniqueCreaNumber);

        randomGenerator = new Random();
        int randIndex = randomGenerator.nextInt(uniqueCreaNumber * 3 + uniqueRituolNumber * 3);
        addToList(possibleCards, possibleCards.get(randIndex), cardMissingNumber);

        Collections.shuffle(possibleCards);

        return possibleCards;
    }

    public int getSize() {
        return size;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public String getDeckOwner() {
        return deckOwner;
    }

    public void setDeckOwner(String deckOwner) {
        this.deckOwner = deckOwner;
    }
}