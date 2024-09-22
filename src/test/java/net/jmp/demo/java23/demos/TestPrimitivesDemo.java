package net.jmp.demo.java23.demos;

/*
 * (#)TestPrimitivesDemo.java   0.7.0   09/21/2024
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

public final class TestPrimitivesDemo {
    @Test
    public void testOldMethods() throws Exception {
        final var demo = new PrimitivesDemo();
        final var method = PrimitivesDemo.class.getDeclaredMethod("oldMethods");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final List<?> list = castToType(List.class, o);
        final List<String> results = listToTypedList(list, String.class);

        assertNotNull(results);
        assertEquals(4, results.size());

        assertEquals("obj1 is a string: Hello!", results.get(0));
        assertEquals("obj2 is an integer: 6", results.get(1));
        assertEquals("HELLO!", results.get(2));
        assertEquals("36", results.get(3));
    }

    @Test
    public void testPatterns() throws Exception {
        final var demo = new PrimitivesDemo();
        final var method = PrimitivesDemo.class.getDeclaredMethod("patterns");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final List<?> list = castToType(List.class, o);
        final List<String> results = listToTypedList(list, String.class);

        assertNotNull(results);
        assertEquals(12, results.size());

        assertEquals("value is a byte: 127", results.get(0));
        assertEquals("65 instanceof byte:   65", results.get(1));
        assertEquals("65 instanceof short:  65", results.get(2));
        assertEquals("65 instanceof int:    65", results.get(3));
        assertEquals("65 instanceof long:   65", results.get(4));
        assertEquals("65 instanceof float:  65.000000", results.get(5));
        assertEquals("65 instanceof double: 65.000000", results.get(6));
        assertEquals("A instanceof char:   A", results.get(7));
        assertEquals("100000 instanceof int:    100000", results.get(8));
        assertEquals("100000 instanceof long:   100000", results.get(9));
        assertEquals("100000 instanceof float:  100000.000000", results.get(10));
        assertEquals("100000 instanceof double: 100000.000000", results.get(11));
    }

    @Test
    public void testSwitching() throws Exception {
        final var demo = new PrimitivesDemo();
        final var method = PrimitivesDemo.class.getDeclaredMethod("switching");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final List<?> list = castToType(List.class, o);
        final List<String> results = listToTypedList(list, String.class);

        assertNotNull(results);
        assertEquals(2, results.size());

        assertEquals("1000000.000000 instanceof int:    1000000", results.get(0));
        assertEquals("1000000000000.000000 instanceof long:   1000000000000", results.get(1));
    }
}
