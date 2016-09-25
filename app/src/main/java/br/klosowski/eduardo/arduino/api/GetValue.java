package br.klosowski.eduardo.arduino.api;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import br.klosowski.eduardo.arduino.models.RunningSensor;
import br.klosowski.eduardo.arduino.models.SensorDirection;
import br.klosowski.eduardo.arduino.models.SensorItem;
import br.klosowski.eduardo.arduino.models.SensorType;

public class GetValue extends AsyncTask<Void, Void, Boolean> {
    private RunningSensor rSensor;

    public GetValue(RunningSensor runningSensor) {
        this.rSensor = runningSensor;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        SensorItem sensor = rSensor.getSensor();

        if (sensor.getType().getId() == SensorType.Analogical.getId() &&
                sensor.getDirection().getId() == SensorDirection.Output.getId()) {
            return true;
        }

        try {
            URL url = new URL(sensor.getArduino().getUrl() + "/" +
                    APIUtils.getStringType(sensor) + "/" + sensor.getPort());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                Scanner s = new Scanner(urlConnection.getInputStream());
                rSensor.setValue(s.nextInt());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
