package fi.solehmainen.harjoitustyo;

import androidx.core.math.MathUtils;

import java.io.Serializable;

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

    protected int wins;

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
        this.wins = 0;
        this.defeats = 0;
        this.id = idCounter();
        if (image == 0) {
            this.image = R.drawable.happy;
        } else if (image == 1) {
            this.image = R.drawable.happy2;
        } else if (image == 2) {
            this.image = R.drawable.fighter;
        }
        this.image2 = R.drawable.delete;



        Storage.getInstance().lutemons.add(this);

        // Tämä rivi on ihan vaan sitä varten, että saisi jotain printattua ulos
        System.out.println("ID: " + this.id + " " + this.name + " niminen Lutemon värillä + " + this.color + " luotiin.");
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
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

    public int getImage2() {
        return image2;
    }


    public String getName() {
        return name;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public void defense(Lutemon lutemon) {
        this.health = this.health - lutemon.attack() + this.defense;

    }

    public int attack() {
        int dmg = this.attack;

        return dmg;
    }

    public int getNumberOfCreatedLutemons() {

        return idCounter;
    }


}
