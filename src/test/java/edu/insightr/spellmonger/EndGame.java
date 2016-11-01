package edu.insightr.spellmonger;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


/**
 * Created by Harry on 01/11/2016.
 */
public class EndGame {
    Player player = new Player();

    @When("^a \"([^\"]*)\"'s life points attain (\\d+)$")
    public void a_s_life_points_attain(String arg1, int arg2) throws Throwable {
        if(player.getName() == arg1 && player.getHp() == arg2)
        {
            player.setAlive(false);
        }
    }

    @Then("^the \"([^\"]*)\" is the winner$")
    public void the_is_the_winner(String arg1) throws Throwable {
        if(player.getName() == arg1)
        {
            player.setAlive(true);
        }
    }
}
