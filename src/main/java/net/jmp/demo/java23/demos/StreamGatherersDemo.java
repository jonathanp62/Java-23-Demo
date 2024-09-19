package net.jmp.demo.java23.demos;

/*
 * (#)StreamGatherersDemo.java  0.5.0   09/19/2024
 * (#)StreamGatherersDemo.java  0.4.0   09/19/2024
 * (#)StreamGatherersDemo.java  0.2.0   09/18/2024
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

import net.jmp.demo.java23.gatherers.*;

import net.jmp.demo.java23.records.Money;

import net.jmp.demo.java23.util.GatherersFactory;

import static net.jmp.demo.java23.util.LoggerUtils.*;

/// The class that demonstrates built-in stream gatherers
/// as well as composing custom ones.
///
/// References
/// - [Stream Gatherers In Practice Part 1](https://softwaremill.com/stream-gatherers-in-practice-part-1/)
/// - [Stream Gatherers In Practice Part 2](https://softwaremill.com/stream-gatherers-in-practice-part-2/)
/// - [Java Stream Gather Example](https://github.com/lukaszrola/java-stream-gather-example)
///
/// @version    0.5.0
/// @since      0.2.0
public final class StreamGatherersDemo implements Demo {
    /// The logger.
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /// The default constructor.
    public StreamGatherersDemo() {
        super();
    }

    /// The demo method.
    @Override
    public void demo() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        this.slidingWindows();
        this.fixedWindows();
        this.scan();
        this.fold();
        this.mapConcurrent();
        this.custom();

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Sliding windows.
    private void slidingWindows() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<String> countries = List.of("India", "Poland", "UK", "Australia", "USA", "Netherlands");

        final List<List<String>> windows = countries
                .stream()
                .gather(Gatherers.windowSliding(3))
                .toList();

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Sliding windows: {}", windows);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Fixed windows.
    private void fixedWindows() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<String> composers = List.of("Mozart", "Bach", "Beethoven", "Mahler", "Bruckner", "Liszt", "Chopin", "Telemann", "Vivaldi");

        final List<List<String>> windows = composers
                .stream()
                .gather(Gatherers.windowFixed(2))
                .toList();

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Fixed windows: {}", windows);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Scan.
    private void scan() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<String> numbers = Stream.of(
                1, 2, 3, 4, 5, 6, 7, 8, 9
        ).gather(
                Gatherers.scan(() -> "", (string, number) -> string + number)
        ).toList();

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Scan: {}", numbers);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Fold.
    private void fold() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final String numbers = Stream.of(
                1, 2, 3, 4, 5, 6, 7, 8, 9
        ).gather(
                Gatherers.fold(() -> "", (string, number) -> string + number)
        ).findFirst().get();

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Fold: {}", numbers);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Map concurrent.
    private void mapConcurrent() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        final Function<Integer, String> toString = String::valueOf;

        final List<String> strings = numbers
                .stream()
                .gather(Gatherers.mapConcurrent(2, toString))
                .toList();

        if (this.logger.isInfoEnabled()) {
            this.logger.info("MapConcurrent: {}", strings);
        }

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Custom gatherers.
    private void custom() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<Money> money = List.of(
                new Money(BigDecimal.valueOf(12), Currency.getInstance("PLN")),
                new Money(BigDecimal.valueOf(11), Currency.getInstance("EUR")),
                new Money(BigDecimal.valueOf(15), Currency.getInstance("PLN"))
        );

        this.customDistinctBy(money);
        this.customReduceByGatherer(money);
        this.customMaxByGatherer(money);
        this.customMinByGatherer(money);
        this.customMapNotNullGatherer();
        this.customFindFirstGatherer(money);
        this.customFindLastGatherer(money);
        this.customGatherAndThen();

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// A custom distinct-by gatherer.
    ///
    /// @param  money   java.util.List<net.jmp.demo.java23.records.Money>
    private void customDistinctBy(final List<Money> money) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(money));
        }

        assert money != null;
        assert !money.isEmpty();

        money.stream()
                .gather(GatherersFactory.distinctBy(Money::currency))
                .forEach(e -> this.logger.info(e.toString()));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// A custom reduce-by gatherer.
    ///
    /// @param  money   java.util.List<net.jmp.demo.java23.records.Money>
    private void customReduceByGatherer(final List<Money> money) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(money));
        }

        assert money != null;
        assert !money.isEmpty();

        money.stream()
                .gather(GatherersFactory.reduceBy(Money::currency, Money::add))
                .forEach(e -> this.logger.info(e.toString()));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// A custom max-by gatherer.
    ///
    /// @param  money   java.util.List<net.jmp.demo.java23.records.Money>
    private void customMaxByGatherer(final List<Money> money) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(money));
        }

        assert money != null;
        assert !money.isEmpty();

        money.stream()
                .parallel()
                .gather(GatherersFactory.maxBy(Money::amount))
                .forEach(e -> this.logger.info(e.toString()));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// A custom min-by gatherer.
    ///
    /// @param  money   java.util.List<net.jmp.demo.java23.records.Money>
    private void customMinByGatherer(final List<Money> money) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(money));
        }

        assert money != null;
        assert !money.isEmpty();

        money.stream()
                .parallel()
                .gather(GatherersFactory.minBy(Money::amount))
                .forEach(e -> this.logger.info(e.toString()));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// A custom map not-null gatherer.
    private void customMapNotNullGatherer() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<Money> money = this.getMoneyWithNulls();

        money.stream()
                .gather(GatherersFactory.mapNotNull(m -> m.multiply(BigDecimal.TWO)))
                .forEach(e -> this.logger.info(e.toString()));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// A custom find-first gatherer.
    ///
    /// @param  money   java.util.List<net.jmp.demo.java23.records.Money>
    private void customFindFirstGatherer(final List<Money> money) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(money));
        }

        assert money != null;
        assert !money.isEmpty();

        money.stream()
                .gather(GatherersFactory.findFirst(m -> m.currency().equals(Currency.getInstance("PLN"))))
                .forEach(e -> this.logger.info(e.toString()));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// A custom find-last gatherer.
    ///
    /// @param  money   java.util.List<net.jmp.demo.java23.records.Money>
    private void customFindLastGatherer(final List<Money> money) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entryWith(money));
        }

        assert money != null;
        assert !money.isEmpty();

        money.stream()
                .gather(GatherersFactory.findLast(m -> m.currency().equals(Currency.getInstance("PLN"))))
                .forEach(e -> this.logger.info(e.toString()));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Try two gatherers using andThen.
    private void customGatherAndThen() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        final List<Money> money = this.getMoneyWithNulls();

        // Combine two gatherers using andThen()

        final MapNotNullGatherer<Money, Money> mapNotNullGatherer = new MapNotNullGatherer<>(m -> m.multiply(BigDecimal.TWO));
        final ReduceByGatherer<Money, Currency> reducerGatherer = new ReduceByGatherer<>(Money::currency, Money::add);

        final Gatherer<Money, ?, ? super Money> gatherers = mapNotNullGatherer.andThen(reducerGatherer);

        money.stream()
                .gather(gatherers)
                .forEach(e -> this.logger.info(e.toString()));

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exit());
        }
    }

    /// Return a list of money with nulls interspersed.
    ///
    /// @return java.util.List<net.jmp.demo.java23.records.Money>
    private List<Money> getMoneyWithNulls() {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace(entry());
        }

        // Cannot add nulls in List.of()

        final List<Money> money = Arrays.asList(
                null,
                new Money(BigDecimal.valueOf(12), Currency.getInstance("PLN")),
                null,
                new Money(BigDecimal.valueOf(11), Currency.getInstance("EUR")),
                null,
                new Money(BigDecimal.valueOf(15), Currency.getInstance("PLN")),
                null
        );

        if (this.logger.isTraceEnabled()) {
            this.logger.trace(exitWith(money));
        }

        return money;
    }
}
