package fi.solehmainen.harjoitustyo;

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
/*
    public void setName(String name) {
        this.name = name;
    }
*/
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
        // Toiminnallisuus tänne
    }

    public int attack() {
        int dmg = 0; // Laitoin nollaksi, koska valittaa, että variablea ei ole alustettu.
        // Toiminnallisuus tänne. Tarkoituksena palauttaa damagea tehty int arvo.
        // Tähän voisi esim käyttää sitä math -kirjastoa, että onko vaikka jokin tietty % -mahdollisuus tehdä tupla dmg tms?
        return dmg;
    }

    public int getNumberOfCreatedLutemons() {

        return idCounter;
    }


}
