package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.org.example.LengthUnit;
import src.main.java.org.example.Quantity;
import src.main.java.org.example.VolumeUnit;
import src.main.java.org.example.WeightUnit;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = a.subtract(b);

        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {

        Quantity<VolumeUnit> a =
                new Quantity<>(10.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(3.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = a.subtract(b);

        assertEquals(7.0, result.getValue(), EPS);
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = a.subtract(b);

        assertEquals(9.5, result.getValue(), EPS);
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                a.subtract(b, LengthUnit.INCHES);

        assertEquals(114.0, result.getValue(), EPS);
    }

    @Test
    void testSubtraction_ResultingInNegative() {

        Quantity<LengthUnit> a =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = a.subtract(b);

        assertEquals(-5.0, result.getValue(), EPS);
    }

    @Test
    void testSubtraction_ResultingInZero() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(120.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = a.subtract(b);

        assertEquals(0.0, result.getValue(), EPS);
    }

    @Test
    void testSubtraction_WithZeroOperand() {

        Quantity<LengthUnit> a =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> zero =
                new Quantity<>(0.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = a.subtract(zero);

        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testSubtraction_NonCommutative() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result1 = a.subtract(b);
        Quantity<LengthUnit> result2 = b.subtract(a);

        assertNotEquals(result1.getValue(), result2.getValue());
    }

    @Test
    void testSubtraction_NullOperand() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> a.subtract(null));
    }

    // ------------------------------------------------
    // DIVISION TESTS
    // ------------------------------------------------

    @Test
    void testDivision_SameUnit_FeetDividedByFeet() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(2.0, LengthUnit.FEET);

        double result = a.divide(b);

        assertEquals(5.0, result, EPS);
    }

    @Test
    void testDivision_SameUnit_LitreDividedByLitre() {

        Quantity<VolumeUnit> a =
                new Quantity<>(10.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        double result = a.divide(b);

        assertEquals(2.0, result, EPS);
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches() {

        Quantity<LengthUnit> inches =
                new Quantity<>(24.0, LengthUnit.INCHES);

        Quantity<LengthUnit> feet =
                new Quantity<>(2.0, LengthUnit.FEET);

        double result = inches.divide(feet);

        assertEquals(1.0, result, EPS);
    }

    @Test
    void testDivision_CrossUnit_KilogramDividedByGram() {

        Quantity<WeightUnit> kg =
                new Quantity<>(2.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(2000.0, WeightUnit.GRAM);

        double result = kg.divide(gram);

        assertEquals(1.0, result, EPS);
    }

    @Test
    void testDivision_RatioGreaterThanOne() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(2.0, LengthUnit.FEET);

        assertTrue(a.divide(b) > 1);
    }

    @Test
    void testDivision_RatioLessThanOne() {

        Quantity<LengthUnit> a =
                new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(10.0, LengthUnit.FEET);

        assertTrue(a.divide(b) < 1);
    }

    @Test
    void testDivision_RatioEqualToOne() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(10.0, LengthUnit.FEET);

        assertEquals(1.0, a.divide(b), EPS);
    }

    @Test
    void testDivision_ByZero() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> zero =
                new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class,
                () -> a.divide(zero));
    }

    @Test
    void testDivision_NonCommutative() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(5.0, LengthUnit.FEET);

        assertNotEquals(a.divide(b), b.divide(a));
    }

    @Test
    void testDivision_CrossCategory() {

        Quantity<LengthUnit> length =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.divide((Quantity) weight));
    }

    // ------------------------------------------------
    // IMMUTABILITY TESTS
    // ------------------------------------------------

    @Test
    void testSubtraction_Immutability() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(5.0, LengthUnit.FEET);

        a.subtract(b);

        assertEquals(10.0, a.getValue(), EPS);
    }

    @Test
    void testDivision_Immutability() {

        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(5.0, LengthUnit.FEET);

        a.divide(b);

        assertEquals(10.0, a.getValue(), EPS);
    }
}