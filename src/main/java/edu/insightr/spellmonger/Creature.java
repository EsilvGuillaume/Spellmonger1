package edu.insightr.spellmonger;

/**
 * Created by Guillaume on 24/09/2016.
 */
public interface Creature {

    boolean alive = true;
    int damage = 0;

    public boolean isAlive();
    public void setAlive(boolean alive);
    public int getDamage();
    public void setDamage(int damage);

}
