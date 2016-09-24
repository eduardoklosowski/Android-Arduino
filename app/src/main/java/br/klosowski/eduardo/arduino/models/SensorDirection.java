package br.klosowski.eduardo.arduino.models;

public enum SensorDirection {
    Input(1, "Entrada"),
    Output(2, "Saída");

    private long id;
    private String name;

    SensorDirection(long id, String name) {
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
