/*
 * copyright (c) 2016  eduardo augusto klosowski
 *
 * this program is free software: you can redistribute it and/or modify
 * it under the terms of the gnu general public license as published by
 * the free software foundation, either version 3 of the license, or
 * (at your option) any later version.
 *
 * this program is distributed in the hope that it will be useful,
 * but without any warranty; without even the implied warranty of
 * merchantability or fitness for a particular purpose.  see the
 * gnu general public license for more details.
 *
 * you should have received a copy of the gnu general public license
 * along with this program.  if not, see <http://www.gnu.org/licenses/>.
 */

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
