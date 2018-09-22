package com.example.satyendra.googlelocationmap;

/**
 * Created by SATYENDRA on 8/26/2018.
 */

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service
{
    private static final String TAG = "BOOMBOOMTESTGPS";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 100;
    private static final float LOCATION_DISTANCE = 10f;
    DataUtility dataUtility = new DataUtility();


    private class LocationListener implements android.location.LocationListener
    {
        Location mLastLocation;
        Thread triggerService;


        public LocationListener(String provider)
        {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(final Location location)
        {
            triggerService = new Thread(new Runnable() {
                @Override
                public void run() {
                    String str1 = String.valueOf(location.getLatitude());
                    String str2 = String.valueOf(location.getLongitude());
                    String[] strArray = {str1,str2};
                    new PostLocation().execute(strArray);
                }
            });
            triggerService.start();

            Toast.makeText(getApplicationContext(),location.getLatitude()+" , "+ location.getLongitude(),Toast.LENGTH_LONG ).show();
            Log.e(TAG, "onLocationChanged: " + location.getLatitude()+" , "+ location.getLongitude());
            mLastLocation.set(location);
        }

        @Override
        public void onProviderDisabled(String provider)
        {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider)
        {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
            Log.e(TAG, "onStatusChanged: " + provider);

        }

    }

    private class PostLocation extends AsyncTask<String, String, String>
    {
        String response = null;
        @Override
        public String doInBackground(String... strings) {
            Log.e("TAG", "main: " + strings[0]+" , "+strings[1]);
            response = dataUtility.postLocationData(strings);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (response.equalsIgnoreCase("success")) {
                Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Not Inserted", Toast.LENGTH_LONG).show();
            }

        }
    }

    LocationListener[] mLocationListeners = new LocationListener[] {
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        /*String str1 = "so jaa";//String.valueOf(location.getLatitude());
        String str2 = "kutte";//String.valueOf(location.getLongitude());
        String[] strArray = {str1,str2};
        new PostLocation().execute(strArray);*/
        return START_STICKY;
    }

    @Override
    public void onCreate()
    {
        String str1 = "so jaa";//String.valueOf(location.getLatitude());
        String str2 = "kutte";//String.valueOf(location.getLongitude());
        String[] strArray = {str1,str2};
        new PostLocation().execute(strArray);
        Log.e(TAG, "onCreate");
        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
            Log.i(TAG, "working");
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    @Override
    public void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }
}
