package fi.solehmainen.harjoitustyo;

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
}
