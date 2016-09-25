package br.klosowski.eduardo.arduino.activities.arduino;

import android.content.Context;

import java.util.List;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.activities.generics.GenericListActivity;
import br.klosowski.eduardo.arduino.models.ArduinoItem;
import br.klosowski.eduardo.arduino.models.ArduinoItemDAO;

public class ArduinoListActivity extends GenericListActivity<ArduinoItem> {
    public ArduinoListActivity() {
        super();
    }

    @Override
    protected int getActivityTitle() {
        return R.string.arduinos;
    }

    @Override
    protected Class getFormActivityClass() {
        return ArduinoFormActivity.class;
    }

    @Override
    protected ArduinoItemRecyclerAdapter getRecyclerAdapter(Context context,
                                                            List<ArduinoItem> items) {
        return new ArduinoItemRecyclerAdapter(context, items);
    }

    @Override
    protected ArduinoItemDAO getItemDAO() {
        return new ArduinoItemDAO(this);
    }
}
