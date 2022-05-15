package com.arsnaz.testing.math;

import java.util.function.Function;

public class System implements Function<Double, Double> {
    private final SystemTrigonometryPart trigPart;
    private final SystemLogarithmPart logPart;

    public System(SystemTrigonometryPart trigPart, SystemLogarithmPart logPart) {
        this.trigPart = trigPart;
        this.logPart = logPart;
    }

    @Override
    public Double apply(Double x) {
        if (Math.abs(x) <= 0.00000001) {
            return Double.NaN;
        }
        if (x <= 0) {
            return trigPart.apply(x);
        } else {
            return logPart.apply(x);
        }
    }
}
