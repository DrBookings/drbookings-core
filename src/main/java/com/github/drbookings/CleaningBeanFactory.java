package com.github.drbookings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.drbookings.ui.CleaningBean;

public class CleaningBeanFactory {

    private static final Logger logger = LoggerFactory.getLogger(CleaningBeanFactory.class);

    private final CleaningFactory cleaningFactory;
    private final RoomFactory roomFactory;
    private Collection<Booking> bookings;

    public CleaningBeanFactory() {

	this(CleaningFactory.getInstance(), RoomFactory.getInstance());
    }

    public CleaningBeanFactory(final CleaningFactory cleaningFactory, final RoomFactory roomFactory) {

	super();
	this.cleaningFactory = cleaningFactory;
	this.roomFactory = roomFactory;

    }

    public List<CleaningBean> build(final Collection<? extends CleaningBeanSer2> cleanings) {
	final List<CleaningBean> result = new ArrayList<>();
	cleanings.forEach(c -> result.add(build(c)));
	return result;
    }

    public CleaningBean build(final CleaningBeanSer2 cleaning) {

	final CleaningBean result = new CleaningBean(cleaning.date, cleaningFactory.getOrCreateElement(cleaning.name),
		roomFactory.getOrCreateElement(cleaning.room));
	result.setCleaningCostsExpression(cleaning.cleaningCosts);
	result.setTaxRelevant(cleaning.tax);
	setCleaningBefore(result, cleaning);
	setCleaningAfter(result, cleaning);
	result.setNote(cleaning.note);
	result.setPayments(PaymentFactory.build(cleaning.payments));
	return result;
    }

    private void setCleaningBefore(final CleaningBean result, final CleaningBeanSer2 cleaning) {
	if ((bookings != null) && !bookings.isEmpty()) {
	    final Optional<Booking> b = bookings.stream().filter(bb -> bb.getId().equals(cleaning.bookingBeforeId))
		    .findFirst();
	    if (b.isPresent()) {
		result.setBookingBefore(b.get());
		// System.err.println("dfdf");
	    } else {
		if (logger.isWarnEnabled()) {
		    logger.warn("Failed to find boooking before for " + cleaning);
		}
	    }
	}
    }

    private void setCleaningAfter(final CleaningBean result, final CleaningBeanSer2 cleaning) {
	if ((bookings != null) && !bookings.isEmpty()) {
	    final Optional<Booking> b = bookings.stream().filter(bb -> bb.getId().equals(cleaning.bookingBeforeId))
		    .findFirst();
	    if (b.isPresent()) {
		result.setBookingAfter(b.get());
	    } else {
		if (logger.isWarnEnabled()) {
		    logger.warn("Failed to find boooking after for " + cleaning);
		}
	    }
	}
    }

    public Collection<Booking> getBookings() {
	return bookings;
    }

    public CleaningBeanFactory setBookings(final Collection<Booking> bookings) {
	this.bookings = bookings;
	return this;
    }

}
