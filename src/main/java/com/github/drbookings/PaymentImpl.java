
package com.github.drbookings;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.money.MonetaryAmount;

import com.github.drbookings.ser.PaymentSer;

/**
 *
 * @author Alexander Kerner
 *
 */
public class PaymentImpl implements Payment {

    public static List<PaymentImpl> build(final List<? extends PaymentSer> paymentsSoFar) {

	final List<PaymentImpl> result = new ArrayList<>();
	for (final PaymentSer ps : Objects.requireNonNull(paymentsSoFar)) {
	    result.add(new PaymentImpl(ps.date, ps.amount));
	}
	return result;
    }

    private final MonetaryAmount amount;
    private final LocalDate date;

    public PaymentImpl(final LocalDate date, final MonetaryAmount amount) {

	this.date = Objects.requireNonNull(date);
	this.amount = Objects.requireNonNull(amount);
    }

    public PaymentImpl(final LocalDate date, final String amount) {

	this(date, Payments2.createMondary(amount));
    }

    public PaymentImpl(final LocalDate date, final double amount) {

	this(date, Double.toString(amount));
    }

    public PaymentImpl(final double amount) {

	this(LocalDate.now(), Double.toString(amount));
    }

    public PaymentImpl(final String amount) {

	this(LocalDate.now(), amount);
    }

    @Override
    public boolean equals(final Object obj) {

	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof PaymentImpl))
	    return false;
	final PaymentImpl other = (PaymentImpl) obj;
	if (amount == null) {
	    if (other.amount != null)
		return false;
	} else if (!amount.equals(other.amount))
	    return false;
	if (date == null) {
	    if (other.date != null)
		return false;
	} else if (!date.equals(other.date))
	    return false;
	return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.github.drbookings.model.Payment#getAmount()
     */
    @Override
    public MonetaryAmount getAmount() {

	return amount;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.github.drbookings.model.Payment#getDate()
     */
    @Override
    public LocalDate getDate() {

	return date;
    }

    @Override
    public int hashCode() {

	final int prime = 31;
	int result = 1;
	result = (prime * result) + (amount == null ? 0 : amount.hashCode());
	result = (prime * result) + (date == null ? 0 : date.hashCode());
	return result;
    }

    @Override
    public String toString() {

	return "Payment{" + "date=" + date + ", amount=" + String.format("%6.2f", amount.getNumber().doubleValue())
		+ '}';
    }
}
