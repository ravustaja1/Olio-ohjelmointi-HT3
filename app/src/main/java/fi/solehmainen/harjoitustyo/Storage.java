package fi.solehmainen.harjoitustyo;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    protected String name;

    private static Storage storage = null;

    HashMap<Integer, Lutemon> lutemonHashMap = new HashMap<>();

    private Storage(){
    }

    public static Storage getInstance() {
        if (storage == null){
            storage = new Storage();
        }
        return storage;
    }

    /* Tätä luokkaa en oikein ymmärrä. Onko tässä siis tarkoitus luoda se Olio, kun esim Homessa:kin on createLutemon, joka ottaa sisäänsä ja palauttaa lutemonin?
    public Lutemon addLutemon() {
        String name, color;
        int attack, defense, experience, health, maxHealth;
        Lutemon lutemon = new Lutemon(name, color, attack, defense, experience, health, maxHealth);

        // Tähän pitää muuttaa lopuksi luotu Lutemon
        return lutemon;
    }
    */

    public Lutemon getLutemon(int id) {

        return lutemonHashMap.get(id);
    }

    public void listLutemons() {
        int i = 0;

        for(i=0;i<lutemonHashMap.size();i++){
            System.out.println(storage.lutemonHashMap.get(i).name);
        }
    }

    public void loadLutemons(Context context) {
        try {
            ObjectInputStream userReader = new ObjectInputStream(context.openFileInput("lutemons.data"));
            lutemonHashMap = (HashMap<Integer, Lutemon>) userReader.readObject();
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

    public void saveLutemons(Context context) {
        try {
            ObjectOutputStream userWriter = new ObjectOutputStream(context.openFileOutput("lutemons.data", Context.MODE_PRIVATE));
            userWriter.writeObject(lutemonHashMap);
            userWriter.close();
        } catch (IOException e) {
            System.out.println("Käyttäjien lukeminen ei onnistunut.");
            e.printStackTrace();
        }
    }

}
