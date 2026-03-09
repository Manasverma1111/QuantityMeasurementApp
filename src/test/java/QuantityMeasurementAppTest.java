package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.org.example.LengthUnit;
import src.main.java.org.example.QuantityLength;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_YardToYard_SameValue() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.YARDS);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(36.0, LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_centimetersToInches_EquivalentValue() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength q2 = new QuantityLength(0.393701, LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {

        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(36.0, LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    @Test
    void testEquality_NullComparison() {

        QuantityLength q = new QuantityLength(1.0, LengthUnit.YARDS);

        assertFalse(q.equals(null));
    }

    @Test
    void testEquality_SameReference() {

        QuantityLength q = new QuantityLength(2.0, LengthUnit.YARDS);

        assertTrue(q.equals(q));
    }
}