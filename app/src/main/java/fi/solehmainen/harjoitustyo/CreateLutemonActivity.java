package fi.solehmainen.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CreateLutemonActivity extends AppCompatActivity {

    private RadioButton white, green, pink, orange, black;

    private TextView stats;

    private EditText lutemonName;

    private Button createLutemon;

    //ArrayList<Lutemon> lutemons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lutemon);


        lutemonName = findViewById(R.id.editName);
        createLutemon = findViewById(R.id.btnCreate);
        white = findViewById(R.id.rbWhite);
        pink = findViewById(R.id.rbPink);
        pink = findViewById(R.id.rbPink);
        orange = findViewById(R.id.rbBlack);
        stats = findViewById(R.id.tvStats);


    }

    public void createLutemon(){
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Lutemon newLutemon = null;

        // Lutemoneilta puuttuu arvot, pitäisikö ne tehdä vaikka sen taulukon pohjalta?
        // Tonne ite Activityyn olisi hieno saada näkyviin tekstikenttään ne arvot, jotka päivittyy, kun painaa eri nappulaa
        // Toki kömpelömpi versio olisi laittaa siihen vain taulukko, jossa näkyy kaikkien arvot
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rbWhite:
                newLutemon = new Lutemon(lutemonName.getText().toString(), "White", 5, 4, 0, 20, 20);
                break;
            case R.id.rbGreen:
                newLutemon = new Lutemon(lutemonName.getText().toString(), "Green", 6, 3, 0, 19, 19);
                break;
            case R.id.rbPink:
                newLutemon = new Lutemon(lutemonName.getText().toString(), "Pink", 7, 2, 0, 18, 18);
                break;
            case R.id.rbOrange:
                newLutemon = new Lutemon(lutemonName.getText().toString(), "Orange", 8, 1, 0, 17, 17);
                break;
            case R.id.rbBlack:
                newLutemon = new Lutemon(lutemonName.getText().toString(), "Black", 9, 0, 0, 16, 16);
                break;


            // Kun lisätään tämä uusi lutemon hashmappiin niin eikös meidän pitäisi saada oikea ID, tolla hashmapin koolla?
            // Jotain häikkää tässä muutenkin on, kun punaista näyttää
            Storage.getInstance().lutemonHashMap.put(newLutemon.getNumberOfCreatedLutemons(), newLutemon);

            //Tähän vois tehdä sen tallennuksen tiedostoon?

            Storage.getInstance().saveLutemons(this);

        }
    }

}