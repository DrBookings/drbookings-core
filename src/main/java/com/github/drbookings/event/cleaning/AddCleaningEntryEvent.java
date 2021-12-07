package com.github.drbookings.event.cleaning;

import com.github.drbookings.CleaningBeanSer2;
import com.github.events1000.api.AsynchronousEvent;
import com.github.events1000.api.EventTopic;
import com.github.events1000.impl.SimpleEventTopic;

public class AddCleaningEntryEvent extends CleaningEntryEvent implements AsynchronousEvent {

    public static final EventTopic EVENT_TOPIC = new SimpleEventTopic("add-cleaning-entry",
	    CleaningEntryEvent.EVENT_TOPIC);

    public AddCleaningEntryEvent(final CleaningBeanSer2 data) {
	super(EVENT_TOPIC, data);
    }

}
