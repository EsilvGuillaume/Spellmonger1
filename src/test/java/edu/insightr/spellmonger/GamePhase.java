package edu.insightr.spellmonger;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Harry on 01/11/2016.
 */
public class GamePhase {

    Player player = new Player();
    Fox fox = new Fox("Fox");



    @Given("^the \"([^\"]*)\" is the one who clicks first the draw button$")
    public void the_is_the_one_who_clicks_first_the_draw_button(String arg1) throws Throwable {
        System.out.println("");
    }

    @Given("^\"([^\"]*)\"'s draw button is disabled$")
    public void s_draw_button_is_disabled(String arg1) throws Throwable {
        System.out.println("");
    }

    @Then("^the \"([^\"]*)\" gets a new card from his/her deck and adds it to his/her existing creatures$")
    public void the_gets_a_new_card_from_his_her_deck_and_adds_it_to_his_her_existing_creatures(String arg1) throws Throwable {
        int hand = player.getHand().size();
        if (player.getName() == arg1)
            Assert.assertThat(hand, is(equalTo(hand+1)));
    }

    @Then("^if the \"([^\"]*)\" has enough energy points to summon a creature he/she choose the corresponding creature and summon it$")
    public void if_the_has_enough_energy_points_to_summon_a_creature_he_she_choose_the_corresponding_creature_and_summon_it(String arg1) throws Throwable {
        if(player.getName() == arg1) {
            if(player.getEnergy() >= fox.getHp()){
                Assert.assertThat(player.getNumberOfCreaOnBoard(), is(equalTo(1)));
            }

        }

    }

    @Then("^if there are no creatures on the opposite field the creatures summoned by the the \"([^\"]*)\" attack directly the \"([^\"]*)\"$")
    public void if_there_are_no_creatures_on_the_opposite_field_the_creatures_summoned_by_the_the_attack_directly_the(String arg1, String arg2) throws Throwable {
        System.out.println("");
    }

    @Then("^if there are creatures on the opposite field the battle phase between creatures begins$")
    public void if_there_are_creatures_on_the_opposite_field_the_battle_phase_between_creatures_begins() throws Throwable {
        System.out.println("");
    }

    @Then("^if there is a draw between creatures strength then corresponding creatures make no move$")
    public void if_there_is_a_draw_between_creatures_strength_then_corresponding_creatures_make_no_move() throws Throwable {
        System.out.println("");
    }

    @Then("^after the battle phase the remaining creatures deal their remaining strength as damage$")
    public void after_the_battle_phase_the_remaining_creatures_deal_their_remaining_strength_as_damage() throws Throwable {
        System.out.println("");
    }

    @Then("^if the \"([^\"]*)\" wants to summon a ritual he/she can summon it directly and profits the effects of the ritual$")
    public void if_the_wants_to_summon_a_ritual_he_she_can_summon_it_directly_and_profits_the_effects_of_the_ritual(String arg1) throws Throwable {
        System.out.println("");
    }

    @Then("^the \"([^\"]*)\" becomes the \"([^\"]*)\" and his/her draw button is enabled$")
    public void the_becomes_the_and_his_her_draw_button_is_enabled(String arg1, String arg2) throws Throwable {
        System.out.println("");
    }

    @Then("^both \"([^\"]*)\" and \"([^\"]*)\" get (\\d+) energy point$")
    public void both_and_get_energy_point(String arg1, String arg2, int arg3) throws Throwable {
        System.out.println("");
    }
}