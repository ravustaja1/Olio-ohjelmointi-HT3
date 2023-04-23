package fi.solehmainen.harjoitustyo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder {
    protected ImageView lutemonImage;
    TextView name, color, attack, defense, wins, defeats, experience, health, maxHealth;
    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);

        lutemonImage = itemView.findViewById(R.id.ivLutemonImg);
        name = itemView.findViewById(R.id.txtLutemonName);
        color = itemView.findViewById(R.id.txtLutemonColor);
        attack = itemView.findViewById(R.id.txtLutemonAttack);
        defense = itemView.findViewById(R.id.txtLutemonDefense);
        experience = itemView.findViewById(R.id.txtLutemonExp);
        maxHealth = itemView.findViewById(R.id.txtLutemonMaxHealth);
        health = itemView.findViewById(R.id.txtLutemonHealth);
        wins = itemView.findViewById(R.id.txtLutemonWins);
        defeats = itemView.findViewById(R.id.txtLutemonDefeats);

    }
}