package src.main.java.com.quantitymeasurementapp;

import java.util.Scanner;

public class QuantityMeasurementApp {

    // Step 3: Inner Immutable Feet Class
    public static class Feet {

        // Encapsulation + Immutability
        private final double value;

        // Step 4: Constructor
        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        // Step 5: Overriding equals()
        @Override
        public boolean equals(Object obj) {

            // Reflexive check
            if (this == obj) {
                return true;
            }

            // Null + Type check
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Feet other = (Feet) obj;

            // Floating point safe comparison
            return Double.compare(this.value, other.value) == 0;
        }

        // Recommended whenever equals() is overridden
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Step 6: Main Method
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first value in feet:");
        String input1 = scanner.nextLine();

        System.out.println("Enter second value in feet:");
        String input2 = scanner.nextLine();

        try {
            double value1 = Double.parseDouble(input1);
            double value2 = Double.parseDouble(input2);

            Feet feet1 = new Feet(value1);
            Feet feet2 = new Feet(value2);

            boolean result = feet1.equals(feet2);

            System.out.println("Equal (" + result + ")");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter numeric values only.");
        }

        scanner.close();
    }
}