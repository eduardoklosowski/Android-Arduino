package br.klosowski.eduardo.arduino.activities.generics;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.klosowski.eduardo.arduino.R;

class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView textName;
    ImageView buttonEdit;
    ImageView buttonDelete;

    ItemViewHolder(View itemView) {
        super(itemView);
        textName = (TextView) itemView.findViewById(R.id.name);
        buttonEdit = (ImageView) itemView.findViewById(R.id.edit);
        buttonDelete = (ImageView) itemView.findViewById(R.id.delete);
    }
}
