package src.main.java.org.example;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println(
                new QuantityWeight(1, WeightUnit.KILOGRAM)
                        .equals(new QuantityWeight(1000, WeightUnit.GRAM)));

        System.out.println(
                new QuantityWeight(1, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM));

        System.out.println(
                new QuantityWeight(1, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000, WeightUnit.GRAM)));

        System.out.println(
                new QuantityWeight(1, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000, WeightUnit.GRAM), WeightUnit.GRAM));
    }
}