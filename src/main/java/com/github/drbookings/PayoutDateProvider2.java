package com.github.drbookings;

import java.time.LocalDate;

import com.github.drbookings.ser.BookingBeanSer;

public class PayoutDateProvider2 {
    /**
     * For Airbnb, the payment is received usually two days after check-in.
     */
    public static final int DEFAULT_PAYOUT_OFFSET = 1;

    private int payoutOffset = DEFAULT_PAYOUT_OFFSET;

    public LocalDate getPayoutDate(final BookingBeanSer bbb) {

	if (!bbb.paymentsSoFar.isEmpty())
	    throw new IllegalArgumentException("Get payment dates from payments");
	else {
	    // backwards compatible
	    if (bbb.dateOfPayment != null)
		return bbb.dateOfPayment;
	    // if no data is available
	    if (bbb.source.equalsIgnoreCase("booking"))
		return bbb.checkOutDate;
	    else
		return bbb.checkInDate.plusDays(getPayoutOffset());
	}
    }

    public int getPayoutOffset() {
	return payoutOffset;
    }

    public PayoutDateProvider2 setPayoutOffset(final int payoutOffset) {
	this.payoutOffset = payoutOffset;
	return this;
    }

}
