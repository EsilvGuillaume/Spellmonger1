package edu.insightr.spellmonger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 01/10/2016.
 */
public class Bear extends Creature{

    private String owner;

    public Bear(String name, String owner){
        super(name+" [Bear]", owner);
        this.setHp(3);
        this.setAttack(3);
        this.setAlive(true);
    }

    public Bear(String name){
        super(name+" [Bear]", "");
        this.setHp(3);
        this.setAttack(3);
        this.setAlive(true);
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
