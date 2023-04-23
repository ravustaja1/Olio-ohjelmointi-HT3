package fi.solehmainen.harjoitustyo;

import java.io.Serializable;

public class Lutemon implements Serializable {

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

    public Lutemon(String name, String color, int attack, int defense, int experience, int health, int maxHealth) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.experience = experience;
        this.health = health;
        this.maxHealth = maxHealth;
        this.id = idCounter();

        Storage.getInstance().lutemonHashMap.put(this.id, this);

        // Tämä rivi on ihan vaan sitä varten, että saisi jotain printattua ulos
        System.out.println(this.name + " niminen Lutemon värillä + " + this.color + " luotiin.");
    }

    private int idCounter() {
        int id;

        id = idCounter++;

        return id;
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
        int size;

        //size = idCounter;
        size = Storage.getInstance().lutemonHashMap.size();

        return size;
    }


}
