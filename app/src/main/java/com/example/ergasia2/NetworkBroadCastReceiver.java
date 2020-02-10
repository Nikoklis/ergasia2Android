package com.example.ergasia2;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class NetworkBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean connectivity = isConnected(context);
        if (connectivity) {
            ComponentName comp = new ComponentName(context.getPackageName(),
                    ServiceLocation.class.getName());
            intent.putExtra("isNetworkConnected",connectivity );
            context.startService(intent.setComponent(comp));
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
        } else {


            //stop the service
            Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
            ComponentName comp = new ComponentName(context.getPackageName(),
                    ServiceLocation.class.getName());
            context.stopService(intent.setComponent(comp));
        }
    }


    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }
}
