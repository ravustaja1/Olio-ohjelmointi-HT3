package fi.solehmainen.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BattlefieldActivity extends AppCompatActivity {
    private ArrayList<Lutemon> lutemonsAtArena = new ArrayList<>();
    private ArrayList<Lutemon> fighters = new ArrayList<>();
    private LinearLayout linearLayout;
    private Button fightButton;
    private TextView textViewBattle;
    private CheckBox checkBox;
    private ArrayList<CheckBox> boxesChecked = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        linearLayout = findViewById(R.id.llFighters);
        textViewBattle = findViewById(R.id.tvBattle);
        fightButton = findViewById(R.id.btnFight);


        makeCheckBoxes();
        /*
        if (checkBox.isChecked() && (checkBox.getId() == 0)) {

        }
 */
        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                for (CheckBox c: boxesChecked) {
                    if (c.isChecked()) {
                        fighters.add(Storage.getInstance().getLutemon(i));
                        System.out.println(fighters.get(i) + " Lutemon väriltä " + fighters.get(i).getName() + "lisättiin taistelulistaan.");
                    }
                }
            }
        });
/*
        int i = 0;
        for (CheckBox c: boxesChecked) {
            if (c.isChecked()) {
                fighters.add(Storage.getInstance().getLutemon(i));
                System.out.println(fighters.get(i) + " Lutemon väriltä " + fighters.get(i).color + "lisättiin taistelulistaan.");
            }
        }
*/
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
            boxesChecked.add(checkBox);
        }

    }

    public void fight(Lutemon l1, Lutemon l2) {

    }
}