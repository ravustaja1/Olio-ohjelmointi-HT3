package fi.solehmainen.harjoitustyo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fi.solehmainen.harjoitustyo.Lutemon;
import fi.solehmainen.harjoitustyo.LutemonListAdapter;
import fi.solehmainen.harjoitustyo.R;
import fi.solehmainen.harjoitustyo.Storage;


public class HomeFragment extends Fragment {
    private Storage storage;

    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private RecyclerView recyclerView;

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

        //Storage.getInstance();
        recyclerView = view.findViewById(R.id.rvLutemonList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));;
        recyclerView.setAdapter(new LutemonListAdapter(getContext(), Storage.getInstance().getLutemonArrayList()));

        return view;
    }
}