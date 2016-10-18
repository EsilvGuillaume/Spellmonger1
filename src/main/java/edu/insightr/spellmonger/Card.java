package edu.insightr.spellmonger;

import java.util.Random;

public abstract class Card {

    private String name;
    private boolean draw;
    private String owner;
    private int cost;
    private int idCode;
    int cardCreatedCount = 0;

    public Card(String name, String owner) {
        Random rand = new Random();
        setName(name);
        setDraw(false);
        setOwner(owner);
        setIdCode(System.identityHashCode(this) + cardCreatedCount);
        cardCreatedCount++;
    }

    public Card(String name) {
        setName(name);
        setDraw(false);
        setOwner("");
        setIdCode(this.hashCode());
        cardCreatedCount++;
    }

    public Card() {
        setName("");
        setDraw(false);
        setOwner("");
        setIdCode(this.hashCode());
        cardCreatedCount++;
    }

    @Override
    public String toString() {
        return "code : "+this.idCode+", name : " + this.name + ", draw : " + this.draw + ", owner : " + this.owner;
    }

    public void draw(Player player) {
        this.setDraw(true);
        player.getHand().add(this);
        player.getDeckInfo().getDeck().remove(this);
        this.setOwner(player.getName()); //necessary?
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }
}
