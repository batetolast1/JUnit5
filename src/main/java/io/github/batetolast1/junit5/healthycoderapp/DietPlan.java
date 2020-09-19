package io.github.batetolast1.junit5.healthycoderapp;

public class DietPlan {

    private int calories;
    private int protein;
    private int fat;
    private int carbohydrate;

    public DietPlan(int calories, int protein, int fat, int carbohydrate) {
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    @Override
    public String toString() {
        return "DietPlan{" +
                "calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbohydrate=" + carbohydrate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DietPlan dietPlan = (DietPlan) o;

        if (calories != dietPlan.calories) return false;
        if (protein != dietPlan.protein) return false;
        if (fat != dietPlan.fat) return false;
        return carbohydrate == dietPlan.carbohydrate;
    }

    @Override
    public int hashCode() {
        int result = calories;
        result = 31 * result + protein;
        result = 31 * result + fat;
        result = 31 * result + carbohydrate;
        return result;
    }
}
