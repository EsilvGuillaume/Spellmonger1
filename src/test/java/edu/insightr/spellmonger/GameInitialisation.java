package edu.insightr.spellmonger;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 01/11/2016.
 */
public class GameInitialisation {

    Player player = new Player();

    @Given("^player \"([^\"]*)\" joins the game$")
    public void player_joins_the_game(String arg1) throws Throwable {
        player.setName(arg1);
    }

    @Then("^Alice has (\\d+) life points and (\\d+) creatures and (\\d+) energy point and (\\d+) cards in his/her deck$")
    public void alice_has_life_points_and_creatures_and_energy_point_and_cards_in_his_her_deck(int arg1, int arg2, int arg3, int arg4) throws Throwable {
        List<Card> hand = new ArrayList<>(arg2);
        if(player.getName() == "Alice")
        {
            player.setHp(arg1);
            player.setHand(hand);
            player.setEnergy(arg3);
            player.setDeckInfo(new Deck(arg4, ""));
        }
    }

    @Then("^Bob has (\\d+) life points and (\\d+) creatures and (\\d+) energy point and (\\d+) cards in his/her deck$")
    public void bob_has_life_points_and_creatures_and_energy_point_and_cards_in_his_her_deck(int arg1, int arg2, int arg3, int arg4) throws Throwable {
        List<Card> hand = new ArrayList<>(arg2);
        if(player.getName() == "Bob")
        {
            player.setHp(arg1);
            player.setHand(hand);
            player.setEnergy(arg3);
            player.setDeckInfo(new Deck(arg4, ""));
        }
    }

}
