package edu.insightr.spellmonger;

/**
 * Created by Guillaume on 24/09/2016.
 */
public class Eagle implements Creature{

    private boolean alive;
    private int damage;


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Eagle(){
        this.alive = true;
        this.damage = 1;
    }

}