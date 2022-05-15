package com.arsnaz.testing.math;

import com.arsnaz.testing.math.log.*;
import com.arsnaz.testing.util.Validation;

import java.util.function.Function;

public class SystemLogarithmPart implements Function<Double, Double> {

    private final Ln ln;
    private final Log3 log3;
    private final Log5 log5;
    private final Log10 log10;


    public SystemLogarithmPart(Ln ln, Log3 log3, Log5 log5, Log10 log10) {
        this.ln = ln;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }

    @Override
    public Double apply(Double x) {
        Validation.functionArgumentValidate(x);
        if (Math.abs(x - 1) <= 0.00000001) {
            return Double.NaN;
        }
        return (((((log3.apply(x) - log10.apply(x)) + (ln.apply(x) + log3.apply(x))) / (log10.apply(x) / log5.apply(x))) * ln.apply(x)) *
                (log3.apply(x)/(log5.apply(x) + ln.apply(x))) );
    }

}
