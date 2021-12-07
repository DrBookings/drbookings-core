package com.github.drbookings;

import java.time.LocalDate;

public class AbstractReader {

    private LocalDate startDate;
    private LocalDate endDate;

    public LocalDate getStartDate() {
	return startDate;
    }

    public LocalDate getEndDate() {
	return endDate;
    }

    public AbstractReader setStartDate(final LocalDate startDate) {
	this.startDate = startDate;
	return this;
    }

    public AbstractReader setStopDate(final LocalDate endDate) {
	this.endDate = endDate;
	return this;
    }
}
