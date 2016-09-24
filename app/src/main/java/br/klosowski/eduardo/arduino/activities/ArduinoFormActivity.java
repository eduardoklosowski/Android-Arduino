package br.klosowski.eduardo.arduino.activities;

import android.widget.EditText;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.models.ArduinoItem;
import br.klosowski.eduardo.arduino.models.ArduinoItemDAO;

public class ArduinoFormActivity extends GenericFormActivity<ArduinoItem> {
    private EditText editUrl;

    @Override
    protected int getLayout() {
        return R.layout.activity_arduino_form;
    }

    @Override
    protected int getActivityTitle() {
        return R.string.arduino;
    }

    @Override
    protected ArduinoItemDAO getItemDAO() {
        return new ArduinoItemDAO(this);
    }

    @Override
    void populateElementsFromLayout() {
        editName = (EditText) findViewById(R.id.name);
        editUrl = (EditText) findViewById(R.id.url);
    }

    @Override
    protected void populateFields(ArduinoItem item) {
        editName.setText(item.getName());
        editUrl.setText(item.getUrl());
    }

    @Override
    protected ArduinoItem getItem() {
        ArduinoItem arduino = new ArduinoItem();
        arduino.setId(id);
        arduino.setName(editName.getText().toString());
        arduino.setUrl(editUrl.getText().toString());
        return arduino;
    }
}
