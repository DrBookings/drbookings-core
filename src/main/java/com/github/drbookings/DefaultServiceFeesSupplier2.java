package com.github.drbookings;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.money.MonetaryAmount;

import com.google.common.collect.Range;

public class DefaultServiceFeesSupplier2 extends AbstractMonetaryAmountFromBookingsSupplier2
	implements ServiceFeesSupplier2 {

    private final DefaultTurnoverCalculator2 turnOverSupplier;

    public DefaultServiceFeesSupplier2(final Range<LocalDate> dates) {

	super(dates);
	turnOverSupplier = new DefaultTurnoverCalculator2(dates);
    }

    public DefaultServiceFeesSupplier2(final YearMonth month) {

	super(month);
	turnOverSupplier = new DefaultTurnoverCalculator2(month);
    }

    public DefaultServiceFeesSupplier2() {

	super();
	turnOverSupplier = new DefaultTurnoverCalculator2();
    }

    @Override
    public MonetaryAmount applyInDateRange(final Booking booking) {

	final float servicesFeesPercent = booking.getServiceFeesPercent();
	final MonetaryAmount grossMinusCleaning = turnOverSupplier.apply(booking);
	final BigDecimal hh = BigDecimal.valueOf(servicesFeesPercent).divide(BigDecimal.valueOf(100));
	final MonetaryAmount result = grossMinusCleaning.multiply(hh);

	return result;
    }
}
