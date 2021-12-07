package com.github.drbookings.event.booking;

import com.github.drbookings.Booking;
import com.github.events1000.api.AsynchronousEvent;
import com.github.events1000.api.EventTopic;
import com.github.events1000.impl.SimpleEventTopic;

public class AddBookingEvent extends BookingEvent implements AsynchronousEvent {

    public static final EventTopic EVENT_TOPIC = new SimpleEventTopic("add-booking", BookingEvent.EVENT_TOPIC);

    public AddBookingEvent(final Booking data) {
	super(EVENT_TOPIC, data);
    }

}
