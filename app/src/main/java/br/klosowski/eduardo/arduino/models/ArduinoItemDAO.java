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

public class ArduinoItemDAO extends ItemDAO<ArduinoItem> {
    private static final String COLUMN_URL = "url";

    public ArduinoItemDAO(Context context) throws SQLException {
        super(context);
    }

    @Override
    protected String getTable() {
        return "arduino";
    }

    @Override
    protected String[] getAllColumns() {
        return new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_URL};
    }

    @Override
    protected ArduinoItem toObject(Cursor cursor) {
        ArduinoItem item = new ArduinoItem();
        item.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        item.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        item.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL)));
        return item;
    }

    @Override
    protected ContentValues toContent(ArduinoItem item) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_NAME, item.getName());
        content.put(COLUMN_URL, item.getUrl());
        return content;
    }
}
