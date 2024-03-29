/*
 * DrBookings
 *
 * Copyright (C) 2016 - 2018 Alexander Kerner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 */

package com.github.drbookings;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.drbookings.io.LocalDateAdapter;

@Deprecated
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PaymentSer {

    public static List<PaymentSer> transform(final List<Payment> payments) {
	final List<PaymentSer> result = new ArrayList<>(payments.size());

	for (final Payment p : payments) {
	    result.add(transform(p));
	}

	return result;
    }

    public static PaymentSer transform(final Payment payment) {
	final PaymentSer result = new PaymentSer();
	result.amount = payment.getAmount().getNumber().floatValue();
	result.date = payment.getDate();
	return result;
    }

    @XmlAttribute
    public float amount;

    @XmlAttribute
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate date;
}
