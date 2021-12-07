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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.drbookings.exception.OverbookingException;

/**
 * A container to hold a {@link RoomBean room's} {@link BookingEntry booking
 * entries}. A room has zero, one or two booking entries.
 *
 * @author Alexander Kerner
 *
 */
public class BookingEntryPair2 implements RoomDateProvider {

    public interface ChangeListener {

	void update();
    }

    public static BookingEntryPair2 newCheckInStayPair(final BookingEntry2 checkIn, final BookingEntry2 stay) {

	return new BookingEntryPair2(checkIn, stay, null);
    }

    public static BookingEntryPair2 newStayCheckOutPair(final BookingEntry2 stay, final BookingEntry2 checkOut) {

	return new BookingEntryPair2(null, stay, checkOut);
    }

    private BookingEntry2 checkIn;
    private BookingEntry2 checkOut;
    private LocalDate date;
    private BookingEntry2 stay;

    public BookingEntryPair2(final BookingEntry2 entry) {

	Objects.requireNonNull(entry, "BookingEntry must not be null");
	date = Objects.requireNonNull(entry.getDate());
	if (entry.isCheckIn()) {
	    checkIn = entry;
	} else if (entry.isCheckOut()) {
	    checkOut = entry;
	} else if (entry.isStayOver()) {
	    stay = entry;
	} else
	    throw new IllegalArgumentException();
    }

    BookingEntryPair2(final BookingEntry2 checkIn, final BookingEntry2 stay, final BookingEntry2 checkOut) {

	date = getDate(checkIn, stay, checkOut);

	validateDates(checkIn, stay, checkOut);
	validateRooms(checkIn, stay, checkOut);
	this.checkIn = checkIn;
	this.stay = stay;
	this.checkOut = checkOut;
    }

    public BookingEntryPair2(final LocalDate date) {

	this.date = Objects.requireNonNull(date);

    }

    public void addBooking(final BookingEntry2 be) throws OverbookingException {

	checkConstrains(be);
	checkOverbooking(be);
	if (be.isCheckIn()) {
	    checkIn = be;
	} else if (be.isCheckOut()) {
	    checkOut = be;
	} else if (be.isStayOver()) {
	    stay = be;
	} else
	    throw new RuntimeException();
	date = getDate(be);

    }

