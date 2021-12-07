package com.github.drbookings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.drbookings.ser.PaymentSerFactory;
import com.github.drbookings.ui.CleaningBean;

public class CleaningBeanSer2Factory2 {

    public CleaningBeanSer2Factory2() {
	super();

    }

    public List<CleaningBeanSer2> build(final Collection<? extends CleaningBean> c) {
	final List<CleaningBeanSer2> result = new ArrayList<>();
	c.forEach(cc -> result.add(build(cc)));
	return result;

    }

    public CleaningBeanSer2 build(final CleaningBean c) {
	// TODO: use interface instead
	final CleaningBeanSer2 result = new CleaningBeanSer2();
	result.cleaningCosts = c.getCleaningCostsExpression();
	result.date = c.getDate();
	result.name = c.getName();
	result.room = c.getRoom().getName();
	result.tax = c.isTaxRelevant();
	result.bookingAfterId = c.getBookingAfter() != null ? c.getBookingAfter().getId() : null;
	result.bookingBeforeId = c.getBookingBefore() != null ? c.getBookingBefore().getId() : null;
	result.payments = PaymentSerFactory.build(c.getPayments());
	return result;
    }

}
