package src.main.java.org.example;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS {
        public double convertToBaseUnit(double value) {
            return value;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue;
        }
    },

    FAHRENHEIT {
        public double convertToBaseUnit(double value) {
            return (value - 32) * 5 / 9;
        }

        public double convertFromBaseUnit(double baseValue) {
            return (baseValue * 9 / 5) + 32;
        }
    },

    KELVIN {
        public double convertToBaseUnit(double value) {
            return value - 273.15;
        }

        public double convertFromBaseUnit(double baseValue) {
            return baseValue + 273.15;
        }
    };

    private static final SupportsArithmetic supportsArithmetic = () -> false;

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                "Temperature does not support arithmetic operation: " + operation
        );
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }
}