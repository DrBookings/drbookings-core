package com.github.drbookings;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Range;

public class PaymentsCollector2 extends SimpleDateRangeHandler {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(PaymentsCollector2.class);

    public PaymentsCollector2(final Range<LocalDate> dates) {

	super(dates);
    }

    public PaymentsCollector2(final YearMonth of) {

	super(of);
    }

    public List<Payment> collect(final Collection<? extends PaymentProvider> elements) {

	final List<Payment> result = new ArrayList<>();
	for (final PaymentProvider bb : elements) {
	    for (final Payment p : bb.getPayments()) {
		if (getDateRange().contains(p.getDate())) {
		    result.add(p);
		}
	    }
	}
	return result;
    }
}
