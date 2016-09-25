package br.klosowski.eduardo.arduino.activities.sensor;

import android.content.Context;

import java.util.List;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.activities.generics.GenericListActivity;
import br.klosowski.eduardo.arduino.models.SensorItem;
import br.klosowski.eduardo.arduino.models.SensorItemDAO;

public class SensorListActivity extends GenericListActivity<SensorItem> {
    public SensorListActivity() {
        super();
    }

    @Override
    protected int getActivityTitle() {
        return R.string.sensors;
    }

    @Override
    protected Class getFormActivityClass() {
        return SensorFormActivity.class;
    }

    @Override
    protected SensorItemRecyclerAdapter getRecyclerAdapter(Context context,
                                                           List<SensorItem> items) {
        return new SensorItemRecyclerAdapter(context, items);
    }

    @Override
    public SensorItemDAO getItemDAO() {
        return new SensorItemDAO(this);
    }
}
