package edu.insightr.spellmonger;

public abstract class Card {

    private String name;
    private boolean draw;
    private String owner;

    public Card(String name, String owner) {
        setName(name);
        setDraw(false);
        setOwner(owner);
    }

    public Card(String name) {
        setName(name);
        setDraw(false);
        setOwner("");
    }

    public Card() {
        setName("");
        setDraw(false);
        setOwner("");
    }

    @Override
    public String toString() {
        return "name : " + this.name + ", draw : " + this.draw + ", owner : " + this.owner;
    }

    public void draw() {
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
