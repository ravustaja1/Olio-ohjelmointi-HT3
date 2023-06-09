package fi.solehmainen.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class BattlefieldActivity extends AppCompatActivity {
    private ArrayList<Lutemon> lutemonsAtArena = new ArrayList<>();
    private ArrayList<Lutemon> fighters = new ArrayList<>();
    private LinearLayout linearLayout;
    private Button fightButton;
    private TextView textViewBattle;
    private Random randomN = new Random();
    //private CheckBox checkBox;

    private int numchecked = 0;
    private ArrayList<CheckBox> boxes = new ArrayList<>();
    private ArrayList<CheckBox> boxesChecked = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        linearLayout = findViewById(R.id.llTrain);
        textViewBattle = findViewById(R.id.tvTrain);
        fightButton = findViewById(R.id.btnMove);


        lutemonsAtArena = Storage.getInstance().getLutemonsAtArena();


        makeCheckBoxes();

        /*
        When "Start Fight" button is pressed it will be checked that 2 fighters are selected. If the condition applies, the fight starts.
         */
        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                numchecked = 0;

                for (CheckBox c: boxes) {
                    if (c.isChecked()) {
                        numchecked++;
                        boxesChecked.add(c);
                    }
                }
                if (numchecked == 2) {

                    for (CheckBox c1 : boxes){

                        if (c1.isChecked()) {
                            fighters.add(Storage.getInstance().getLutemon(c1.getId(), "ARENA"));
                        }

                    }
                    fight(fighters);
                } else {
                    Toast.makeText(getApplicationContext(),"Valitse tasan kaksi taistelijaa!",Toast.LENGTH_SHORT).show();
                    numchecked = 0;
                }
            }
        });

    }
/*
    Method used for creating checkboxes for fighters to pick from
 */
    public void makeCheckBoxes() {
        lutemonsAtArena = Storage.getInstance().getLutemonsAtArena();
        linearLayout.removeAllViews();
        boxes.clear();


        CheckBox checkBox;
        int i = 0;
        for (Lutemon l : lutemonsAtArena) {
            checkBox = new CheckBox(this);
            checkBox.setText(l.getName() + "(" + l.getColor() + ")");
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

        // Randomize the first attacker
        int starter = randomStarter();
        int second = -1;
        if (starter == 0) {
            second = 1;
        } else {
            second = 0;
        }

        Lutemon l1 = fighters.get(starter);
        Lutemon l2 = fighters.get(second);


        StringBuilder sb = new StringBuilder();
        textViewBattle.setText("");
        sb.append("1: " + l1.getColor() + "(" + l1.getName() + ")" + " att: " + l1.getAttack() + " def: " + l1.getDefense() + " exp: " + l1.getExperience() + " HP: " + l1.getHealth() + "/" + l1.getMaxHealth() + "\n");
        sb.append("2: " + l2.getColor() + "(" + l2.getName() + ")" + " att: " + l2.getAttack() + " def: " + l2.getDefense() + " exp: " + l2.getExperience() + " HP: " + l2.getHealth() + "/" + l2.getMaxHealth() + "\n");

        // While loop which keeps the fight going on until one loses
        while ((l1.getHealth() > 0) || (l2.getHealth() > 0)) {

            /*
            StringBuilder appends the ongoing fight and it's happenings. L2 defends from the L1's attack. If one of the fighter's HP goes to < 0, he/she loses the fight. The winner receives +1 win, +1 exp and is set to
            max health. The loser receives +1 lost fight and max health unless it is his/hers 3rd lost; then he dies and is removed from the game. After the match, both fighters' "hasTrained" attribute is set to false.
             */
            sb.append(l1.getColor() + "(" + l1.getName() + ")" + " hyökkää " + l2.getColor() + "(" + l2.getName() + ")" + "\n");
            l2.defense(l1);
            if (l2.getHealth() > 0) {
                sb.append(l2.getColor() + "(" + l2.getName() + ")" + " selvisi hengissä!\n");
                sb.append("1: " + l1.getColor() + "(" + l1.getName() + ")" + " att: " + l1.getAttack() + " def: " + l1.getDefense() + " exp: " + l1.getExperience() + " HP: " + l1.getHealth() + "/" + l1.getMaxHealth() + "\n");
                sb.append("2: " + l2.getColor() + "(" + l2.getName() + ")" + " att: " + l2.getAttack() + " def: " + l2.getDefense() + " exp: " + l2.getExperience() + " HP: " + l2.getHealth() + "/" + l2.getMaxHealth() + "\n");
            } else {
                l2.setDefeats(1);
                if (l2.getDefeats() == 3) {
                    sb.append(l2.getColor() + "(" + l2.getName() + ")" + " kuoli.\n");
                    l1.setWins(1);
                    l1.setExperience(1);
                    l1.setMaxHealth();
                    lutemonsAtArena.remove(l2);
                    l1.hasTrained = false;


                } else {
                    sb.append(l2.getColor() + "(" + l2.getName() + ")" + " hävisi.\n");
                    l1.setWins(1);
                    l1.setExperience(1);
                    l1.setMaxHealth();
                    l2.setMaxHealth();
                    l2.hasTrained = false;
                    l1.hasTrained = false;
                }

                break;
            }
            /*
            StringBuilder appends the ongoing fight and it's happenings. L1 defends from the L2's attack. If one of the fighter's HP goes to < 0, he/she loses the fight. The winner receives +1 win, +1 exp and is set to
            max health. The loser receives +1 lost fight and max health unless it is his/hers 3rd lost; then he dies and is removed from the game.
             */
            sb.append(l2.getColor() + "(" + l2.getName() + ")" + " hyökkää " + l1.getColor() + "(" + l1.getName() + ")" + "\n");
            l1.defense(l2);
            if (l1.getHealth() > 0) {
                sb.append(l1.getColor() + "(" + l1.getName() + ")" + " selvisi hengissä!\n");
                sb.append("1: " + l1.getColor() + "(" + l1.getName() + ")" + " att: " + l1.getAttack() + " def: " + l1.getDefense() + " exp: " + l1.getExperience() + " HP: " + l1.getHealth() + "/" + l1.getMaxHealth() + "\n");
                sb.append("2: " + l2.getColor() + "(" + l2.getName() + ")" + " att: " + l2.getAttack() + " def: " + l2.getDefense() + " exp: " + l2.getExperience() + " HP: " + l2.getHealth() + "/" + l2.getMaxHealth() + "\n");
            } else {
                l1.setDefeats(1);
                if (l1.getDefeats() == 3) {
                    sb.append(l1.getColor() + "(" + l1.getName() + ")" + " kuoli.\n");
                    l2.setWins(1);
                    l2.setExperience(1);
                    l2.setMaxHealth();
                    lutemonsAtArena.remove(l1);
                    l2.hasTrained = false;


                } else {
                    sb.append(l1.getColor() + "(" + l1.getName() + ")" + " hävisi.\n");
                    l2.hasTrained = false;
                    l1.hasTrained = false;
                    l2.setWins(1);
                    l2.setExperience(1);
                    l1.setMaxHealth();
                    l2.setMaxHealth();
                }

                break;
            }

        }


        // Print the fight's result to the textfield
        textViewBattle.setText(sb);

        // Prepare for next press of the "Fight" button
        numchecked = 0;
        fighters.clear();
        makeCheckBoxes();

    }

    /*
    Method used for generating a random number which is later used in fight() to determine the first attacker
     */
    public int randomStarter() {
        int starter = 0;
        int min=0, max=2;
        starter = min + randomN.nextInt(max);
        return starter;
    }

}