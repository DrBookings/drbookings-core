package com.github.drbookings;

import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

public interface ByOriginFilter {

    static Predicate<OriginProvider> AIRBNB_FILTER = b -> (b.getBookingOrigin() != null)
	    && "airbnb".equalsIgnoreCase(b.getBookingOrigin().getName());

    static Predicate<OriginProvider> BOOKING_FILTER = b -> (b.getBookingOrigin() != null)
	    && "booking".equalsIgnoreCase(b.getBookingOrigin().getName());

    /**
     * Filters for cheat origins, that is known origins.
     */
    static Predicate<BookingOrigin> CHEAT_FILTER_BOOKING_ORIGIN = o -> StringUtils.isBlank(o.getName());

    /**
     * Filters for no-cheat origins, that is known origins.
     */
    static Predicate<BookingOrigin> NON_CHEAT_FILTER_BOOKING_ORIGIN = CHEAT_FILTER_BOOKING_ORIGIN.negate();

    /**
     * Filters for no-cheat bookings, that is bookings that have a known origin.
     */
    public static Predicate<OriginProvider> CHEAT_FILTER_BOOKING = b -> NON_CHEAT_FILTER_BOOKING_ORIGIN
	    .test(b.getBookingOrigin());

    public static Predicate<OriginProvider> OTHER_FILTER = BOOKING_FILTER.negate().and(AIRBNB_FILTER.negate());

}
