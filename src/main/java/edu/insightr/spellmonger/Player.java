package edu.insightr.spellmonger;

/**
 * Created by Guillaume on 26/09/2016.
 */
public class Player {

    private String name;
    private int hp;
    private int energy;
    //private Deck deck;

    Player(String name){
        this.name = name;
        this.hp = 20;
        this.energy = 0;
        //this.deck = new Deck(2);
    }

    @Override
    public String toString(){
        return name+" has "+hp+" hp and "+energy+" energy.";
    }

}
