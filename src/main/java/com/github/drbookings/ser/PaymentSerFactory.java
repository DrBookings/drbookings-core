package com.github.drbookings.ser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.drbookings.Payment;

public class PaymentSerFactory {

    public static List<PaymentSer> build(final Collection<? extends Payment> payments) {

	final List<PaymentSer> result = new ArrayList<>(payments.size());
	payments.forEach(pp -> result.add(build(pp)));
	return result;
    }

    public static PaymentSer build(final Payment payment) {

	final PaymentSer result = new PaymentSer();
	result.amount = payment.getAmount().getNumber().toString();
	result.date = payment.getDate();
	return result;
    }

}
