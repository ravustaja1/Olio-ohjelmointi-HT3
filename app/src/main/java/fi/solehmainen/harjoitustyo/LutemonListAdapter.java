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
    public LutemonListAdapter(Context context, HashMap<Integer, Lutemon> lutemonHashMap) {
        this.context = context;
        this.lutemonHashMap = Storage.getInstance().getLutemonHashMap();
    }


    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {

        holder.name.setText(lutemonHashMap.get(position).name);
        holder.color.setText(lutemonHashMap.get(position).color);
        holder.attack.setText(lutemonHashMap.get(position).attack);
        holder.defense.setText(lutemonHashMap.get(position).defense);
        holder.experience.setText(lutemonHashMap.get(position).experience);
        holder.maxHealth.setText(lutemonHashMap.get(position).maxHealth);
        holder.health.setText(lutemonHashMap.get(position).health);
        holder.wins.setText(lutemonHashMap.get(position).wins);
        holder.defeats.setText(lutemonHashMap.get(position).defense);

    }

    @Override
    public int getItemCount() {
        return lutemonHashMap.size();
    }
}
