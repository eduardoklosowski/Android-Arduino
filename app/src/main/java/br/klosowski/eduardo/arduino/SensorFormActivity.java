package br.klosowski.eduardo.arduino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class SensorFormActivity extends AppCompatActivity {

    private AppCompatSpinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sensor");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSpinner = (AppCompatSpinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<>();
        list.add("Exemplo Android 1");
        list.add("Exemplo Android 2");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(dataAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
