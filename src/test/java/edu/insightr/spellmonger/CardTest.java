package edu.insightr.spellmonger;

import org.junit.Test;

import static org.junit.Assert.*;


public class CardTest {

    @Test
    public void declareCard() throws Exception {
        Card card = new Bear("toto", "paul");
        assertEquals("paul", card.getOwner());

    }
}