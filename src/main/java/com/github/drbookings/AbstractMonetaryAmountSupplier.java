package com.github.drbookings;

import java.util.Collection;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;

public interface AbstractMonetaryAmountSupplier<T> {

    default MonetaryAmount apply(final Collection<? extends T> elements) {
	MonetaryAmount result = Money.of(0, "EUR");
	for (final T b : elements) {
	    result = result.add(apply(b));
	}
	return result;
    }

    MonetaryAmount apply(final T element);

}
