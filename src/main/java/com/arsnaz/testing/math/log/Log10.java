package com.arsnaz.testing.math.log;

import java.util.function.Function;

public class Log10 implements Function<Double, Double> {
    private final long BASE = 10L;
    private final Ln ln;

    public Log10(Ln ln) {
        this.ln = ln;
    }

    @Override
    public Double apply(Double x) {
        return ln.apply(x) / ln.apply((double) BASE);
    }

}
