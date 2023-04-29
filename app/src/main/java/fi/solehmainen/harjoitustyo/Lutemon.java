package fi.solehmainen.harjoitustyo;


import androidx.core.math.MathUtils;

import java.io.Serializable;
import java.util.Random;

public class Lutemon implements Serializable {
    protected int image, image2, image3;
    protected String name;
    protected String color;
    protected int attack;
    protected int defense;
    protected int experience;
    protected int health;
    protected int maxHealth;
    protected int id;


    protected boolean hasTrained;

    protected int wins;
    protected int trains;

    protected int defeats;

    private static int idCounter = 0;

    public Lutemon(String name, String color, int attack, int defense, int experience, int health, int maxHealth, int image) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.experience = experience;
        this.health = health;
        this.maxHealth = maxHealth;
        this.hasTrained = false;
        this.wins = 0;
        this.trains = 0;
        this.defeats = 0;
        this.id = idCounter();
        if (image == 0) {
            this.image = R.drawable.happy;
        } else if (image == 1) {
            this.image = R.drawable.happy2;
        } else if (image == 2) {
            this.image = R.drawable.fighter;
        }


        // I Know this wasn't kind of allowed. I still desided to leave it, because I had made the "delete" button already in the Stats view
        // and this helped a lot in it. I considered the button an additional feature which wasn't required so maybe it's +/- 0. Hopefully :)
        //Storage.getInstance().lutemons.add(this);

        // Created lutemons are put to the Home list. I had started the work on Sunday and had done everything except the Fragments view
        // when it was declared that using lists (like this) isn't the proper way to to do it so I just left it because of major changes
        // required otherwise to keep the program working.
        Storage.getInstance().lutemonsAtHome.add(this);


    }

    public int getId() {
        return id;
    }

    public boolean HasTrained() {
        return hasTrained;
    }

    public void setHasTrained(boolean hasTrained) {
        this.hasTrained = hasTrained;
    }

    public String getColor() {
        return color;
    }

    public boolean isHasTrained() {
        return hasTrained;
    }

    public void setTrains(int trains) {
        this.trains = this.trains + trains;
    }

    public int getTrains() {
        return trains;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getExperience() {
        return experience;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getWins() {
        return wins;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setExperience(int exp) {
        this.experience = this.experience + exp;
    }

    public void setWins(int win) {
        this.wins = this.wins + win;
    }

    public void setMaxHealth() {
        this.health = this.maxHealth;
    }

    public void setDefeats(int defeat) {
        this.defeats = this.defeats + defeat;
    }

    private int idCounter() {
        int id;

        id = idCounter++;

        return id;
    }

    public int getImage() {
        return image;
    }


    public String getName() {
        return name;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public void defense(Lutemon lutemon) {
        this.health = this.health + this.defense - lutemon.attack() - lutemon.experience;

    }

    public int attack() {
        int dmg = this.attack;

        return dmg;
    }

}
