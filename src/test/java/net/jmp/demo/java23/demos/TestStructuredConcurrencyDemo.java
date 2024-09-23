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
        final List<?> list = castToType(List.class, o);
        final List<String> results = listToTypedList(list, String.class);

        assertNotNull(results);
        assertEquals(8, results.size());

        assertEquals("User: Jonathan; Order: 123", results.get(0));
        assertEquals("Red", results.get(1));
        assertEquals("Orange", results.get(2));
        assertEquals("Yellow", results.get(3));
        assertEquals("Green", results.get(4));
        assertEquals("Blue", results.get(5));
        assertEquals("Indigo", results.get(6));
        assertEquals("Violet", results.get(7));
    }

    @Test
    public void testShutdownOnSuccess() throws Exception {
        final var demo = new StructuredConcurrencyDemo();
        final var method = StructuredConcurrencyDemo.class.getDeclaredMethod("shutdownOnSuccess");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final String result = castToType(String.class, o);

        assertNotNull(result);

        List<String> expected = List.of(
                "Result: Jonathan",
                "Result: Martin",
                "Result: Parker"
        );

        assertTrue(expected.contains(result));
    }

    @Test
    public void testNoShutdownPolicy() throws Exception {
        final var demo = new StructuredConcurrencyDemo();
        final var method = StructuredConcurrencyDemo.class.getDeclaredMethod("noShutdownPolicy");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final List<?> list = castToType(List.class, o);
        final List<String> results = listToTypedList(list, String.class);

        assertNotNull(results);
        assertEquals(4, results.size());

        assertEquals("Future: 1", results.get(0));
        assertEquals("Future: 2", results.get(1));
        assertEquals("Future: java.lang.ArithmeticException: / by zero", results.get(2));
        assertEquals("Future: 0", results.get(3));
    }

    @Test
    public void testCustomPolicy() throws Exception {
        final var demo = new StructuredConcurrencyDemo();
        final var method = StructuredConcurrencyDemo.class.getDeclaredMethod("customPolicy");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final List<?> list = castToType(List.class, o);
        final List<String> results = listToTypedList(list, String.class);

        assertNotNull(results);
        assertEquals(6, results.size());

        final List<String> expected = List.of(
                "Custom result: 0",
                "Custom result: 1",
                "Custom result: 2",
                "Custom result: 5",
                "Custom throwable: / by zero"
        );

        results.forEach(result -> assertTrue(expected.contains(result)));
    }
}
