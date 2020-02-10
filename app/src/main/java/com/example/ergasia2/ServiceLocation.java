package com.example.ergasia2;

import android.app.IntentService;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class ServiceLocation extends Service {
    LocationManager locationManager;
    CoordinatesDBHelper dbHelper;
    SQLiteDatabase db;



    private final int LOCATION_REFRESH_TIME = 5000;
//    private final int LOCATION_REFRESH_DISTANCE = 10;


    public ServiceLocation() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {


    }

    @Override
    public void onDestroy() {
        this.locationManager.removeUpdates(mLocationListener);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle extras = intent.getExtras();
        boolean isNetworkConnected = extras.getBoolean("isNetworkConnected");

        if (isNetworkConnected) {
            dbHelper = new CoordinatesDBHelper(MainActivity.getContext());
            db = dbHelper.getWritableDatabase();

             locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                    0, mLocationListener);
        }

        return START_STICKY;
    }


    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {

            long unixTime = System.currentTimeMillis() / 1000L;

            ContentValues values = new ContentValues();
            values.put(Coordinates.FeedEntry.unix_timestamp, unixTime);
            values.put(Coordinates.FeedEntry.lat, location.getLatitude());
            values.put(Coordinates.FeedEntry.lon, location.getLongitude());

            Log.w("going to insert values ", String.valueOf(values));
            db.insert(Coordinates.FeedEntry.TABLE_NAME, null, values);
            Toast.makeText(MainActivity.getContext(), "Inserted in db new location values", Toast.LENGTH_SHORT).show();

//            Cursor res = db.rawQuery("select * from " + Coordinates.FeedEntry.TABLE_NAME, null);
//            res.moveToFirst();
//            while(res.isAfterLast() == false) {
//                Log.w("asdasasaa", res.getString(res.getColumnIndex("unix_timestamp")));
//                res.moveToNext();
//            }

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.w("we are offff","offff");
        }
    };


}
