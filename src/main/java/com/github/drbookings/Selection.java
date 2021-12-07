package com.github.drbookings;

import java.time.LocalDate;
import java.util.Objects;

public class Selection {

    private final LocalDate date;

    private final String roomName;

    public Selection(final String roomName, final LocalDate date) {
	super();
	this.roomName = Objects.requireNonNull(roomName);
	this.date = Objects.requireNonNull(date);
    }

    public LocalDate getDate() {
	return date;
    }

    public String getRoomName() {
	return roomName;
    }

    @Override
    public String toString() {
	return "Selection [roomName=" + roomName + ", date=" + date + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = (prime * result) + ((date == null) ? 0 : date.hashCode());
	result = (prime * result) + ((roomName == null) ? 0 : roomName.hashCode());
	return result;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Selection))
	    return false;
	final Selection other = (Selection) obj;
	if (date == null) {
	    if (other.date != null)
		return false;
	} else if (!date.equals(other.date))
	    return false;
	if (roomName == null) {
	    if (other.roomName != null)
		return false;
	} else if (!roomName.equals(other.roomName))
	    return false;
	return true;
    }

}
