package edu.insightr.spellmonger;


public class Player {

    private String name;
    private int hp;
    private int energy;
    private boolean alive;
    private Deck deckInfo;
    private int numberOfCreaOnBoard;

    private static Player currentPlayer = new Player();
    private static Player currentOpponent = new Player();

    Player() {
        this.setName("");
        this.setHp(20);
        this.setEnergy(0);
        this.setDeckInfo(new Deck(30, ""));
        this.setNumberOfCreaOnBoard(0);
    }

    Player(String name) {
        this.setName(name);
        this.setHp(20);
        this.setEnergy(0);
        this.setDeckInfo(new Deck(30, name));
        this.setNumberOfCreaOnBoard(0);
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        Player.currentPlayer = currentPlayer;
    }

    public static Player getCurrentOpponent() {
        return currentOpponent;
    }

    public static void setCurrentOpponent(Player currentOpponent) {
        Player.currentOpponent = currentOpponent;
    }

    @Override
    public String toString() {
        return this.getName() + " has " + this.getHp() + " hp and " + this.getEnergy() + " energy.";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getNumberOfCreaOnBoard() {
        return numberOfCreaOnBoard;
    }

    public void setNumberOfCreaOnBoard(int numberOfCreaOnBoard) {
        this.numberOfCreaOnBoard = numberOfCreaOnBoard;
    }

    public Deck getDeckInfo() {
        return deckInfo;
    }

    public void setDeckInfo(Deck deckInfo) {
        this.deckInfo = deckInfo;
    }
}
