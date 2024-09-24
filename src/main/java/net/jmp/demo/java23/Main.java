package net.jmp.demo.java23;

/*
 * (#)Main.java 0.8.0   09/24/2024
 * (#)Main.java 0.6.0   09/20/2024
 * (#)Main.java 0.5.0   09/19/2024
 * (#)Main.java 0.4.0   09/19/2024
 * (#)Main.java 0.3.0   09/18/2024
 * (#)Main.java 0.2.0   09/18/2024
 * (#)Main.java 0.1.0   09/18/2024
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

import net.jmp.demo.java23.demos.*;

import static net.jmp.util.logging.LoggerUtils.*;

/// The main class. This class is instantiated
/// and run from the bootstrap class when the
/// application starts.
///
/// @version    0.8.0
/// @since      0.1.0
final class Main implements Runnable {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The command line arguments.
    private final String[] arguments;

    /// A constructor that takes the
    /// command line arguments from
    /// the bootstrap class.
    ///
    /// @param  args    java.lang.String[]
    Main(final String[] args) {
        super();

        this.arguments = Objects.requireNonNull(args);
    }

    /// The run method.
    @Override
    public void run() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        if (this.logger.isInfoEnabled() || this.logger.isWarnEnabled() || this.logger.isErrorEnabled()) {
            System.out.format("%s %s%n", Name.NAME_STRING, Version.VERSION_STRING);
        } else {
            this.logger.debug("{} {}", Name.NAME_STRING, Version.VERSION_STRING);
        }

        this.runDemos();

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Method that runs the demo classes.
    private void runDemos() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final Stream<Demo> demos = Stream.of(
                new ScopedValueDemo(),
                new StreamGatherersDemo(),
                new BeforeSuperDemo(),
                new UnnamedVariablesDemo(),
                new StructuredConcurrencyDemo(),
                new PrimitivesDemo()
        );

        demos.forEach(Demo::demo);

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }
}
