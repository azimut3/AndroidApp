package com.example.androidapp.data;


public class Consts {

    public final static String PREFS_NAME = "com.example.android.ksinternshipdemo.SETTINGS";
    //public final static String PREFS_USERS_REPO = "PREFS_USERS_REPO";
    //public final static String PREFS_DONT_CLEAR_LIST = "PREFS_DONT_CLEAR_LIST";
    //public final static String PREFS_REPO_LIST = "PREFS_REPO_LIST";

    // Configuration of a CursorLoader
    public final static int LOADER_ID = 0;

    // Configuration of a database
    public final static String DB_NAME = "weather_app_db";
    public final static int DB_VERSION = 1;

    public final static String DB_SIMPLE_TABLE_NAME = "simple_forecast";
    public final static String DB_COMPLEX_TABLE_NAME = "complex_forecast";

    public final static String DB_COL_ID_PRIMARY = "_id";
    public final static String DB_COL_DATE = "date";
    public final static String DB_COL_WEATHER_STATE = "weatherState";
    public final static String DB_COL_MIN_T = "minT";
    public final static String DB_COL_MAX_T = "maxT";

    public final static String DB_COMPLEX_COL_WIND_SPEED = "windSpeed";
    public final static String DB_COMPLEX_COL_WIND_DEGREES = "windDegrees";
    public final static String DB_COMPLEX_COL_HUMIDITY = "humidity";
    public final static String DB_COMPLEX_COL_PRESSURE = "pressure";


    // SQL Query
    public static final String DB_CREATE_SIMPLE =
            "create table " + DB_SIMPLE_TABLE_NAME + "(" +
                    DB_COL_ID_PRIMARY + " integer primary key autoincrement, " +
                    DB_COL_DATE + " text, " +
                    DB_COL_WEATHER_STATE + " text, " +
                    DB_COL_MIN_T + " double," +
                    DB_COL_MAX_T + " double," +
                    " UNIQUE ( " + DB_COL_DATE + " ) ON CONFLICT IGNORE" +
                    ");";
    public static final String DB_CREATE_COMPLEX =
            "create table " + DB_SIMPLE_TABLE_NAME + "(" +
                    DB_COL_ID_PRIMARY + " integer primary key autoincrement, " +
                    DB_COL_DATE + " text, " +
                    DB_COL_WEATHER_STATE + " text, " +
                    DB_COL_MIN_T + " double," +
                    DB_COL_MAX_T + " double," +
                    DB_COMPLEX_COL_WIND_SPEED + " double," +
                    DB_COMPLEX_COL_WIND_DEGREES + " double," +
                    DB_COMPLEX_COL_HUMIDITY + " text," +
                    DB_COMPLEX_COL_PRESSURE + " double," +
                    " UNIQUE ( " + DB_COL_DATE + " ) ON CONFLICT IGNORE" +
                    ");";

    public static final String DB_DELETE_SIMPLE_ENTRIES =
            "DROP TABLE IF EXISTS " + DB_SIMPLE_TABLE_NAME;
    public static final String DB_DELETE_COMPLEX_ENTRIES =
            "DROP TABLE IF EXISTS " + DB_COMPLEX_TABLE_NAME;

}
