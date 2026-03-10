package src.main.java.org.example;

import java.util.Objects;

public final class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.unit = Objects.requireNonNull(unit, "Unit cannot be null");
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit) {

        double baseValue = toBaseUnit();

        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(round(converted), targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        Objects.requireNonNull(other);

        if (unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different measurement categories");

        double baseSum = this.toBaseUnit() + other.toBaseUnit();

        double result = targetUnit.convertFromBaseUnit(baseSum);

        return new Quantity<>(round(result), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different measurement categories");

        double baseResult = this.toBaseUnit() - other.toBaseUnit();

        double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(round(result), targetUnit);
    }

    public double divide(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different measurement categories");

        double baseA = this.toBaseUnit();
        double baseB = other.toBaseUnit();

        if (Math.abs(baseB) < EPSILON)
            throw new ArithmeticException("Division by zero");

        return baseA / baseB;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity<?>))
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (unit.getClass() != other.unit.getClass())
            return false;

        double baseA = unit.convertToBaseUnit(value);
        double baseB = other.unit.convertToBaseUnit(other.value);

        return Math.abs(baseA - baseB) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}