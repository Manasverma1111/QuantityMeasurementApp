package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.org.example.Inches;
import static org.junit.jupiter.api.Assertions.*;

public class InchesTest {

    @Test
    void testEquality_SameValue() {
        Inches i1 = new Inches(10.0);
        Inches i2 = new Inches(10.0);
        assertEquals(i1, i2);
    }

    @Test
    void testEquality_DifferentValue() {
        Inches i1 = new Inches(10.0);
        Inches i2 = new Inches(12.0);
        assertNotEquals(i1, i2);
    }

    @Test
    void testEquality_NullComparison() {
        Inches i1 = new Inches(10.0);
        assertNotEquals(i1, null);
    }

    @Test
    void testEquality_SameReference() {
        Inches i1 = new Inches(8.0);
        assertEquals(i1, i1);
    }

    @Test
    void testEquality_NonNumericInput() {
        assertThrows(IllegalArgumentException.class,
                () -> new Inches(Double.NaN));
    }
}

