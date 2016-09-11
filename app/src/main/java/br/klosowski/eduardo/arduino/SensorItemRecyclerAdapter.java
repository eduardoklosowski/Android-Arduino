package br.klosowski.eduardo.arduino;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class SensorItemRecyclerAdapter extends RecyclerView.Adapter<SensorItemViewHolder> {
    private List<SensorItem> list;

    public SensorItemRecyclerAdapter(Context context, List<SensorItem> list) {
        this.list = list;
    }

    @Override
    public SensorItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_list, parent, false);
        return new SensorItemViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(SensorItemViewHolder holder, int position) {
        final SensorItem item = list.get(position);

        holder.textName.setText(item.getName());

        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public SensorItem getItem(int position) {
        return list.get(position);
    }
}
