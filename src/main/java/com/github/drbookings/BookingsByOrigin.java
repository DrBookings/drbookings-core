/*
 * DrBookings
 * Copyright (C) 2016 - 2018 Alexander Kerner
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package com.github.drbookings;

import java.util.Collection;

public class BookingsByOrigin<T extends OriginProvider> extends AbstractByOriginFilter<T> implements ByOriginFilter {

    public BookingsByOrigin() {
	super();

    }

    public BookingsByOrigin(final Collection<? extends T> elements) {
	super(elements);

    }

    @Deprecated
    public Collection<T> getAirbnbBookings() {

	return getAirbnbElements();
    }

    @Deprecated
    public Collection<T> getAllBookings() {

	return getAllBookings(false);
    }

    @Deprecated
    public Collection<T> getOtherBookings() {

	return getOtherBookings();
    }

    @Deprecated
    public Collection<T> getAllBookings(final boolean cheat) {

	return getAllElements(cheat);
    }

    @Deprecated
    public Collection<T> getBookingBookings() {

	return getBookingElements();
    }

}
