package src.main.java.org.example;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);

        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        demonstrateLengthConversion(yard, LengthUnit.INCHES);
    }

    /**
     * Overloaded Method #1
     */
    public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {

        double result = QuantityLength.convert(value, from, to);

        System.out.println(
                "Input: convert(" + value + ", " + from + ", " + to + ") → Output: " + result
        );
    }

    /**
     * Overloaded Method #2
     */
    public static void demonstrateLengthConversion(QuantityLength quantity, LengthUnit target) {

        QuantityLength converted = quantity.convertTo(target);

        System.out.println(
                "Converted: " + quantity + " → " + converted
        );
    }
}