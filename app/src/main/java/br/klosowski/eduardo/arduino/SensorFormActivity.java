package br.klosowski.eduardo.arduino;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class SensorFormActivity extends AppCompatActivity {
    private EditText editName;
    private AppCompatSpinner editArduino;
    private RadioGroup editType;
    private RadioButton editTypeDigital;
    private RadioButton editTypeAnalogical;
    private RadioGroup editDirection;
    private RadioButton editDirectionInput;
    private RadioButton editDirectionOutput;
    private EditText editPort;
    private Button buttonSave;

    private long id;
    private SensorItem item;
    private SensorItemDAO itemDAO;
    private ArduinoItemDAO arduinoItemDAO;

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

        itemDAO = new SensorItemDAO(this);
        arduinoItemDAO = new ArduinoItemDAO(this);
        List<ArduinoItem> arduinosList = arduinoItemDAO.getAll();

        editName = (EditText) findViewById(R.id.name);
        editArduino = (AppCompatSpinner) findViewById(R.id.arduino);
        editArduino.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                arduinosList));
        editType = (RadioGroup) findViewById(R.id.type);
        editTypeDigital = (RadioButton) findViewById(R.id.type_digital);
        editTypeAnalogical = (RadioButton) findViewById(R.id.type_analogical);
        editDirection = (RadioGroup) findViewById(R.id.direction);
        editDirectionInput = (RadioButton) findViewById(R.id.direction_input);
        editDirectionOutput = (RadioButton) findViewById(R.id.direction_output);
        editPort = (EditText) findViewById(R.id.port);

        buttonSave = (Button) findViewById(R.id.save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SensorItem sensor = new SensorItem();
                if (id != 0) {
                    sensor.setId(id);
                }
                sensor.setName(editName.getText().toString());
                sensor.setArduino((ArduinoItem) editArduino.getSelectedItem());
                Log.d("FORM", "Save: " + sensor.getArduino().getId());
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
                itemDAO.save(sensor);
                setResult(RESULT_OK);
                finish();
            }
        });

        id = getIntent().getLongExtra("id", 0);
        if (id != 0) {
            item = itemDAO.get(id);
            Log.d("FORM", "Arduino: " + item.getArduino().getId());
            editName.setText(item.getName());
            Log.d("LOAD", "ID item: " + item.getId());
            for (int i = arduinosList.size(); i-- > 0;) {
                Log.d("FOR", "item: " + arduinosList.get(i).getId());
                if (arduinosList.get(i).getId() == item.getId()) {
                    Log.d("FOR", "esse " + i);
                    editArduino.setSelection(i);
                    break;
                }
            }
            editTypeDigital.setChecked(item.getType().getId().equals("digital"));
            editTypeAnalogical.setChecked(item.getType().getId().equals("analogical"));
            editDirectionInput.setChecked(item.getDirection().getId().equals("input"));
            editDirectionOutput.setChecked(item.getDirection().getId().equals("output"));
            editPort.setText(Integer.toString(item.getPort()));
        }
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
