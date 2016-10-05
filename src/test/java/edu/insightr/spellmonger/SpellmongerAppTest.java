package edu.insightr.spellmonger;

import static org.junit.Assert.*;

/**
 * Created by Guillaume on 26/09/2016.
 */
public class SpellmongerAppTest {

    public static boolean testDeckSize(int size){
        Player play1 = new Player("one");
        Player play2 = new Player("two");

        System.out.println("****TESTING DECK SIZE = "+size+" FOR BOTH PLAYERS ?");

        if (play1.getDeckInfo().getSize() != size)
            return false;
        if (play2.getDeckInfo().getSize() != size)
            return false;
        return true;
    }

    public static boolean testDistinctDeck(){
        System.out.println("****TESTING IF THE TWO DECKS ARE DIFFERENT");
        Player play1 = new Player("one");
        Player play2 = new Player("two");
        if (play1.getDeckInfo().getDeck().equals(play2.getDeckInfo().getDeck())){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean testDrawAndSetOnBoard(){
        System.out.println("****TESTING IF YOU PUT CREATURE ON BOARD IF YOU DRAW ONE");

        Player play1 = new Player("one");
        Player play2 = new Player("two");

        SpellmongerApp game = new SpellmongerApp();
        game.drawACard(play1, play2);
        Card cardDraw = null;

        for (Card card : play1.getDeckInfo().getDeck()) {
            if(card.isDraw() == true) {
                cardDraw = card;
                break;
            }
        }

        if (cardDraw instanceof Creature){
            if (Creature.getPlayerCreaOnBoard(play1).size() == 1){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if (Creature.getPlayerCreaOnBoard(play1).size() == 0){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public static void main(String[] args) {

        System.out.println(testDeckSize(30));
        System.out.println(testDistinctDeck());
        System.out.println(testDrawAndSetOnBoard());

    }

}