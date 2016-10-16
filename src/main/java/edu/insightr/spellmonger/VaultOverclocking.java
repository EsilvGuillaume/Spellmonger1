package edu.insightr.spellmonger;

/**
 * Created by Guillaume on 14/10/2016.
 */
public class VaultOverclocking extends Enchantment {

    private String effectDescription = "+1 energy each turn but 35% that the vault overburn and is empty";

    public VaultOverclocking(String name, String owner) {
        super(name, owner);
        this.setCost(3);
    }

    public VaultOverclocking() {
        this.setCost(3);
    }

    public void play(Player currentPlayer) {
        currentPlayer.setVaultOverclocking(true);
        System.out.println(currentPlayer.getName()+" overclocks his energy vault !");
    }

    public String getEffectDescription() {
        return effectDescription;
    }

    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }

}
