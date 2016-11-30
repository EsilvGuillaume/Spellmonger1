package edu.insightr.spellmonger;

//import gherkin.lexer.Pl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//import static edu.insightr.spellmonger.SpellmongerApp.app;
import static edu.insightr.spellmonger.MenuController.app;
import static org.junit.Assert.*;

/**
 * Created by Salem on 17/10/2016.
 */
public class SpellmongerAppTest {

    Player p;
    Player p2;
    Deck d;
    SpellmongerApp app;

    @Before
    public void init() {
        p = new Player("P1");
        d = new Deck(50, "P1");
        p2 = new Player("p2");
        app = new SpellmongerApp();
    }
    @Test
    public void main() throws Exception {

    }

    @Test
    public void drawFirstTwoCards() throws Exception {
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
        app.setCurrentPlayer(p);
        app.setOpponent(p2);
        app.drawFirstTwoCards();
        Card tmpCard = p.getHand().get(0);
        p.setEnergy(10);
        app.playCard(p.getHand().get(0), p, p2);
        Assert.assertTrue(!p.getHand().contains(tmpCard));

    }

    @Test
    public void verifyVaultOverclock() throws Exception {
        p.setEnergy(0);
        for(int i = 0; i<999; i++)
        {
            SpellmongerApp.verifyVaultOverclock(p);
        }
        Assert.assertTrue(p.getEnergy()>300|| p.getEnergy()<400);



    }
}