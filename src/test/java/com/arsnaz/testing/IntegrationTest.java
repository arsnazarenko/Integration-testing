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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

public class IntegrationTest {
    private static final double DELTA = 0.00001;
    private static final double PRECISION = 0.0000000001;
    private Sin sin;
    private Csc csc;
    private Ln ln;
    private Log3 log3;
    private Log5 log5;
    private Log10 log10;
    private SystemTrigonometryPart sysTrig;
    private SystemLogarithmPart sysLog;
    private System system;

    private Sin sinStub = Mockito.mock(Sin.class);
    private Csc cscStub = Mockito.mock(Csc.class);
    private Ln lnStub = Mockito.mock(Ln.class);
    private Log3 log3Stub = Mockito.mock(Log3.class);
    private Log5 log5Stub = Mockito.mock(Log5.class);
    private Log10 log10Stub = Mockito.mock(Log10.class);
    private SystemTrigonometryPart sysTrigStub = Mockito.mock(SystemTrigonometryPart.class);
    private SystemLogarithmPart sysLogStub = Mockito.mock(SystemLogarithmPart.class);
    private System SystemStub = Mockito.mock(System.class);


    // Stubs init



    @BeforeEach
    public void setUp() {
        sin = new Sin(PRECISION);
        csc = new Csc(sin);
        ln  = new Ln(PRECISION);
        log3 = new Log3(ln);
        log5 = new Log5(ln);
        log10 = new Log10(ln);
        sysLog = new SystemLogarithmPart(ln, log3, log5, log10);
        sysTrig = new SystemTrigonometryPart(csc, sin);
        system = new System(sysTrig, sysLog);

        // system with stub arg more than one
        Mockito.when(lnStub.apply(3.)).thenReturn(1.0986122885294338);
        Mockito.when(lnStub.apply(5.)).thenReturn(1.6094379121080877);
        Mockito.when(lnStub.apply(10.)).thenReturn(2.302585092159792);

        Mockito.when(log3Stub.apply(15.)).thenReturn(2.464973519864892);
        Mockito.when(log5Stub.apply(15.)).thenReturn(1.6826061940321397);
        Mockito.when(log10Stub.apply(15.)).thenReturn(1.176091258926336);
        Mockito.when(lnStub.apply(15.)).thenReturn(2.708050199823223);

        Mockito.when(log3Stub.apply(87.45)).thenReturn(4.069740744764371);
        Mockito.when(log5Stub.apply(87.45)).thenReturn(2.7780302425402277);
        Mockito.when(log10Stub.apply(87.45)).thenReturn(1.941759810984128);
        Mockito.when(lnStub.apply(87.45)).thenReturn(4.471067193327069);

        // system with stub less than 0
        Mockito.when(sinStub.apply(-12.2)).thenReturn(0.35822928223672407);
        Mockito.when(cscStub.apply(-12.2)).thenReturn(2.7915082590573452);
        Mockito.when(sinStub.apply(-56.2)).thenReturn(0.3416460379627466);
        Mockito.when(cscStub.apply(-56.2)).thenReturn(2.9270059912389232);
        Mockito.when(sinStub.apply(-1.2)).thenReturn(-0.9320390859671641);
        Mockito.when(cscStub.apply(-1.2)).thenReturn(-1.0729163777099688);

        // csc with sin stub
        Mockito.when(sinStub.apply(1.)).thenReturn(0.8414709848078937);
        Mockito.when(sinStub.apply(12.5)).thenReturn(-0.06632189735110033);
        Mockito.when(sinStub.apply(63.48)).thenReturn(0.6037101670791181);

        // log3 with ln stub
        Mockito.when(lnStub.apply(4.)).thenReturn(1.386294360876086);
        Mockito.when(lnStub.apply(45.)).thenReturn(3.8066624856953672);
        Mockito.when(lnStub.apply(88.)).thenReturn(4.477336806370683);

        // log5 with ln stub
        Mockito.when(lnStub.apply(6.)).thenReturn(1.7917594687567289);
        Mockito.when(lnStub.apply(55.)).thenReturn(4.007333180224593);
        Mockito.when(lnStub.apply(15.4)).thenReturn(2.7343675081382104);

        // log10 with ln stub
        Mockito.when(lnStub.apply(11.)).thenReturn(2.3978952718778856);
        Mockito.when(lnStub.apply(32.)).thenReturn(3.4657358998876133);
        Mockito.when(lnStub.apply(105.)).thenReturn(4.653960340445435);

    }


    @ParameterizedTest
    @CsvSource(value = {
            "1, 1.18839510",
            "12.5, -15.0779763538",
            "63.48, 1.65642398377"
    })
    void cscWithSinStubReturnsRightValues(double arg, double res) {
        Csc csc = new Csc(sinStub);
        Assertions.assertEquals(res, csc.apply(arg), DELTA);
    }


    @ParameterizedTest
    @CsvSource(value = {
            "4, 1.261859507",
            "45, 3.4649735",
            "88, 4.0754475"
    })
    void log3WithLnStubReturnsRightValues(double arg, double res) {
        Log3 log3 = new Log3(lnStub);
        Assertions.assertEquals(res, log3.apply(arg), DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "6, 1.1132827",
            "55, 2.4898960",
            "15.4, 1.698958"
    })
    void log5WithLnStubReturnsRightValues(double arg, double res) {
        Log5 log5 = new Log5(lnStub);
        Assertions.assertEquals(res, log5.apply(arg), DELTA);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "11, 1.0413926",
            "32, 1.505149",
            "105, 2.0211892"
    })
    void log10WithLnStubReturnsRightValues(double arg, double res) {
        Log10 log10 = new Log10(lnStub);
        Assertions.assertEquals(res, log10.apply(arg), DELTA);
    }

    @ParameterizedTest
    @ValueSource(doubles = { -12.2, -56.2, -1.2 })
    void trigSystemPartWithStubsAlwaysReturnsOne(double arg) {
        SystemTrigonometryPart trig = new SystemTrigonometryPart(cscStub, sinStub);
        Assertions.assertEquals(1, trig.apply(arg), DELTA);

    }

    @ParameterizedTest
    @CsvSource(value = {
            "87.45, 38.3133905",
            "15, 14.0553483"
    })
    void logSystemPartWithStubsReturnRightValues(double arg, double res) {
        SystemLogarithmPart logPart = new SystemLogarithmPart(lnStub, log3Stub, log5Stub, log10Stub);
        Assertions.assertEquals(res, logPart.apply(arg), DELTA);
    }
}
