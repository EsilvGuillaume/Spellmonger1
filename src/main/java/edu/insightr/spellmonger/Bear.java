package edu.insightr.spellmonger;

import java.util.ArrayList;
import java.util.List;


public class Bear extends Creature {

    //private String owner;

    public Bear(String name, String owner) {
        super(name + " [Bear]", owner);
        this.setHp(3);
        this.setAttack(3);
        this.setAlive(true);
        this.setCost(3);
    }

    public Bear(String name) {
        super(name + " [Bear]", "");
        this.setHp(3);
        this.setAttack(3);
        this.setAlive(true);
        this.setCost(3);
    }
}
