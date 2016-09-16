package br.klosowski.eduardo.arduino;

public enum ArduinoDirection {
    Input("input"),
    Output("output");

    private String id;

    ArduinoDirection(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
