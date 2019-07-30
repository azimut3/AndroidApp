package com.example.androidapp.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.androidapp.managers.ComplexForecast;
import com.example.androidapp.managers.SimplifiedForecast;

import java.util.List;

public class Database {

    private final Context ctx;


    private DBHelper helper;

    private SQLiteDatabase mDB;

    public Database(Context ctx) {
        this.ctx = ctx;
    }

    public void open() {
        helper = new DBHelper(ctx, Consts.DB_NAME, null, Consts.DB_VERSION);
        mDB = helper.getWritableDatabase();
    }

    public void close() {
        if (helper != null) {
            helper.close();
        }
    }

    public Cursor getAllSimpleData() {
        return mDB.query(Consts.DB_SIMPLE_TABLE_NAME, null, null,
                null, null, null, Consts.DB_COL_ID_PRIMARY + " DESC");
    }
    public Cursor getAllComplexData() {
        return mDB.query(Consts.DB_COMPLEX_TABLE_NAME, null, null,
                null, null, null, Consts.DB_COL_ID_PRIMARY + " DESC");
    }
    public Cursor getComplexDataByDate(String date) {
        return mDB.query(Consts.DB_COMPLEX_TABLE_NAME, null, Consts.DB_COMPLEX_COL_SHORT_DATE+" = ?",
                new String[]{date}, null, null, Consts.DB_COL_DATE + " ASC");
    }

    public void clearSimpleData() {
        mDB.delete(Consts.DB_SIMPLE_TABLE_NAME, null, null);
    }
    public void clearComplexData() {
        mDB.delete(Consts.DB_COMPLEX_TABLE_NAME, null, null);
    }

    private void addSimpleRec(SimplifiedForecast item) {
        ContentValues cv = new ContentValues();
        cv.put(Consts.DB_COL_DATE, item.getDate());
        cv.put(Consts.DB_COL_WEATHER_STATE, item.getWeatherState());
        cv.put(Consts.DB_COL_MIN_T, item.getMinT());
        cv.put(Consts.DB_COL_MAX_T, item.getMaxT());

        mDB.insert(Consts.DB_SIMPLE_TABLE_NAME, null, cv);
    }
    private void addComplexRec(ComplexForecast item) {
        ContentValues cv = new ContentValues();
        cv.put(Consts.DB_COL_DATE, item.getDate());
        cv.put(Consts.DB_COL_WEATHER_STATE, item.getWeatherState());
        cv.put(Consts.DB_COL_MIN_T, item.getMinT());
        cv.put(Consts.DB_COL_MAX_T, item.getMaxT());
        cv.put(Consts.DB_COMPLEX_COL_WIND_SPEED, item.getMaxT());
        cv.put(Consts.DB_COMPLEX_COL_WIND_DEGREES, item.getMaxT());
        cv.put(Consts.DB_COMPLEX_COL_HUMIDITY, item.getMaxT());
        cv.put(Consts.DB_COMPLEX_COL_PRESSURE, item.getMaxT());
        cv.put(Consts.DB_COMPLEX_COL_SHORT_DATE, item.getShortDate());

        mDB.insert(Consts.DB_COMPLEX_TABLE_NAME, null, cv);
    }

    public void addSimpleData(List<SimplifiedForecast> items) {
        if (items.size() != 0) {
            for (int i = items.size() - 1; i >= 0; i--) {
                addSimpleRec(items.get(i));
            }
        }
    }
    public void addComplexData(List<ComplexForecast> items) {
        if (items.size() != 0) {
            for (int i = items.size() - 1; i >= 0; i--) {
                addComplexRec(items.get(i));
            }
        }
    }

    /**
     * Subclass of {@link SQLiteOpenHelper} which provides custom database helper.
     */
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Consts.DB_CREATE_SIMPLE);
            db.execSQL(Consts.DB_CREATE_COMPLEX);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(Consts.DB_DELETE_SIMPLE_ENTRIES);
            db.execSQL(Consts.DB_DELETE_COMPLEX_ENTRIES);
            onCreate(db);
        }
    }

}
