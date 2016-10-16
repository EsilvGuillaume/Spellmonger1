package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnergyDrainTest {

    @Test
    public void playDrainPossible() throws Exception {
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        EnergyDrain ed = new EnergyDrain();

        p1.setEnergy(2);
        p2.setEnergy(2);

        ed.play(p1, p2);

        Assert.assertTrue(p1.getEnergy() == 4);
        Assert.assertTrue(p2.getEnergy() == 0);
    }

    @Test
    public void playDrainImpossible() throws Exception {
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        EnergyDrain ed = new EnergyDrain();

        p1.setEnergy(1);
        p2.setEnergy(1);

        ed.play(p1, p2);

        Assert.assertTrue(p1.getEnergy() == 1);
        Assert.assertTrue(p2.getEnergy() == 1);
    }

}