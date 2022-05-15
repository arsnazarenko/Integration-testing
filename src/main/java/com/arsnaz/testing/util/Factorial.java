package com.arsnaz.testing.util;

public class Factorial {
    public static double calculate(long n) {
        if (n == 0) {
            return 1.0;
        }
        return n * calculate(n - 1);
    }
}
