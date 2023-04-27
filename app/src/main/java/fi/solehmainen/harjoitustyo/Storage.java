package fi.solehmainen.harjoitustyo;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    protected String name;

    private static Storage storage = null;

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

    public void addLutemonToArena(Lutemon lutemon) {

        lutemonsAtArena.add(lutemon);
    }

    public void addLutemonToHome(Lutemon lutemon) {

        lutemonsAtHome.add(lutemon);
    }

    public void addLutemonToTrain(Lutemon lutemon) {

        lutemonsAtHome.add(lutemon);
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
    public Lutemon getLutemon(int id) {

        return lutemons.get(id);
    }

    public void moveLutemon(Lutemon lutemon, ArrayList<Lutemon> moveTo, ArrayList<Lutemon> moveFrom) {

        moveTo.add(lutemon);
        moveFrom.remove(lutemon);
        System.out.println(lutemon + "siirrettiin listaan " + moveTo);
        //Storage.getInstance().addLutemon(lutemon);
    }

/*
    public void listLutemons() {
        int i = 0;.

        System.out.println("Lutemonit:");
        for (Lutemon l: lutemons) {
            System.out.println("Lutemoni ID:llä " + lutemons.get(i).id + " nimellä " + lutemons.get(i).name + " " + lutemons.get(i).color);
            i++;
        }
    }
*/
    // Load Lutemons from lutemons.data
    public void loadLutemons(Context context) {

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
    }

    //Save Lutemons to lutemons.data
    public void saveLutemons(Context context) {
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
