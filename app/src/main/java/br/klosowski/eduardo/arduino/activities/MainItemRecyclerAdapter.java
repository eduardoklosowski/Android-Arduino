package br.klosowski.eduardo.arduino.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.models.SensorItem;

public class MainItemRecyclerAdapter extends RecyclerView.Adapter<MainItemViewHolder> {
    private List<SensorItem> list;

    public MainItemRecyclerAdapter(Context context, List<SensorItem> list) {
        this.list = list;
    }

    /*@Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }*/

    @Override
    public MainItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new MainItemViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(MainItemViewHolder holder, int position) {
        final SensorItem item = list.get(position);

        holder.textName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public SensorItem getItem(int position) {
        return list.get(position);
    }
}
