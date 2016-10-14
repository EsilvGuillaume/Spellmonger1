package edu.insightr.spellmonger;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;


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
        Fox foxTest = new Fox("fox", playerName);
        Blessing blessingTest = new Blessing("blessing");
        EnergyDrain energyDrainTest = new EnergyDrain("energyDrain");
        VaultOverclocking vaultTest = new VaultOverclocking("vault overclocking");

        int creatureNumber = (int) (size * 0.75);
        int rituolOrEnchantNumber = (int) (size * 0.25);

        int uniqueCreaNumber = (int) (creatureNumber / 4);
        int uniqueRituolNumber = (int) (rituolOrEnchantNumber / 4);

        int cardMissingNumber = size - (uniqueCreaNumber * 4 + uniqueRituolNumber * 4);

        addToList(possibleCards, energyDrainTest, uniqueRituolNumber);
        addToList(possibleCards, foxTest, uniqueCreaNumber);
        addToList(possibleCards, blessingTest, uniqueRituolNumber);
        addToList(possibleCards, curseTest, uniqueRituolNumber);
        addToList(possibleCards, wolfTest, uniqueCreaNumber);
        addToList(possibleCards, eagleTest, uniqueCreaNumber);
        addToList(possibleCards, bearTest, uniqueCreaNumber);
        addToList(possibleCards, vaultTest, uniqueRituolNumber);

        randomGenerator = new Random();
        int randIndex = randomGenerator.nextInt(uniqueCreaNumber * 4 + uniqueRituolNumber * 4);
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