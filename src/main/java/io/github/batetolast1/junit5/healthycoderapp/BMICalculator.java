package io.github.batetolast1.junit5.healthycoderapp;

import java.util.Comparator;
import java.util.List;

public class BMICalculator {

    public static final double BMI_TRESHOLD = 25.0;

    public static boolean isDietRecommended(double weight, double height) {
        if (height == 0) {
            throw new ArithmeticException();
        }
        double bmi = weight / (height * height);
        return bmi > BMI_TRESHOLD;
    }

    public static Coder findCoderWithWorstBMI(List<Coder> coderList) {
        return coderList.stream()
                .sorted(Comparator.comparing(BMICalculator::calculateBMI))
                .reduce((first, second) -> second)
                .orElse(null);
    }

    private static double calculateBMI(Coder coder) {
        double height = coder.getHeight();
        double weight = coder.getWeight();
        if (height == 0) {
            throw new ArithmeticException();
        }
        double bmi = weight / (height * height);
        return Math.round(bmi * 100) / 100.0;
    }

    public static double[] getBMIScores(List<Coder> coderList) {
        double[] bmiScores = new double[coderList.size()];
        for (int i = 0; i < bmiScores.length; i++) {
            bmiScores[i] = BMICalculator.calculateBMI(coderList.get(i));
        }
        return bmiScores;
    }
}
