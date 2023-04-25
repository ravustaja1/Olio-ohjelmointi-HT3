package fi.solehmainen.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private Button save, load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Storage storage = Storage.getInstance();
        context = MainActivity.this;
        save = findViewById(R.id.btnSave);
        load = findViewById(R.id.btnLoad);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().saveLutemons(context);
                System.out.println("Lutemonit tallennettu");
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().loadLutemons(context);
                System.out.println("Lutemonit ladattu");
            }
        });

    }

    public void switchToCreate(View view) {
        Intent intent = new Intent(this, CreateLutemonActivity.class);
        startActivity(intent);
    }

    public void switchToList(View view) {
        Intent intent = new Intent(this, ListLutemonsActivity.class);
        startActivity(intent);
    }

    public void switchToBattle(View view) {
        Intent intent = new Intent(this, BattlefieldActivity.class);
        startActivity(intent);
    }

}