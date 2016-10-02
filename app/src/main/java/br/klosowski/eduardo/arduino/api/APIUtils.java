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

package br.klosowski.eduardo.arduino.api;

import br.klosowski.eduardo.arduino.models.SensorItem;
import br.klosowski.eduardo.arduino.models.SensorType;

class APIUtils {
    private APIUtils() {
    }

    static String getStringType(SensorItem sensorItem) {
        SensorType type = sensorItem.getType();
        if (type.getId() == SensorType.Digital.getId()) {
            return "digital";
        } else {
            return "analog";
        }
    }
}
