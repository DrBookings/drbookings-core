package com.github.drbookings;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Range;

/**
 *
 * @author Alexander Kerner
 * @date 2018-11-14
 *
 */
public class PaymentDateFilter5 implements Predicate<PaymentsProviderBean> {

    private static final Logger logger = LoggerFactory.getLogger(PaymentDateFilter5.class);

    private final Range<LocalDate> dateRange;

    public PaymentDateFilter5() {

	dateRange = null;
    }

    public PaymentDateFilter5(final Range<LocalDate> dates) {

	dateRange = dates;
    }

    public PaymentDateFilter5(final YearMonth month) {

	dateRange = LocalDates.toDateRange(month);
    }

    public boolean bookingInRange(final PaymentsProviderBean b) {

	return bookingInRange(getDateRange(), b);
    }

    public static boolean bookingInRange(final Range<LocalDate> dateRange, final PaymentsProviderBean b) {

	final List<Payment> payments = b.getPayments();
	final List<LocalDate> paymentDates = payments.stream().map(p -> p.getDate()).collect(Collectors.toList());
	if (paymentDates.isEmpty()) {
	    if (logger.isDebugEnabled()) {
		logger.debug("No payments found for " + b);
	    }
	    return false;
	}
	// System.err.println(b + "\n" + paymentDates);
	if ((dateRange == null) && logger.isDebugEnabled()) {
	    logger.debug("No date range given");
	} else if ((dateRange.isEmpty()) && logger.isDebugEnabled()) {
	    logger.debug("Empty date range given");
	}
	return (dateRange == null) || dateRange.isConnected(LocalDates.toDateRange(paymentDates));
    }

    public Range<LocalDate> getDateRange() {

	return dateRange;
    }

    @Override
    public boolean test(final PaymentsProviderBean t) {

	return bookingInRange(t);
    }
}
