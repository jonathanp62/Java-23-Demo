package net.jmp.demo.java23.demos;

/*
 * (#)ScopedValueDemo.java  0.7.0   09/20/2024
 * (#)ScopedValueDemo.java  0.5.0   09/19/2024
 * (#)ScopedValueDemo.java  0.4.0   09/19/2024
 * (#)ScopedValueDemo.java  0.2.0   09/18/2024
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

import net.jmp.demo.java23.util.WrappedObject;

/// The class that demonstrates the scoped value.
///
/// A scoped value is a container object that allows
/// a data value to be safely and efficiently shared
/// by a method with its direct and indirect callees
/// within the same thread, and with child threads,
/// without resorting to method parameters. It is a
/// variable of type ScopedValue. Typically, it is
/// declared as a final static field, and its accessibility
/// is set to private so that it cannot be directly accessed
/// by code in other classes.
///
/// @version    0.7.0
/// @since      0.2.0
public final class ScopedValueDemo implements Demo {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// A scoped value UUID.
    private static final ScopedValue<String> UID = ScopedValue.newInstance();

    /// A scoped value name.
    private static final ScopedValue<String> NAME = ScopedValue.newInstance();

    /// The default constructor.
    public ScopedValueDemo() {
        super();
    }

    /// The demo method.
    @Override
    public void demo() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        this.basic();
        this.multiples();
        this.rebinding();
        this.inheritance();

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Basic usage. Bind 'uuid' to UUID
    /// and pass it to the runnable.
    ///
    /// @return boolean
    private boolean basic() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final WrappedObject<Boolean> result = new WrappedObject<>(false);

        final String uuid = UUID.randomUUID().toString();

        // The 'where' method returns a ScopedValue.Carrier object

        ScopedValue
                .where(UID, uuid)   // Bind 'uuid' to the scoped value
                .run(() -> {
                    if (this.logger.isInfoEnabled()) {
                        this.logger.info("UUID: {}", UID.get());

                        result.set(UID.isBound());

                        if (UID.isBound()) {
                            this.logger.info("UID is bound");
                        }
                    }
                });

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result.get()));
        }

        return result.get();
    }

    /// Multiple bindings.
    ///
    /// @return java.lang.String
    private String multiples() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final WrappedObject<String> result = new WrappedObject<>();

        final String uuid = UUID.randomUUID().toString();
        final String name = "Parker";

        ScopedValue
                .where(UID, uuid)
                .where(NAME, name)
                .run(() -> {
                    if (this.logger.isInfoEnabled()) {
                        this.logger.info("UUID: {}", UID.get());
                        this.logger.info("NAME: {}", NAME.get());
                    }

                    result.set(NAME.get());
                });

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result.get()));
        }

        return result.get();
    }

    /// Rebinding a scoped value.
    ///
    /// @return java.lang.String
    private String rebinding() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final WrappedObject<String> result = new WrappedObject<>();

        ScopedValue.where(NAME, "Jonathan").run(() -> {
                this.bar();
                result.set(NAME.get()); // NAME is still bound
        });

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(result.get()));
        }

        return result.get();
    }

    /// Method bar.
    private void bar() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        if (this.logger.isInfoEnabled()) {
            this.logger.info("NAME: {}", NAME.get());   // Jonathan
        }

        ScopedValue.where(NAME, "Martin").run(this::baz);

        if (this.logger.isInfoEnabled()) {
            this.logger.info("NAME: {}", NAME.get());   // Jonathan
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Method baz.
    private void baz() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        if (this.logger.isInfoEnabled()) {
            this.logger.info("NAME: {}", NAME.get());   // Martin
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Inheritance or sharing across threads.
    ///
    /// @return java.util.List<net.jmp.demo.java23.demos.ScopedValueDemo.ChildTaskInfo>
    private List<ChildTaskInfo> inheritance() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<ChildTaskInfo> childTaskInfos = new ArrayList<>();

        final Callable<String> childTask1 = () -> {
            if (this.logger.isInfoEnabled()) {
                this.logger.info("NAME 1: {}", NAME.get());   // Martin
            }

            return NAME.get() + ":1";
        };

        final Callable<String> childTask2 = () -> {
            if (this.logger.isInfoEnabled()) {
                this.logger.info("NAME 2: {}", NAME.get());   // Martin
            }

            return NAME.get() + ":2";
        };

        final Callable<String> childTask3 = () -> {
            if (this.logger.isInfoEnabled()) {
                this.logger.info("NAME 3: {}", NAME.get());   // Martin
            }

            return NAME.get() + ":3";
        };

        ScopedValue.runWhere(NAME, "Duke", () -> {
            // This scope does not use a shutdown policy which makes it
            // different from those in the structured concurrency demo
            // Variables returned by fork() should be typed as suppliers.

            try (final var scope = new StructuredTaskScope<String>()) {
                final StructuredTaskScope.Subtask<String> subtask1 = scope.fork(childTask1);
                final StructuredTaskScope.Subtask<String> subtask2 = scope.fork(childTask2);
                final StructuredTaskScope.Subtask<String> subtask3 = scope.fork(childTask3);

                scope.join();

                childTaskInfos.add(new ChildTaskInfo(subtask1.get(), 1));
                childTaskInfos.add(new ChildTaskInfo(subtask2.get(), 2));
                childTaskInfos.add(new ChildTaskInfo(subtask3.get(), 3));

                this.logSubtaskStatus(subtask1, 1);
                this.logSubtaskStatus(subtask2, 2);
                this.logSubtaskStatus(subtask3, 3);
            } catch (final InterruptedException ie) {
                this.logger.error(ie.getMessage());
                Thread.currentThread().interrupt();
            }
        });

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(childTaskInfos));
        }

        return childTaskInfos;
    }

    /// Log the status of a subtask.
    ///
    /// @param  subtask java.util.concurrent.StructuredTaskScope.Subtask<java.lang.String>
    /// @param  item    int
    private void logSubtaskStatus(final StructuredTaskScope.Subtask<String> subtask, final int item) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(subtask, item));
        }

        assert subtask != null;

        if (subtask.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
            this.logger.info("Child task {}: {}", item, subtask.get());
        } else if (subtask.state() == StructuredTaskScope.Subtask.State.FAILED) {
            this.logger.error("Child task {} failed: {}", item, subtask.exception().getMessage());
        } else {
            this.logger.error("Child task {} result or exception is not available", item);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// A record to include child task information.
    ///
    /// @param  name    java.lang.String
    /// @param  item    int
    public record ChildTaskInfo(String name, int item) {
    }
}
