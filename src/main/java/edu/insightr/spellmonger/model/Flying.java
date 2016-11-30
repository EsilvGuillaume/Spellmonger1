package edu.insightr.spellmonger.model;

/**
 * Created by Melvax on 09/11/2016.
 */
public abstract class Flying extends Creature {

    public Flying(String name, String owner, int hp) {
        super(name, owner, hp);
    }

    public Flying(String name, String owner) {
        super(name, owner);
    }


}
