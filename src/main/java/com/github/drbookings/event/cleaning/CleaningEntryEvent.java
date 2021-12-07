package com.github.drbookings.event.cleaning;

import com.github.drbookings.CleaningBeanSer2;
import com.github.events1000.api.AbstractTransportingEvent;
import com.github.events1000.api.EventTopic;
import com.github.events1000.impl.SimpleEventTopic;

public class CleaningEntryEvent extends AbstractTransportingEvent<CleaningBeanSer2> {

    public static final EventTopic EVENT_TOPIC = new SimpleEventTopic("cleaning-entry");

    public CleaningEntryEvent(final EventTopic topic, final CleaningBeanSer2 data) {
	super(topic, data);
    }

}