    public Set<String> bookingOriginNamesView() {

	return bookingOriginsView().stream().map(e -> e.getName()).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<BookingOrigin> bookingOriginsView() {

	return toList().stream().map(e -> e.getParent().getBookingOrigin())
		.collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * {@link Room} and date must match for all {@link BookingEntry booking
     * entries}.
     *
     * @throws IllegalArgumentException
     *             if room or date does not match
     */
    private void checkConstrains(final BookingEntry2 be) throws IllegalArgumentException {

	if (getDate() != null)
	    if (!getDate().equals(be.getDate()))
		throw new IllegalArgumentException("Dates do not match");
	if (getRoom() != null)
	    if (!getRoom().equals(be.getRoom()))
		throw new IllegalArgumentException("Rooms do not match");
    }

    private void checkOverbooking(final BookingEntry2 be) throws OverbookingException {

	if (be.isCheckIn() && hasCheckIn())
	    throw new OverbookingException("This entry pair has already a check-in");
	if (be.isCheckOut() && hasCheckOut())
	    throw new OverbookingException("This entry pair has already a check-out");
	if (!be.isStayOver() && hasStay())
	    throw new OverbookingException("This entry pair has already a stay");
    }

    @Override
    public boolean equals(final Object obj) {

	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (obj instanceof BookingEntryPair2)
	    return Objects.equals(toList(), ((BookingEntryPair2) obj).toList());
	return false;
    }

    public BookingEntry2 getCheckIn() {

	return checkIn;
    }

    public BookingEntry2 getCheckOut() {

	return checkOut;
    }

    @Override
    public LocalDate getDate() {

	return date;
    }

    LocalDate getDate(final BookingEntry2... be) {

	LocalDate date = this.date;
	for (final BookingEntry2 e : be)
	    if (date == null) {
		date = e.getDate();
	    } else if (date.equals(e.getDate())) {
		// good
	    } else
		throw new IllegalArgumentException("Dates do not match");
	// if array is empty, date is null
	return null;
    }

    public BookingEntry2 getFirst() {

	if (checkIn != null)
	    return checkIn;
	if (stay != null)
	    return stay;
	if (checkOut != null)
	    return checkOut;
	throw new RuntimeException();
    }

    public BookingEntry2 getLast() {

	if (checkOut != null)
	    return checkOut;
	if (stay != null)
	    return stay;
	if (checkIn != null)
	    return checkIn;
	// empty booking entry pair
	return null;
    }

    @Override
    public Room getRoom() {

	if (hasCheckIn())
	    return checkIn.getRoom();
	if (hasCheckOut())
	    return checkOut.getRoom();
	if (hasStay())
	    return stay.getRoom();
	return null;
    }

    public BookingEntry2 getStay() {

	return stay;
    }

    public Set<String> guestNameView() {

	return guestView().stream().map(e -> e.getName()).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<Guest> guestView() {

	return toList().stream().map(e -> e.getParent().getGuest())
		.collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public boolean hasCheckIn() {

	return checkIn != null;
    }

    public boolean hasCheckOut() {

	return checkOut != null;
    }

    public boolean hasGuest(final Guest guest) {

	return toList().stream().map(b -> b.getParent().getGuest()).filter(g -> g.equals(guest)).findFirst()
		.isPresent();
    }

    @Override
    public int hashCode() {

	return Objects.hash(toList());
    }

    public boolean hasRoom(final Room room) {

	return toList().stream().map(b -> b.getParent().getRoom()).filter(g -> g.equals(room)).findFirst().isPresent();
    }

    public boolean hasStay() {

	return stay != null;
    }

    public short size() {

	short result = 0;
	if (checkIn != null) {
	    result++;
	}
	if (stay != null) {
	    result++;
	}
	if (checkOut != null) {
	    result++;
	}
	return result;
    }

    public List<BookingEntry2> toList() {

	final List<BookingEntry2> result = new ArrayList<>(2);
	if (checkIn != null) {
	    result.add(checkIn);
	}
	if (stay != null) {
	    result.add(stay);
	}
	if (checkOut != null) {
	    result.add(checkOut);
	}
	return result;
    }

    public Stream<BookingEntry2> toStream() {

	return toList().stream();
    }

    private void validateDates(final BookingEntry2 checkIn, final BookingEntry2 stay, final BookingEntry2 checkOut) {

	if ((checkIn != null) && (stay != null) && !checkIn.getDate().equals(stay.getDate()))
	    throw new IllegalArgumentException("Dates do not match");
	if ((checkOut != null) && (stay != null) && !checkOut.getDate().equals(stay.getDate()))
	    throw new IllegalArgumentException("Dates do not match");
	if ((checkOut != null) && (checkIn != null) && !checkOut.getDate().equals(checkIn.getDate()))
	    throw new IllegalArgumentException("Dates do not match");
    }

    private void validateRooms(final BookingEntry2 checkIn, final BookingEntry2 stay, final BookingEntry2 checkOut) {

	if ((checkIn != null) && (stay != null) && !checkIn.getRoom().equals(stay.getRoom()))
	    throw new IllegalArgumentException("Rooms do not match");
	if ((checkOut != null) && (stay != null) && !checkOut.getRoom().equals(stay.getRoom()))
	    throw new IllegalArgumentException("Rooms do not match");
	if ((checkOut != null) && (checkIn != null) && !checkOut.getRoom().equals(checkIn.getRoom()))
	    throw new IllegalArgumentException("Rooms do not match");
    }
}
