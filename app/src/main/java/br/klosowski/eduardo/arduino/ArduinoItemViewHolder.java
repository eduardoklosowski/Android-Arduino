package br.klosowski.eduardo.arduino;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ArduinoItemViewHolder extends RecyclerView.ViewHolder {
    public TextView textName;
    public ImageView buttonEdit;
    public ImageView buttonDelete;

    public ArduinoItemViewHolder(View itemView) {
        super(itemView);
        textName = (TextView) itemView.findViewById(R.id.name);
        buttonEdit = (ImageView) itemView.findViewById(R.id.edit);
        buttonDelete = (ImageView) itemView.findViewById(R.id.delete);
    }
}
