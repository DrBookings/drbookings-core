package com.github.drbookings;

public class GuestFactory extends NamedFactory<Guest> {

    @Override
    protected Guest buildNewElement(final String name) {
	return new Guest(name);
    }

}
