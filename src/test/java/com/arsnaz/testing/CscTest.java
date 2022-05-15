package com.arsnaz.testing;

import com.arsnaz.testing.math.trigonometry.Csc;
import com.arsnaz.testing.math.trigonometry.Sin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CscTest {
    private Csc csc;
    private static final double DELTA = 0.00001;
    private static final double PRECISION = 0.000001;


    @BeforeEach
    public void setUp() {
        csc = new Csc(new Sin(PRECISION));
    }


    @ParameterizedTest
    @CsvSource(value = {
            "1.047198, 1.15470",
            "0.78539, 1.414225",
            "0.52359, 2.00003"
    })
    public void cscReturnsPositiveValuesWhenSinPositive(double arg, double ans) {
        double tmp = csc.apply(arg);
        assertEquals(tmp, ans, DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-1.047198, -1.15470",
            "-0.78539, -1.414225",
            "-0.52359, -2.00003"
    })
    public void cscReturnsNegativeValuesWhenSinNegative(double arg, double ans) {
        double tmp = csc.apply(arg);
        assertEquals(tmp, ans, DELTA);
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0, Math.PI, Math.PI * 2, Math.PI * 3}
    )
    public void cscReturnsInfinityWhenSinEqualZero(double arg) {
        double tmp = csc.apply(arg);
        assertEquals(tmp, Double.POSITIVE_INFINITY);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2, (Math.PI / 2) * 5, (Math.PI / 2) * 9})
    public void cscReturnsOneWhenSinEqualOne(double arg) {
        double tmp = csc.apply(arg);
        assertEquals(tmp, 1, DELTA);
    }

    @ParameterizedTest
    @ValueSource(doubles = {(Math.PI / 2) * 3, (Math.PI / 2) * 7})
    public void cscReturnsMinusOneWhenSinEqualMinusOne(double arg) {
        double tmp = csc.apply(arg);
        assertEquals(tmp, -1, DELTA);
    }


    @Test
    public void cscThrowsExceptionWhenNaN() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> csc.apply(Double.NaN));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    public void cscThrowsExceptionWhenInfinity(double arg) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> csc.apply(arg));

    }

}
