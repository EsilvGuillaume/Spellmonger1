package edu.insightr.spellmonger;

/**
 * Created by Guillaume on 01/10/2016.
 */
public abstract class Card {

    private String name;
    private boolean draw;
    private String owner;

    public Card(String name){
        setName(name);
        setDraw(false);
    }

    public void draw(){
        this.setDraw(true);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
