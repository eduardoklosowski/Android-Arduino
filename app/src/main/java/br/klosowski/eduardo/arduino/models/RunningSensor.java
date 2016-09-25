package br.klosowski.eduardo.arduino.models;

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
}
