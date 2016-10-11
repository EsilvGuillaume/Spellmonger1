package edu.insightr.spellmonger;

/**
 * Created by Guillaume on 02/10/2016.
 */
public class Wolf extends Creature {

    public Wolf(String name) {
        super(name + " [Wolf]", "");
        this.setHp(2);
        this.setAttack(2);
        this.setAlive(true);
    }

    public Wolf(String name, String owner) {
        super(name + " [Wolf]", owner);
        this.setHp(2);
        this.setAttack(2);
        this.setAlive(true);
    }

}