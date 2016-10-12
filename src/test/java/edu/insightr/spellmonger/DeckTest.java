package edu.insightr.spellmonger;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


/**
 * Created by hugoalix on 12/10/2016.
 */

public class DeckTest {

    @Test
    public void checkDeckSize() throws Exception {
        Random randomGenerator = new Random();
        int size = randomGenerator.nextInt(100);
        Deck deck = new Deck(size, "Owner");
        Assert.assertEquals(deck.getSize(), size);
    }

    @Test
    public void changeDeckOwner() throws Exception {
        Deck d1 = new Deck(20, "owner1");
        d1.setDeckOwner("owner2");
        Assert.assertEquals("owner2", d1.getDeckOwner());
    }

    @Test
    public void createDeck() throws Exception {
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        List<Card> d1 = p1.getDeckInfo().getDeck();
        List<Card> d2 = p2.getDeckInfo().getDeck();
        assertNotEquals(d1, d2);
    }

    @Test
    public void getDeck() throws Exception {
        Player p1 = new Player("p1");
        p1.getDeckInfo().getDeck();
    }

}

