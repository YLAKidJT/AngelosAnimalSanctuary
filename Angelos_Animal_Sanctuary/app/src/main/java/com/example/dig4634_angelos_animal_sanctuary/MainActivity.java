package com.example.dig4634_angelos_animal_sanctuary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button statsButton;
    public int age, type, decayRate;
    public String name;
    public boolean firstTime = true;
    public Pet newPet = new Pet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            name = extras.getString("Name");
            age = extras.getInt("Age");
            type = extras.getInt("Type");
            decayRate = extras.getInt("dRate");
            firstTime = extras.getBoolean("firstTime");
            setStats();
        }

        statsButton = findViewById(R.id.mainScreenStatsButton);
        statsButton.setStateListAnimator(null);

        if (firstTime == true)
        {
            createPet();
            setPet();
            firstTime = false;
        }
        else
        {
            getStats();
            setPet();
        }
    }

    public void onClick(View view)
    {
        Intent intent = new Intent(this, StatActivity.class);
        intent.putExtra("Name", name);
        intent.putExtra("Age", age);
        intent.putExtra("Type", type);
        intent.putExtra("dRate", decayRate);
        intent.putExtra("firstTime", firstTime);
        startActivity(intent);
    }

    public void createPet()
    {
        newPet.createPet();
        getStats();
        setStats();
    }

    public void getStats()
    {
        name = newPet.getName();
        age = newPet.getAge();
        type = newPet.getAnimalType();
        decayRate = newPet.getDecayRate();
    }

    public void setStats()
    {
        newPet.setName(name);
        newPet.setAge(age);
        newPet.setAnimalType(type);
        newPet.setDecayRate(decayRate);
    }

    public void setPet()
    {
        TextView petName = findViewById(R.id.mainScreenPetName);
        ImageView petImg = findViewById(R.id.mainScreenPetImage);

        petName.setText(name);

        if (type == 1)
        {
            petImg.setImageDrawable(getResources().getDrawable(R.drawable.doggo));
        }
        else if (type == 2)
        {
            petImg.setImageDrawable(getResources().getDrawable(R.drawable.catto));
        }
        else if (type == 3)
        {
            petImg.setImageDrawable(getResources().getDrawable(R.drawable.birb));
        }
    }
}
