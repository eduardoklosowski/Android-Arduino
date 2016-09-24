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
