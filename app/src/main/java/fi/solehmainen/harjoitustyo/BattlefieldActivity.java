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

        /*
        When "Start Fight" button is pressed there will be made sure that 2 fighters are selected. If the condition applies, the fight starts.
         */
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

                        if (boxes.get(y).isChecked()) {
                            fighters.add(Storage.getInstance().getLutemon(c1.getId()));
                        }
                        y++;
                    }
                    fight(fighters);
                } else {
                    System.out.println("Valitse tasan kaksi taistelijaa!");
                    numchecked = 0;
                }
                // Testi tulostus
                for (Lutemon lutemon : fighters) {
                    System.out.println(lutemon.getName() + " lisättiin taistelulistaan.");
                }
            }
        });

    }
/*
    Method used for creating checkboxes for fighters to pick from
 */
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

    /*
    Method takes in a list of two fighters who fight each other taking turns until one's HP is < 0. Winner gets a win point, loser a lost match. Lutemons are sent to Home
    where they recover to full health unless one has a 3rd lost figth which means death.
     */
    public void fight(ArrayList<Lutemon> fighters) {

        Lutemon l1 = fighters.get(0);
        Lutemon l2 = fighters.get(1);
        StringBuilder sb = new StringBuilder();

        sb.append("1: " + l1.color + "(" + l1.name + ")" + " att: " + l1.attack + " def: " + l1.defense + " exp: " + l1.experience + " HP: " + l1.health + "/" + l1.maxHealth + "\n");
        sb.append("2: " + l2.color + "(" + l2.name + ")" + " att: " + l2.attack + " def: " + l2.defense + " exp: " + l2.experience + " HP: " + l2.health + "/" + l2.maxHealth + "\n");

        while ((l1.health > 0) || (l2.health > 0)) {

            sb.append(l1.color + "(" + l1.name + ")" + " hyökkää " + l2.color + "(" + l2.name + ")" + "\n");
            l2.defense(l1);
            if (l2.health > 0) {
                sb.append(l2.color + "(" + l1.name + ")" + " selvisi hengissä!\n");
                sb.append("1: " + l1.color + "(" + l1.name + ")" + " att: " + l1.attack + " def: " + l1.defense + " exp: " + l1.experience + " HP: " + l1.health + "/" + l1.maxHealth + "\n");
                sb.append("2: " + l2.color + "(" + l2.name + ")" + " att: " + l2.attack + " def: " + l2.defense + " exp: " + l2.experience + " HP: " + l2.health + "/" + l2.maxHealth + "\n");
            } else {
                sb.append(l2.color + "(" + l2.name + ")" + "kuoli.\n");
                l2.defeats++;
                l1.wins++;
                l1.experience++;
                break;
            }

            sb.append(l2.color + "(" + l2.name + ")" + " hyökkää " + l1.color + "(" + l1.name + ")" + "\n");
            l1.defense(l2);
            if (l1.health > 0) {
                sb.append(l1.color + "(" + l1.name + ")" + " selvisi hengissä!\n");
                sb.append("1: " + l1.color + "(" + l1.name + ")" + " att: " + l1.attack + " def: " + l1.defense + " exp: " + l1.experience + " HP: " + l1.health + "/" + l1.maxHealth + "\n");
                sb.append("2: " + l2.color + "(" + l2.name + ")" + " att: " + l2.attack + " def: " + l2.defense + " exp: " + l2.experience + " HP: " + l2.health + "/" + l2.maxHealth + "\n");
            } else {
                sb.append(l1.color + "(" + l1.name + ")" + " kuoli.\n");
                l1.defeats++;
                l2.wins++;
                l2.experience++;

                break;
            }

        }
        // Print the fight to the textfield
        textViewBattle.setText(sb);

        //return ArrayList<Lutemon> fighters;
    }
}