package fi.solehmainen.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CreateLutemonActivity extends AppCompatActivity {

    private RadioButton white, green, pink, orange, black;

    private EditText lutemonName;

    private Button createLutemon;

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



    }

    public void createLutemon(){
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Lutemon newLutemon = null;

        // Lutemoneilta puuttuu arvot, pitäisikö ne tehdä vaikka sen taulukon pohjalta?
        // Tonne ite Activityyn olisi hieno saada näkyviin tekstikenttään ne arvot, jotka päivittyy, kun painaa eri nappulaa
        // Toki kömpelömpi versio olisi laittaa siihen vain taulukko, jossa näkyy kaikkien arvot
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rbWhite:
                newLutemon = new Lutemon();
                break;
            case R.id.rbGreen:
                newLutemon = new Lutemon();
                break;
            case R.id.rbPink:
                newLutemon = new Lutemon();
                break;
            case R.id.rbOrange:
                newLutemon = new Lutemon();
                break;
            case R.id.rbBlack:
                newLutemon = new Lutemon();
                break;

            // Kun lisätään tämä uusi lutemon hashmappiin niin eikös meidän pitäisi saada oikea ID, tolla hashmapin koolla?
            Storage.getInstance().lutemonHashMap.put(Storage.getInstance().lutemonHashMap.size(), newLutemon);
        }
    }

}