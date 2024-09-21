package net.jmp.demo.java23.demos;

/*
 * (#)TestUnnamedVariablesDemo.java 0.7.0   09/21/2024
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

public final class TestUnnamedVariablesDemo {
    @Test
    public void testUnnamedVariables() throws Exception {
        final var demo = new UnnamedVariablesDemo();
        final var method = UnnamedVariablesDemo.class.getDeclaredMethod("unnamedVariables");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final List<?> list = castToType(List.class, o);
        final List<String> results = listToTypedList(list, String.class);

        assertNotNull(results);
        assertEquals(7, results.size());

        assertEquals("Division by zero", results.get(0));
        assertEquals("An iteration", results.get(1));
        assertEquals("An iteration", results.get(2));
        assertEquals("An iteration", results.get(3));
        assertEquals("An iteration", results.get(4));
        assertEquals("An iteration", results.get(5));
        assertEquals("Opened the fork join common pool", results.get(6));
    }

    @Test
    public void testUnnamedPatterns() throws Exception {
        final var demo = new UnnamedVariablesDemo();
        final var method = UnnamedVariablesDemo.class.getDeclaredMethod("unnamedPatterns");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final String result = castToType(String.class, o);

        assertNotNull(result);

        assertEquals("Set", result);
    }
}
