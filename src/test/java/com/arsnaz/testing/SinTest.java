package com.arsnaz.testing;

import com.arsnaz.testing.math.trigonometry.Sin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {
    private Sin sin;
    private static final double DELTA = 0.00001;
    private static final double PRECISION = 0.000001;

    @BeforeEach
    public void setUp() {
        sin = new Sin(PRECISION);
    }


    @ParameterizedTest
    @ValueSource(doubles = {
            0, -2.0, -100.0, -0.001})
    public void sinThrowsExceptionIfPrecisionLessOrEqualsZero(double precision) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Sin(precision));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0.523599, 0.5000",
            "0.785398, 0.70710",
            "1.0472, 0.86602"
    })
    public void sinReturnsPositiveValuesInFirstQuarter(double arg, double ans) {
        double tmp = sin.apply(arg);
        assertEquals(tmp, ans, DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-0.523599, -0.5000",
            "-0.785398, -0.7071",
            "-1.0472, -0.86602"
    })
    public void sinReturnsNegativeValuesInFourthQuarter(double arg, double ans) {
        double tmp = sin.apply(arg);
        assertEquals(tmp, ans, DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2.0944, 0.86602",
            "2.35619, 0.70710",
            "2.61799, 0.5000"
    })
    public void sinReturnsPositiveValuesInSecondQuarter(double arg, double ans) {
        double tmp = sin.apply(arg);
        assertEquals(tmp, ans, DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-2.0944, -0.86602",
            "-2.35619, -0.70710",
            "-2.61799, -0.5000"
    })
    public void sinReturnsNegativeValuesInThirdQuarter(double arg, double ans) {
        double tmp = sin.apply(arg);
        assertEquals(tmp, ans, DELTA);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0})
    public void sinReturnsZeroWhenZero(double arg) {
        double tmp = sin.apply(arg);
        assertEquals(tmp, 0, DELTA);
    }


    @ParameterizedTest
    @ValueSource(doubles = {3.141592/2})
    public void sinReturnsOneWhen90(double arg) {
        double tmp = sin.apply(arg);
        assertEquals(tmp, 1, DELTA);
    }

    @Test
    public void sinReturnsMinusOneWhen270() {
        double tmp = sin.apply(-3.141592/2);
        assertEquals(tmp, -1, DELTA);
    }

    @Test
    public void sinThrowsExceptionWhenNaN() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sin.apply(Double.NaN));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    public void sinThrowsExceptionWhenInfinity(double arg) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sin.apply(arg));

    }


}
