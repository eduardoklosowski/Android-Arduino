package br.klosowski.eduardo.arduino;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ArduinoItemRecyclerAdapter extends RecyclerView.Adapter<ArduinoItemViewHolder> {
    private List<ArduinoItem> list;
    private Context context;

    public ArduinoItemRecyclerAdapter(Context context, List<ArduinoItem> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ArduinoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_edit_list, parent, false);
        return new ArduinoItemViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ArduinoItemViewHolder holder, int position) {
        final ArduinoItem item = list.get(position);

        holder.textName.setText(item.getName());

        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArduinoFormActivity.class);
                context.startActivity(intent);
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConfirmDeleteDialog(context, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).make().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArduinoItem getItem(int position) {
        return list.get(position);
    }
}
