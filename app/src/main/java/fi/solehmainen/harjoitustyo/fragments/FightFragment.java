package fi.solehmainen.harjoitustyo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import fi.solehmainen.harjoitustyo.Lutemon;
import fi.solehmainen.harjoitustyo.R;
import fi.solehmainen.harjoitustyo.Storage;


public class FightFragment extends Fragment {
    protected ArrayList<Lutemon> lutemonsAtArena = new ArrayList<>();
    protected ArrayList<Lutemon> lutemonsAtHome = new ArrayList<>();
    protected ArrayList<Lutemon> lutemonsAtTrain = new ArrayList<>();
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
        linearLayout = view.findViewById(R.id.llFight);
        textViewBattle = view.findViewById(R.id.tvHome);
        Button moveButton = view.findViewById(R.id.btnMove);
        RadioButton moveToArena = view.findViewById(R.id.rbArena);
        RadioButton moveToTrain = view.findViewById(R.id.rbTrain);
        RadioButton moveToHome = view.findViewById(R.id.rbHome);


        lutemonsAtArena = Storage.getInstance().getLutemonsAtArena();

        makeCheckBoxes();

        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (moveToHome.isChecked()) {
                    int i = 0;

                    for (CheckBox c : boxes){

                        if (boxes.get(i).isChecked()) {
                            lutemonsAtHome = Storage.getInstance().getLutemonsAtHome();
                            lutemonsAtHome.add(Storage.getInstance().getLutemon(c.getId()));
                            //Storage.getInstance().setLutemonsAtHome(lutemonsAtHome);

                        }
                        i++;
                    }

                }

                if (moveToTrain.isChecked()) {
                    int i = 0;

                    for (CheckBox c : boxes){

                        if (boxes.get(i).isChecked()) {
                            lutemonsAtTrain = Storage.getInstance().getLutemonsAtTrain();
                            lutemonsAtTrain.add(Storage.getInstance().getLutemon(c.getId()));
                            //Storage.getInstance().setLutemonsAtArena(lutemonsAtTrain);

                        }
                        i++;
                    }
                }


                makeCheckBoxes();
            }
        });

        return view;
    }

    public void makeCheckBoxes() {
        lutemonsAtArena = Storage.getInstance().getLutemonsAtArena();
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

}
