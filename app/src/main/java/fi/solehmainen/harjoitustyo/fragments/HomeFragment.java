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


public class HomeFragment extends Fragment {
    protected ArrayList<Lutemon> lutemonsAtHome = new ArrayList<>();
    protected ArrayList<Lutemon> lutemonsAtTrain = new ArrayList<>();
    protected ArrayList<Lutemon> lutemonsAtArena = new ArrayList<>();

    private LinearLayout linearLayout;
    private Button moveButton;
    private TextView textViewHome;

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        linearLayout = view.findViewById(R.id.llHome);
        textViewHome = view.findViewById(R.id.tvHome);
        moveButton = view.findViewById(R.id.btnMove);
        RadioButton moveToArena = view.findViewById(R.id.rbArena);
        RadioButton moveToTrain = view.findViewById(R.id.rbTrain);
        RadioButton moveToHome = view.findViewById(R.id.rbHome);


        lutemonsAtHome = Storage.getInstance().getLutemonsAtHome();
        lutemonsAtTrain = Storage.getInstance().getLutemonsAtTrain();
        lutemonsAtArena = Storage.getInstance().getLutemonsAtArena();

        onResume();
        StringBuilder sb = new StringBuilder();

        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moveToArena.isChecked()) {
                    int i = 0;

                    for (CheckBox c : boxes){

                        if (boxes.get(i).isChecked()) {
                            Storage.getInstance().moveLutemon(Storage.getInstance().getLutemon(c.getId(), "HOME"), lutemonsAtArena, lutemonsAtHome);
                            sb.append("Lutemon " + c.getText() + " siirtyi areenalle.\n");
                            textViewHome.setText(sb);
                        }
                        i++;
                    }

                }

                if (moveToTrain.isChecked()) {
                    int i = 0;

                    for (CheckBox c : boxes){

                        if (boxes.get(i).isChecked()) {
                            Storage.getInstance().moveLutemon(Storage.getInstance().getLutemon(c.getId(), "HOME"), lutemonsAtTrain, lutemonsAtHome);
                            sb.append("Lutemon " + c.getText() + " siirtyi treenaamaan.\n");
                            textViewHome.setText(sb);
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
        lutemonsAtHome = Storage.getInstance().getLutemonsAtHome();
        linearLayout.removeAllViews();
        boxes.clear();


        CheckBox checkBox;
        int i = 0;
        for (Lutemon l : lutemonsAtHome) {
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