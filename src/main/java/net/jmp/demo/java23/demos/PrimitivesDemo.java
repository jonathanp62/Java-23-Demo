package net.jmp.demo.java23.demos;

/*
 * (#)PrimitivesDemo.java   0.8.0   09/24/2024
 * (#)PrimitivesDemo.java   0.7.0   09/22/2024
 * (#)PrimitivesDemo.java   0.6.0   09/20/2024
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
import module org.slf4j;

import static net.jmp.util.logging.LoggerUtils.*;

/// A class the demonstrates primitive types
/// in patterns, instanceOf, and switch.
///
/// @version    0.8.0
/// @since      0.6.0
public final class PrimitivesDemo implements Demo {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The default constructor.
    public PrimitivesDemo() {
        super();
    }

    /// The demo method.
    @Override
    public void demo() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        if (this.logger.isInfoEnabled()) {
            this.oldMethods().forEach(this.logger::info);
            this.patterns().forEach(this.logger::info);
            this.switching().forEach(this.logger::info);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Demonstrate the old methods to
    /// do pattern matching and switching.
    ///
    /// @return java.util.List<java.lang.String>
    private List<String> oldMethods() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<String> strings = new ArrayList<>();

        final Object obj1 = (Object) new String("Hello!");
        final Object obj2 = (Object) Integer.valueOf(6);

        // instanceOf

        if (obj1 instanceof String s && s.length() >= 5) {
            strings.add(String.format("obj1 is a string: %s", s));
        } else if (obj1 instanceof Integer i) {
            strings.add(String.format("obj1 is an integer: %d", i));
        } else {
            strings.add("obj1 is unknown");;
        }

        if (obj2 instanceof String s && s.length() >= 5) {
            strings.add(String.format("obj2 is a string: %s", s));
        } else if (obj2 instanceof Integer i) {
            strings.add(String.format("obj2 is an integer: %d", i));
        } else {
            strings.add("obj2 is unknown");;
        }

        // switch (when is a guard, i.e. a boolean expression)

        switch (obj1) {
            case String s when s.length() >= 5 -> strings.add(s.toUpperCase());
            case Integer i                     -> strings.add(String.valueOf(i * i));
            case null, default                 -> strings.add(obj1.toString());
        }

        switch (obj2) {
            case String s when s.length() >= 5 -> strings.add(s.toUpperCase());
            case Integer i                     -> strings.add(String.valueOf(i * i));
            case null, default                 -> strings.add(obj2.toString());
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(strings));
        }

        return strings;
    }

    /// Demonstrate primitive
    /// types in patterns.
    ///
    /// @return java.util.List<java.lang.String>
    private List<String> patterns() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<String> strings = new ArrayList<>();

        int value = 127;

        // byte ranges from -128 to 127

        if (value instanceof byte b) {
            strings.add(String.format("value is a byte: %d", b));   // Does execute
        }

        value = 128;

        if (value instanceof byte b) {
            strings.add(String.format("value is a byte: %d", b));   // Does not execute
        }

        value = 65;

        if (value instanceof byte b)   strings.add(String.format("%d instanceof byte:   %d", value, b));  // Does execute
        if (value instanceof short s)  strings.add(String.format("%d instanceof short:  %d", value, s));  // Does execute
        if (value instanceof int i)    strings.add(String.format("%d instanceof int:    %d", value, i));  // Does execute
        if (value instanceof long l)   strings.add(String.format("%d instanceof long:   %d", value, l));  // Does execute
        if (value instanceof float f)  strings.add(String.format("%d instanceof float:  %f", value, f));  // Does execute
        if (value instanceof double d) strings.add(String.format("%d instanceof double: %f", value, d));  // Does execute
        if (value instanceof char c)   strings.add(String.format("%c instanceof char:   %c", value, c));  // Does execute

        value = 100_000;

        if (value instanceof byte b)   strings.add(String.format("%d instanceof byte:   %d", value, b));  // Does not execute
        if (value instanceof short s)  strings.add(String.format("%d instanceof short:  %d", value, s));  // Does not execute
        if (value instanceof int i)    strings.add(String.format("%d instanceof int:    %d", value, i));  // Does execute
        if (value instanceof long l)   strings.add(String.format("%d instanceof long:   %d", value, l));  // Does execute
        if (value instanceof float f)  strings.add(String.format("%d instanceof float:  %f", value, f));  // Does execute
        if (value instanceof double d) strings.add(String.format("%d instanceof double: %f", value, d));  // Does execute
        if (value instanceof char c)   strings.add(String.format("%c instanceof char:   %c", value, c));  // Does not execute

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(strings));
        }

        return strings;
    }

    /// Demonstrate primitive
    /// types in switch.
    ///
    /// @return java.util.List<java.lang.String>
    private List<String> switching() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<String> strings = new ArrayList<>();

        double value = 1_000_000.0;

        switch (value) {
            case byte   b -> strings.add(String.format("%f instanceof byte:   %d", value, b));    // Does not execute
            case short  s -> strings.add(String.format("%f instanceof short:  %d", value, s));    // Does not execute
            case char   c -> strings.add(String.format("%f instanceof char:   %c", value, c));    // Does not execute
            case int    i -> strings.add(String.format("%f instanceof int:    %d", value, i));    // Does execute
            case long   l -> strings.add(String.format("%f instanceof long:   %d", value, l));    // Does not execute
            case float  f -> strings.add(String.format("%f instanceof float:  %f", value, f));    // Does not execute
            case double d -> strings.add(String.format("%f instanceof double: %f", value, d));    // Does not execute
        }

        // when is a guard, i.e. a boolean expression

        value = 1_000_000_000_000.0;

        switch (value) {
            case byte   b -> strings.add(String.format("%f instanceof byte:   %d", value, b));    // Does not execute
            case short  s -> strings.add(String.format("%f instanceof short:  %d", value, s));    // Does not execute
            case char   c -> strings.add(String.format("%f instanceof char:   %c", value, c));    // Does not execute
            case int    i when i <= 1_000_000 -> strings.add(String.format("%f instanceof int:    %d", value, i));    // Does not execute
            case long   l -> strings.add(String.format("%f instanceof long:   %d", value, l));    // Does execute
            case float  f -> strings.add(String.format("%f instanceof float:  %f", value, f));    // Does not execute
            case double d -> strings.add(String.format("%f instanceof double: %f", value, d));    // Does not execute
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(strings));
        }

        return strings;
    }
}
