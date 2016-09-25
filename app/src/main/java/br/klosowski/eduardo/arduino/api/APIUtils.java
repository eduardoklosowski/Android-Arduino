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
