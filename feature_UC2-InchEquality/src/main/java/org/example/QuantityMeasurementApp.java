package src.main.java.org.example;

public class QuantityMeasurementApp {

    public static boolean checkFeetEquality(double a, double b) {
        Feet feet1 = new Feet(a);
        Feet feet2 = new Feet(b);
        return feet1.equals(feet2);
    }

    public static boolean checkInchesEquality(double a, double b) {
        Inches inch1 = new Inches(a);
        Inches inch2 = new Inches(b);
        return inch1.equals(inch2);
    }

    public static void main(String[] args) {

        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" + checkInchesEquality(1.0, 1.0) + ")");

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + checkFeetEquality(1.0, 1.0) + ")");
    }
}
