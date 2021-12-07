package com.github.drbookings.ser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.drbookings.Booking;

public class BookingBeanSer2Factory {

    public List<BookingBeanSer2> build(final Collection<? extends Booking> bs) {
	final List<BookingBeanSer2> result = new ArrayList<>();
	bs.forEach(bb -> result.add(build(bb)));
	return result;
    }

    public BookingBeanSer2 build(final Booking b) {
	final BookingBeanSer2 result = new BookingBeanSer2();
	result.bookingId = b.getId();
	result.welcomeMailSend = b.isWelcomeMailSend();
	result.checkInDate = b.getCheckInDate();
	result.checkOutDate = b.getCheckOutDate();
	result.roomName = b.getRoom().getName();
	result.guestName = b.getGuest().getName();
	result.payments = PaymentSerFactory.build(b.getPayments());
	result.origin = b.getBookingOrigin().toString();
	result.serviceFeePercentExpression = Float.toString(b.getServiceFeesPercent());
	result.cleaningFeesExpression = b.getCleaningFeesExpression();
	result.grossPaymentsExpression = b.getGrossPaymentExpression();
	result.numberOfGuests = b.getNumberOfGuests();
	return result;
    }

}
