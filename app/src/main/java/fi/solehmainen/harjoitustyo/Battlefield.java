package fi.solehmainen.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.math.MathUtils;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Battlefield extends AppCompatActivity {
    protected ArrayList<Lutemon> lutemonsAtArena = new ArrayList<>();
    protected LinearLayout linearLayout;
    protected TextView textViewBattle;
    protected CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        linearLayout = findViewById(R.id.llFighters);
        textViewBattle = findViewById(R.id.tvBattle);

        makeCheckBoxes();




    }

    public void makeCheckBoxes() {
        lutemonsAtArena = Storage.getInstance().getLutemonArrayList();
        linearLayout.removeAllViews();

        CheckBox checkBox;
        int i = 0;
        for (Lutemon l : lutemonsAtArena) {
            checkBox = new CheckBox(this);
            checkBox.setText(l.getName());
            checkBox.setId(i++);
            linearLayout.addView(checkBox);
        }

    }

    public void fight(Lutemon l1, Lutemon l2) {

    }
}