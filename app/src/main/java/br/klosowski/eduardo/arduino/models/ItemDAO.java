package br.klosowski.eduardo.arduino.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.klosowski.eduardo.arduino.DatabaseHelper;

public abstract class ItemDAO<I extends Item> {
    static final String COLUMN_ID = "id";
    static final String COLUMN_NAME = "name";

    private SQLiteDatabase db;

    ItemDAO(Context context) throws SQLException {
        DatabaseHelper helper = DatabaseHelper.getIntance(context);
        db = helper.getWritableDatabase();
    }

    abstract protected String getTable();

    abstract protected String[] getAllColumns();

    abstract protected I toObject(Cursor cursor);

    abstract protected ContentValues toContent(I item);

    public long save(I item) {
        String table = getTable();
        ContentValues content = toContent(item);

        if (item.getId() == I.NEW_ID) {
            return db.insert(table, null, content);
        } else {
            db.update(table, content, "id = ?", new String[]{Long.toString(item.getId())});
            return item.getId();
        }
    }

    public void delete(I item) {
        db.delete(getTable(), "id = ?", new String[]{Long.toString(item.getId())});
    }

    public I get(long id) {
        Cursor cursor = null;
        I item = null;
        try {
            cursor = db.query(getTable(), getAllColumns(),
                    "id = ?", new String[]{Long.toString(id)},
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

    public List<I> getAll() {
        Cursor cursor = null;
        List<I> list = new ArrayList<>();
        try {
            cursor = db.query(getTable(), getAllColumns(), null, null, null, null, "name");
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
}
