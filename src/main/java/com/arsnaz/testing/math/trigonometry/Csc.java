package com.arsnaz.testing.math.trigonometry;

import com.arsnaz.testing.util.Validation;

import java.util.function.Function;

public class Csc implements Function<Double, Double> {
    private final Sin sin;

    public Csc(Sin sin) {
        this.sin = sin;
    }



    @Override
    public Double apply(Double x) {
        Validation.functionArgumentValidate(x);
        if (Math.abs(x % Math.PI) <= 0.00000001) {
            return Double.POSITIVE_INFINITY;
        }
        return 1 / sin.apply(x);
    }
}


