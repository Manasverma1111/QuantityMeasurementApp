package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.org.example.Feet;

import static org.junit.jupiter.api.Assertions.*;

public class FeetTest {

    @Test
    void testEquality_SameValue() {
        Feet f1 = new Feet(2.0);
        Feet f2 = new Feet(2.0);
        assertEquals(f1, f2);
    }

    @Test
    void testEquality_DifferentValue() {
        Feet f1 = new Feet(2.0);
        Feet f2 = new Feet(3.0);
        assertNotEquals(f1, f2);
    }

    @Test
    void testEquality_NullComparison() {
        Feet f1 = new Feet(2.0);
        assertNotEquals(f1, null);
    }

    @Test
    void testEquality_SameReference() {
        Feet f1 = new Feet(5.0);
        assertEquals(f1, f1);
    }

    @Test
    void testEquality_NonNumericInput() {
        assertThrows(IllegalArgumentException.class,
                () -> new Feet(Double.NaN));
    }
}

