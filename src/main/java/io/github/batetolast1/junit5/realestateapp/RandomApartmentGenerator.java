package io.github.batetolast1.junit5.realestateapp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RandomApartmentGenerator {

    private static final double MAX_MULTIPLIER = 4.0;

    private final double minArea;
    private final BigDecimal minPricePerSquareMeter;

    public RandomApartmentGenerator() {
        this.minArea = 30.0;
        this.minPricePerSquareMeter = new BigDecimal("3000.0");
    }

    public RandomApartmentGenerator(double minArea, BigDecimal minPricePerSquareMeter) {
        this.minArea = minArea;
        this.minPricePerSquareMeter = minPricePerSquareMeter;
    }

    public Apartment generate() {
        double maxArea = minArea * MAX_MULTIPLIER; // 120
        BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER)); // 12000

        double area = Math.round((minArea + Math.random() * (maxArea - minArea)) * 10) / 10.0; // 30 + 90
        BigDecimal pricePerSquareMeter = minPricePerSquareMeter.add((BigDecimal.valueOf(Math.random()) // 3000+(0-1)*
                .multiply(maxPricePerSquareMeter.subtract(minPricePerSquareMeter))));

        BigDecimal price = pricePerSquareMeter.multiply(new BigDecimal(area)).setScale(1, RoundingMode.FLOOR);

        return new Apartment(area, price);
    }
}
