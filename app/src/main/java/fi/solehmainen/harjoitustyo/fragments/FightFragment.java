package fi.solehmainen.harjoitustyo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import fi.solehmainen.harjoitustyo.Lutemon;
import fi.solehmainen.harjoitustyo.R;
import fi.solehmainen.harjoitustyo.Storage;


public class FightFragment extends Fragment {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fight, container, false);
        fightButton = view.findViewById(R.id.btnFight);

        linearLayout = view.findViewById(R.id.llFighters);
        textViewBattle = view.findViewById(R.id.tvBattle);
        fightButton = view.findViewById(R.id.btnFight);


        makeCheckBoxes();

        /*
        When "Start Fight" button is pressed it will be checked that 2 fighters are selected. If the condition applies, the fight starts.
         */
        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i = 0;
                numchecked = 0;
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
                    Toast.makeText(getContext(),"Valitse tasan kaksi taistelijaa!",Toast.LENGTH_SHORT).show();
                    numchecked = 0;
                }
                // Testi tulostus
                for (Lutemon lutemon : fighters) {
                    System.out.println(lutemon.getName() + " lisättiin taistelulistaan.");
                }
            }
        });
        //return inflater.inflate(R.layout.fragment_fight, container, false);
        return view;
    }

    public void makeCheckBoxes() {
        lutemonsAtArena = Storage.getInstance().getLutemonArrayList();
        linearLayout.removeAllViews();
        boxes.clear();


        CheckBox checkBox;
        int i = 0;
        for (Lutemon l : lutemonsAtArena) {
            checkBox = new CheckBox(getContext());
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

        while ((l1.getHealth() > 0) || (l2.getHealth() > 0)) {

            sb.append(l1.getColor() + "(" + l1.getName() + ")" + " hyökkää " + l2.getColor() + "(" + l2.getName() + ")" + "\n");
            l2.defense(l1);
            if (l2.getHealth() > 0) {
                sb.append(l2.getColor() + "(" + l1.getName() + ")" + " selvisi hengissä!\n");
                sb.append("1: " + l1.getColor() + "(" + l1.getName() + ")" + " att: " + l1.getAttack() + " def: " + l1.getDefense() + " exp: " + l1.getExperience() + " HP: " + l1.getHealth() + "/" + l1.getMaxHealth() + "\n");
                sb.append("2: " + l2.getColor() + "(" + l2.getName() + ")" + " att: " + l2.getAttack() + " def: " + l2.getDefense() + " exp: " + l2.getExperience() + " HP: " + l2.getHealth() + "/" + l2.getMaxHealth() + "\n");
            } else {
                l2.setDefeats(1);
                if (l2.getDefeats() == 3) {
                    sb.append(l2.getColor() + "(" + l2.getName() + ")" + " kuoli.\n");
                    l1.setWins(1);
                    l1.setExperience(1);
                    Storage.getInstance().removeLutemon(l2.getId());
                } else {
                    sb.append(l2.getColor() + "(" + l2.getName() + ")" + " hävisi.\n");
                    //l2.setDefeats(1);
                    l1.setWins(1);
                    l1.setExperience(1);
                }

                break;
            }

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
                    Storage.getInstance().removeLutemon(l1.getId());
                } else {
                    sb.append(l1.getColor() + "(" + l1.getName() + ")" + " hävisi.\n");
                    //l1.setDefeats(1);
                    l2.setWins(1);
                    l2.setExperience(1);
                }

                break;
            }

        }
        l1.setMaxHealth();
        l2.setMaxHealth();
        // Print the fight to the textfield
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