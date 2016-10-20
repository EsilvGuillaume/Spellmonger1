package edu.insightr.spellmonger;


public class Eagle extends Creature {

    public Eagle(String name) {
        super(name, "");
        this.setHp(1);
        this.setAttack(1);
        this.setAlive(true);
        this.setCost(1);
    }

    public Eagle(String name, String owner) {
        super(name, owner);
        this.setHp(1);
        this.setAttack(1);
        this.setAlive(true);
        this.setCost(1);
    }

}
