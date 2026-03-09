package src.main.java.org.example;

import java.util.Objects;

public final class QuantityLength {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.unit = Objects.requireNonNull(unit, "Unit cannot be null");
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.toFeet(value);
    }


    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        double baseFeet = source.toFeet(value);

        return target.fromFeet(baseFeet);
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {

        double converted = convert(this.value, this.unit, targetUnit);

        return new QuantityLength(converted, targetUnit);
    }


    public QuantityLength add(QuantityLength other) {

        Objects.requireNonNull(other, "Second operand cannot be null");

        double sumFeet = this.toBaseUnit() + other.toBaseUnit();

        double resultValue = this.unit.fromFeet(sumFeet);

        return new QuantityLength(resultValue, this.unit);
    }

    public static QuantityLength add(QuantityLength a, QuantityLength b, LengthUnit targetUnit) {

        if (a == null || b == null)
            throw new IllegalArgumentException("Operands cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumFeet = a.toBaseUnit() + b.toBaseUnit();

        double result = targetUnit.fromFeet(sumFeet);

        return new QuantityLength(result, targetUnit);
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        QuantityLength other = (QuantityLength) obj;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseUnit());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}