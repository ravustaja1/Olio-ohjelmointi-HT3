package fi.solehmainen.harjoitustyo;

import android.content.Context;
import android.view.LayoutInflater;
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
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lutemonHashMap.size();
    }
}
