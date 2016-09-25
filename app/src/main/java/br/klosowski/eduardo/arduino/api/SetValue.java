package br.klosowski.eduardo.arduino.api;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import br.klosowski.eduardo.arduino.models.RunningSensor;
import br.klosowski.eduardo.arduino.models.SensorDirection;
import br.klosowski.eduardo.arduino.models.SensorItem;

public class SetValue extends AsyncTask<Void, Void, Boolean> {
    private RunningSensor rSensor;
    private int value;

    public SetValue(RunningSensor runningSensor, int value) {
        this.rSensor = runningSensor;
        this.value = value;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        SensorItem sensor = rSensor.getSensor();

        if (sensor.getDirection().getId() == SensorDirection.Input.getId()) {
            return false;
        }

        try {
            URL url = new URL(sensor.getArduino().getUrl() + "/" +
                    APIUtils.getStringType(sensor) + "/" + sensor.getPort() + "/" + value);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                Scanner s = new Scanner(urlConnection.getInputStream());
                if (s.nextInt() == 0) {
                    rSensor.setValue(value);
                    return true;
                }
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
