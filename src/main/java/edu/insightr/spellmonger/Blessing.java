package edu.insightr.spellmonger;


public class Blessing extends Rituol {

    private String effectDescription = "Blessing - Restores 3 hp to you";
    private boolean bonus;

    public Blessing(String name, String owner) {
        super(name, owner);
        bonus = true;
        this.setCost(1);
    }

    public Blessing() {
        bonus = true;
        this.setCost(1);
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
