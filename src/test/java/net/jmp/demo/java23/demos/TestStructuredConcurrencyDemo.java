package net.jmp.demo.java23.demos;

/*
 * (#)TestStructuredConcurrencyDemo.java    0.7.0   09/21/2024
 *
 * @version  0.7.0
 * @since    0.7.0
 *
 * MIT License
 *
 * Copyright (c) 2024 Jonathan M. Parker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import module java.base;

import static net.jmp.demo.java23.testutil.TestUtils.*;

import static org.junit.Assert.*;

import org.junit.Test;

public final class TestStructuredConcurrencyDemo {
    @Test
    public void testShutdownOnFailure() throws Exception {
        final var demo = new StructuredConcurrencyDemo();
        final var method = StructuredConcurrencyDemo.class.getDeclaredMethod("shutdownOnFailure");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
    }

    @Test
    public void testShutdownOnSuccess() throws Exception {
        final var demo = new StructuredConcurrencyDemo();
        final var method = StructuredConcurrencyDemo.class.getDeclaredMethod("shutdownOnSuccess");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
    }

    @Test
    public void testNoShutdownPolicy() throws Exception {
        final var demo = new StructuredConcurrencyDemo();
        final var method = StructuredConcurrencyDemo.class.getDeclaredMethod("noShutdownPolicy");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
    }

    @Test
    public void testCustomPolicy() throws Exception {
        final var demo = new StructuredConcurrencyDemo();
        final var method = StructuredConcurrencyDemo.class.getDeclaredMethod("customPolicy");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
    }
}
