package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnergyDrainTest {
    Player p1;
    Player p2;


    @Before
    public void init(){
        p1 = new Player("p1");
        p2 = new Player("p2");
    }

    @Test
    public void playDrainPossible() throws Exception {
        EnergyDrain ed = new EnergyDrain();

        p1.setEnergy(2);
        p2.setEnergy(2);

        ed.play(p1, p2);

        Assert.assertEquals(4, p1.getEnergy());
        Assert.assertEquals(0,p2.getEnergy());
    }

    @Test
    public void playDrainImpossible() throws Exception {
        EnergyDrain ed = new EnergyDrain();

        p1.setEnergy(1);
        p2.setEnergy(1);

        ed.play(p1, p2);

        Assert.assertEquals(1,p1.getEnergy());
        Assert.assertEquals(1,p2.getEnergy());
    }

}