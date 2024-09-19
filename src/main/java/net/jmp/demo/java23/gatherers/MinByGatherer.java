package net.jmp.demo.java23.gatherers;

/*
 * (#)MinByGatherer.java    0.5.0   09/19/2024
 * (#)MinByGatherer.java    0.4.0   09/19/2024
 * (#)MinByGatherer.java    0.2.0   09/18/2024
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

/// This gatherer designed to find the minimum element in a stream based on a selector function.
///
/// @param   <T>    The type of input elements to the gathering operation
/// @param   <C>    A type that extends Comparable; T must extend Comparable
///
/// @version    0.5.0
/// @since      0.2.0
public class MinByGatherer<T, C extends Comparable<C>> implements Gatherer<T, MinByGatherer.MinByGathererState<T>, T>  {
    /// The selector function.
    private final Function<T, C> selector;

    /// The constructor.
    ///
    /// @param  selector    java.util.function.Function<T, A>
    public MinByGatherer(final Function<T, C> selector) {
        this.selector = Objects.requireNonNull(selector);
    }

    /// A function that produces an instance of the intermediate
    /// state used for this gathering operation.
    ///
    /// @return java.util.function.Supplier<java.util.Map<A, T>>
    @Override
    public Supplier<MinByGathererState<T>> initializer() {
        return MinByGathererState::new;
    }

    ///A function which integrates provided elements,
    /// potentially using the provided intermediate state,
    /// optionally producing output to the provided
    /// downstream type.
    ///
    /// @return java.util.stream.Gatherer.Integrator<net.jmp.demo.java23.gatherers.MinByGathererState<T>, T, T>
    @Override
    public Integrator<MinByGathererState<T>, T, T> integrator() {
        /*
         * Greedy integrators consume all their input,
         * and may only relay that the downstream does
         * not want more elements. The greedy lambda is
         * the state (A), the element type (T), and the
         * result type (R).
         */

        return Integrator.ofGreedy((state, item, _) -> {
            if (state.minElement == null) {
                state.minElement = item;

                return true;    // True if subsequent integration is desired
            }

            final C currentItem = selector.apply(item);
            final C minItem = selector.apply(state.minElement);

            if (currentItem.compareTo(minItem) < 0) {
                state.minElement = item;
            }

            return true;    // True if subsequent integration is desired
        });
    }

    /// A function which accepts two intermediate states and combines them into one.
    /// Used for parallel streams to combine states from different segments.
    ///
    /// @return java.util.function.BinaryOperator<net.jmp.demo.java23.gatherers.MinByGathererState<T>>
    @Override
    public BinaryOperator<MinByGathererState<T>> combiner() {
        /*
         * A BinaryOperator represents an operation upon two
         * operands of the same type, producing a result of
         * the same type as the operands.
         */

        return (first, second) -> {
            // Check for nulls

            if (first.minElement == null && second.minElement == null) {
                return null;
            }

            if (first.minElement == null) {
                return second;
            }

            if (second.minElement == null) {
                return first;
            }

            final C firstItem = selector.apply(first.minElement);
            final C secondItem = selector.apply(second.minElement);

            if (firstItem.compareTo(secondItem) < 0) {
                return first;
            } else {
                return second;
            }
        };
    }

    /// A function which accepts the final intermediate state and a
    /// downstream object, allowing to perform a final action at the
    /// end of input elements. The lambda is the state (A) and the
    /// result type (R).
    ///
    /// @return java.util.function.BiConsumer<net.jmp.demo.java23.gatherers.MinByGathererState<T>, java.util.stream.Gatherer.Downstream<? super T>>
    @Override
    public BiConsumer<MinByGathererState<T>, Downstream<? super T>> finisher () {
        return (state, downstream) -> downstream.push(state.minElement);
    }

    /// A class containing the internal state of the minBy gatherer.
    ///
    /// @param   <T>    The type of element
    public static class MinByGathererState<T> {
        /// The default constructor.
        private MinByGathererState() {
            super();
        }

        /// The minimum element.
        T minElement;
    }
}
