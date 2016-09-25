package br.klosowski.eduardo.arduino.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class RunningSensorDAO {
    private SensorItemDAO sensorItemDAO;

    public RunningSensorDAO(Context context) {
        sensorItemDAO = new SensorItemDAO(context);
    }

    public List<RunningSensor> getAll() {
        List<RunningSensor> list = new ArrayList<>();
        for (SensorItem sensor : sensorItemDAO.getAll()) {
            RunningSensor rSensor = new RunningSensor(sensor);
            rSensor.requestValue();
            list.add(rSensor);
        }
        return list;
    }
}
