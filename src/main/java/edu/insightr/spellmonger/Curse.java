package edu.insightr.spellmonger;

/**
 * Created by Guillaume on 02/10/2016.
 */
public class Curse extends Rituol {

    private String effectDescription = "Curse - Deals 3 damage to your opponent";
    private boolean bonus;

    public Curse(String name) {
        super(name);
        bonus = false;
    }

    public Curse() {
        bonus = false;
    }

    public void play(Player opponent) {
        //Problem
        if (Creature.getPlayerCreaOnBoard(opponent).isEmpty()) {
            opponent.setHp(opponent.getHp() - 3);
            System.out.println(this.getName() + " used, " + opponent.getName() + " loses 3 health points !");
        } else {
            System.out.println(this.getName() + " does nothing because opponent has no creature on board.");
        }
    }

    @Override
    public void play(Player target, Player emetter) {

    }

    public String getEffectDescription() {
        return effectDescription;
    }

    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

}
