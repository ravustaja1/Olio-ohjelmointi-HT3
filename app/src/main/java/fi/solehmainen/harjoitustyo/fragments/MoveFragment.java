package fi.solehmainen.harjoitustyo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fi.solehmainen.harjoitustyo.Lutemon;
import fi.solehmainen.harjoitustyo.R;
import fi.solehmainen.harjoitustyo.Storage;


public class MoveFragment extends Fragment {

    private ArrayList<CheckBox> boxes = new ArrayList<>();
    private ArrayList<Lutemon> lutemonsAtArena = new ArrayList<>();
    private int numchecked = 0;

    private RadioGroup radioGroupLutemons;
    private TextView tvInfo;

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
        View view = inflater.inflate(R.layout.fragment_move, container, false);

        Button fightButton = view.findViewById(R.id.btnMoveLutemon);


        makeCheckBoxes();


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

    public void makeRadioButtons () {
        RadioGroup rgLutemons = findViewById(R.id.rgLutemons);
        rgLutemons.removeAllViews();

        ArrayList<Lutemon> lutemons = Storage.getInstance().getLutemonArrayList();

        RadioButton rb;
        int i = 0;
        for (Lutemon l : lutemons) {
            rb = new RadioButton(getContext());
            rb.setText(l.getName());
            rb.setId(i++);
            radioGroupLutemons.addView(rb);
        }
    }
}