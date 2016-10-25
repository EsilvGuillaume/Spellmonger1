package edu.insightr.spellmonger;

import javafx.scene.image.Image;

public class Dragon extends Creature {

    public Dragon(String name) {
        super(name, "");
        this.setHp(5);
        this.setAttack(5);
        this.setAlive(true);
        this.setCost(5);
        setImg(new Image(getClass().getResourceAsStream("/img/dragon-card.jpg")));
    }

    public Dragon(String name, String owner) {
        super(name, owner);
        this.setHp(5);
        this.setAttack(5);
        this.setAlive(true);
        this.setCost(5);
        setImg(new Image(getClass().getResourceAsStream("/img/dragon-card.jpg")));
    }

}
