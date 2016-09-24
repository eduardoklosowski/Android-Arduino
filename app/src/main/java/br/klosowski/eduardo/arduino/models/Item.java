package br.klosowski.eduardo.arduino.models;

public abstract class Item {
    public static final long NEW_ID = 0;

    private long id = NEW_ID;
    private String name;

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
}
