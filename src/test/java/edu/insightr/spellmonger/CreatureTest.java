package edu.insightr.spellmonger;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CreatureTest {
    @Test
    public void getPlayerCreatures() throws Exception {
        Player player1 = new Player("p1");
        player1.setNumberOfCreaOnBoard(4);
        Assert.assertEquals(player1.getNumberOfCreaOnBoard(), 4);
    }

    @Test
    public void attackOpponent() throws Exception {
        Player player = new Player("Player");
        player.setHp(20);
        player.setHp(player.getHp() - 5);
        Assert.assertEquals(player.getHp(), 15);
    }

    @Test
    public void attackCreature() throws Exception {

    }

    @Test
    public void findBestTarget() throws Exception {

    }

    @Test
    public void attack() throws Exception {

    }

    @Test
    public void getHp() throws Exception {

    }

    @Test
    public void setHp() throws Exception {

    }

    @Test
    public void getAttack() throws Exception {

    }

    @Test
    public void setAttack() throws Exception {

    }

    @Test
    public void isAlive() throws Exception {

    }

    @Test
    public void setAlive() throws Exception {

    }

}