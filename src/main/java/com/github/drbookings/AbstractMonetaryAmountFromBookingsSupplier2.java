package com.github.drbookings;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collection;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Range;

public abstract class AbstractMonetaryAmountFromBookingsSupplier2 extends PaymentDateFilter5
	implements AbstractMonetaryAmountSupplier<Booking>, MonetaryAmountFromBookingsSupplier2 {

    private final static Logger logger = LoggerFactory.getLogger(AbstractMonetaryAmountFromBookingsSupplier2.class);

    public AbstractMonetaryAmountFromBookingsSupplier2(final Range<LocalDate> dates) {

	super(dates);
    }

    public AbstractMonetaryAmountFromBookingsSupplier2(final YearMonth month) {

	super(month);
    }

    public AbstractMonetaryAmountFromBookingsSupplier2() {

	super();
    }

    @Override
    public MonetaryAmount apply(final Collection<? extends Booking> elements) {
	return AbstractMonetaryAmountSupplier.super.apply(elements);
    }

    @Override
    public MonetaryAmount apply(final Booking booking) {

	if (bookingInRange(booking))
	    return applyInDateRange(booking);
	else {
	    if (logger.isDebugEnabled()) {
		logger.debug("Skip " + booking);
	    }
	}
	return Money.of(0, "EUR");
    }

    protected abstract MonetaryAmount applyInDateRange(Booking booking);
}
