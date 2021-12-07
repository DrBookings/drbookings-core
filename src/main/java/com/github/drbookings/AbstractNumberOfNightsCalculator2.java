package com.github.drbookings;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collection;

import com.google.common.collect.Range;

public class AbstractNumberOfNightsCalculator2 extends BookingsPaymentDateRangeHandler2 {

    public static final boolean DEFAULT_IGNORE_PAYMENT_DATE = true;

    private boolean ignorePaymentDate = DEFAULT_IGNORE_PAYMENT_DATE;

    public AbstractNumberOfNightsCalculator2(final Collection<? extends Booking> bookings) {
	super(bookings);

    }

    public AbstractNumberOfNightsCalculator2(final Range<LocalDate> dates,
	    final Collection<? extends Booking> bookings) {
	super(dates, bookings);

    }

    public AbstractNumberOfNightsCalculator2(final YearMonth month, final Collection<? extends Booking> bookings) {
	super(month, bookings);

    }

    public boolean isIgnorePaymentDate() {
	return ignorePaymentDate;
    }

    public AbstractNumberOfNightsCalculator2 setIgnorePaymentDate(final boolean ignorePaymentDate) {
	this.ignorePaymentDate = ignorePaymentDate;
	return this;
    }

}
