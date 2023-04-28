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
import android.widget.Toast;

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
        stats = findViewById(R.id.tvStats);
        radioGroup = findViewById(R.id.radioGroup);
        stats.setText("Luo itsellesi Lutemon!");


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.images, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        /*
        This listener changes the TextField to match the selected Radiobutton (Info of the Lutemon shown in txtField
         */
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbWhite:
                        stats.setText("Väri: Valkoinen\nDamage: 5\nPuolustus: 4\nMax HP: 20");
                        break;
                    case R.id.rbGreen:
                        stats.setText("Väri: Vihreä\nDamage: 6\nPuolustus: 3\nMax HP: 19");
                        break;
                    case R.id.rbPink:
                        stats.setText("Väri: Pinkki\nDamage: 7\nPuolustus: 2\nMax HP: 18");
                        break;
                    case R.id.rbOrange:
                        stats.setText("Väri: Oranssi\nDamage: 8\nPuolustus: 1\nMax HP: 17");
                        break;
                    case R.id.rbBlack:
                        stats.setText("Väri: Musta\nDamage: 9\nPuolustus: 0\nMax HP: 16");
                        break;

                }
            }
        });

        // Spinner used for selecting an image for the Lutemon
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (spinner.getSelectedItemPosition() == 0){
                    previewImg.setImageResource(image = R.drawable.happy);

                } else if (spinner.getSelectedItemPosition() == 1) {
                    previewImg.setImageResource(image = R.drawable.happy2);
                } else if (spinner.getSelectedItemPosition() == 2) {
                    previewImg.setImageResource(image = R.drawable.fighter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                previewImg.setImageResource(image = R.drawable.happy);
            }

        });
    }

    /*
    Lutemon and it's color created here by selecting from a Radiobutton. Spinner is used for selecting the image for the Lutemon
     */
    public void createLutemon(View view){
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Lutemon newLutemon = null;
        int choice = spinner.getSelectedItemPosition();


        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rbWhite:
                newLutemon = new White(lutemonName.getText().toString(), choice);
                break;
            case R.id.rbGreen:
                newLutemon = new Green(lutemonName.getText().toString(), choice);
                break;
            case R.id.rbPink:
                newLutemon = new Pink(lutemonName.getText().toString(), choice);
                break;
            case R.id.rbOrange:
                newLutemon = new Orange(lutemonName.getText().toString(), choice);
                break;
            case R.id.rbBlack:
                newLutemon = new Black(lutemonName.getText().toString(), choice);
                break;
        }
        Toast.makeText(getApplicationContext(),"Lutemon luotu!",Toast.LENGTH_SHORT).show();
    }

}

