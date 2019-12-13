package com.example.dig4634_angelos_animal_sanctuary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StatActivity extends AppCompatActivity implements LocationListener {

    public String name;
    public int age, type, decayRate, curHealth, curHunger, curHappy;
    public Pet newPet = new Pet();
    public boolean firstTime;

    final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        boolean permissionAccessFineLocationApproved = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if (permissionAccessFineLocationApproved)
        {
            startGPS();
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_ACCESS_FINE_LOCATION);
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            name = extras.getString("Name");
            age = extras.getInt("Age");
            type = extras.getInt("Type");
            decayRate = extras.getInt("dRate");
            firstTime = extras.getBoolean("firstTime");
            curHealth = extras.getInt("curHealth");
            curHunger = extras.getInt("curHunger");
            curHappy = extras.getInt("curHappy");
        }

        Button newPetButton = findViewById(R.id.newPetButton);
        Button menuButton = findViewById(R.id.menuButton);

        newPetButton.setStateListAnimator(null);
        menuButton.setStateListAnimator(null);

        setStats();
        setPet();
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode == REQUEST_PERMISSION_ACCESS_FINE_LOCATION)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                finish();
            }
            else
            {
                startGPS();
            }
        }
    }

    private void startGPS()
    {
        boolean permissionAccessFineLocationApproved = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if (permissionAccessFineLocationApproved)
        {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);
        }
    }

    /*Handler handler = new Handler();
    Runnable runnable;
    int delay = 1000;

    @Override
    protected void onResume()
    {
        handler.postDelayed(runnable = new Runnable() {
            public void run()
            {
                statActive();
                handler.postDelayed(runnable, delay);
            }
        }, delay);
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        handler.removeCallbacks(runnable);
        super.onPause();
    }*/

    public void makePet(View view)
    {
        newPet.petGenerate();

        getStats();
        setStats();

        setPet();
    }

    public void getStats()
    {
        name = newPet.getName();
        age = newPet.getAge();
        type = newPet.getAnimalType();
        decayRate = newPet.getDecayRate();
        curHealth = newPet.getHealth();
        curHunger = newPet.getHunger();
        curHappy = newPet.getHappiness();
    }

    public void setStats()
    {
        newPet.setName(name);
        newPet.setAge(age);
        newPet.setAnimalType(type);
        newPet.setDecayRate(decayRate);
        newPet.setHealth(curHealth);
        newPet.setHunger(curHunger);
        newPet.setHappiness(curHappy);
    }

    public void setPet()
    {
        TextView petName = findViewById(R.id.petName);
        TextView petType = findViewById(R.id.petType);
        TextView petAge = findViewById(R.id.petAge);
        ImageView petImage = findViewById(R.id.petImage);
        ProgressBar healthProgress = findViewById(R.id.healthProgress);
        ProgressBar hungerProgress = findViewById(R.id.hungerProgress);
        ProgressBar happinessProgress = findViewById(R.id.happinessProgress);

        healthProgress.setProgress(curHealth);
        hungerProgress.setProgress(curHunger);
        happinessProgress.setProgress(curHappy);

        petName.setText("Name: " + name);
        petAge.setText("Pet Age: " + age);

        if (age == 0)
            petAge.setText("Pet Age: Baby");

        if (type == 1)
        {
            petType.setText("Pet Type: Doggo");
            petImage.setImageDrawable(getResources().getDrawable(R.drawable.doggo));
        }
        else if (type == 2)
        {
            petType.setText("Pet Type: Catto");
            petImage.setImageDrawable(getResources().getDrawable(R.drawable.catto));
        }
        else if (type == 3)
        {
            petType.setText("Pet Type: Birb");
            petImage.setImageDrawable(getResources().getDrawable(R.drawable.birb));
        }
        else if (type == 4)
        {
            petType.setText("Pet Type: Phoenix CACAW!!");
            petImage.setImageDrawable(getResources().getDrawable(R.drawable.birb));
        }
    }

    public void statActive()
    {
        final RadioButton healthButton = findViewById(R.id.healthButton);
        final RadioButton hungerButton = findViewById(R.id.hungerButton);
        final RadioButton happinessButton = findViewById(R.id.happinessButton);
        final ProgressBar healthProg = findViewById(R.id.healthProgress);
        final ProgressBar hungerProg = findViewById(R.id.hungerProgress);
        final ProgressBar happyProg = findViewById(R.id.happinessProgress);

        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run()
            {
                if (healthButton.isChecked() && curHealth <= 100)
                {
                    healthProg.setProgress(curHealth);
                    curHealth += 4;
                }
                else if (!healthButton.isChecked() && curHealth >= 0)
                {
                    healthProg.setProgress(curHealth);
                    curHealth -= decayRate;
                }

                if (hungerButton.isChecked() && curHunger <= 100)
                {
                    hungerProg.setProgress(curHunger);
                    curHunger += 4;
                }
                else if (!hungerButton.isChecked() && curHunger >= 0)
                {
                    hungerProg.setProgress(curHunger);
                    curHunger -= decayRate;
                }

                if (happinessButton.isChecked() && curHappy <= 100)
                {
                    happyProg.setProgress(curHappy);
                    curHappy += 4;
                }
                else if (!happinessButton.isChecked() && curHappy >= 0)
                {
                    happyProg.setProgress(curHappy);
                    curHappy -= decayRate;
                }
            }
        }, 1000);
    }

    public void backButton(View view)
    {
        setStats();
        getStats();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Name", name);
        intent.putExtra("Age", age);
        intent.putExtra("Type", type);
        intent.putExtra("dRate", decayRate);
        intent.putExtra("firstTime", firstTime);
        intent.putExtra("curHealth", curHealth);
        intent.putExtra("curHunger", curHunger);
        intent.putExtra("curHappy", curHappy);
        startActivity(intent);
    }

    @Override
    public void onLocationChanged(Location location) {
        final RadioButton healthButton = findViewById(R.id.healthButton);
        final RadioButton hungerButton = findViewById(R.id.hungerButton);
        final RadioButton happinessButton = findViewById(R.id.happinessButton);
        final ProgressBar healthProg = findViewById(R.id.healthProgress);
        final ProgressBar hungerProg = findViewById(R.id.hungerProgress);
        final ProgressBar happyProg = findViewById(R.id.happinessProgress);

        if (healthButton.isChecked() && curHealth <= 100)
        {
            healthProg.setProgress(curHealth);
            curHealth += 4;
        }
        else if (!healthButton.isChecked() && curHealth >= 0)
        {
            healthProg.setProgress(curHealth);
            curHealth -= decayRate;
        }

        if (hungerButton.isChecked() && curHunger <= 100)
        {
            hungerProg.setProgress(curHunger);
            curHunger += 4;
        }
        else if (!hungerButton.isChecked() && curHunger >= 0)
        {
            hungerProg.setProgress(curHunger);
            curHunger -= decayRate;
        }

        if (happinessButton.isChecked() && curHappy <= 100)
        {
            happyProg.setProgress(curHappy);
            curHappy += 4;
        }
        else if (!happinessButton.isChecked() && curHappy >= 0)
        {
            happyProg.setProgress(curHappy);
            curHappy -= decayRate;
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
