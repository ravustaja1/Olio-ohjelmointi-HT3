package fi.solehmainen.harjoitustyo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateLutemonActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton white, green, pink, orange, black;

    private TextView stats;

    private Spinner spinner;

    private EditText lutemonName;
    private int image;
    private ImageView previewImg;

    private Button createLutemon;

    //ArrayList<Lutemon> lutemons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lutemon);


        lutemonName = findViewById(R.id.editName);
        createLutemon = findViewById(R.id.btnCreate);
        previewImg = findViewById(R.id.ivPreviewImg);
        spinner = findViewById(R.id.spinner);
        white = findViewById(R.id.rbWhite);
        pink = findViewById(R.id.rbPink);
        pink = findViewById(R.id.rbPink);
        orange = findViewById(R.id.rbBlack);
        stats = findViewById(R.id.tvStats);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.images, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (spinner.getSelectedItemPosition() == 0){

                    previewImg.setImageResource(image = R.drawable.happy);
                }

                if (spinner.getSelectedItemPosition() == 1){
                    previewImg.setImageResource(image = R.drawable.happy2);
                    //image = R.drawable.ic_android_black_24dp;
                }

                if (spinner.getSelectedItemPosition() == 2){
                    previewImg.setImageResource(image = R.drawable.fighter);
                    //image = R.drawable.ic_android_black_24dp;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                image = R.drawable.happy;
            }

        });
    }

    public void createLutemon(View view){
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Lutemon newLutemon = null;
        int choice = spinner.getSelectedItemPosition();
        // Lutemoneilta puuttuu arvot, pitäisikö ne tehdä vaikka sen taulukon pohjalta?
        // Tonne ite Activityyn olisi hieno saada näkyviin tekstikenttään ne arvot, jotka päivittyy, kun painaa eri nappulaa
        // Toki kömpelömpi versio olisi laittaa siihen vain taulukko, jossa näkyy kaikkien arvot
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rbWhite:
                newLutemon = new Lutemon(lutemonName.getText().toString(), "White", 5, 4, 0, 20, 20, choice);
                //color = "White";
                break;
            case R.id.rbGreen:
                newLutemon = new Lutemon(lutemonName.getText().toString(), "Green", 6, 3, 0, 19, 19, choice);
                //color = "Green";
                break;
            case R.id.rbPink:
                newLutemon = new Lutemon(lutemonName.getText().toString(), "Pink", 7, 2, 0, 18, 18, choice);
                //color = "Pink";
                break;
            case R.id.rbOrange:
                newLutemon = new Lutemon(lutemonName.getText().toString(), "Orange", 8, 1, 0, 17, 17, choice);
                //color = "Orange";
                break;
            case R.id.rbBlack:
                newLutemon = new Lutemon(lutemonName.getText().toString(), "Black", 9, 0, 0, 16, 16, choice);
                //color = "Black";
                break;
        }

        // Kun lisätään tämä uusi lutemon hashmappiin niin eikös meidän pitäisi saada oikea ID, tolla hashmapin koolla?
        // Pitäisikö tolle napille saada joku listeneri, jolloin se luo ton Lutemonin nappia painaessa ja toinen joka vaihtaa tekstikentän tekstiä kun noita värejä vaihtaa?
        Storage.getInstance().lutemonHashMap.put(Storage.getInstance().lutemonHashMap.size(), newLutemon);

        //Tähän vois tehdä sen tallennuksen tiedostoon?

        //Storage.getInstance().saveLutemons(this);

    }
/*
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        if (view.getId() == R.id.rbWhite) {
            stats.setText("Väri: Valkoinen\nDamage: 5\nPuolustus: 4\nMax HP: 20");
        } else if (view.getId() == R.id.rbGreen) {
            stats.setText("Väri: Vihreä\nDamage: 6\nPuolustus: 3\nMax HP: 19");
        }

    }

 */
}

