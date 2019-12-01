package com.example.petclasstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.text);
        Button newPet = findViewById(R.id.button);

        Pet firstPet = new Pet();
        firstPet.createPet();

        text.setText(firstPet.getName());
    }

    public void makePet(View view)
    {
        TextView text = findViewById(R.id.text);

        Pet newPet = new Pet();
        newPet.createPet();

        text.setText(newPet.getName());
    }
}
