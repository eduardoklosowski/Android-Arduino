/*
 * copyright (c) 2016  eduardo augusto klosowski
 *
 * this program is free software: you can redistribute it and/or modify
 * it under the terms of the gnu general public license as published by
 * the free software foundation, either version 3 of the license, or
 * (at your option) any later version.
 *
 * this program is distributed in the hope that it will be useful,
 * but without any warranty; without even the implied warranty of
 * merchantability or fitness for a particular purpose.  see the
 * gnu general public license for more details.
 *
 * you should have received a copy of the gnu general public license
 * along with this program.  if not, see <http://www.gnu.org/licenses/>.
 */

package br.klosowski.eduardo.arduino.activities.arduino;

import android.widget.EditText;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.activities.generics.GenericFormActivity;
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
    protected void populateElementsFromLayout() {
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
