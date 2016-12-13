package edu.insightr.spellmonger.model.Card;

import edu.insightr.spellmonger.model.Player;

public abstract class Enchantment extends Card {

    String effectDescription = "";

    public Enchantment(String name, String owner) {
        super(name, owner);
    }

    public Enchantment() {
    }

    public abstract void play(Player target);

}
