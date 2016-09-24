package br.klosowski.eduardo.arduino.models;

public enum SensorType {
    Digital(1, "Digital"),
    Analogical(2, "Analógico");

    private long id;
    private String name;

    SensorType(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
