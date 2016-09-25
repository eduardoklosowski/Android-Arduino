package br.klosowski.eduardo.arduino.models;

import br.klosowski.eduardo.arduino.api.GetValue;
import br.klosowski.eduardo.arduino.api.SetValue;

public class RunningSensor {
    private SensorItem sensor;
    private int value = 0;

    RunningSensor(SensorItem sensor) {
        this.sensor = sensor;
    }

    public long getId() {
        return sensor.getId();
    }

    public SensorItem getSensor() {
        return sensor;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void requestValue() {
        new GetValue(this).execute();
    }

    public void putValue(int value) {
        new SetValue(this, value).execute();
    }
}
