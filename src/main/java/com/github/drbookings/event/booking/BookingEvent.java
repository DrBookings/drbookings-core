package com.github.drbookings.event.booking;

import com.github.drbookings.Booking;
import com.github.events1000.api.AbstractTransportingEvent;
import com.github.events1000.api.EventTopic;
import com.github.events1000.impl.SimpleEventTopic;

public class BookingEvent extends AbstractTransportingEvent<Booking> {

    public static final EventTopic EVENT_TOPIC = new SimpleEventTopic("booking");

    public BookingEvent(final EventTopic topic, final Booking data) {
	super(topic, data);
    }

}
