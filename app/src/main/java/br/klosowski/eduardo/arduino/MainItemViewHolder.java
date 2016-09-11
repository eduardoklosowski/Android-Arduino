package br.klosowski.eduardo.arduino;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MainItemViewHolder extends RecyclerView.ViewHolder {
    public TextView textName;

    public MainItemViewHolder(View itemView) {
        super(itemView);
        textName = (TextView) itemView.findViewById(R.id.name);
    }
}
