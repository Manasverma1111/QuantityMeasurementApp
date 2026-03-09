package src.main.java.org.example;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                QuantityLength.add(a, b, LengthUnit.YARDS);

        System.out.println("Input: add(" + a + ", " + b + ", YARDS)");
        System.out.println("Output: " + result);

        QuantityLength x = new QuantityLength(36.0, LengthUnit.INCHES);
        QuantityLength y = new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength result2 =
                QuantityLength.add(x, y, LengthUnit.FEET);

        System.out.println("Input: add(" + x + ", " + y + ", FEET)");
        System.out.println("Output: " + result2);
    }
}