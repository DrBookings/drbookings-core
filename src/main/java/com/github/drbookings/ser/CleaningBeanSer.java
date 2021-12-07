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
package com.github.drbookings.ser;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.drbookings.io.LocalDateAdapter;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class CleaningBeanSer {

	@XmlAttribute
	public boolean black;
	@XmlAttribute
	public String bookingId;
	@XmlAttribute
	public List<String> calendarIds;
	@XmlAttribute
	public String cleaningCosts;
	@XmlAttribute
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	public LocalDate date;
	@XmlAttribute
	public String id;
	@XmlAttribute
	public String name;
	@XmlAttribute
	public String room;

	@Override
	public String toString() {

		return "CleaningBeanSer id=" + id + ", date=" + date + ", name=" + name + ", booking=" + bookingId + "]";
	}

	@Override
	public int hashCode() {

		return Objects.hash(date, name, room);
	}

	@Override
	public boolean equals(final Object obj) {

		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof CleaningBeanSer))
			return false;
		final CleaningBeanSer other = (CleaningBeanSer)obj;
		return Objects.equals(date, other.date) && Objects.equals(name, other.name) && Objects.equals(room, other.room);
	}
}
