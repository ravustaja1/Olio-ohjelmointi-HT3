package fi.solehmainen.harjoitustyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {
    private Context context;
    private ArrayList<Lutemon> lutemons;
    public LutemonListAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }


    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {

        holder.lutemonImage.setImageResource(lutemons.get(position).getImage());
        holder.name.setText("Nimi: " + lutemons.get(position).name);
        holder.color.setText("Väri: " + lutemons.get(position).color);
        holder.attack.setText("Hyökkäys: " + String.valueOf(lutemons.get(position).attack));
        holder.defense.setText("Puolustus: " + String.valueOf(lutemons.get(position).defense));
        holder.experience.setText("Kokemus: " + String.valueOf(lutemons.get(position).experience));
        holder.maxHealth.setText("Täydet elämäpisteet: " + String.valueOf(lutemons.get(position).maxHealth));
        holder.health.setText("Elämäpisteet: " + String.valueOf(lutemons.get(position).health));
        holder.wins.setText("Voitot: " + String.valueOf(lutemons.get(position).wins));
        holder.defeats.setText("Tappiot: " + String.valueOf(lutemons.get(position).defeats));
        holder.delete.setImageResource(lutemons.get(position).getImage2());
        //holder.edit.setImageResource(lutemonHashMap.get(position).getImage3());
        //holder.editText.setText(lutemonHashMap.get(position).name);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                Storage.getInstance().lutemons.remove(pos);
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
        return lutemons.size();
    }
}
