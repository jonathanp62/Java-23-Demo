package net.jmp.demo.java23.records;

/*
 * (#)Money.java    0.4.0   09/19/2024
 * (#)Money.java    0.2.0   09/18/2024
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

import java.math.BigDecimal;

import java.util.Currency;

/// A money record. It is used
/// by the stream gatherers demo.
///
/// @param   amount     java.math.BigDecimal
/// @param   currency   java.util.Currency
/// @version            0.4.0
/// @since              0.2.0
public record Money(BigDecimal amount, Currency currency) {
    /// Method that adds some money.
    ///
    /// @param  money   net.jmp.demo.java23.StreamGatherersDemo.Money
    /// @return         net.jmp.demo.java23.StreamGatherersDemo.Money
    public Money add(final Money money) {
        return new Money(money.amount.add(this.amount), currency);
    }

    /// Method that multiplies.
    ///
    /// @param  multiplier  java.math.BigDecimal
    /// @return             net.jmp.demo.java23.StreamGatherersDemo.Money
    public Money multiply(final BigDecimal multiplier) {
        return new Money(amount.multiply(multiplier), currency);
    }
}
