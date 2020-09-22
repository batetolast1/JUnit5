package io.github.batetolast1.junit5.realestateapp;

import java.math.BigDecimal;
import java.util.Objects;

public class Apartment {

    private final double area;
    private final BigDecimal price;

    public Apartment(double area, BigDecimal price) {
        if (area <= 0) {
            throw new IllegalArgumentException("Area must be greater than 0");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        this.area = area;
        this.price = price;
    }

    public double getArea() {
        return area;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        if (Double.compare(apartment.area, area) != 0) return false;
        return Objects.equals(price, apartment.price);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "area=" + area +
                ", price=" + price +
                '}';
    }
}
