package com.arsnaz.testing;

import com.arsnaz.testing.io.Logger;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class App {
    private final List<Function<Double, Double>> functions;
    private final Logger logger;

    public App(List<Function<Double, Double>> functions, Logger logger) {
        this.functions = functions;
        this.logger = logger;
    }

    public void run(double start, double step, long n) {
        for (long i = 0; i <= n; ++i) {
            double arg = start + (step * i);
            functions.forEach(f -> {
                try {
                    logger.log(f.getClass(), arg, f.apply(arg));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}
