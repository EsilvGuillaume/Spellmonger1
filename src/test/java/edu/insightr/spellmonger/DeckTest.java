package edu.insightr.spellmonger;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Created by Guillaume on 05/10/2016.
 */
public class DeckTest {

    @Test
    public void createDeck() throws Exception{
        // init
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        List<Card> d1 = p1.getDeckInfo().getDeck();
        List<Card> d2 = p2.getDeckInfo().getDeck();

        // action


        //assertion
        assertNotEquals(d1, d2);
    }

    @Test
    public void getDeck() throws Exception{
        Player p1 = new Player("p1");
        p1.getDeckInfo().getDeck();
    }

}