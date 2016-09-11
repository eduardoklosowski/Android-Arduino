package br.klosowski.eduardo.arduino;

public class SensorItem {
    private long id;
    private String name;
    private ArduinoItem arduino;
    private ArduinoType type;
    private int port;

    public String toString() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArduinoItem getArduino() {
        return arduino;
    }

    public void setArduino(ArduinoItem arduino) {
        this.arduino = arduino;
    }

    public ArduinoType getType() {
        return type;
    }

    public void setType(ArduinoType type) {
        this.type = type;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
