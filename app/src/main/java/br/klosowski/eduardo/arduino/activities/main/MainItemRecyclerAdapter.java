package br.klosowski.eduardo.arduino.activities.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.models.SensorDirection;
import br.klosowski.eduardo.arduino.models.SensorItem;
import br.klosowski.eduardo.arduino.models.SensorType;

class MainItemRecyclerAdapter extends RecyclerView.Adapter<MainItemViewHolder> {
    private List<SensorItem> list;

    MainItemRecyclerAdapter(Context context, List<SensorItem> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<SensorItem> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    @Override
    public int getItemViewType(int position) {
        SensorItem sensor = list.get(position);
        long typeId = sensor.getType().getId();
        long directionId = sensor.getDirection().getId();

        if (typeId == SensorType.Digital.getId()) {
            if (directionId == SensorDirection.Input.getId()) {
                return R.layout.item_main_digital_input;
            } else {
                return R.layout.item_main_digital_output;
            }
        } else {
            if (directionId == SensorDirection.Input.getId()) {
                return R.layout.item_main_analogical_input;
            } else {
                return R.layout.item_main_analogical_output;
            }
        }
    }

    @Override
    public MainItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(MainItemViewHolder holder, int position) {
        final SensorItem item = list.get(position);

        holder.textName.setText(item.getName());
    }
}
