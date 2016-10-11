package edu.insightr.spellmonger;

/**
 * Created by Guillaume on 02/10/2016.
 */
public class EnergyDrain extends Rituol {

    private String effectDescription = "Energy Drain - consume 2 energies to your opponent and you win 2 energies";
    private boolean bonus;

    public EnergyDrain(String name) {
        super(name);
        bonus = true;
    }

    public EnergyDrain() {
        bonus = true;
    }

    public void play(Player currentPlayer) {
        Player opponent = Player.getCurrentOpponent(); // not working
        if (opponent.getEnergy() > 1) {
            opponent.setEnergy(opponent.getEnergy() - 2);
            currentPlayer.setEnergy(currentPlayer.getEnergy() + 2);
            System.out.println(this.getName() + " used, " + opponent.getName() + " loses 2 energies, " + currentPlayer.getName() + " wins 2 energies");
        } else {
            System.out.println(this.getName() + " used, but " + opponent.getName() + " did not lose 2 energies and " + currentPlayer.getName() + " did not win 2 energies because " + opponent.getName() + " did not have enough energy");
        }
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
