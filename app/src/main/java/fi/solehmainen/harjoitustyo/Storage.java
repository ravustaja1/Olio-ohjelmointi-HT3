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

    public Lutemon getLutemon(int id) {

        return lutemons.get(id);
    }

    public void listLutemons() {
        int i = 0;

        System.out.println("Lutemonit:");
        for (Lutemon l: lutemons) {
            System.out.println("Lutemoni ID:llä " + lutemons.get(i).id + " nimellä " + lutemons.get(i).name + " " + lutemons.get(i).color);
            i++;
        }
    }

    public ArrayList<Lutemon> getLutemonArrayList() {
        return lutemons;
    }

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

    public void saveLutemons(Context context) {
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
