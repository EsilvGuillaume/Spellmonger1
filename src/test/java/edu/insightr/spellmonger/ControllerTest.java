package edu.insightr.spellmonger;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Salem on 25/10/2016.
 */
public class ControllerTest extends Controller{
    Player p;

    @Before
    public void init() {
        p = new Player("p1");
    }

    }

    @Test
    public void main() throws Exception {

    }

/*    @Test
    public void initialize() throws Exception {

    }*/



  /*  @Test
    public void playNoCard() throws Exception {

    }

    @Test
    public void turnEnded() throws Exception {

    }

    @Test
    public void refreshDiscard() throws Exception {

    }

    @Test
    public void goPlayCard() throws Exception {

    }
*/
    @Test
    public void removecardhand() throws Exception {


        List<Card> hand = new ArrayList<>();
        hand.add(new Bear("p1"));
        hand.add(new Wolf("p1"));
        p.setHand(hand);

        p.getHand().remove(1);
        Assert.assertEquals(p.getHand().size(), 1);
    }


    @Test
    public void checkcardhand() throws Exception {
        List<Card> hand = new ArrayList<>();
        Bear b = new Bear("p1");
        hand.add(b);
        hand.add(new Wolf("p1"));
        p.setHand(hand);

        Assert.assertEquals(p.getHand().get(0), b);


    }



    @Test
    public void displayBoard() throws Exception {

    }

}