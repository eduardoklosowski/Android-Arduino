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
    private static final int TYPE_DIGITAL = 0x0;
    private static final int TYPE_ANALOGICAL = 0x2;
    private static final int DIRECTION_INPUT = 0x0;
    private static final int DIRECTION_OUTPUT = 0x1;
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
        int viewType = 0;

        long typeId = sensor.getType().getId();
        if (typeId == SensorType.Digital.getId()) {
            viewType |= TYPE_DIGITAL;
        } else if (typeId == SensorType.Analogical.getId()) {
            viewType |= TYPE_ANALOGICAL;
        }

        long directionId = sensor.getDirection().getId();
        if (directionId == SensorDirection.Input.getId()) {
            viewType |= DIRECTION_INPUT;
        } else if (directionId == SensorDirection.Output.getId()) {
            viewType |= DIRECTION_OUTPUT;
        }

        return viewType;
    }

    @Override
    public MainItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(MainItemViewHolder holder, int position) {
        final SensorItem item = list.get(position);

        holder.textName.setText(item.getName());
    }
}
