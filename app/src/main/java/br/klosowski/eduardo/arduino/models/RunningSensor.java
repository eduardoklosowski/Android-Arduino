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
