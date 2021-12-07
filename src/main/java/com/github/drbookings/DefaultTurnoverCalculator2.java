package com.github.drbookings;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collection;

import javax.money.MonetaryAmount;

import com.google.common.collect.Range;

public class DefaultTurnoverCalculator2 extends AbstractMonetaryAmountFromBookingsSupplier2 {

    public DefaultTurnoverCalculator2(final Range<LocalDate> dates) {

	super(dates);
    }

    public DefaultTurnoverCalculator2(final YearMonth month) {

	super(month);
    }

    public DefaultTurnoverCalculator2() {

	super();
    }

    @Override
    public MonetaryAmount apply(final Collection<? extends Booking> bookings) {

	return super.apply(bookings);
    }

    @Override
    protected MonetaryAmount applyInDateRange(final Booking booking) {

	final MonetaryAmount gross = new DefaultGrossPaymentsSupplier2(getDateRange()).apply(booking);
	final MonetaryAmount cleaning = booking.getCleaningFees();
	final MonetaryAmount grossMinusCleaning = gross.subtract(cleaning);
	return grossMinusCleaning;
    }
}
