package br.klosowski.eduardo.arduino.activities.sensor;

import android.content.Context;

import java.util.List;

import br.klosowski.eduardo.arduino.activities.generics.GenericItemRecyclerAdapter;
import br.klosowski.eduardo.arduino.models.ItemDAO;
import br.klosowski.eduardo.arduino.models.SensorItem;
import br.klosowski.eduardo.arduino.models.SensorItemDAO;

class SensorItemRecyclerAdapter
        extends GenericItemRecyclerAdapter<SensorItem> {
    SensorItemRecyclerAdapter(Context context, List<SensorItem> list) {
        super(context, list);
    }

    @Override
    protected ItemDAO<SensorItem> getItemDAO(Context context) {
        return new SensorItemDAO(context);
    }
}
