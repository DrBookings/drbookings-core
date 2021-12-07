package com.github;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.drbookings.Booking;
import com.github.drbookings.BookingOriginFactory;
import com.github.drbookings.GuestFactory;
import com.github.drbookings.PaymentImpl;
import com.github.drbookings.PayoutDateProvider2;
import com.github.drbookings.RoomFactory;
import com.github.drbookings.Scripting;
import com.github.drbookings.ser.BookingBeanSer;
import com.github.drbookings.ser.BookingBeanSer2;
import com.github.drbookings.ser.PaymentSer;

public class BookingFactory {

    private static Logger logger = LoggerFactory.getLogger(BookingFactory.class);

    private final RoomFactory roomFactory;

    private final GuestFactory guestFactory;

    private final BookingOriginFactory bookingOriginFactory;

    public Booking build(final BookingBeanSer ser) {
	try {
	    Booking b = new Booking(ser.bookingId, roomFactory.getOrCreateElement(ser.roomName), ser.checkInDate,
		    ser.checkOutDate, guestFactory.getOrCreateElement(ser.guestName),
		    bookingOriginFactory.getOrCreateElement(ser.source));
	    b = readPayments(b, ser);
	    b.setServiceFeesPercentExpression(Float.toString(ser.serviceFeePercent));
	    b.setCleaningFeesExpression(ser.cleaningFees);
	    b.setWelcomeMailSend(ser.welcomeMailSend);
	    b.setCleaningFeesExpression(ser.cleaningFees);
	    b.setGrossPaymentExpression(ser.grossEarningsExpression);
	    return b;
	} catch (final Exception e) {
	    if (logger.isErrorEnabled()) {
		logger.error(e.getLocalizedMessage(), e);
	    }
	}
	return null;
    }

    public Booking build(final BookingBeanSer2 ser) {
	try {
	    Booking b = new Booking(ser.bookingId, roomFactory.getOrCreateElement(ser.roomName), ser.checkInDate,
		    ser.checkOutDate, guestFactory.getOrCreateElement(ser.guestName),
		    bookingOriginFactory.getOrCreateElement(ser.origin));
	    b = readPayments(b, ser);
	    b.setServiceFeesPercentExpression(ser.serviceFeePercentExpression);
	    b.setCleaningFeesExpression(ser.cleaningFeesExpression);
	    b.setWelcomeMailSend(ser.welcomeMailSend);
	    b.setCleaningFeesExpression(ser.cleaningFeesExpression);
	    b.setGrossPaymentExpression(ser.grossPaymentsExpression);
	    b.setNumberOfGuests(ser.numberOfGuests);
	    return b;
	} catch (final Exception e) {
	    if (logger.isErrorEnabled()) {
		logger.error(e.getLocalizedMessage(), e);
	    }
	}
	return null;
    }

    private Booking readPayments(final Booking b, final BookingBeanSer newBooking) {
	if (newBooking.paymentsSoFar.isEmpty()) {
	    if (newBooking.paymentDone) {
		b.addPayment(new PaymentImpl(new PayoutDateProvider2().getPayoutDate(newBooking),
			Scripting.evaluateExpression(newBooking.grossEarningsExpression)));
	    }
	} else {
	    for (final PaymentSer ps : newBooking.paymentsSoFar) {
		b.addPayment(new PaymentImpl(ps.date, ps.amount));
	    }
	}
	return b;
    }

    private Booking readPayments(final Booking b, final BookingBeanSer2 newBooking) {
	if (newBooking.payments.isEmpty()) {
	    // if (logger.isWarnEnabled()) {
	    // logger.warn("No Payments for booking");
	    // }
	} else {
	    for (final PaymentSer ps : newBooking.payments) {
		b.addPayment(new PaymentImpl(ps.date, ps.amount));
	    }
	}
	return b;
    }

    public BookingFactory(final RoomFactory roomFactory, final GuestFactory guestFactory,
	    final BookingOriginFactory bookingOriginFactory) {
	super();
	this.roomFactory = roomFactory;
	this.guestFactory = guestFactory;
	this.bookingOriginFactory = bookingOriginFactory;
    }

    public BookingFactory(final RoomFactory roomFactory) {
	this(roomFactory, new GuestFactory(), BookingOriginFactory.getInstance());
    }

}
