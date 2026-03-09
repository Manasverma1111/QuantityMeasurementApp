package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.org.example.QuantityWeight;
import src.main.java.org.example.WeightUnit;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testEquality_KilogramToKilogram_SameValue() {

        QuantityWeight a = new QuantityWeight(1, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1, WeightUnit.KILOGRAM);

        assertTrue(a.equals(b));
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {

        QuantityWeight a = new QuantityWeight(1, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000, WeightUnit.GRAM);

        assertTrue(a.equals(b));
    }

    @Test
    void testEquality_KilogramToPound_EquivalentValue() {

        QuantityWeight a = new QuantityWeight(1, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(2.20462, WeightUnit.POUND);

        assertTrue(a.equals(b));
    }

    @Test
    void testConversion_KilogramToGram() {

        QuantityWeight q = new QuantityWeight(1, WeightUnit.KILOGRAM);

        QuantityWeight result = q.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_PoundToKilogram() {

        QuantityWeight q = new QuantityWeight(2.20462, WeightUnit.POUND);

        QuantityWeight result = q.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_KilogramPlusGram() {

        QuantityWeight a = new QuantityWeight(1, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000, WeightUnit.GRAM);

        QuantityWeight result = a.add(b);

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit() {

        QuantityWeight a = new QuantityWeight(1, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000, WeightUnit.GRAM);

        QuantityWeight result = a.add(b, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_WithZero() {

        QuantityWeight a = new QuantityWeight(5, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(0, WeightUnit.GRAM);

        QuantityWeight result = a.add(b);

        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testInvalidUnit() {

        assertThrows(
                NullPointerException.class,
                () -> new QuantityWeight(1, null));
    }
}