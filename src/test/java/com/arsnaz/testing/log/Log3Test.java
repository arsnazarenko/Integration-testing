package com.arsnaz.testing.log;

import com.arsnaz.testing.math.log.Ln;
import com.arsnaz.testing.math.log.Log3;
import com.arsnaz.testing.math.trigonometry.Csc;
import com.arsnaz.testing.math.trigonometry.Sin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Log3Test {

    private Log3 log3;
    private static final double DELTA = 0.00001;
    private static final double PRECISION = 0.0000001;


    @BeforeEach
    public void setUp() {
        log3 = new Log3(new Ln(PRECISION));
    }


    @ParameterizedTest
    @CsvSource(value = {
            "0.5, -0.630930",
            "5.5, 1.55173",
            "24.2, 2.900343"
    })
    public void log3ReturnsRightValuesWhenNormalParameters(double arg, double ans) {
        double tmp = log3.apply(arg);
        assertEquals(tmp, ans, DELTA);
    }

    @Test
    public void log3ReturnsZeroWhenOne() {
        double tmp = log3.apply(1.0);
        assertEquals(tmp, 0, DELTA);
    }

    @ParameterizedTest
    @ValueSource( doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    public void log3ThrowsExceptionWhenArgInvalid(double arg) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> log3.apply(arg));
    }



}
