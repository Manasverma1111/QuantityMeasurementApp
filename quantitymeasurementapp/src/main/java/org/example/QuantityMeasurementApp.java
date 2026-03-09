package src.main.java.org.example;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("Input: Quantity(1.0, YARDS) and Quantity(3.0, FEET)");
        System.out.println("Output: Equal (" + q1.equals(q2) + ")");

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q4 = new QuantityLength(36.0, LengthUnit.INCH);

        System.out.println("Input: Quantity(1.0, YARDS) and Quantity(36.0, INCH)");
        System.out.println("Output: Equal (" + q3.equals(q4) + ")");

        QuantityLength q5 = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength q6 = new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println("Input: Quantity(1.0, CENTIMETERS) and Quantity(0.393701, INCH)");
        System.out.println("Output: Equal (" + q5.equals(q6) + ")");
    }
}