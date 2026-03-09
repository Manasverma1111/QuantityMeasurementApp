package src.main.java.org.example;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(
                "Convert: " + q1.convertTo(LengthUnit.INCHES));

        System.out.println(
                "Add: " + q1.add(q2, LengthUnit.FEET));

        System.out.println(
                "Equality: " +
                        new QuantityLength(36, LengthUnit.INCHES)
                                .equals(new QuantityLength(1, LengthUnit.YARDS)));
    }
}