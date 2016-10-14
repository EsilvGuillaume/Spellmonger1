package edu.insightr.spellmonger;

public abstract class Enchantment extends Card {

    String effectDescription = "";

    public Enchantment(String name) {
        super(name);
    }

    public Enchantment() {
    }

    public abstract void play(Player target);

}
