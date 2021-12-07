package com.github.drbookings.ser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataStoreCoreSer {

	private final List<BookingBeanSer> bookings = new ArrayList<>();
	private final List<CleaningBeanSer> cleanings = new ArrayList<>();
	private final List<ExpenseSer> expenses = new ArrayList<>();

	@Override
	public boolean equals(final Object obj) {

		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof DataStoreCoreSer))
			return false;
		final DataStoreCoreSer other = (DataStoreCoreSer)obj;
		if(bookings == null) {
			if(other.bookings != null)
				return false;
		} else if(!bookings.equals(other.bookings))
			return false;
		if(cleanings == null) {
			if(other.cleanings != null)
				return false;
		} else if(!cleanings.equals(other.cleanings))
			return false;
		if(expenses == null) {
			if(other.expenses != null)
				return false;
		} else if(!expenses.equals(other.expenses))
			return false;
		return true;
	}

	@XmlElementWrapper(name = "bookings")
	@XmlElement(name = "booking")
	public List<BookingBeanSer> getBookingsSer() {

		return bookings;
	}

	@XmlElementWrapper(name = "cleanings")
	@XmlElement(name = "cleaning")
	public List<CleaningBeanSer> getCleaningsSer() {

		return cleanings;
	}

	@XmlElementWrapper(name = "expenses")
	@XmlElement(name = "expense")
	public List<ExpenseSer> getExpenses() {

		return expenses;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = (prime * result) + (bookings == null ? 0 : bookings.hashCode());
		result = (prime * result) + (cleanings == null ? 0 : cleanings.hashCode());
		result = (prime * result) + (expenses == null ? 0 : expenses.hashCode());
		return result;
	}

	public DataStoreCoreSer setBookingSer(final Collection<? extends BookingBeanSer> bookings) {

		this.bookings.clear();
		this.bookings.addAll(bookings);
		return this;
	}

	public DataStoreCoreSer setCleaningSer(final Collection<? extends CleaningBeanSer> cleanings) {

		this.cleanings.clear();
		this.cleanings.addAll(cleanings);
		return this;
	}

	public DataStoreCoreSer setExpenses(final Collection<? extends ExpenseSer> expenses) {

		this.expenses.clear();
		this.expenses.addAll(expenses);
		return this;
	}
}
