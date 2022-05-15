package com.arsnaz.testing;

import com.arsnaz.testing.math.System;
import com.arsnaz.testing.math.SystemLogarithmPart;
import com.arsnaz.testing.math.SystemTrigonometryPart;
import com.arsnaz.testing.math.log.Ln;
import com.arsnaz.testing.math.log.Log10;
import com.arsnaz.testing.math.log.Log3;
import com.arsnaz.testing.math.log.Log5;
import com.arsnaz.testing.math.trigonometry.Csc;
import com.arsnaz.testing.math.trigonometry.Sin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SystemTest {
    private static final double DELTA = 0.00001;
    private static final double PRECISION = 0.0000000001;
    private System system;


    @BeforeEach
    void setUp() {
        Sin sin = new Sin(PRECISION);
        Csc csc = new Csc(sin);
        Ln ln  = new Ln(PRECISION);
        Log3 log3 = new Log3(ln);
        Log5 log5 = new Log5(ln);
        Log10 log10 = new Log10(ln);
        SystemLogarithmPart logPart = new SystemLogarithmPart(ln, log3, log5, log10);
        SystemTrigonometryPart trigPart = new SystemTrigonometryPart(csc, sin);
        this.system = new System(trigPart, logPart);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    public void systemThrowsExceptionWhenInfinity(double arg) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> system.apply(arg));
    }

    @Test
    public void systemThrowsExceptionWhenNaN() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> system.apply(Double.NaN));
    }


    //  Only 1 and 0 values out of range for this function
    @ParameterizedTest
    @ValueSource(doubles = {1.0, 1.000000001, 1.0000000002, 0.0, 0.000000001, -0.0000000005})
    public void systemReturnsNaNWhenArgumentOutOfRange(double arg) {
        Assertions.assertEquals(Double.NaN, system.apply(arg));
    }


    @ParameterizedTest
    @ValueSource(doubles = {-0.1, -500, -14, -0.132, -2})
    public void systemReturnsOneWhenArgumentLessThanZero(double arg) {
        Assertions.assertEquals(1.0, system.apply(arg), DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0.0025, 68.8009095",
            "0.31, 2.6289219",
            "0.49, 0.9752891",
            "0.77, 0.1309249",
            "0.93, 0.0100937"
    })
    public void systemReturnsRightValueWhenArgFromZeroToOne(double arg, double res) {
        Assertions.assertEquals(res, system.apply(arg), DELTA);
    }


    @ParameterizedTest
    @CsvSource(value = {

            "1.21, 0.0696413",
            "4.48, 4.3101502",
            "59.88, 32.0975699",
            "591.5, 78.0785685"
    })
    public void systemReturnsRightValueWhenArgMoreThanOne(double arg, double res) {
        Assertions.assertEquals(res, system.apply(arg), DELTA);
    }








}
