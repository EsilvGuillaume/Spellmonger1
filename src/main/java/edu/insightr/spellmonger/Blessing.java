package edu.insightr.spellmonger;

/**
 * Created by Guillaume on 02/10/2016.
 */
public class Blessing extends Rituol {

    private String effectDescription = "Blessing - Restores 3 hp to you";
    private boolean bonus;

    public Blessing(String name) {
        super(name);
        bonus = true;
    }

    public Blessing() {
        bonus = true;
    }

    public void play(Player currentPlayer) {
        int lifeGain = 0;
        if (currentPlayer.getHp() <= 17) {
            currentPlayer.setHp(currentPlayer.getHp() + 3);
            lifeGain = 3;
        } else {
            lifeGain = 20 - currentPlayer.getHp();
            currentPlayer.setHp(20);
        }
        System.out.println(this.getName() + " used, " + currentPlayer.getName() + " has regenerated " + lifeGain + " hp !");
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
