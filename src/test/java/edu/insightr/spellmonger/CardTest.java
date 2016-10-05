package edu.insightr.spellmonger;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Guillaume on 05/10/2016.
 */
public class CardTest {

    @Test
    public void declareCard() throws Exception {
        Card card = new Bear("toto", "paul");
        assertEquals("paul", card.getOwner());

    }
}