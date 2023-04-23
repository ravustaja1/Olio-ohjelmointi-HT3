package fi.solehmainen.harjoitustyo;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {
    private Context context;
    private HashMap<Integer, Lutemon> lutemonHashMap;
    public LutemonListAdapter(Context applicationContext, HashMap<Integer, Lutemon> lutemonHashMap) {
        this.context = context;
        this.lutemonHashMap = lutemonHashMap;
    }


    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
