package com.github.drbookings3;

public class ChannelImpl extends Named {

    public ChannelImpl(final String name) {
	super(name);
    }

    @Override
    public String toString() {
	return getName();
    }

}
