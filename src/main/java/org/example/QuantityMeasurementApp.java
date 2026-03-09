package src.main.java.org.example;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println("Input: add(" + q1 + ", " + q2 + ")");
        System.out.println("Output: " + q1.add(q2));

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q4 = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("Input: add(" + q3 + ", " + q4 + ")");
        System.out.println("Output: " + q3.add(q4));

        QuantityLength q5 = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
        QuantityLength q6 = new QuantityLength(1.0, LengthUnit.INCHES);

        System.out.println("Input: add(" + q5 + ", " + q6 + ")");
        System.out.println("Output: " + q5.add(q6));
    }
}