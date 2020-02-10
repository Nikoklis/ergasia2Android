package com.example.ergasia2;

import android.provider.BaseColumns;

public class Coordinates {
    private Coordinates() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "coordinates";
        public static final String unix_timestamp = "unix_timestamp";
        public static final String lat= "lat";
        public static final String lon= "lon";

    }
}
