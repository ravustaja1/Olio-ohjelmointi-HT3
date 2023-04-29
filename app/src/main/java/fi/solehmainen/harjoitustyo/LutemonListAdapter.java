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
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
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
        holder.name.setText("Nimi: " + lutemons.get(position).getName());
        holder.color.setText("Väri: " + lutemons.get(position).getColor());
        holder.attack.setText("Hyökkäys: " + String.valueOf(lutemons.get(position).getAttack()));
        holder.defense.setText("Puolustus: " + String.valueOf(lutemons.get(position).getDefense()));
        holder.experience.setText("Kokemus: " + String.valueOf(lutemons.get(position).getExperience()));
        holder.maxHealth.setText("Täydet elämäpisteet: " + String.valueOf(lutemons.get(position).getMaxHealth()));
        holder.health.setText("Elämäpisteet: " + String.valueOf(lutemons.get(position).getHealth()));
        holder.wins.setText("Voitot: " + String.valueOf(lutemons.get(position).getWins()));
        holder.defeats.setText("Tappiot: " + String.valueOf(lutemons.get(position).getDefeats()));
        holder.delete.setImageResource(lutemons.get(position).getImage2());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                Storage.getInstance().removeLutemon(lutemons.get(pos).getId());
                notifyItemRemoved(pos);

            }
        });
        holder.trained.setText("Harjoittelukertojen määrä: " + String.valueOf(lutemons.get(position).getTrains()));

        if (lutemons.get(position).isHasTrained() == true) {
            holder.exhausted.setText("Onko väsynyt: On");
        } else {
            holder.exhausted.setText("Onko väsynyt: Ei");
        }

    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
