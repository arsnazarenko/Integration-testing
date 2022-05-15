package com.arsnaz.testing;

import com.arsnaz.testing.io.CsvLogger;
import com.arsnaz.testing.io.Logger;
import com.arsnaz.testing.math.System;
import com.arsnaz.testing.math.SystemLogarithmPart;
import com.arsnaz.testing.math.SystemTrigonometryPart;
import com.arsnaz.testing.math.log.Ln;
import com.arsnaz.testing.math.log.Log10;
import com.arsnaz.testing.math.log.Log3;
import com.arsnaz.testing.math.log.Log5;
import com.arsnaz.testing.math.trigonometry.Csc;
import com.arsnaz.testing.math.trigonometry.Sin;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static final double PRECISION = 0.000001;

    public static void main(String[] args) {
        Sin sin = new Sin(0.001);
        Csc csc = new Csc(sin);
        Ln ln  = new Ln(0.001);
        Log3 log3 = new Log3(ln);
        Log5 log5 = new Log5(ln);
        Log10 log10 = new Log10(ln);
        SystemLogarithmPart logPart = new SystemLogarithmPart(ln, log3, log5, log10);
        SystemTrigonometryPart trigPart = new SystemTrigonometryPart(csc, sin);
        System system = new System(trigPart, logPart);

        List<Function<Double, Double>> functions = Arrays.asList(sin, csc, ln, log3, log5, log10, system);

        final String fileName = "./src/main/resources/result.csv";
        try(Logger csvLog = new CsvLogger(new BufferedWriter(new FileWriter(fileName)))) {
            App app = new App(functions, csvLog);
            app.run(-20, 0.25, 160);
        } catch (IOException e) {
            throw new RuntimeException("No permission to open file or file does not exist", e);
        }
    }
}
