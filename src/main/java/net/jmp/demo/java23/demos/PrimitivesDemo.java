package net.jmp.demo.java23.demos;

/*
 * (#)BeforeSuperDemo.java  0.6.0   09/20/2024
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

import static net.jmp.demo.java23.util.LoggerUtils.*;

/// A class the demonstrates primitive types
/// in patterns, instanceOf, and switch.
///
/// @version    0.6.0
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
            this.oldMethods();
            this.patterns();
            this.switching();
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Demonstrate the old methods to
    /// do pattern matching and switching.
    private void oldMethods() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final Object obj1 = (Object) new String("Hello!");
        final Object obj2 = (Object) Integer.valueOf(6);

        // instanceOf

        if (obj1 instanceof String s && s.length() >= 5) {
            this.logger.info("obj1 is a string: {}", s);
        } else if (obj1 instanceof Integer i) {
            this.logger.info("obj1 is an integer: {}", i);
        } else {
            this.logger.info("obj1 is unknown");;
        }

        if (obj2 instanceof String s && s.length() >= 5) {
            this.logger.info("obj2 is a string: {}", s);
        } else if (obj2 instanceof Integer i) {
            this.logger.info("obj2 is an integer: {}", i);
        } else {
            this.logger.info("obj2 is unknown");;
        }

        // switch (when is a guard, i.e. a boolean expression)

        switch (obj1) {
            case String s when s.length() >= 5 -> this.logger.info(s.toUpperCase());
            case Integer i                     -> this.logger.info(String.valueOf(i * i));
            case null, default                 -> this.logger.info(obj1.toString());
        }

        switch (obj2) {
            case String s when s.length() >= 5 -> this.logger.info(s.toUpperCase());
            case Integer i                     -> this.logger.info(String.valueOf(i * i));
            case null, default                 -> this.logger.info(obj2.toString());
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Demonstrate primitive
    /// types in patterns.
    private void patterns() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        int value = 127;

        // byte ranges from -128 to 127

        if (value instanceof byte b) {
            this.logger.info("value is a byte: {}", b); // Does execute
        }

        value = 128;

        if (value instanceof byte b) {
            this.logger.info("value is a byte: {}", b); // Does not execute
        }

        value = 65;

        if (value instanceof byte b)   this.logger.info("{} instanceof byte:  {} ", value, b);  // Does execute
        if (value instanceof short s)  this.logger.info("{} instanceof short:  {}", value, s);  // Does execute
        if (value instanceof int i)    this.logger.info("{} instanceof int:    {}", value, i);  // Does execute
        if (value instanceof long l)   this.logger.info("{} instanceof long:   {}", value, l);  // Does execute
        if (value instanceof float f)  this.logger.info("{} instanceof float:  {}", value, f);  // Does execute
        if (value instanceof double d) this.logger.info("{} instanceof double: {}", value, d);  // Does execute
        if (value instanceof char c)   this.logger.info("{} instanceof char:   {}", value, c);  // Does execute

        value = 100_000;

        if (value instanceof byte b)   this.logger.info("{} instanceof byte:  {} ", value, b);  // Does not execute
        if (value instanceof short s)  this.logger.info("{} instanceof short:  {}", value, s);  // Does not execute
        if (value instanceof int i)    this.logger.info("{} instanceof int:    {}", value, i);  // Does execute
        if (value instanceof long l)   this.logger.info("{} instanceof long:   {}", value, l);  // Does execute
        if (value instanceof float f)  this.logger.info("{} instanceof float:  {}", value, f);  // Does execute
        if (value instanceof double d) this.logger.info("{} instanceof double: {}", value, d);  // Does execute
        if (value instanceof char c)   this.logger.info("{} instanceof char:   {}", value, c);  // Does not execute

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Demonstrate primitive
    /// types in switch.
    private void switching() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        double value = 1_000_000.0;

        switch (value) {
            case byte   b -> this.logger.info("{} instanceof byte:  {} ", value, b);    // Does not execute
            case short  s -> this.logger.info("{} instanceof short:  {}", value, s);    // Does not execute
            case char   c -> this.logger.info("{} instanceof char:   {}", value, c);    // Does not execute
            case int    i -> this.logger.info("{} instanceof int:    {}", value, i);    // Does execute
            case long   l -> this.logger.info("{} instanceof long:   {}", value, l);    // Does not execute
            case float  f -> this.logger.info("{} instanceof float:  {}", value, f);    // Does not execute
            case double d -> this.logger.info("{} instanceof double: {}", value, d);    // Does not execute
        }

        // when is a guard, i.e. a boolean expression

        value = 1_000_000_000_000.0;

        switch (value) {
            case byte   b -> this.logger.info("{} instanceof byte:  {} ", value, b);    // Does not execute
            case short  s -> this.logger.info("{} instanceof short:  {}", value, s);    // Does not execute
            case char   c -> this.logger.info("{} instanceof char:   {}", value, c);    // Does not execute
            case int    i when i <= 1_000_000 -> this.logger.info("{} instanceof int:    {}", value, i);    // Does not execute
            case long   l -> this.logger.info("{} instanceof long:   {}", value, l);    // Does execute
            case float  f -> this.logger.info("{} instanceof float:  {}", value, f);    // Does not execute
            case double d -> this.logger.info("{} instanceof double: {}", value, d);    // Does not execute
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }
}
