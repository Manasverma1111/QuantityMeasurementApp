package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.org.example.LengthUnit;
import src.main.java.org.example.QuantityLength;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testAddition_TargetUnit_Yards() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityLength.add(a, b, LengthUnit.YARDS);

        assertEquals(
                new QuantityLength(0.6666666667, LengthUnit.YARDS),
                result
        );
    }

    @Test
    void testAddition_TargetUnit_Feet() {

        QuantityLength a = new QuantityLength(36.0, LengthUnit.INCHES);
        QuantityLength b = new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength result = QuantityLength.add(a, b, LengthUnit.FEET);

        assertEquals(
                new QuantityLength(6.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testAddition_TargetUnit_Inches() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = QuantityLength.add(a, b, LengthUnit.INCHES);

        assertEquals(
                new QuantityLength(24.0, LengthUnit.INCHES),
                result
        );
    }

    @Test
    void testAddition_Commutativity() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength r1 = QuantityLength.add(a, b, LengthUnit.FEET);
        QuantityLength r2 = QuantityLength.add(b, a, LengthUnit.FEET);

        assertEquals(r1, r2);
    }

    @Test
    void testAddition_WithZero() {

        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(0.0, LengthUnit.INCHES);

        QuantityLength result = QuantityLength.add(a, b, LengthUnit.FEET);

        assertEquals(
                new QuantityLength(5.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testAddition_NegativeValues() {

        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(a, b, LengthUnit.FEET);

        assertEquals(
                new QuantityLength(3.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testAddition_NullFirstOperand() {

        QuantityLength b = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.add(null, b, LengthUnit.FEET)
        );
    }

    @Test
    void testAddition_NullSecondOperand() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.add(a, null, LengthUnit.FEET)
        );
    }

    @Test
    void testAddition_NullTargetUnit() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        assertThrows(
                IllegalArgumentException.class,
                () -> QuantityLength.add(a, b, null)
        );
    }
}