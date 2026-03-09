package src.main.java.org.example;

public class QuantityMeasurementApp {

    // Inner class representing Feet measurement
    public static class Feet {

        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Override equals() for value comparison
        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true; // same reference
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false; // null or different type
            }

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }

        // Optional but recommended when equals() is overridden
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Main method for quick verification
    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + f1.equals(f2) + ")");
    }
}