package com.github.drbookings;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Range;

/**
 *
 * @author Alexander Kerner
 * @date 2018-11-14
 *
 */
public class DefaultGrossPaymentsSupplier2 extends AbstractMonetaryAmountFromBookingsSupplier2
	implements GrossIncomeSupplier2 {

    private static final Logger logger = LoggerFactory.getLogger(DefaultGrossPaymentsSupplier2.class);

    public DefaultGrossPaymentsSupplier2() {
	super();

    }

    public DefaultGrossPaymentsSupplier2(final Range<LocalDate> dates) {
	super(dates);

    }

    public DefaultGrossPaymentsSupplier2(final YearMonth month) {
	super(month);

    }

    @Override
    protected MonetaryAmount applyInDateRange(final Booking booking) {
	MonetaryAmount gross = Money.of(0, "EUR");
	if ((getDateRange() != null) && (booking.getPayments() != null) && !booking.getPayments().isEmpty()) {
	    final List<Payment> paymentsInRange = Payments2.getPaymentsInRange(getDateRange(), booking.getPayments());
	    gross = Payments2.getSum(paymentsInRange);
	} else {
	    if (logger.isDebugEnabled()) {
		logger.debug("No payments found");
	    }
	}
	return gross;
    }
}
