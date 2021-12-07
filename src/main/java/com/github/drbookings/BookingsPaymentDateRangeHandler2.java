package com.github.drbookings;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collection;

import com.google.common.collect.Range;

public class BookingsPaymentDateRangeHandler2 extends PaymentDateFilter5 {

    private final Collection<? extends Booking> bookings;

    public BookingsPaymentDateRangeHandler2(final Range<LocalDate> dates,
	    final Collection<? extends Booking> bookings) {
	super(dates);
	this.bookings = bookings;
    }

    public BookingsPaymentDateRangeHandler2(final Collection<? extends Booking> bookings) {
	super();
	this.bookings = bookings;
    }

    public BookingsPaymentDateRangeHandler2(final YearMonth month, final Collection<? extends Booking> bookings) {
	super(month);
	this.bookings = bookings;
    }

    public Collection<? extends Booking> getBookings() {
	return bookings;
    }

}
