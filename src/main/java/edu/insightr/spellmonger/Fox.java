package edu.insightr.spellmonger;

public class Fox extends Creature {

    public Fox(String name, String owner) {
        super(name + " [Fox]", owner);
        this.setHp(1);
        this.setAttack(1);
        this.setAlive(true);
        this.setCost(1);
    }

    public Fox(String name) {
        super(name + " [Fox]", "");
        this.setHp(1);
        this.setAttack(1);
        this.setAlive(true);
        this.setCost(1);
    }

}