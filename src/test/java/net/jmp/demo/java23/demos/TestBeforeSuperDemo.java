package net.jmp.demo.java23.demos;

/*
 * (#)TestBeforeSuperDemo.java  0.8.0   09/24/2024
 * (#)TestBeforeSuperDemo.java  0.7.0   09/20/2024
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

import java.lang.reflect.InvocationTargetException;

import static net.jmp.util.testing.testutil.TestUtils.castToType;

import static org.junit.Assert.*;

import org.junit.Test;

/// The test class for BeforeSuperDemo.
///
/// @version    0.8.0
/// @since      0.7.0
public final class TestBeforeSuperDemo {
    @Test
    public void testNewSquareOk() throws Exception {
        final var demo = new BeforeSuperDemo();
        final var method = BeforeSuperDemo.class.getDeclaredMethod("newSquare", int.class, int.class, String.class);

        method.setAccessible(true);

        final Object o = method.invoke(demo, 4, 3, "Yellow");
        final BeforeSuperDemo.Square square = castToType(BeforeSuperDemo.Square.class, o);

        assertNotNull(square);
        assertEquals(4, square.sides);
        assertEquals(3, square.length);
        assertEquals("Yellow", square.color);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewSquareNotOk() throws Exception {
        final var demo = new BeforeSuperDemo();
        final var method = BeforeSuperDemo.class.getDeclaredMethod("newSquare", int.class, int.class, String.class);

        method.setAccessible(true);

        try {
            method.invoke(demo, 4, 0, "Orange");
        } catch (final InvocationTargetException ite) {
            final Throwable cause = ite.getCause();

            if (cause instanceof IllegalArgumentException) {
                throw (IllegalArgumentException) cause;
            }
        }
    }
}
