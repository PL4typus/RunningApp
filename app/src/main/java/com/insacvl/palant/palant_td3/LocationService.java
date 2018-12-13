package com.insacvl.palant.palant_td3;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;


public class LocationService extends Service {
    private static final String TAG = LocationService.class
            .getSimpleName();
    protected static int MY_PERMISSIONS_REQUESTS_ACCESS_FINE_LOCATION = 64;
    LocationManager locationManager;
    LocationListener gpsLocationListener;
    FileOutputStream fos;

    {
        try {
            fos = openFileOutput("log.txt", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onCreate(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions((Activity)getApplicationContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUESTS_ACCESS_FINE_LOCATION);
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        gpsLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(TAG, "onLocationChanged: " + location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d(TAG, "onStatusChanged: " + status);
            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10 * 1000L, 0, gpsLocationListener);

        }catch (SecurityException e){
            Log.e("2su",e.getMessage());
        }
    }

    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "Service destroying");
        locationManager.removeUpdates(gpsLocationListener);
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public void onStart(Intent intent, int startId){

    }

    public int onStartCommand(Intent intent, int flags, int startId){
        try {
            Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            String trace = "lat:"+lat+",lon:"+lon+";\n";
            try {
                fos.write(trace.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (SecurityException e){
            Log.e("2su",e.getMessage());
        }
        return START_NOT_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
