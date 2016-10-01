package edu.insightr.spellmonger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 26/09/2016.
 */
public class Deck {

    private String deckOwner; // type Player?
    private int size;
    private List<Card> deck = new ArrayList<Card>(); //

    Deck(int size, String playerName){
        this.size = size;
        this.deck = createDeck(size);
        this.setDeckOwner(playerName);
    }

    public List<Card> createDeck(int size){
        Bear bearTest = new Bear("bear", 3);

        for (int i = 0; i < size; i++) {
            (this.deck).add(bearTest); // .add(card) // null for testing
            bearTest.setOwner(deckOwner);
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
