package com.github.drbookings;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.google.common.collect.Range;

public interface DatesSupplier {

    LocalDate getCheckInDate();

    LocalDate getCheckOutDate();

    default Range<LocalDate> getDateRange() {
	return Range.closed(getCheckInDate(), getCheckOutDate());
    }

    default Number getNumberOfDays() {

	final long daysElapsed = ChronoUnit.DAYS.between(getCheckInDate(), getCheckOutDate().plusDays(1));
	return daysElapsed;
    }

    default Number getNumberOfNights() {

	final long daysElapsed = getNumberOfDays().longValue();
	return daysElapsed - 1;
    }

}
