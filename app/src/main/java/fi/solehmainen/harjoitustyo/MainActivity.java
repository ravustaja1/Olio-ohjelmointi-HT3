package fi.solehmainen.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Context context;
    //private Storage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Storage storage = Storage.getInstance();
        context = MainActivity.this;

        // TÄmä on testi lutemon, kun koitan saada jotain printattua ulos

        Lutemon lutemon = new Lutemon("Teppo", "Valkoinen", 1, 1, 0, 10, 10);
        Lutemon lutemon2 = new Lutemon("Kalle", "Punainen", 1, 1, 0, 10, 10);

        Storage.getInstance().listLutemons();
    }
}