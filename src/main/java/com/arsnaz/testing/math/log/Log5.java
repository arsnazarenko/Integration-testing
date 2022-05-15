package com.arsnaz.testing.math.log;

import java.util.function.Function;

public class Log5 implements Function<Double, Double> {
    private final long BASE = 5L;
    private final Ln ln;

    public Log5(Ln ln) {
        this.ln = ln;
    }


    @Override
    public Double apply(Double x) {
        return ln.apply(x) / ln.apply((double) BASE);
    }
}
