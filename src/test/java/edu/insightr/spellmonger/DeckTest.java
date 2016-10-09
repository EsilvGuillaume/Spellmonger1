package edu.insightr.spellmonger;

/*import java.util.ArrayList;
import java.util.List;
import java.util.Collections;*/

/**
 * Created by Guillaume on 26/09/2016.
 */

/*
public class Deck {

    private String deckOwner; // type Player?
    private int size;
    private List<Card> deck = new ArrayList<Card>(); //

    Deck(int size, String playerName){
        this.size = size;
        this.deck = createDeck(size, playerName);
        this.setDeckOwner(playerName);
    }

    public void addToList(List<Card> list, Card card, int dupli){
        for (int i = 0; i < dupli; i++) {
            card.setOwner(deckOwner);
            list.add(card);
        }
    }

    public List<Card> createDeck(int size, String playerName){

        List<Card> possibleCards = new ArrayList<>();
        Bear bearTest = new Bear("bear", playerName);
        Eagle eagleTest = new Eagle("eagle", playerName);
        Wolf wolfTest = new Wolf("wolf", playerName);
        Curse curseTest = new Curse("curse");
        Blessing blessingTest = new Blessing("blessing");
        EnergyDrain energyDrainTest = new EnergyDrain("energyDrain");
        addToList(possibleCards, energyDrainTest, 2);
        addToList(possibleCards, blessingTest, 2);
        addToList(possibleCards, curseTest, 2);
        addToList(possibleCards, wolfTest, 8);
        addToList(possibleCards, eagleTest, 8);
        addToList(possibleCards, bearTest, 8);

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
}*/