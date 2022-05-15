package com.arsnaz.testing.math;

import com.arsnaz.testing.math.trigonometry.Csc;
import com.arsnaz.testing.math.trigonometry.Sin;
import com.arsnaz.testing.util.Validation;

import java.util.function.Function;

public class SystemTrigonometryPart implements Function<Double, Double> {
    private final Csc csc;
    private final Sin sin;

    public SystemTrigonometryPart(Csc csc, Sin sin) {
        this.csc = csc;
        this.sin = sin;
    }


    @Override
    public Double apply(Double x) {
        Validation.functionArgumentValidate(x);
        return csc.apply(x) * sin.apply(x);
    }
}
