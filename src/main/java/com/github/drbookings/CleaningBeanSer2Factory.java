package com.github.drbookings;

import com.github.drbookings.ser.CleaningBeanSer;

public class CleaningBeanSer2Factory {

    private final RoomFactory roomFactory;

    public CleaningBeanSer2Factory() {
	this(RoomFactory.getInstance());
    }

    public CleaningBeanSer2Factory(final RoomFactory roomFactory) {
	super();
	this.roomFactory = roomFactory;
    }

    public CleaningBeanSer2 build(final CleaningBeanSer c) {
	final CleaningBeanSer2 result = new CleaningBeanSer2();
	result.bookingBeforeId = c.bookingId;
	result.calendarIds = c.calendarIds;
	result.date = c.date;
	result.cleaningCosts = c.cleaningCosts;
	result.id = c.id;
	result.name = c.name;
	result.room = c.room;
	result.tax = !c.black;
	return result;
    }

}
