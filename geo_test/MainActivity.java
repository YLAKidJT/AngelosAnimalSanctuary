package com.example.geo_test;


import android.Manifest;

import android.content.Context;

//import android.content.Intent;

import android.content.pm.PackageManager;

import android.location.Location;

import android.location.LocationListener;

import android.location.LocationManager;

import android.os.Bundle;

import android.renderscript.Sampler;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;





//Your activity must implement a LocationListener so that it listens to onLocationChanged events.

public class MainActivity extends AppCompatActivity implements LocationListener {


    //This integer serves as a "memory address" for storing and retrieving the user's permission status

    final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION=0;



    int count = 0;
    float statA = 5.0f;
    float statB = 5.0f;
    float statC = 5.0f;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        //This retrieves the user's permission status.

        boolean permissionAccessFineLocationApproved =

                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

                        == PackageManager.PERMISSION_GRANTED;





        TextView myLabel=findViewById(R.id.myLabel);

        if(permissionAccessFineLocationApproved) {



            //This block of code will run if the user has previously granted permissions.



            myLabel.setText("User has previously provided permission.");



            //Start location services

            startGPS();



        }

        else {



            //This block of code will run if the user has not granted permissions yet.



            myLabel.setText("User has not provided permission yet.");



            //This requests permission by opening a standardized dialog box.

            ActivityCompat.requestPermissions( this,

                    new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  },

                    REQUEST_PERMISSION_ACCESS_FINE_LOCATION );





        }





    }



    //This piece of code will run when the user responds to a permission dialog box.

    @Override public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_PERMISSION_ACCESS_FINE_LOCATION){

            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {



                //This block of code will run if the user clicks on the "DENY" button.



                finish();//terminate the application if the user does not provide permission

            }

            else {



                //This block of code will run if the user clicks on the "ALLOW" button.



                //the user just provided permission

                startGPS();





            }

        }

    }



    //Here I put the necessary code to start the Location Service.

    private void startGPS() {

        //First we make sure that the user has indeed granted permissions.

        boolean permissionAccessFineLocationApproved =

                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

                        == PackageManager.PERMISSION_GRANTED;

        if (permissionAccessFineLocationApproved) {



            //And then we start the location service.

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);

        }

    }


    @Override

    public void onStatusChanged(String s, int i, Bundle bundle) { }

    @Override

    public void onProviderEnabled(String s) { }

    @Override

    public void onProviderDisabled(String s) { }



    @Override

    public void onLocationChanged(Location location) {

        TextView myLabel=findViewById(R.id.myLabel);
        myLabel.setText("NEW DATA RECEIVED: "+location.getLongitude()+", "+location.getLatitude());

        //this is where we ad out "step" functionality. I've hardcoded this just as an example, delete it first

        statA += 0.2f;
        statB -= 0.1f;
        statC -= 0.3f;

        String stats = ("Stat A: " + statA + "  Stat B: " + statB + "  Stat C:" + statC);

        TextView statText = findViewById(R.id.statText);
        statText.setText(stats);

        count++;
        String countText = Integer.toString(count);

        TextView counter = findViewById(R.id.counter);
        counter.setText(countText);

    }



    /*Calculates the approximate geodesic distance in meters between two geographical locations.

    private double distance(double lon1, double lat1, Location loc) {

        double theta = lon1 - loc.getLongitude();double dist = Math.sin(deg2rad(lat1))*  Math.sin(deg2rad(loc.getLatitude()))+ Math.cos(deg2rad(lat1))*  Math.cos(deg2rad(loc.getLatitude()))*  Math.cos(deg2rad(theta));dist = Math.acos(dist);dist = rad2deg(dist);dist = dist * 60 * 1.1515* 1000.0;return (dist);

    }

    private double deg2rad(double deg) {return (deg * Math.PI / 180.0);}private double rad2deg(double rad) {return (rad * 180.0 / Math.PI);}

*/

}
