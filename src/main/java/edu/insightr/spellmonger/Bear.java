package edu.insightr.spellmonger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillaume on 01/10/2016.
 */
public class Bear extends Creature{

    public Bear(String name, int hp){
        super(name, hp);
        this.setHp(hp);
        this.setAttack(hp);
        this.setAlive(true);
    }

}
