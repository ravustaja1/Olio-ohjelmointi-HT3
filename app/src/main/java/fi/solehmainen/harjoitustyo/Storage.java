package fi.solehmainen.harjoitustyo;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Storage {
    protected String name;

    private static Storage storage = null;

    public void setLutemonsAtHome(ArrayList<Lutemon> lutemonsAtHome) {
        this.lutemonsAtHome = lutemonsAtHome;
    }

    public void setLutemons(ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
    }

    protected ArrayList<Lutemon> lutemons = new ArrayList<>();
    protected ArrayList<Lutemon> lutemonsAtArena = new ArrayList<>();
    protected ArrayList<Lutemon> lutemonsAtHome = new ArrayList<>();
    protected ArrayList<Lutemon> lutemonsAtTrain = new ArrayList<>();

    public ArrayList<Lutemon> getLutemonsAtArena() {
        return lutemonsAtArena;
    }

    public ArrayList<Lutemon> getLutemonsAtHome() {
        return lutemonsAtHome;
    }

    public ArrayList<Lutemon> getLutemonsAtTrain() {
        return lutemonsAtTrain;
    }

    public ArrayList<Lutemon> getLutemonArrayList() {

        return lutemons;
    }
    private Storage(){
    }

    public static Storage getInstance() {
        if (storage == null){
            storage = new Storage();
        }
        return storage;
    }


    // Remove a Lumeton by it's ID
    public void removeLutemon(int id) {
        int i = 0;
        for (Lutemon l : lutemons) {
            if (l.getId() == id){
                break;
            }
        i++;
        }
        lutemons.remove(i);
    }

    // Return a Lutemon by it's id
    public Lutemon getLutemon(int idx) {

        return lutemons.get(idx);
    }

    // Return a Lutemon by giving parameters index and target list to pick from
    public Lutemon getLutemon(int idx, String target) {
        switch (target) {
            case "HOME":
                return lutemonsAtHome.get(idx);
            case "ARENA":
                return lutemonsAtArena.get(idx);
            case "TRAIN":
                return lutemonsAtTrain.get(idx);
            default:
                return lutemons.get(idx);
        }
    }

    // Method used for moving a Lutemon from a list to another.
    public void moveLutemon(Lutemon lutemon, ArrayList<Lutemon> moveTo, ArrayList<Lutemon> moveFrom) {

        moveTo.add(lutemon);
        moveFrom.remove(lutemon);

        // Sort Lutemons in their list by their ID number.
        Collections.sort(moveTo, Comparator.comparing(Lutemon::getId).thenComparing(Lutemon::getId));
        Collections.sort(moveFrom, Comparator.comparing(Lutemon::getId).thenComparing(Lutemon::getId));



    }

    // Load Lutemons from lutemons.data and set them to home.
    public void loadLutemons(Context context) {
        lutemons.clear();
        lutemonsAtHome.clear();
        lutemonsAtTrain.clear();
        lutemonsAtArena.clear();

        try {
            ObjectInputStream userReader = new ObjectInputStream(context.openFileInput("lutemons.data"));
            lutemons = (ArrayList<Lutemon>) userReader.readObject();
            userReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Käyttäjien lukeminen ei onnistunut.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Käyttäjien lukeminen ei onnistunut.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Käyttäjien lukeminen ei onnistunut.");
            e.printStackTrace();
        }


        Storage.getInstance().setLutemonsAtHome(lutemons);

    }

    //Save Lutemons to lutemons.data
    public void saveLutemons(Context context) {
        lutemons.clear();
        lutemons.addAll(lutemonsAtTrain);
        lutemons.addAll(lutemonsAtArena);
        lutemons.addAll(lutemonsAtHome);

        try {
            ObjectOutputStream userWriter = new ObjectOutputStream(context.openFileOutput("lutemons.data", Context.MODE_PRIVATE));
            userWriter.writeObject(lutemons);
            userWriter.close();
        } catch (IOException e) {
            System.out.println("Käyttäjien lukeminen ei onnistunut.");
            e.printStackTrace();
        }
    }

}
