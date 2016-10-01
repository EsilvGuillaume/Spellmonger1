package edu.insightr.spellmonger;

/**
 * Created by Guillaume on 01/10/2016.
 */

import java.util.List;
import java.util.ArrayList;

public abstract class Creature extends Card {

    private int hp;
    private int attack;
    private boolean alive;

    static ArrayList<Creature> allCreatures = new ArrayList<Creature>();

    void killCreature() {
        allCreatures.remove(this);
    }

    public Creature(String name, int hp){
        super(name);
        this.setHp(hp);
        this.setAttack(hp);
        this.setAlive(true);
        allCreatures.add(this);
    }

    public static List<Creature> getPlayerCreatures(String playerName){
        for(int i=0; i<allCreatures.size(); i++) {
            if (allCreatures.get(i).getOwner() != playerName) {
                allCreatures.remove(allCreatures.get(i));
            }
        }
        return allCreatures;
    }

    public void attackOpponent(Player opponent){
        opponent.setHp(opponent.getHp()- this.getAttack());
        if (opponent.getHp() <= 0){
            opponent.setAlive(false);
            System.out.println(opponent+" is dead !");
        }
    }

    public void attackCreature(Creature creature){
        creature.setHp(creature.getHp()-this.getAttack());
        this.setHp(this.getHp()-creature.getAttack());
        if (creature.getHp() <= 0){
            creature.setAlive(false);
        }
        if (this.getHp() <= 0){
            this.setAlive(false);
        }
    }

    public Creature findBestTarget(int attack, int hp){
        Creature bestTarget = null;
        List<Creature> potentialTargets = null;
        Player opponentPlayer = Player.getCurrentOpponent();

        //we retrieve all opponent creatures
        for(int i=0; i<allCreatures.size(); i++) {
            if (allCreatures.get(i).getOwner() == opponentPlayer.getName()) {
                potentialTargets.add(allCreatures.get(i));
            }
        }

        //we only keep the target we can kill (if they are none, we'll attack opponent)
        for(int i=0; i<potentialTargets.size(); i++) {
            if (potentialTargets.get(i).getHp() > attack) {
                potentialTargets.remove(potentialTargets.get(i));
            }
        }

        //we check on targets if we can stay alive while killing them
        for(int i=0; i<potentialTargets.size(); i++) {
            if (potentialTargets.get(i).getAttack() >= hp) {
                potentialTargets.remove(potentialTargets.get(i));
            }
        }

        //then we choose the one with the highest health
        int healthiest = 0;
        for(int i=0; i<potentialTargets.size(); i++) {
            if (potentialTargets.get(i).getHp() > healthiest){
                healthiest = potentialTargets.get(i).getHp();
            }
        }
        for(Creature target : potentialTargets)
        {
            if(target.getHp() == healthiest)
                bestTarget = target;
        }

        return bestTarget;
    }

    public void attack(){

        Player opponent = Player.getCurrentOpponent();
        Creature bestCreaTarget = findBestTarget(this.getHp(), this.getAttack());

        if (bestCreaTarget ==  null){
            attackOpponent(opponent);
        }
        else{
            attackCreature(bestCreaTarget);
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

}
