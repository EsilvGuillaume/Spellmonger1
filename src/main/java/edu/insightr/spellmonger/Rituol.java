package edu.insightr.spellmonger;


public abstract class Rituol extends Card {

    private int cost = 1;
    String effectDescription = "";
    private boolean bonus = false; // false will indicate it is a malus

    public Rituol(String name) {
        super(name);
        this.setCost(1);
    }

    public Rituol() {
        this.setCost(1);
    }

    public abstract void play(Player target);

    public abstract void play(Player target, Player emetter);

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }
}
