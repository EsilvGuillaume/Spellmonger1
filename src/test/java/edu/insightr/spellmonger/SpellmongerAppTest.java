package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Salem on 17/10/2016.
 */
public class SpellmongerAppTest {
    @Test
    public void main() throws Exception {

    }

    @Test
    public void drawFirstTwoCards() throws Exception {
        Player p = new Player("P1");
        Deck d = new Deck(50, "P1");
        p.setDeckInfo(d);

        p.getDeckInfo().getDeck().get(1).draw(p);
        p.getDeckInfo().getDeck().get(1).draw(p);

        Assert.assertEquals(2,p.getHand().size());
    }

    @Test
    public void endOfTurn() throws Exception {

    }

    @Test
    public void drawACard() throws Exception {

    }

    @Test
    public void displayCardInHand() throws Exception {

    }

    @Test
    public void askToPlay() throws Exception {

    }

    @Test
    public void playCard() throws Exception {

    }

    @Test
    public void verifyVaultOverclock() throws Exception {

    }
}