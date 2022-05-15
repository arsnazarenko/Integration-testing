package com.arsnaz.testing.io;

import java.io.*;

public class CsvLogger implements Logger {
    private final BufferedWriter writer;

    public CsvLogger(BufferedWriter wr) {
        this.writer = wr;
    }

    public void log(Class<?> moduleClass, double arg, double result) throws IOException {
            writer.write(arg + ", Result of module " + moduleClass.getSimpleName() + ": " + result + "\n");
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
