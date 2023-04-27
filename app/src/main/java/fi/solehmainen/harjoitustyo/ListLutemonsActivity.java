package fi.solehmainen.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ListLutemonsActivity extends AppCompatActivity {

    private Storage storage;

    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lutemons);

        storage = Storage.getInstance();
        recyclerView = findViewById(R.id.rvLutemonList);
        ArrayList<Lutemon> allLutemons = new ArrayList<>();
        allLutemons.addAll(Storage.getInstance().getLutemonsAtHome());
        allLutemons.addAll(Storage.getInstance().getLutemonsAtArena());
        allLutemons.addAll(Storage.getInstance().getLutemonsAtTrain());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));;
        recyclerView.setAdapter(new LutemonListAdapter(getApplicationContext(), allLutemons));

    }
}