package net.jmp.demo.java23.demos;

/*
 * (#)TestScopedValueDemo.java  0.8.0   09/24/2024
 * (#)TestScopedValueDemo.java  0.7.0   09/20/2024
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

import static net.jmp.util.testing.testutil.TestUtils.*;

import static org.junit.Assert.*;

import org.junit.Test;

/// The test class for ScopedValueDemo.
///
/// @version    0.8.0
/// @since      0.7.0
public final class TestScopedValueDemo {
    @Test
    public void testBasics() throws Exception {
        final var demo = new ScopedValueDemo();
        final var method = ScopedValueDemo.class.getDeclaredMethod("basic");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final boolean result = castToType(Boolean.class, o);

        assertTrue(result);
    }

    @Test
    public void testMultiples() throws Exception {
        final var demo = new ScopedValueDemo();
        final var method = ScopedValueDemo.class.getDeclaredMethod("multiples");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final String result = castToType(String.class, o);

        assertNotNull(result);
        assertEquals("Parker", result);
    }

    @Test
    public void testRebinding() throws Exception {
        final var demo = new ScopedValueDemo();
        final var method = ScopedValueDemo.class.getDeclaredMethod("rebinding");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final String result = castToType(String.class, o);

        assertNotNull(result);
        assertEquals("Jonathan", result);
    }

    @Test
    public void testInheritance() throws Exception {
        final var demo = new ScopedValueDemo();
        final var method = ScopedValueDemo.class.getDeclaredMethod("inheritance");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final List<?> list = castToType(List.class, o);
        final List<ScopedValueDemo.ChildTaskInfo> results = listToTypedList(list, ScopedValueDemo.ChildTaskInfo.class);

        assertNotNull(results);
        assertEquals(3, results.size());
        assertEquals(new ScopedValueDemo.ChildTaskInfo("Duke:1", 1), results.get(0));
        assertEquals(new ScopedValueDemo.ChildTaskInfo("Duke:2", 2), results.get(1));
        assertEquals(new ScopedValueDemo.ChildTaskInfo("Duke:3", 3), results.get(2));
    }
}
