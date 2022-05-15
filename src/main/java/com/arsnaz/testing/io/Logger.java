package com.arsnaz.testing.io;

import java.io.Closeable;
import java.io.IOException;

public interface Logger extends Closeable {
    void log(Class<?> moduleClass, double arg, double result) throws IOException;
}
