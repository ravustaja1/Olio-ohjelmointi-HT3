package fi.solehmainen.harjoitustyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {
    private Context context;
    private HashMap<Integer, Lutemon> lutemonHashMap;
    public LutemonListAdapter(Context context, HashMap<Integer, Lutemon> lutemonHashMap) {
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

        holder.lutemonImage.setImageResource(lutemonHashMap.get(position).getImage());
        holder.name.setText("Nimi: " + lutemonHashMap.get(position).name);
        holder.color.setText("Väri: " + lutemonHashMap.get(position).color);
        holder.attack.setText("Hyökkäys: " + String.valueOf(lutemonHashMap.get(position).attack));
        holder.defense.setText("Puolustus: " + String.valueOf(lutemonHashMap.get(position).defense));
        holder.experience.setText("Kokemus: " + String.valueOf(lutemonHashMap.get(position).experience));
        holder.maxHealth.setText("Täydet elämäpisteet: " + String.valueOf(lutemonHashMap.get(position).maxHealth));
        holder.health.setText("Elämäpisteet: " + String.valueOf(lutemonHashMap.get(position).health));
        holder.wins.setText("Voitot: " + String.valueOf(lutemonHashMap.get(position).wins));
        holder.defeats.setText("Tappiot: " + String.valueOf(lutemonHashMap.get(position).defeats));
        holder.delete.setImageResource(lutemonHashMap.get(position).getImage2());
        //holder.edit.setImageResource(lutemonHashMap.get(position).getImage3());
        //holder.editText.setText(lutemonHashMap.get(position).name);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                Storage.getInstance().getLutemonHashMap().remove(pos);
                notifyItemRemoved(pos);
            }
        });
/*
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();

                if(holder.editText.getVisibility() == View.VISIBLE) {
                    Lutemon lutemon = Storage.getInstance().getLutemon(pos);
                    lutemon.setName(holder.editText.getText().toString());
                    holder.editText.setVisibility(View.INVISIBLE);
                    notifyDataSetChanged();
                }
                else {
                    holder.editText.setVisibility(View.VISIBLE);
                }

            }
        });
*/
    }

    @Override
    public int getItemCount() {
        return lutemonHashMap.size();
    }
}
