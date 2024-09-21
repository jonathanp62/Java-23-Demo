package net.jmp.demo.java23.demos;

/*
 * (#)TestStreamGatherersDemo.java  0.7.0   09/20/2024
 *
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

import net.jmp.demo.java23.records.Money;

import static net.jmp.demo.java23.testutil.TestUtils.*;

import static org.junit.Assert.*;

import org.junit.Test;

public final class TestStreamGatherersDemo {
    @Test
    public void testSlidingWindows() throws Exception {
        final var demo = new StreamGatherersDemo();
        final var method = StreamGatherersDemo.class.getDeclaredMethod("slidingWindows");

        method.setAccessible(true);

        final Object o = method.invoke(demo);

        @SuppressWarnings("unchecked")
        final List<List<String>> windows = (List<List<String>>) o;

        assertNotNull(windows);
        assertEquals(4, windows.size());

        final List<String> window0 = listToTypedList(windows.get(0), String.class);
        final List<String> window1 = listToTypedList(windows.get(1), String.class);
        final List<String> window2 = listToTypedList(windows.get(2), String.class);
        final List<String> window3 = listToTypedList(windows.get(3), String.class);

        assertNotNull(window0);
        assertNotNull(window1);
        assertNotNull(window2);
        assertNotNull(window3);

        assertEquals(3, window0.size());
        assertEquals(3, window1.size());
        assertEquals(3, window2.size());
        assertEquals(3, window3.size());

        assertEquals(List.of("India", "Poland", "UK"), window0);
        assertEquals(List.of("Poland", "UK", "Australia"), window1);
        assertEquals(List.of("UK", "Australia", "USA"), window2);
        assertEquals(List.of("Australia", "USA", "Netherlands"), window3);
    }

    @Test
    public void testFixedWindows() throws Exception {
        final var demo = new StreamGatherersDemo();
        final var method = StreamGatherersDemo.class.getDeclaredMethod("fixedWindows");

        method.setAccessible(true);

        final Object o = method.invoke(demo);

        @SuppressWarnings("unchecked")
        final List<List<String>> windows = (List<List<String>>) o;

        assertNotNull(windows);
        assertEquals(5, windows.size());

        final List<String> window0 = listToTypedList(windows.get(0), String.class);
        final List<String> window1 = listToTypedList(windows.get(1), String.class);
        final List<String> window2 = listToTypedList(windows.get(2), String.class);
        final List<String> window3 = listToTypedList(windows.get(3), String.class);
        final List<String> window4 = listToTypedList(windows.get(4), String.class);

        assertNotNull(window0);
        assertNotNull(window1);
        assertNotNull(window2);
        assertNotNull(window3);
        assertNotNull(window4);

        assertEquals(2, window0.size());
        assertEquals(2, window1.size());
        assertEquals(2, window2.size());
        assertEquals(2, window3.size());
        assertEquals(1, window4.size());

        assertEquals(List.of("Mozart", "Bach"), window0);
        assertEquals(List.of("Beethoven", "Mahler"), window1);
        assertEquals(List.of("Bruckner", "Liszt"), window2);
        assertEquals(List.of("Chopin", "Telemann"), window3);
        assertEquals(List.of("Vivaldi"), window4);
    }

    @Test
    public void testScan() throws Exception {
        final var demo = new StreamGatherersDemo();
        final var method = StreamGatherersDemo.class.getDeclaredMethod("scan");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final List<?> list = castToType(List.class, o);
        final List<String> results = listToTypedList(list, String.class);

        assertNotNull(results);
        assertEquals(9, results.size());

        assertEquals("1", results.get(0));
        assertEquals("12", results.get(1));
        assertEquals("123", results.get(2));
        assertEquals("1234", results.get(3));
        assertEquals("12345", results.get(4));
        assertEquals("123456", results.get(5));
        assertEquals("1234567", results.get(6));
        assertEquals("12345678", results.get(7));
        assertEquals("123456789", results.get(8));
    }

    @Test
    public void testFold() throws Exception {
        final var demo = new StreamGatherersDemo();
        final var method = StreamGatherersDemo.class.getDeclaredMethod("fold");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final String result = castToType(String.class, o);

        assertNotNull(result);

        assertEquals("123456789", result);
    }

    @Test
    public void testMapConcurrent() throws Exception {
        final var demo = new StreamGatherersDemo();
        final var method = StreamGatherersDemo.class.getDeclaredMethod("mapConcurrent");

        method.setAccessible(true);

        final Object o = method.invoke(demo);
        final List<?> list = castToType(List.class, o);
        final List<String> results = listToTypedList(list, String.class);

        assertNotNull(results);
        assertEquals(9, results.size());

        assertEquals("1", results.get(0));
        assertEquals("2", results.get(1));
        assertEquals("3", results.get(2));
        assertEquals("4", results.get(3));
        assertEquals("5", results.get(4));
        assertEquals("6", results.get(5));
        assertEquals("7", results.get(6));
        assertEquals("8", results.get(7));
        assertEquals("9", results.get(8));
    }

    @Test
    public void testCustomDistinctBy() throws Exception {
        final var demo = new StreamGatherersDemo();
        final var method = StreamGatherersDemo.class.getDeclaredMethod("customDistinctBy", List.class);

        method.setAccessible(true);

        final Object o = method.invoke(demo, this.getMoney());
        final List<?> list = castToType(List.class, o);
        final List<Money> results = listToTypedList(list, Money.class);

        assertNotNull(results);
        assertEquals(2, results.size());

        assertEquals(new Money(BigDecimal.valueOf(12), Currency.getInstance("PLN")), results.get(0));
        assertEquals(new Money(BigDecimal.valueOf(11), Currency.getInstance("EUR")), results.get(1));
    }

    @Test
    public void testCustomReduceByGatherer() throws Exception {
        final var demo = new StreamGatherersDemo();
        final var method = StreamGatherersDemo.class.getDeclaredMethod("customReduceByGatherer", List.class);

        method.setAccessible(true);

        final Object o = method.invoke(demo, this.getMoney());
        final List<?> list = castToType(List.class, o);
        final List<Money> results = listToTypedList(list, Money.class);

        assertNotNull(results);
        assertEquals(2, results.size());

        final var expectedEur = new Money(BigDecimal.valueOf(11), Currency.getInstance("EUR"));
        final var expectedPln = new Money(BigDecimal.valueOf(27), Currency.getInstance("PLN"));

        assertTrue(results.contains(expectedEur));
        assertTrue(results.contains(expectedPln));
    }

    @Test
    public void testCustomMaxByGatherer() throws Exception {
        final var demo = new StreamGatherersDemo();
        final var method = StreamGatherersDemo.class.getDeclaredMethod("customMaxByGatherer", List.class);

        method.setAccessible(true);

        final Object o = method.invoke(demo, this.getMoney());
        final Money result = castToType(Money.class, o);

        assertNotNull(result);
        assertEquals(new Money(BigDecimal.valueOf(15), Currency.getInstance("PLN")), result);
    }

    @Test
    public void testCustomMinByGatherer() throws Exception {
        final var demo = new StreamGatherersDemo();
        final var method = StreamGatherersDemo.class.getDeclaredMethod("customMinByGatherer", List.class);

        method.setAccessible(true);

        final Object o = method.invoke(demo, this.getMoney());
        final Money result = castToType(Money.class, o);

        assertNotNull(result);
        assertEquals(new Money(BigDecimal.valueOf(11), Currency.getInstance("EUR")), result);
    }

    @Test
    public void testCustomMapNotNullGatherer() throws Exception {

    }

    @Test
    public void testCustomFindFirstGatherer() throws Exception {

    }

    @Test
    public void testCustomFindLastGatherer() throws Exception {

    }

    @Test
    public void testCustomGatherAndThen() throws Exception {

    }

    private List<Money> getMoney() {
        return List.of(
                new Money(BigDecimal.valueOf(12), Currency.getInstance("PLN")),
                new Money(BigDecimal.valueOf(11), Currency.getInstance("EUR")),
                new Money(BigDecimal.valueOf(15), Currency.getInstance("PLN"))
        );
    }
}
