package edu.insightr.spellmonger.model;


import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static edu.insightr.spellmonger.MenuController.app;

public class Creature extends Card {

    protected static ArrayList<Creature> allCreatures = new ArrayList<Creature>();
    private static ArrayList<Creature> temp;
    private int hp;
    private int attack;
    private boolean alive;
    private int played;
    private boolean putOnBoard;
    private ImageView pic;

    public Creature(String name, String owner, int hp) {
        super(name, owner);
        this.setHp(hp);
        this.setAttack(hp);
        this.setAlive(true);
        allCreatures.add(this);
        this.setPutOnBoard(false);
    }

    public Creature(String name, String owner) {
        super(name, owner);
        this.setHp(0);
        this.setAttack(0);
        this.setAlive(true);
        this.setPlayed(0);
        allCreatures.add(this);
        this.setPutOnBoard(false);
    }

    public static int getCreaDamageForPlayer(Player player) {
        int damage = 0;
        List<Creature> creatures = getPlayerCreatures(player.getName());
        for (Creature crea : creatures) {
            damage += crea.getAttack();
        }
        return damage;
    }

    public static void displayGroupOfCrea(List<Creature> listOfCrea) {
        int i = 1;
        System.out.println("********Displaying the creatures :");
        for (Creature crea : listOfCrea) {
            System.out.println("Creature " + i + " : " + crea.getName() + " (" + crea.getOwner() + ")");
            i++;
        }
        System.out.println("********END");
    }

    public static List<Creature> getPlayerCreaOnBoard(Player player) {

        List<Creature> creaOnBoard = (ArrayList<Creature>) allCreatures.clone();

        if (creaOnBoard.isEmpty()) {
            return null;
        } else {
            Iterator<Creature> i = creaOnBoard.iterator();
            while (i.hasNext()) {
                Creature crea = i.next();
                if ((crea.getOwner() != player.getName()) || !(crea.isDraw()) || !(crea.isAlive()) || (player.getHand().contains(crea))) {
                    i.remove();
                }
            }
            return creaOnBoard;
        }
    }

    private static List<Creature> getPlayerCreatures(String playerName) {
        temp = allCreatures; // use clone?
        for (int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).getOwner().equals(playerName)) {
                temp.remove(temp.get(i));
            }
        }
        return temp;
    }

    private static Creature findBestTarget(int attack, int hp, Player opponent) {
        Creature bestTarget = null;
        List<Creature> potentialTargets = new ArrayList<>();
        List<Creature> opponentCrea;
        opponentCrea = getPlayerCreaOnBoard(opponent);
        //System.out.print(opponentCrea.isEmpty());
        //we retrieve all opponent creatures on board
        if (opponentCrea == null) {
            return null;
        } else {
            for (int i = 0; i < opponentCrea.size(); i++) {
                if (opponentCrea.get(i).getOwner() == opponent.getName()) {
                    potentialTargets.add(opponentCrea.get(i));
                }
            }
        }
        //we only keep the target we can kill (if they are none, we'll attack opponent)
        //System.out.print("size of potential targets : "+potentialTargets.size());
        for (int i = 0; i < potentialTargets.size(); i++) {
            if (potentialTargets.get(i).getHp() > attack) {
                potentialTargets.remove(potentialTargets.get(i));
            }
        }
        //we check on targets if we can stay alive while killing them
        for (int i = 0; i < potentialTargets.size(); i++) {
            if (potentialTargets.get(i).getAttack() >= hp) {
                if (potentialTargets.get(i).getAttack() <= attack) {
                    potentialTargets.remove(potentialTargets.get(i));
                }
            }
        }

        //then we choose the one with the highest health
        int healthiest = 0;
        for (int i = 0; i < potentialTargets.size(); i++) {
            if (potentialTargets.get(i).getHp() > healthiest) {
                healthiest = potentialTargets.get(i).getHp();
            }
        }
        for (Creature target : potentialTargets) {
            if (target.getHp() == healthiest)
                bestTarget = target;
        }

        return bestTarget;
    }

     public void killCreature(Creature creatures) {
        allCreatures.remove(creatures);

        //Controller ctrl = new Controller(); // test
        //ctrl.creaDies(this.getPic(), this); // test

        app.getAllCreaOnBoard().remove(creatures);
        app.getLastDeadCrea().add(creatures);
        if (creatures.getOwner() == app.getCurrentPlayer().getName()) {
            app.getCurrentPlayer().getDiscard().add(creatures);
        } else if (creatures.getOwner() == app.getOpponent().getName()) {
            app.getOpponent().getDiscard().add(creatures);
        }
        app.setIgMsg(app.getIgMsg() + "\n" + creatures.getName() + " of " + creatures.getOwner() + ",\nwas killed by " + this.getName() + " of " + this.getOwner());
    }

    /*private void killCreature(Creature creatures) {
        allCreatures.remove(this);

        //Controller ctrl = new Controller(); // test
        //ctrl.creaDies(this.getPic(), this); // test

        app.getAllCreaOnBoard().remove(this);
        app.getLastDeadCrea().add(this);
        if (this.getOwner() == app.getCurrentPlayer().getName()) {
            app.getCurrentPlayer().getDiscard().add(this);
        } else if (this.getOwner() == app.getOpponent().getName()) {
            app.getOpponent().getDiscard().add(this);
        }
        app.setIgMsg(app.getIgMsg() + "\n" + this.getName() + " of " + this.getOwner() + ",\nwas killed by " + creatures.getName() + " of " + creatures.getOwner());
    }*/

    @Override
    public String toString() {
        return super.toString() + ", hp = " + this.hp + ", attack = " + this.attack + ", alive = " + this.alive;
    }

    private void attackOpponent(Player opponent) {
        opponent.setHp(opponent.getHp() - this.getAttack());
        if (opponent.getHp() <= 0) {
            opponent.setAlive(false);
            System.out.println(opponent.getName() + " is dead !");
            app.setOnePlayerDead(true);
        }
    }

    private void attackCreature(Creature creature) {
        creature.setHp(creature.getHp() - this.getAttack());
        this.setHp(this.getHp() - creature.getAttack());
        if (creature.getHp() <= 0) {
            creature.setAlive(false);
            //creature.killCreature(this);
            killCreature((creature));
        }
        if (this.getHp() <= 0) {
            this.setAlive(false);
            //this.killCreature(creature);
            killCreature(this);
        }
    }

    public void attack(Player opponent) {

        Creature bestCreaTarget = findBestTarget(this.getHp(), this.getAttack(), opponent);

        if (bestCreaTarget == null) {
            attackOpponent(opponent);
            System.out.println(this.getName() + " attacks " + opponent.getName() + " and deals " + this.getAttack() + " damage");
        } else {
            attackCreature(bestCreaTarget);
            System.out.println(this.getName() + " attacks " + bestCreaTarget.getName() + " and deals it " + this.getAttack() + " damage and receives " + bestCreaTarget.getAttack() + " damage");
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int play) {
        this.played = play;
    }

    public boolean isPutOnBoard() {
        return putOnBoard;
    }

    public void setPutOnBoard(boolean putOnBoard) {
        this.putOnBoard = putOnBoard;
    }

    public ImageView getPic() {
        return pic;
    }

    public void setPic(ImageView pic) {
        this.pic = pic;
    }
}