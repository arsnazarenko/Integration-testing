package com.arsnaz.testing.math.log;

import com.arsnaz.testing.util.Validation;

import java.util.function.Function;

public class Ln implements Function<Double, Double> {
    private final double precision;

    public Ln(double precision) {
        Validation.precisionValidate(precision);
        this.precision = precision;
    }


    @Override
    public Double apply(Double x) {
        Validation.functionArgumentValidate(x);
        if (x <= 0) {
            return Double.NaN;
        }
        long n = 1;
        double prevSum = -Double.MAX_VALUE;
        double sum = 0;
        if (x <= 2) {
            x -= 1;
            while(Math.abs(sum - prevSum) >= precision) {
                prevSum = sum;
                sum += (Math.pow(-1, n + 1) * Math.pow(x, n)) / (n);
                ++n;
            }
        } else {
            x = (x / (x - 1));
            while(Math.abs(sum - prevSum) >= precision) {
                prevSum = sum;
                sum += 1/(n * Math.pow(x, n));
                ++n;
            }
        }
        return sum;
    }


}
