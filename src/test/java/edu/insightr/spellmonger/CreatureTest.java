package edu.insightr.spellmonger;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
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
        // Class Creature is Abstract, so How can I do it ?

    }

    @Test
    public void findBestTarget() throws Exception {

        Player p = new Player("Player");
        List<Creature> list = new ArrayList<Creature>();
        Creature bestCrea = null;
        Wolf w = new Wolf("Player");
        Bear b = new Bear("Player");
        Eagle e = new Eagle("Player");
        e.setHp(30);
        list.add(w);
        list.add(b);
        list.add(e);

        Creature.getPlayerCreaOnBoard(p);
        bestCrea = Creature.findBestTarget(1, 20, p);
        System.out.print("loooooooooool");
        System.out.print(bestCrea.toString());
        //Assert.assertEquals(bestCrea,b );

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