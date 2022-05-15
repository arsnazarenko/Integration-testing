package com.arsnaz.testing.math.log;

import java.util.function.Function;

public class Log3 implements Function<Double, Double> {
    private final long BASE = 3L;
    private final Ln ln;

    public Log3(Ln ln) {
        this.ln = ln;
    }


    @Override
    public Double apply(Double x) {
        return ln.apply(x) / ln.apply((double) BASE);
    }
}
