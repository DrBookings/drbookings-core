package com.github.drbookings.ser;

import java.time.LocalDate;

import javax.money.MonetaryAmount;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.drbookings.Payment;
import com.github.drbookings.Payments2;
import com.github.drbookings.io.LocalDateAdapter;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class ExpenseSer implements Payment {
    @XmlAttribute
    public String name;
    @XmlAttribute
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate date;
    @XmlAttribute
    public String amount;
    @XmlAttribute
    public String origin;

    @Override
    public MonetaryAmount getAmount() {
	return Payments2.createMondary(amount);
    }

    @Override
    public LocalDate getDate() {
	return date;
    }
}
