package br.klosowski.eduardo.arduino.models;

public class SensorItem extends Item {
    private ArduinoItem arduino;
    private SensorType type;
    private SensorDirection direction;
    private int port;

    public ArduinoItem getArduino() {
        return arduino;
    }

    public void setArduino(ArduinoItem arduino) {
        this.arduino = arduino;
    }

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    public SensorDirection getDirection() {
        return direction;
    }

    public void setDirection(SensorDirection direction) {
        this.direction = direction;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
