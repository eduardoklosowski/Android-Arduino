package br.klosowski.eduardo.arduino;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ArduinoItemDAO {
    private final String TABLE = "arduino";

    private final String COLUMN_ID = "id";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_URL = "url";

    private final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_URL};

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public ArduinoItemDAO(Context context) throws SQLException {
        helper = DatabaseHelper.getIntance(context);
        open();
    }

    private void open() throws SQLException {
        db = helper.getWritableDatabase();
    }

    public long add(ArduinoItem item) {
        return db.insert(TABLE, null, toContent(item));
    }

    public ArduinoItem get(long id) {
        Cursor cursor = null;
        ArduinoItem item = null;
        try {
            cursor = db.query(TABLE, ALL_COLUMNS,
                    "id = ?", new String[] {new Long(id).toString()},
                    null, null, null, "1");
            if (cursor.moveToFirst()) {
                item = toObject(cursor);
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return item;
    }

    public List<ArduinoItem> getAll() {
        Cursor cursor = null;
        List<ArduinoItem> list = new ArrayList<>();
        try {
            cursor = db.query(TABLE, ALL_COLUMNS, null, null, null, null, "name");
            if (cursor.moveToFirst()) {
                do {
                    list.add(toObject(cursor));
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;
    }

    private ArduinoItem toObject(Cursor cursor) {
        ArduinoItem item = new ArduinoItem();
        item.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        item.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        item.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL)));
        return item;
    }

    private ContentValues toContent(ArduinoItem item) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_ID, item.getId());
        content.put(COLUMN_NAME, item.getName());
        content.put(COLUMN_URL, item.getUrl());
        return content;
    }
}
