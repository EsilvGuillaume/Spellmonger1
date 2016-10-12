package edu.insightr.spellmonger;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by hugoalix on 12/10/2016.
 */
public class CardTest {

    @Test
    public void declareCard() throws Exception {
        Card card = new Bear("toto", "paul");
        assertEquals("paul", card.getOwner());

    }
}

