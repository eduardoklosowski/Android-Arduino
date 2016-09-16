package br.klosowski.eduardo.arduino;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class SensorFormActivity extends AppCompatActivity {
    private EditText editName;
    private AppCompatSpinner editArduino;
    private RadioGroup editType;
    private RadioGroup editDirection;
    private EditText editPort;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_form);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.sensor);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editName = (EditText) findViewById(R.id.name);
        editArduino = (AppCompatSpinner) findViewById(R.id.arduino);
        editArduino.setAdapter(new ArrayAdapter<ArduinoItem>(this,
                android.R.layout.simple_spinner_item,
                getArduinoList()));
        editType = (RadioGroup) findViewById(R.id.type);
        editDirection = (RadioGroup) findViewById(R.id.direction);
        editPort = (EditText) findViewById(R.id.port);

        buttonSave = (Button) findViewById(R.id.save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SensorItem sensor = new SensorItem();
                sensor.setName(editName.getText().toString());
                sensor.setArduino((ArduinoItem) editArduino.getSelectedItem());
                switch (editType.getCheckedRadioButtonId()) {
                    case R.id.type_digital:
                        sensor.setType(ArduinoType.Digital);
                        break;
                    case R.id.type_analogical:
                        sensor.setType(ArduinoType.Analogical);
                        break;
                }
                switch (editDirection.getCheckedRadioButtonId()) {
                    case R.id.direction_input:
                        sensor.setDirection(ArduinoDirection.Input);
                        break;
                    case R.id.direction_output:
                        sensor.setDirection(ArduinoDirection.Output);
                        break;
                }
                sensor.setPort(Integer.parseInt(editPort.getText().toString()));
            }
        });
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

    private List<ArduinoItem> getArduinoList() {
        List<ArduinoItem> list = new ArrayList<>();

        ArduinoItem a = new ArduinoItem();
        a.setId(1);
        a.setName("Arduino 1");
        a.setUrl("http://localhost/");
        list.add(a);

        a = new ArduinoItem();
        a.setId(2);
        a.setName("Arduino 2");
        a.setUrl("http://localhost:8080/");
        list.add(a);

        return list;
    }
}
