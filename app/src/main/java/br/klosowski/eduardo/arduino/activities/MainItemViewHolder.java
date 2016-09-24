package br.klosowski.eduardo.arduino.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.klosowski.eduardo.arduino.R;

public class MainItemViewHolder extends RecyclerView.ViewHolder {
    public TextView textName;

    public MainItemViewHolder(View itemView) {
        super(itemView);
        textName = (TextView) itemView.findViewById(R.id.name);
    }
}
