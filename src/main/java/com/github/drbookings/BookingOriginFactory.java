package com.github.drbookings;

public class BookingOriginFactory extends NamedFactory<BookingOrigin> {

    private static class InstanceHolder {
	private static final BookingOriginFactory instance = new BookingOriginFactory();
    }

    public static BookingOriginFactory getInstance() {
	return InstanceHolder.instance;
    }

    private BookingOriginFactory() {

    }

    @Override
    protected BookingOrigin buildNewElement(final String name) {
	return new BookingOrigin(name);
    }

}
