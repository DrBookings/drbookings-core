package com.github.drbookings;

import java.util.ArrayList;
import java.util.List;

import com.github.drbookings.ser.PaymentSer;

public class PaymentFactory {

    public static List<Payment> build(final List<? extends PaymentSer> paymentsSoFar) {

	return new ArrayList<>(PaymentImpl.build(paymentsSoFar));
    }

}
