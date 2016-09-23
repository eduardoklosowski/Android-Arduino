package br.klosowski.eduardo.arduino;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SensorItemDAO {
    private final String TABLE = "sensor";

    private final String COLUMN_ID = "id";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_ARDUINO = "arduino";
    private final String COLUMN_TYPE = "type";
    private final String COLUMN_DIRECTION = "direction";
    private final String COLUMN_PORT = "port";

    private final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_ARDUINO, COLUMN_TYPE,
                                          COLUMN_DIRECTION, COLUMN_PORT};

    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private ArduinoItemDAO arduinoItemDAO;

    public SensorItemDAO(Context context) throws SQLException {
        arduinoItemDAO = new ArduinoItemDAO(context);
        helper = DatabaseHelper.getIntance(context);
        open();
    }

    private void open() throws SQLException {
        db = helper.getWritableDatabase();
    }

    public long save(SensorItem item) {
        if (item.getId() != 0) {
            return update(item);
        } else {
            return add(item);
        }
    }

    public long add(SensorItem item) {
        return db.insert(TABLE, null, toContent(item));
    }

    private long update(SensorItem item) {
        db.update(TABLE, toContent(item),
                "id = ?", new String[]{Long.toString(item.getId())});
        return item.getId();
    }

    public void delete(SensorItem item) {
        db.delete(TABLE, "id = ?", new String[]{Long.toString(item.getId())});
    }

    public SensorItem get(long id) {
        Cursor cursor = null;
        SensorItem item = null;
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

    public List<SensorItem> getAll() {
        Cursor cursor = null;
        List<SensorItem> list = new ArrayList<>();
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

    private SensorItem toObject(Cursor cursor) {
        SensorItem item = new SensorItem();
        item.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        item.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        item.setArduino(arduinoItemDAO.get(cursor.getLong(cursor.getColumnIndex(COLUMN_ARDUINO))));
        String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
        if (type.equals(ArduinoType.Analogical.getId())) {
            item.setType(ArduinoType.Analogical);
        } else if (type.equals(ArduinoType.Digital.getId())) {
            item.setType(ArduinoType.Digital);
        }
        String direction = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECTION));
        if (direction.equals(ArduinoDirection.Input.getId())) {
            item.setDirection(ArduinoDirection.Input);
        } else if (direction.equals(ArduinoDirection.Output.getId())){
            item.setDirection(ArduinoDirection.Output);
        }
        item.setPort(cursor.getInt(cursor.getColumnIndex(COLUMN_PORT)));
        return item;
    }

    private ContentValues toContent(SensorItem item) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_NAME, item.getName());
        content.put(COLUMN_ARDUINO, item.getArduino().getId());
        content.put(COLUMN_TYPE, item.getType().getId());
        content.put(COLUMN_DIRECTION, item.getDirection().getId());
        content.put(COLUMN_PORT, item.getPort());
        return content;
    }
}
