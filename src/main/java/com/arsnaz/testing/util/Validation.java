package com.arsnaz.testing.util;

public class Validation {
    public static void precisionValidate(double precision) {
        if (precision <= 0) {
            throw new IllegalArgumentException("Precision value annot be negative or zero");
        }
    }


    public static void functionArgumentValidate(Double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Argument cannot be NaN or infinite");
        }
    }
}
