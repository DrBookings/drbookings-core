package com.github.drbookings.event.booking;

import com.github.drbookings.ser.ExpenseSer;
import com.github.events1000.api.AbstractTransportingEvent;
import com.github.events1000.api.EventTopic;
import com.github.events1000.impl.SimpleEventTopic;

public class ExpenseEvent extends AbstractTransportingEvent<ExpenseSer> {

    public static final EventTopic EVENT_TOPIC = new SimpleEventTopic("expense");

    public ExpenseEvent(final EventTopic topic, final ExpenseSer data) {
	super(topic, data);
    }

}
