package src.main.java.org.example;

import java.util.Objects;

public final class QuantityWeight {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.unit = Objects.requireNonNull(unit, "Unit cannot be null");
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {

        double baseValue = unit.convertToBaseUnit(value);

        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityWeight(converted, targetUnit);
    }

    public QuantityWeight add(QuantityWeight other) {

        Objects.requireNonNull(other, "Second operand cannot be null");

        double baseSum =
                unit.convertToBaseUnit(value)
                        + other.unit.convertToBaseUnit(other.value);

        double resultValue = unit.convertFromBaseUnit(baseSum);

        return new QuantityWeight(resultValue, unit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {

        Objects.requireNonNull(other);

        double baseSum =
                unit.convertToBaseUnit(value)
                        + other.unit.convertToBaseUnit(other.value);

        double resultValue = targetUnit.convertFromBaseUnit(baseSum);

        return new QuantityWeight(resultValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof QuantityWeight))
            return false;

        QuantityWeight other = (QuantityWeight) obj;

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
        return "Quantity(" + value + ", " + unit + ")";
    }
}