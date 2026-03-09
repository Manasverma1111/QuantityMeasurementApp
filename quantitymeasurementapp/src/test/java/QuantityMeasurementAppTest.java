package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.org.example.LengthUnit;
import src.main.java.org.example.QuantityLength;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(2.0, LengthUnit.FEET);

        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), a.add(b));
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {

        QuantityLength a = new QuantityLength(6.0, LengthUnit.INCHES);
        QuantityLength b = new QuantityLength(6.0, LengthUnit.INCHES);

        assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), a.add(b));
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), a.add(b));
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {

        QuantityLength a = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength b = new QuantityLength(1.0, LengthUnit.FEET);

        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), a.add(b));
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);

        assertEquals(new QuantityLength(2.0, LengthUnit.YARDS), a.add(b));
    }

    @Test
    void testAddition_WithZero() {

        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(0.0, LengthUnit.INCHES);

        assertEquals(new QuantityLength(5.0, LengthUnit.FEET), a.add(b));
    }

    @Test
    void testAddition_NegativeValues() {

        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(-2.0, LengthUnit.FEET);

        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), a.add(b));
    }

    @Test
    void testAddition_NullSecondOperand() {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(NullPointerException.class, () -> a.add(null));
    }
}