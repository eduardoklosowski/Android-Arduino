package br.klosowski.eduardo.arduino.activities;

import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.models.ArduinoItem;
import br.klosowski.eduardo.arduino.models.ArduinoItemDAO;
import br.klosowski.eduardo.arduino.models.SensorDirection;
import br.klosowski.eduardo.arduino.models.SensorItem;
import br.klosowski.eduardo.arduino.models.SensorItemDAO;
import br.klosowski.eduardo.arduino.models.SensorType;

public class SensorFormActivity extends GenericFormActivity {
    private AppCompatSpinner editArduino;
    private RadioGroup editType;
    private RadioButton editTypeDigital;
    private RadioButton editTypeAnalogical;
    private RadioGroup editDirection;
    private RadioButton editDirectionInput;
    private RadioButton editDirectionOutput;
    private EditText editPort;

    private SensorItemDAO itemDAO;

    private List<ArduinoItem> arduinosList;

    public SensorFormActivity() {
        super();
        itemDAO = new SensorItemDAO(this);
        ArduinoItemDAO arduinoDAO = new ArduinoItemDAO(this);
        arduinosList = arduinoDAO.getAll();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_sensor_form;
    }

    @Override
    protected int getActivityTitle() {
        return R.string.sensor;
    }

    @Override
    void getElementsFromLayout() {
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
    }

    @Override
    protected void save() {
        SensorItem sensor = new SensorItem();
        sensor.setId(id);
        sensor.setName(editName.getText().toString());
        sensor.setArduino((ArduinoItem) editArduino.getSelectedItem());
        SensorType type = null;
        switch (editType.getCheckedRadioButtonId()) {
            case R.id.type_digital:
                type = SensorType.Digital;
                break;
            case R.id.type_analogical:
                type = SensorType.Analogical;
                break;
        }
        sensor.setType(type);
        SensorDirection direction = null;
        switch (editDirection.getCheckedRadioButtonId()) {
            case R.id.direction_input:
                direction = SensorDirection.Input;
                break;
            case R.id.direction_output:
                direction = SensorDirection.Output;
                break;
        }
        sensor.setDirection(direction);
        sensor.setPort(Integer.parseInt(editPort.getText().toString()));
        itemDAO.save(sensor);
    }

    @Override
    void loadItem(long id) {
        this.id = id;
        SensorItem item = itemDAO.get(id);
        editName.setText(item.getName());
        long arduinoId = item.getArduino().getId();
        for (int i = arduinosList.size(); i-- > 0; ) {
            if (arduinosList.get(i).getId() == arduinoId) {
                editArduino.setSelection(i);
                break;
            }
        }
        long typeId = item.getType().getId();
        editTypeDigital.setChecked(typeId == SensorType.Digital.getId());
        editTypeAnalogical.setChecked(typeId == SensorType.Analogical.getId());
        long directionId = item.getDirection().getId();
        editDirectionInput.setChecked(directionId == SensorDirection.Input.getId());
        editDirectionOutput.setChecked(directionId == SensorDirection.Output.getId());
        editPort.setText(Integer.toString(item.getPort()));
    }
}
