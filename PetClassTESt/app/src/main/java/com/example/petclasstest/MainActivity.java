package com.example.petclasstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public boolean healthON, hungerON, happyON;
    public String petType;
    public int healthProgNum, hungerProgNum, happyProgNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newPet = findViewById(R.id.button);

        makePet(newPet);


    }

    Handler handler = new Handler();
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
    }

    public void makePet(View view)
    {
        TextView nameText = findViewById(R.id.petName);
        TextView animalType = findViewById(R.id.animalType);
        TextView petAge = findViewById(R.id.petAge);

        Pet newPet = new Pet();
        newPet.createPet();

        if (newPet.getAnimalType() == 1)
        {
            petType = "Dog";
        }
        else if (newPet.getAnimalType() == 2)
        {
            petType = "Cat";
        }
        else if (newPet.getAnimalType() == 3)
        {
            petType = "Birb";
        }

        nameText.setText("Name: " + newPet.getName());
        animalType.setText("Pet Type: " + petType);
        petAge.setText("Pet Age: " + newPet.getAge());
    }

    public void statActive()
    {
        final RadioButton healthButton = findViewById(R.id.healthButton);
        final RadioButton hungerButton = findViewById(R.id.hungerButton);
        final RadioButton happinessButton = findViewById(R.id.happinessButton);
        final ProgressBar healthProg = findViewById(R.id.healthBar);
        final ProgressBar hungerProg = findViewById(R.id.hungerBar);
        final ProgressBar happyProg = findViewById(R.id.happyBar);

        healthProg.setMax(100);
        healthProg.setProgress(0);

        Timer timer = new Timer();
        timer.schedule(new TimerTask (){
            public void run()
            {
                if (healthButton.isChecked() && healthProgNum <= 100)
                {
                    healthProg.setProgress(healthProgNum);
                    healthProgNum += 4;
                }
                else if (!healthButton.isChecked() && healthProgNum >= 0)
                {
                    healthProg.setProgress(healthProgNum);
                    healthProgNum -= 1;
                }

                if (hungerButton.isChecked() && hungerProgNum <= 100)
                {
                    hungerProg.setProgress(hungerProgNum);
                    hungerProgNum += 4;
                }
                else if (!hungerButton.isChecked() && hungerProgNum >= 0)
                {
                    hungerProg.setProgress(hungerProgNum);
                    hungerProgNum -= 1;
                }

                if (happinessButton.isChecked() && happyProgNum <= 100)
                {
                    happyProg.setProgress(happyProgNum);
                    happyProgNum += 4;
                }
                else if (!happinessButton.isChecked() && happyProgNum >= 0)
                {
                    happyProg.setProgress(happyProgNum);
                    happyProgNum -= 1;
                }
            }
        }, 1000);
    }
}
