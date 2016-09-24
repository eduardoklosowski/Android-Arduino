package br.klosowski.eduardo.arduino.activities;

import android.widget.EditText;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.models.ArduinoItem;
import br.klosowski.eduardo.arduino.models.ArduinoItemDAO;

public class ArduinoFormActivity extends GenericFormActivity {
    private EditText editUrl;

    private ArduinoItemDAO itemDAO;

    public ArduinoFormActivity() {
        super();
        itemDAO = new ArduinoItemDAO(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_arduino_form;
    }

    @Override
    protected int getActivityTitle() {
        return R.string.arduino;
    }

    @Override
    void getElementsFromLayout() {
        editName = (EditText) findViewById(R.id.name);
        editUrl = (EditText) findViewById(R.id.url);
    }

    @Override
    protected void save() {
        ArduinoItem arduino = new ArduinoItem();
        arduino.setId(id);
        arduino.setName(editName.getText().toString());
        arduino.setUrl(editUrl.getText().toString());
        itemDAO.save(arduino);
    }

    @Override
    protected void loadItem(long id) {
        this.id = id;
        ArduinoItem item = itemDAO.get(id);
        editName.setText(item.getName());
        editUrl.setText(item.getUrl());
    }
}
