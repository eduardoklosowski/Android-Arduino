package br.klosowski.eduardo.arduino.activities.sensor;

import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.activities.generics.GenericFormActivity;
import br.klosowski.eduardo.arduino.models.ArduinoItem;
import br.klosowski.eduardo.arduino.models.ArduinoItemDAO;
import br.klosowski.eduardo.arduino.models.SensorDirection;
import br.klosowski.eduardo.arduino.models.SensorItem;
import br.klosowski.eduardo.arduino.models.SensorItemDAO;
import br.klosowski.eduardo.arduino.models.SensorType;

public class SensorFormActivity extends GenericFormActivity<SensorItem> {
    private AppCompatSpinner editArduino;
    private RadioGroup editType;
    private RadioButton editTypeDigital;
    private RadioButton editTypeAnalogical;
    private RadioGroup editDirection;
    private RadioButton editDirectionInput;
    private RadioButton editDirectionOutput;
    private EditText editPort;

    private List<ArduinoItem> arduinosList;

    @Override
    protected int getLayout() {
        return R.layout.activity_sensor_form;
    }

    @Override
    protected int getActivityTitle() {
        return R.string.sensor;
    }

    @Override
    protected SensorItemDAO getItemDAO() {
        return new SensorItemDAO(this);
    }

    private List<ArduinoItem> getArduinoLists() {
        if (arduinosList == null) {
            ArduinoItemDAO arduinoItemDAO = new ArduinoItemDAO(this);
            arduinosList = arduinoItemDAO.getAll();
        }
        return arduinosList;
    }

    private int getArduinoItemPosition(ArduinoItem item) {
        long id = item.getId();
        List<ArduinoItem> list = getArduinoLists();
        int i = list.size();
        for (; i-- > 0; ) {
            if (list.get(i).getId() == id) {
                break;
            }
        }
        return i;
    }

    @Override
    protected void populateElementsFromLayout() {
        editName = (EditText) findViewById(R.id.name);
        editArduino = (AppCompatSpinner) findViewById(R.id.arduino);
        editArduino.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, getArduinoLists()));
        editType = (RadioGroup) findViewById(R.id.type);
        editTypeDigital = (RadioButton) findViewById(R.id.type_digital);
        editTypeAnalogical = (RadioButton) findViewById(R.id.type_analogical);
        editDirection = (RadioGroup) findViewById(R.id.direction);
        editDirectionInput = (RadioButton) findViewById(R.id.direction_input);
        editDirectionOutput = (RadioButton) findViewById(R.id.direction_output);
        editPort = (EditText) findViewById(R.id.port);
    }

    @Override
    protected void populateFields(SensorItem item) {
        editName.setText(item.getName());
        editArduino.setSelection(getArduinoItemPosition(item.getArduino()));
        long typeId = item.getType().getId();
        editTypeDigital.setChecked(typeId == SensorType.Digital.getId());
        editTypeAnalogical.setChecked(typeId == SensorType.Analogical.getId());
        long directionId = item.getDirection().getId();
        editDirectionInput.setChecked(directionId == SensorDirection.Input.getId());
        editDirectionOutput.setChecked(directionId == SensorDirection.Output.getId());
        editPort.setText(Integer.toString(item.getPort()));
    }

    @Override
    protected SensorItem getItem() {
        SensorItem sensor = new SensorItem();
        sensor.setId(id);
        sensor.setName(editName.getText().toString());
        sensor.setArduino((ArduinoItem) editArduino.getSelectedItem());
        SensorType type = null;
        int editTypeId = editType.getCheckedRadioButtonId();
        if (editTypeId == R.id.type_digital) {
            type = SensorType.Digital;
        } else if (editTypeId == R.id.type_analogical) {
            type = SensorType.Analogical;
        }
        sensor.setType(type);
        SensorDirection direction = null;
        int editDirectionId = editDirection.getCheckedRadioButtonId();
        if (editDirectionId == R.id.direction_input) {
            direction = SensorDirection.Input;
        } else if (editDirectionId == R.id.direction_output) {
            direction = SensorDirection.Output;
        }
        sensor.setDirection(direction);
        String textPort = editPort.getText().toString();
        int port = 0;
        if (!textPort.isEmpty()) {
            port = Integer.parseInt(textPort);
        }
        sensor.setPort(port);
        return sensor;
    }
}
