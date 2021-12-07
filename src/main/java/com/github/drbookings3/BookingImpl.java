package com.github.drbookings3;

import java.time.LocalDate;

import com.github.drbookings.DatesSupplier;
import com.google.common.collect.Range;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookingImpl implements DatesSupplier {

    private LocalDate arrival, departure, created;

    private ChannelImpl channel;

    private GuestImpl guest;

    private final StringProperty paymentExpression;

    private RoomImpl room;

    BookingImpl() {
	this(null, null);
    }

    public BookingImpl(final LocalDate arrival, final LocalDate departure) {
	super();
	this.arrival = arrival;
	this.departure = departure;
	this.paymentExpression = new SimpleStringProperty();
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof BookingImpl))
	    return false;
	final BookingImpl other = (BookingImpl) obj;
	if (arrival == null) {
	    if (other.arrival != null)
		return false;
	} else if (!arrival.equals(other.arrival))
	    return false;
	if (channel == null) {
	    if (other.channel != null)
		return false;
	} else if (!channel.equals(other.channel))
	    return false;
	if (created == null) {
	    if (other.created != null)
		return false;
	} else if (!created.equals(other.created))
	    return false;
	if (departure == null) {
	    if (other.departure != null)
		return false;
	} else if (!departure.equals(other.departure))
	    return false;
	if (guest == null) {
	    if (other.guest != null)
		return false;
	} else if (!guest.equals(other.guest))
	    return false;
	if (room == null) {
	    if (other.room != null)
		return false;
	} else if (!room.equals(other.room))
	    return false;
	return true;
    }

    public LocalDate getArrival() {
	return arrival;
    }

    public ChannelImpl getChannel() {
	return channel;
    }

    @Override
    public LocalDate getCheckInDate() {
	return getArrival();
    }

    @Override
    public LocalDate getCheckOutDate() {
	return getDeparture();
    }

    public LocalDate getCreated() {
	return created;
    }

    public Range<LocalDate> getDateDange() {
	return Range.closed(getArrival(), getDeparture());
    }

    public LocalDate getDeparture() {
	return departure;
    }

    public GuestImpl getGuest() {
	return guest;
    }

    public String getGuestName() {
	return getGuest().getName();
    }

    public String getPaymentExpression() {
	return this.paymentExpressionProperty().get();
    }

    public RoomImpl getRoom() {
	return room;
    }

    public String getRoomName() {
	return getRoom().getName();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = (prime * result) + ((arrival == null) ? 0 : arrival.hashCode());
	result = (prime * result) + ((channel == null) ? 0 : channel.hashCode());
	result = (prime * result) + ((created == null) ? 0 : created.hashCode());
	result = (prime * result) + ((departure == null) ? 0 : departure.hashCode());
	result = (prime * result) + ((guest == null) ? 0 : guest.hashCode());
	result = (prime * result) + ((room == null) ? 0 : room.hashCode());
	return result;
    }

    public StringProperty paymentExpressionProperty() {
	return this.paymentExpression;
    }

    public void setArrival(final LocalDate arrival) {
	this.arrival = arrival;
    }

    public void setChannel(final ChannelImpl channel) {
	this.channel = channel;
    }

    public void setCreated(final LocalDate created) {
	this.created = created;
    }

    public void setDeparture(final LocalDate departure) {
	this.departure = departure;
    }

    public void setGuest(final GuestImpl guest) {
	this.guest = guest;
    }

    public void setPaymentExpression(final String paymentExpression) {
	this.paymentExpressionProperty().set(paymentExpression);
    }

    public void setRoom(final RoomImpl room) {
	this.room = room;
    }

    @Override
    public String toString() {
	return "BookingImpl [arrival=" + arrival + ", departure=" + departure + ", guest=" + guest + ", room=" + room
		+ ", channel=" + channel + "]";
    }

}
