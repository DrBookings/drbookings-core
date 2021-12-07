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

import java.time.LocalDate;
import java.util.Objects;

public class BookingEntry2 implements RoomDateProvider {

    private final Booking parent;
    private final LocalDate date;

    public BookingEntry2(final LocalDate date, final Booking booking) {

	this.date = Objects.requireNonNull(date);
	parent = Objects.requireNonNull(booking);
    }

    @Override
    public LocalDate getDate() {

	return date;
    }

    @Override
    public Room getRoom() {

	return parent.getRoom();
    }

    public Booking getParent() {

	return parent;
    }

    public Guest getGuest() {

	return getParent().getGuest();
    }

    public boolean isCheckIn() {

	return getDate().equals(getParent().getCheckInDate());
    }

    public boolean isCheckOut() {

	return getDate().equals(getParent().getCheckOutDate());
    }

    public boolean isStayOver() {

	return !isCheckIn() && !isCheckOut();
    }

    public BookingOrigin getBookingOrigin() {

	return getParent().getBookingOrigin();
    }

    @Override
    public String toString() {
	return getDate() + ",room:" + getRoom() + ",guest:" + getGuest();
    }
}
