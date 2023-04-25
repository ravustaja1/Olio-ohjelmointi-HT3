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
    private ArrayList<CheckBox> boxes = new ArrayList<>();
    private ArrayList<CheckBox> boxesChecked = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        linearLayout = findViewById(R.id.llFighters);
        textViewBattle = findViewById(R.id.tvBattle);
        fightButton = findViewById(R.id.btnFight);


        makeCheckBoxes();

        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                int numchecked = 0;
                for (CheckBox c: boxes) {
                    if (c.isChecked()) {
                        numchecked++;
                        boxesChecked.add(c);
                    }
                    i++;
                }
                if (numchecked == 2) {
                    int y = 0;
                    for (CheckBox c1 : boxes){
                        //c1.getId()
                        if (boxes.get(y).isChecked()) {
                            fighters.add(Storage.getInstance().getLutemon(c1.getId()));
                        }
                        y++;
                    }

                } else {
                    System.out.println("Valitse vain kaksi taistelijaa!");
                    numchecked = 0;
                }
                for (Lutemon lutemon : fighters) {
                    System.out.println(lutemon.getName() + " lisättiin taistelulistaan.");
                }
            }
        });

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
            boxes.add(checkBox);
        }

    }

    public void fight(Lutemon l1, Lutemon l2) {

    }
}