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

import java.util.ArrayList;
import java.util.Collections;

import fi.solehmainen.harjoitustyo.Lutemon;
import fi.solehmainen.harjoitustyo.R;
import fi.solehmainen.harjoitustyo.Storage;


public class FightFragment extends Fragment {
    protected ArrayList<Lutemon> lutemonsAtArena = new ArrayList<>();
    protected ArrayList<Lutemon> lutemonsAtHome = new ArrayList<>();
    protected ArrayList<Lutemon> lutemonsAtTrain = new ArrayList<>();
    private LinearLayout linearLayout;
    private TextView textViewBattle;
    private CheckBox checkBox;


    private ArrayList<CheckBox> boxes = new ArrayList<>();

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
        textViewBattle = view.findViewById(R.id.tvFight);
        Button moveButton = view.findViewById(R.id.btnMove);
        RadioButton moveToArena = view.findViewById(R.id.rbArena);
        RadioButton moveToTrain = view.findViewById(R.id.rbTrain);
        RadioButton moveToHome = view.findViewById(R.id.rbHome);


        lutemonsAtArena = Storage.getInstance().getLutemonsAtArena();
        lutemonsAtTrain = Storage.getInstance().getLutemonsAtTrain();
        lutemonsAtHome = Storage.getInstance().getLutemonsAtHome();

        onResume();
        StringBuilder sb = new StringBuilder();

        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (moveToHome.isChecked()) {
                    int i = 0;

                    for (CheckBox c : boxes){

                        if (boxes.get(i).isChecked()) {
                            Storage.getInstance().moveLutemon(Storage.getInstance().getLutemon(c.getId(), "ARENA"), lutemonsAtHome, lutemonsAtArena);
                            sb.append("Lutemon " + c.getText() + " siirtyi kotiin.\n");
                            textViewBattle.setText(sb);
                        }
                        i++;
                    }

                }

                if (moveToTrain.isChecked()) {
                    int i = 0;

                    for (CheckBox c : boxes){

                        if (boxes.get(i).isChecked()) {
                            Storage.getInstance().moveLutemon(Storage.getInstance().getLutemon(c.getId(), "ARENA"), lutemonsAtTrain, lutemonsAtArena);
                            sb.append("Lutemon " + c.getText() + " siirtyi treenaamaan.\n");
                            textViewBattle.setText(sb);
                        }
                        i++;
                    }
                }


                onResume();
            }
        });

        return view;
    }
// Method to create the checkboxes for Lutemons
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

// This makes the move visible when pressing the button
    public void onResume() {
        super.onResume();
        makeCheckBoxes();
    }
}
