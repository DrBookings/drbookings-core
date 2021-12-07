package com.github.drbookings.exception;

import java.util.NoSuchElementException;

public class NoSuchBookingException extends NoSuchElementException {

    /**
     *
     */
    private static final long serialVersionUID = -1254891510634505241L;

    public NoSuchBookingException() {
	super();

    }

    public NoSuchBookingException(final String s) {
	super(s);

    }

}
