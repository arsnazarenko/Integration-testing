package com.arsnaz.testing.log;

import com.arsnaz.testing.math.log.Ln;
import com.arsnaz.testing.math.log.Log5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Log5Test {

    private Log5 log5;
    private static final double DELTA = 0.00001;
    private static final double PRECISION = 0.0000001;


    @BeforeEach
    public void setUp() {
        log5 = new Log5(new Ln(PRECISION));
    }


    @ParameterizedTest
    @CsvSource(value = {
            "1.5, 0.251930",
            "0.625, -0.292030",
            "16, 1.722706"
    })
    public void log5ReturnsRightValuesWhenNormalParameters(double arg, double ans) {
        double tmp = log5.apply(arg);
        assertEquals(tmp, ans, DELTA);
    }

    @Test
    public void log5ReturnsZeroWhenOne() {
        double tmp = log5.apply(1.0);
        assertEquals(tmp, 0, DELTA);
    }

    @ParameterizedTest
    @ValueSource( doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    public void log5ThrowsExceptionWhenArgInvalid(double arg) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> log5.apply(arg));
    }
}
