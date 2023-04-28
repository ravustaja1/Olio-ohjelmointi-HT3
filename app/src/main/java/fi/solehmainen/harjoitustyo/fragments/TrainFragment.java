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

import fi.solehmainen.harjoitustyo.Lutemon;
import fi.solehmainen.harjoitustyo.R;
import fi.solehmainen.harjoitustyo.Storage;


public class TrainFragment extends Fragment {
    protected ArrayList<Lutemon> lutemonsAtTrain = new ArrayList<>();
    protected ArrayList<Lutemon> lutemonsAtHome = new ArrayList<>();
    protected ArrayList<Lutemon> lutemonsAtArena = new ArrayList<>();
    private LinearLayout linearLayout;
    private Button moveButton;
    private TextView textViewTrain;

    private CheckBox checkBox;

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
        View view = inflater.inflate(R.layout.fragment_train, container, false);


        linearLayout = view.findViewById(R.id.llTrain);
        textViewTrain = view.findViewById(R.id.tvTrain);
        Button moveButton = view.findViewById(R.id.btnMove);
        RadioButton moveToHome = view.findViewById(R.id.rbHome);
        RadioButton moveToTrain = view.findViewById(R.id.rbTrain);
        RadioButton moveToArena = view.findViewById(R.id.rbArena);

        lutemonsAtTrain = Storage.getInstance().getLutemonsAtTrain();
        lutemonsAtHome = Storage.getInstance().getLutemonsAtHome();
        lutemonsAtArena = Storage.getInstance().getLutemonsAtArena();

        makeCheckBoxes();

        StringBuilder sb = new StringBuilder();
        //makeRadioButtons();
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (moveToArena.isChecked()) {
                    int i = 0;

                    for (CheckBox c : boxes){

                        if (boxes.get(i).isChecked()) {
                            Storage.getInstance().moveLutemon(Storage.getInstance().getLutemon(c.getId(), "TRAIN"), lutemonsAtArena, lutemonsAtTrain);
                            //sb.append("Lutemon " + lutemonsAtArena.get(c.getId()) + "siirtyi areenalle.\n");
                            //textViewTrain.setText(sb);
                            //Storage.getInstance().moveToArena(Storage.getInstance().getLutemon(c.getId()), lutemonsAtTrain, lutemonsAtArena);
                        }
                        i++;
                    }

                }

                if (moveToHome.isChecked()) {
                    int i = 0;

                    for (CheckBox c : boxes){

                        if (boxes.get(i).isChecked()) {
                            Storage.getInstance().moveLutemon(Storage.getInstance().getLutemon(c.getId(), "TRAIN"), lutemonsAtHome, lutemonsAtTrain);
                            //sb.append("Lutemon " + lutemonsAtHome.get(c.getId()) + "siirtyi kotiin.\n");
                            //textViewTrain.setText(sb);
                            //Storage.getInstance().moveToHome(Storage.getInstance().getLutemon(c.getId()), lutemonsAtTrain, lutemonsAtHome);
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
        lutemonsAtTrain = Storage.getInstance().getLutemonsAtTrain();
        linearLayout.removeAllViews();
        boxes.clear();


        CheckBox checkBox;
        int i = 0;
        for (Lutemon l : lutemonsAtTrain) {
            checkBox = new CheckBox(getContext());
            checkBox.setText(l.getName() + "(" + l.getColor() + ")");
            checkBox.setId(i++);
            linearLayout.addView(checkBox);
            boxes.add(checkBox);
        }

    }

    public void train() {

    }

}