package br.klosowski.eduardo.arduino.activities.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.klosowski.eduardo.arduino.R;

class MainItemViewHolder extends RecyclerView.ViewHolder {
    TextView textName;
    View viewValue;

    MainItemViewHolder(View itemView) {
        super(itemView);
        textName = (TextView) itemView.findViewById(R.id.name);
        viewValue = itemView.findViewById(R.id.value);
    }
}
