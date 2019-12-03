package com.example.petclasstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public boolean healthON, hungerON, happyON = false;
    public String petType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newPet = findViewById(R.id.button);

        makePet(newPet);
        statActive();
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
        RadioButton healthButton = findViewById(R.id.healthButton);
        RadioButton hungerButton = findViewById(R.id.hungerButton);
        RadioButton happinessButton = findViewById(R.id.happinessButton);
        ProgressBar healthProg = findViewById(R.id.healthBar);

        healthProg.setMax(100);
        healthProg.setProgress(0);

        if (healthButton.isChecked() == true)
        {
            healthON = true;
            hungerON = false;
            happyON = false;
        }
        else if (hungerButton.isChecked() == true)
        {
            healthON = false;
            hungerON = true;
            happyON = false;
        }
        else if (happinessButton.isChecked() == true)
        {
            healthON = false;
            hungerON = false;
            happyON = true;
        }

        if (healthON)
        {
            for(int i = 0; i <= 100; i++)
            {
                healthProg.setProgress(i);
            }
        }
        else if (!healthON)
        {
            for (int i = 100; i >= 0; i--)
            {
                healthProg.setProgress(i);
            }
        }
    }
}
