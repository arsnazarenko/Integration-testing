package com.arsnaz.testing.log;

import com.arsnaz.testing.math.log.Ln;
import com.arsnaz.testing.math.log.Log10;
import com.arsnaz.testing.math.log.Log5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Log10Test {
    private Log10 log10;
    private static final double DELTA = 0.00001;
    private static final double PRECISION = 0.0000001;


    @BeforeEach
    public void setUp() {
        log10 = new Log10(new Ln(PRECISION));
    }


    @ParameterizedTest
    @CsvSource(value = {
            "4.56, 0.658965",
            "0.159, -0.798603",
            "283, 2.451786"
    })
    public void log10ReturnsRightValuesWhenNormalParameters(double arg, double ans) {
        double tmp = log10.apply(arg);
        assertEquals(tmp, ans, DELTA);
    }

    @Test
    public void log10ReturnsZeroWhenOne() {
        double tmp = log10.apply(1.0);
        assertEquals(tmp, 0, DELTA);
    }

    @ParameterizedTest
    @ValueSource( doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    public void log10ThrowsExceptionWhenArgInvalid(double arg) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> log10.apply(arg));
    }
}
