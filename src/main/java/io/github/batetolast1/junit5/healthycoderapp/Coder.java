package io.github.batetolast1.junit5.healthycoderapp;

public class Coder {
    private double height;
    private double weight;
    private int age;
    private Gender gender;

    public Coder(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public Coder(double height, double weight, int age, Gender gender) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
