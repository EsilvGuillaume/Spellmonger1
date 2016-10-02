package edu.insightr.spellmonger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Guillaume on 26/09/2016.
 */
public class Deck {

    private String deckOwner; // type Player?
    private int size;
    private List<Card> deck = new ArrayList<Card>(); //

    Deck(int size, String playerName){
        this.size = size;
        this.deck = createDeck(size, playerName);
        this.setDeckOwner(playerName);
    }

    public List<Card> createDeck(int size, String playerName){

        List<Card> possibleCards = new ArrayList<>();
        Bear bearTest = new Bear("bear", playerName);
        //System.out.println("owner : " +bearTest.getOwner());
        Eagle eagleTest = new Eagle("eagle", playerName);
        Wolf wolfTest = new Wolf("wolf", playerName);
        Curse curseTest = new Curse("curse");
        possibleCards.add(bearTest);
        possibleCards.add(wolfTest);
        possibleCards.add(eagleTest);
        possibleCards.add(curseTest);

        Random randomGenerator = new Random();

        for (int i = 0; i < size; i++) {
            int randIndex = randomGenerator.nextInt(possibleCards.size());
            (this.deck).add(possibleCards.get(randIndex));
            possibleCards.get(randIndex).setOwner(deckOwner);
        }
        return this.deck;
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
