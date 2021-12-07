package com.github.drbookings;

import java.math.RoundingMode;
import java.util.Collection;

import javax.money.MonetaryAmount;

/**
 *
 * @author Alexander Kerner
 * @date 2018-11-14
 *
 * @param <T>
 */
public interface MonetaryAmountSupplier<T> {

    MonetaryAmount apply(T element);

    MonetaryAmount apply(Collection<? extends T> elements);

    default RoundingMode getRoundingMode() {
	return Numbers.DEFAULT_ROUNDING_MODE;
    }

    default int getScale() {
	return Numbers.DEFAULT_SCALE;
    }

}
