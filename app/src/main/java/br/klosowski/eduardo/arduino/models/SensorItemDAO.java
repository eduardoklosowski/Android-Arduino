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

package br.klosowski.eduardo.arduino.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

public class SensorItemDAO extends ItemDAO<SensorItem> {
    private final String COLUMN_ARDUINO = "arduino";
    private final String COLUMN_TYPE = "type";
    private final String COLUMN_DIRECTION = "direction";
    private final String COLUMN_PORT = "port";

    private ArduinoItemDAO arduinoItemDAO;

    public SensorItemDAO(Context context) throws SQLException {
        super(context);
        arduinoItemDAO = new ArduinoItemDAO(context);
    }

    @Override
    protected String getTable() {
        return "sensor";
    }

    @Override
    protected String[] getAllColumns() {
        return new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_ARDUINO,
                COLUMN_TYPE, COLUMN_DIRECTION, COLUMN_PORT};
    }

    @Override
    protected SensorItem toObject(Cursor cursor) {
        SensorItem item = new SensorItem();
        item.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        item.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        item.setArduino(arduinoItemDAO.get(cursor.getLong(cursor.getColumnIndex(COLUMN_ARDUINO))));
        Long typeId = cursor.getLong(cursor.getColumnIndex(COLUMN_TYPE));
        SensorType type = null;
        if (typeId == SensorType.Digital.getId()) {
            type = SensorType.Digital;
        } else if (typeId == SensorType.Analogical.getId()) {
            type = SensorType.Analogical;
        }
        item.setType(type);
        Long directionId = cursor.getLong(cursor.getColumnIndex(COLUMN_DIRECTION));
        SensorDirection direction = null;
        if (directionId == SensorDirection.Input.getId()) {
            direction = SensorDirection.Input;
        } else if (directionId == SensorDirection.Output.getId()) {
            direction = SensorDirection.Output;
        }
        item.setDirection(direction);
        item.setPort(cursor.getInt(cursor.getColumnIndex(COLUMN_PORT)));
        return item;
    }

    @Override
    protected ContentValues toContent(SensorItem item) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_NAME, item.getName());
        content.put(COLUMN_ARDUINO, item.getArduino().getId());
        content.put(COLUMN_TYPE, item.getType().getId());
        content.put(COLUMN_DIRECTION, item.getDirection().getId());
        content.put(COLUMN_PORT, item.getPort());
        return content;
    }
}
