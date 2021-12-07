package com.github.drbookings.ser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.github.drbookings.CleaningBeanSer2;

@XmlRootElement
public class DataStoreCoreSer2 {

    private final List<BookingBeanSer2> bookings = new ArrayList<>();
    private final List<CleaningBeanSer2> cleanings = new ArrayList<>();
    private final List<ExpenseSer> expenses = new ArrayList<>();

    @XmlElementWrapper(name = "bookings")
    @XmlElement(name = "booking")
    public List<BookingBeanSer2> getBookingsSer() {

	return bookings;
    }

    @XmlElementWrapper(name = "cleanings")
    @XmlElement(name = "cleaning")
    public List<CleaningBeanSer2> getCleaningsSer() {

	return cleanings;
    }

    @XmlElementWrapper(name = "expenses")
    @XmlElement(name = "expense")
    public List<ExpenseSer> getExpenses() {

	return expenses;
    }

    public DataStoreCoreSer2 setBookingSer(final Collection<? extends BookingBeanSer2> bookings) {

	this.bookings.clear();
	this.bookings.addAll(bookings);
	return this;
    }

    public DataStoreCoreSer2 setCleaningSer(final Collection<? extends CleaningBeanSer2> cleanings) {

	this.cleanings.clear();
	this.cleanings.addAll(cleanings);
	return this;
    }

    public DataStoreCoreSer2 setExpensesSer(final Collection<? extends ExpenseSer> expenses) {

	this.expenses.clear();
	this.expenses.addAll(expenses);
	return this;
    }
}
