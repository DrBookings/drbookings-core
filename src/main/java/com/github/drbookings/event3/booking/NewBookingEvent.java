package com.github.drbookings.event3.booking;

import com.github.drbookings3.BookingImpl;
import com.github.events1000.api.AbstractTransportingEvent;
import com.github.events1000.api.AsynchronousEvent;
import com.github.events1000.api.EventTopic;

public class NewBookingEvent extends AbstractTransportingEvent<BookingImpl> implements AsynchronousEvent {

    public static final EventTopic TOPIC = EventTopic.get("new-booking");

    public NewBookingEvent(final BookingImpl data) {
	super(TOPIC, data);
    }

}
