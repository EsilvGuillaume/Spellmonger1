package edu.insightr.spellmonger;


public class Wolf extends Creature {

    public Wolf(String name) {
        super(name + " [Wolf]", "");
        this.setHp(2);
        this.setAttack(2);
        this.setAlive(true);
        this.setCost(2);
    }

    public Wolf(String name, String owner) {
        super(name + " [Wolf]", owner);
        this.setHp(2);
        this.setAttack(2);
        this.setAlive(true);
        this.setCost(2);
    }

}