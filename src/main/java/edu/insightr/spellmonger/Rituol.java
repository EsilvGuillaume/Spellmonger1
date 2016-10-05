package edu.insightr.spellmonger;

/**
 * Created by Guillaume on 02/10/2016.
 */
public abstract class Rituol extends Card{

    String effectDescription = "";
    private boolean bonus = false; // false will indicate it is a malus

    public Rituol(String name){
        super(name);
    }

    public Rituol(){
    }

    public abstract void play(Player target);

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }
}
