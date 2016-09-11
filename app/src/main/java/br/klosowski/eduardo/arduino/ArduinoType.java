package br.klosowski.eduardo.arduino;

public enum ArduinoType {
    Digital("digital"),
    Analogical("analogical");

    private String id;

    ArduinoType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
