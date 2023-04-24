package fi.solehmainen.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Battlefield extends AppCompatActivity {
    protected ArrayList<Lutemon> lutemonsAtArena = new ArrayList<>();
    protected LinearLayout linearLayout;
    protected TextView textViewBattle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        linearLayout = findViewById(R.id.llFighters);
        textViewBattle = findViewById(R.id.tvBattle);



        lutemonsAtArena = Storage.getInstance().getLutemonArrayList();
    }

    public void makeCheckBoxes() {

    }
}