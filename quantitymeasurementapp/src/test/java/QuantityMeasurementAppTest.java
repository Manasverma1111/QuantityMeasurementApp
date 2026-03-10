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
    void testEquality_LitreToLitre_SameValue() {

        Quantity<VolumeUnit> a =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertTrue(a.equals(b));
    }

    @Test
    void testEquality_LitreToLitre_DifferentValue() {

        Quantity<VolumeUnit> a =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        assertFalse(a.equals(b));
    }

    @Test
    void testEquality_LitreToMillilitre_EquivalentValue() {

        Quantity<VolumeUnit> a =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(a.equals(b));
    }

    @Test
    void testEquality_MillilitreToLitre_EquivalentValue() {

        Quantity<VolumeUnit> a =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertTrue(a.equals(b));
    }

    @Test
    void testEquality_LitreToGallon_EquivalentValue() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(3.78541, VolumeUnit.LITRE);

        Quantity<VolumeUnit> gallon =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        assertTrue(litre.equals(gallon));
    }

    @Test
    void testEquality_NullComparison() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertFalse(volume.equals(null));
    }

    @Test
    void testEquality_SameReference() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertTrue(volume.equals(volume));
    }

    @Test
    void testConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                litre.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_MillilitreToLitre() {

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                ml.convertTo(VolumeUnit.LITRE);

        assertEquals(1.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_GallonToLitre() {

        Quantity<VolumeUnit> gallon =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result =
                gallon.convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), EPS);
    }

    @Test
    void testConversion_LitreToGallon() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(3.78541, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                litre.convertTo(VolumeUnit.GALLON);

        assertEquals(1.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_SameUnit() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                litre.convertTo(VolumeUnit.LITRE);

        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_ZeroValue() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(0.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                litre.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(0.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_SameUnit_LitrePlusLitre() {

        Quantity<VolumeUnit> a =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> b =
                new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = a.add(b);

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_LitrePlusMillilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                litre.add(ml);

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_MillilitrePlusLitre() {

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                ml.add(litre);

        assertEquals(2000.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_GallonPlusLitre() {

        Quantity<VolumeUnit> gallon =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> litre =
                new Quantity<>(3.78541, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                gallon.add(litre);

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Litre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                litre.add(ml, VolumeUnit.LITRE);

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Millilitre() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                litre.add(ml, VolumeUnit.MILLILITRE);

        assertEquals(2000.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_WithZero() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> zero =
                new Quantity<>(0.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                litre.add(zero);

        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testVolumeVsLength_Incompatible() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(volume.equals(length));
    }

    @Test
    void testVolumeVsWeight_Incompatible() {

        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(volume.equals(weight));
    }
}