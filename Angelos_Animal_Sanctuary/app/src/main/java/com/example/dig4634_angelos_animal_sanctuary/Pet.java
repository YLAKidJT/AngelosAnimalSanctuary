package com.example.dig4634_angelos_animal_sanctuary;

import java.util.Random;

public class Pet {
    private String name;
    private int age, animalType, decayRate;
    private int hunger, health, happiness;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAnimalType() {
        return animalType;
    }

    public void setAnimalType(int animalType) {
        this.animalType = animalType;
    }

    public int getDecayRate() {
        return decayRate;
    }

    public void setDecayRate(int decayRate) {
        this.decayRate = decayRate;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void createPet()
    {
        String[] petNames = {"Jim", "Bob", "Reginald", "Morrigan", "Esther", "Julie", "Mike", "Lucky", "Princess", "Fluffy", "Gizmo", "Buster", "Esteban", "Mia"};
        String newName = petNames[new Random().nextInt(petNames.length)];
        setName(newName);

        int[] animalType = {1, 2, 3};
        int newAnimal = animalType[new Random().nextInt(animalType.length)];
        setAnimalType(newAnimal);

        Random randAge = new Random();
        setAge(randAge.nextInt((15 + 1) + 1));

        Random decayR = new Random();
        setDecayRate(decayR.nextInt((3 + 1) + 1));

        if (decayRate < 1)
            decayRate = 1;
    }
}
