package br.klosowski.eduardo.arduino.activities;

import android.content.Context;

import java.util.List;

import br.klosowski.eduardo.arduino.models.ArduinoItem;
import br.klosowski.eduardo.arduino.models.ArduinoItemDAO;
import br.klosowski.eduardo.arduino.models.ItemDAO;

public class ArduinoItemRecyclerAdapter
        extends GenericItemRecyclerAdapter<ArduinoItem> {
    public ArduinoItemRecyclerAdapter(Context context, List<ArduinoItem> list) {
        super(context, list);
    }

    @Override
    protected ItemDAO<ArduinoItem> getItemDAO(Context context) {
        return new ArduinoItemDAO(context);
    }
}
