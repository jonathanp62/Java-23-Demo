package net.jmp.demo.java23.util;

/*
 * (#)DemoGatherers.java    0.4.0   09/19/2024
 * (#)DemoGatherers.java    0.2.0   09/18/2024
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

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import net.jmp.demo.java23.gatherers.*;

/// A factory class for gatherers.
///
/// @version    0.4.0
/// @since      0.2.0
public final class GatherersFactory {
    /// The default constructor.
    private GatherersFactory() {
        super();
    }

    /// A distinct-by gatherer.
    ///
    /// @param  <T>         The type of input elements to the gathering operation
    /// @param  <A>         The potentially mutable state type of the gathering operation
    /// @param  selector    java.util.function.Function<T, A>
    /// @return             net.jmp.demo.java23.gatherers.DistinctByGatherer<T, A>
    public static <T, A> DistinctByGatherer<T, A> distinctBy(final Function<T, A> selector) {
        return new DistinctByGatherer<>(selector);
    }

    /// A reduce-by gatherer.
    ///
    /// @param   <T>         The type of input elements to the gathering operation
    /// @param   <A>         The potentially mutable state type of the gathering operation
    /// @param   selector    java.util.function.Function>T, A>
    /// @param   reducer     java.util.function.BiFunction<T, T, T>
    /// @return              net.jmp.demo.java23.gatherers.ReduceByGatherer<T, A>
    public static <T, A> ReduceByGatherer<T, A> reduceBy(final Function<T, A> selector,
                                                         final BiFunction<T, T, T> reducer) {
        return new ReduceByGatherer<>(selector, reducer);
    }

    /// A max-by gatherer.
    ///
    /// @param  <T>         The type of input elements to the gathering operation
    /// @param  <C>         A type that extends Comparable; T must extend Comparable
    /// @param  selector    java.util.function.Function<T, C>
    /// @return             net.jmp.demo.java23.gatherers.MaxByGatherer<T, C extends Comparable<C>>
    public static <T, C extends Comparable<C>> MaxByGatherer<T, C> maxBy(final Function<T, C> selector) {
        return new MaxByGatherer<>(selector);
    }

    /// A min-by gatherer.
    ///
    /// @param  <T>         The type of input elements to the gathering operation
    /// @param  <C>         A type that extends Comparable; T must extend Comparable
    /// @param  selector    java.util.function.Function<T, C>
    /// @return             net.jmp.demo.java23.gatherers.MinByGatherer<T, C extends Comparable<C>>
    public static <T, C extends Comparable<C>> MinByGatherer<T, C> minBy(final Function<T, C> selector) {
        return new MinByGatherer<>(selector);
    }

    /// A map not null gatherer.
    ///
    /// @param  <T>     The type of input elements to the gathering operation
    /// @param  <R>     The type of output elements from the gatherer operation
    /// @param  mapper  java.util.function.Function<T, R>
    /// @return         net.jmp.demo.java23.gatherers.MapNotNullGatherer<T, R>
    public static <T, R> MapNotNullGatherer<T, R> mapNotNull(final Function<T, R> mapper) {
        return new MapNotNullGatherer<>(mapper);
    }

    /// A find first gatherer.
    ///
    /// @param  <T>         The type of input elements to the gathering operation
    /// @param  predicate   java.util.function.Predicate<T>
    /// @return             net.jmp.demo.java23.gatherers.FindFirstGatherer<T>
    public static <T> FindFirstGatherer<T> findFirst(final Predicate<T> predicate) {
        return new FindFirstGatherer<>(predicate);
    }

    /// A find last gatherer.
    ///
    /// @param  <T>         The type of input elements to the gathering operation
    /// @param  predicate   java.util.function.Predicate<T>
    /// @return             net.jmp.demo.java23.gatherers.FindLastGatherer<T>
    public static <T> FindLastGatherer<T> findLast(final Predicate<T> predicate) {
        return new FindLastGatherer<>(predicate);
    }
}
