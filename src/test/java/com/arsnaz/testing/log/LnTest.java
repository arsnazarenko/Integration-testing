package com.arsnaz.testing.log;

import com.arsnaz.testing.math.log.Ln;
import com.arsnaz.testing.math.trigonometry.Sin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LnTest {
    private Ln ln;
    private static final double DELTA = 0.00001;
    private static final double PRECISION = 0.000001;

    @BeforeEach
    public void setUp(){
        ln = new Ln(PRECISION);
    }



    @ParameterizedTest
    @ValueSource(doubles = {
            0, -2.0, -100.0, -0.001})
    public void lnThrowsExceptionIfPrecisionLessOrEqualsZero(double precision) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Ln(precision));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.2, -5, 0})
    public void lnReturnsNaNWhenLessOrEqualsZero(double arg){
        double tmp = ln.apply(arg);
        assertEquals(tmp, Double.NaN);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2.7, 0.993251",
            "6.0, 1.7917550",
            "17.6, 2.8678844"
    })
    public void lnReturnsRightValuesWhenNormalParameters(double arg, double ans){
        double tmp = ln.apply(arg);
        assertEquals(tmp, ans, DELTA);
    }


    @Test
    public void lnReturnsZeroWhenOne(){
        double tmp = ln.apply(1.0);
        assertEquals(tmp, 0);
    }


    @Test
    public void lnThrowsExceptionWhenNaN() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ln.apply(Double.NaN));
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    public void lnThrowsExceptionWhenInfinity(double arg) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ln.apply(arg));

    }


}
