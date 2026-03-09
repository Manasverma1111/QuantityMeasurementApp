package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.org.example.LengthUnit;
import src.main.java.org.example.Quantity;
import src.main.java.org.example.WeightUnit;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityTest {

    private static final double EPS = 1e-6;

    @Test
    void testLengthEquality() {

        Quantity<LengthUnit> a =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12, LengthUnit.INCHES);

        assertTrue(a.equals(b));
    }

    @Test
    void testWeightEquality() {

        Quantity<WeightUnit> a =
                new Quantity<>(1, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> b =
                new Quantity<>(1000, WeightUnit.GRAM);

        assertTrue(a.equals(b));
    }

    @Test
    void testLengthConversion() {

        Quantity<LengthUnit> q =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPS);
    }

    @Test
    void testWeightConversion() {

        Quantity<WeightUnit> q =
                new Quantity<>(1, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                q.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPS);
    }

    @Test
    void testLengthAddition() {

        Quantity<LengthUnit> a =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                a.add(b, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testWeightAddition() {

        Quantity<WeightUnit> a =
                new Quantity<>(1, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> b =
                new Quantity<>(1000, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                a.add(b, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), EPS);
    }
}