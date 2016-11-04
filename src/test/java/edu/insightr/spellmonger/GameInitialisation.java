package edu.insightr.spellmonger;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Harry on 01/11/2016.
 */
public class GameInitialisation {

    Player player = new Player();

    @Given("^player \"([^\"]*)\" joins the game$")
    public void player_joins_the_game(String arg1) throws Throwable {
        player.setName(arg1);
        Assert.assertThat(player.getName(), is(equalTo(arg1)));
    }

    @Then("^Alice has (\\d+) life points and (\\d+) creatures and (\\d+) energy point and (\\d+) cards in his/her deck$")
    public void alice_has_life_points_and_creatures_and_energy_point_and_cards_in_his_her_deck(int arg1, int arg2, int arg3, int arg4) throws Throwable {
        List<Card> hand = new ArrayList<>(arg2);
        player.setHp(arg1);
        player.setHand(hand);
        player.setEnergy(arg3);
        player.setDeckInfo(new Deck(arg4, ""));
        if(player.getName() == "Alice")
        {
            Assert.assertThat(player.getHp(), is(equalTo(arg1)));
            Assert.assertThat(player.getHand(), is(equalTo(hand)));
            Assert.assertThat(player.getEnergy(), is(equalTo(arg3)));
            Assert.assertThat(player.getDeckInfo(), is(equalTo(new Deck(arg4, ""))));
        }
    }

    @Then("^Bob has (\\d+) life points and (\\d+) creatures and (\\d+) energy point and (\\d+) cards in his/her deck$")
    public void bob_has_life_points_and_creatures_and_energy_point_and_cards_in_his_her_deck(int arg1, int arg2, int arg3, int arg4) throws Throwable {
        List<Card> hand = new ArrayList<>(arg2);
        player.setHp(arg1);
        player.setHand(hand);
        player.setEnergy(arg3);
        player.setDeckInfo(new Deck(arg4, ""));
        if(player.getName() == "Bob")
        {
            Assert.assertThat(player.getHp(), is(equalTo(arg1)));
            Assert.assertThat(player.getHand(), is(equalTo(hand)));
            Assert.assertThat(player.getEnergy(), is(equalTo(arg3)));
            Assert.assertThat(player.getDeckInfo(), is(equalTo(new Deck(arg4, ""))));
        }
    }

}
