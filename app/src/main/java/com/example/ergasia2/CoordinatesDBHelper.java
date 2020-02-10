package com.example.ergasia2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CoordinatesDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Persons.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Coordinates.FeedEntry.TABLE_NAME + " (" +
                    Coordinates.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    Coordinates.FeedEntry.unix_timestamp+ " TEXT," +
                    Coordinates.FeedEntry.lat + " TEXT," +
                    Coordinates.FeedEntry.lon + " TEXT )";


    public CoordinatesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
