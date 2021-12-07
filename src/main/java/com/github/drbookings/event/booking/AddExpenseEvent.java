package com.github.drbookings.event.booking;

import com.github.drbookings.ser.ExpenseSer;
import com.github.events1000.api.AsynchronousEvent;
import com.github.events1000.api.EventTopic;
import com.github.events1000.impl.SimpleEventTopic;

public class AddExpenseEvent extends ExpenseEvent implements AsynchronousEvent {

    public static final EventTopic EVENT_TOPIC = new SimpleEventTopic("add-expense", BookingEvent.EVENT_TOPIC);

    public AddExpenseEvent(final ExpenseSer data) {
	super(EVENT_TOPIC, data);
    }

}
