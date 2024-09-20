package net.jmp.demo.java23.testutil;

/*
 * (#)TestUtils.java    0.7.0   09/20/2024
 *
 * @author   Jonathan Parker
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

/**
 * A class of utility methods in support
 * of the unit test classes.
 */
public final class TestUtils {
    /**
     * Cast object to an instance of type T.
     *
     * @param   <T>     The type of instance to cast to
     * @param   t       The class of type T
     * @param   object  java.lang.Object
     * @return          T
     */
    public static <T> T castToType(final Class<T> t, final Object object) {
        Objects.requireNonNull(t, () -> "Class<T> t is null");
        Objects.requireNonNull(object, () -> "Object object is null");

        return t.cast(object);
    }

    /**
     * Create a list of elements of type T from
     * a List of wildcard-typed objects.
     *
     * @param   <T>         The type of element in the list
     * @param   list        java.util.List<?>
     * @param   clazz       The class of type T
     * @return              java.util.List<T>
     */
    public static <T> List<T> listToTypedList(final List<?> list, final Class<T> clazz) {
        Objects.requireNonNull(list, () -> "List<?> list is null");
        Objects.requireNonNull(clazz, () -> "Class<T> clazz");

        return list
                .stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .toList();
    }
}
