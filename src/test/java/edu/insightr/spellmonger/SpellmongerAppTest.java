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
    public void drawACard() throws Exception {

    }

    @Test
    public void displayCardInHand() throws Exception {

    }


    @Test
    public void playCard() throws Exception {
        Player p = new Player("p1");
        Player p2 = new Player("p2");

        Bear b = new Bear("name", "p1");
        Assert.assertTrue(!SpellmongerApp.playCard(b, p, p2));



    }

    @Test
    public void verifyVaultOverclock() throws Exception {
        Player p = new Player("P1");
        p.setEnergy(0);
        for(int i = 0; i<999; i++)
        {
            SpellmongerApp.verifyVaultOverclock(p);
        }
        Assert.assertTrue(p.getEnergy()>300|| p.getEnergy()<400);



    }
}