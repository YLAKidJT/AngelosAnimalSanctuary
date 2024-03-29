package com.example.petclasstest;

import java.util.Random;

public class Pet {
    private String name;
    private int age, animalType, decayRate;
    private float hunger, health, happiness;


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

    public float getHunger() {
        return hunger;
    }

    public void setHunger(float hunger) {
        this.hunger = hunger;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getHappiness() {
        return happiness;
    }

    public void setHappiness(float happiness) {
        this.happiness = happiness;
    }

    public void createPet()
    {
        String[] petNames = {"Jim", "Bob", "Reginald"};
        String newName = petNames[new Random().nextInt(petNames.length)];
        setName(newName);

        int[] animalType = {1, 2, 3};
        int newAnimal = animalType[new Random().nextInt(animalType.length)];
        setAnimalType(newAnimal);

        Random randAge = new Random();
        setAge(randAge.nextInt((3 - 1) + 1) + 1);

        int decayR = new Random().nextInt((10 + 1) + 1);
        setDecayRate(decayR);
    }


}
