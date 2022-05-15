package com.arsnaz.testing.math.trigonometry;

import com.arsnaz.testing.util.Factorial;
import com.arsnaz.testing.util.Validation;

import java.util.function.Function;

public class Sin implements Function<Double, Double> {


    private final double precision;

    public Sin(double precision) {
        Validation.precisionValidate(precision);
        this.precision = precision;
    }

    @Override
    public Double apply(Double x) {
        Validation.functionArgumentValidate(x);
        int flag = 1;
        x %= (2 * Math.PI);
        if (x > Math.PI) {
            x -= Math.PI;
            flag = -1;
        }
        double prevSum = 0;
        double sum = x;
        long n = 1;
        while(Math.abs(sum - prevSum) >= precision) {
            prevSum = sum;
            sum += (Math.pow(-1, n) * Math.pow(x, 2 * n + 1))/(Factorial.calculate(2 * n + 1));
            n++;
        }
        return sum * flag;
    }
}
