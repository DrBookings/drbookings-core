
package com.github.drbookings;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.drbookings.io.LocalDateAdapter;
import com.github.drbookings.ser.PaymentSer;

/**
 * Cleaning data bean.
 *
 * @author Alexander Kerner
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CleaningBeanSer2 {

    @XmlAttribute
    public String name;
    @XmlAttribute
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate date;
    @XmlAttribute
    public String room;
    @XmlAttribute
    public String cleaningCosts;
    @XmlAttribute
    public boolean tax;
    @XmlAttribute
    public String bookingBeforeId;
    @XmlAttribute
    public String bookingAfterId;
    @XmlAttribute
    public List<String> calendarIds = new ArrayList<>(0);
    @XmlAttribute
    public String id;
    @XmlAttribute
    public String note;
    @XmlElementWrapper
    public List<PaymentSer> payments = new ArrayList<>(0);

    @Override
    public String toString() {
	return date + " " + name + ",room:" + room + ",costs:" + cleaningCosts + ",payments:" + payments;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = (prime * result) + ((date == null) ? 0 : date.hashCode());
	result = (prime * result) + ((name == null) ? 0 : name.hashCode());
	result = (prime * result) + ((room == null) ? 0 : room.hashCode());
	return result;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof CleaningBeanSer2))
	    return false;
	final CleaningBeanSer2 other = (CleaningBeanSer2) obj;
	if (date == null) {
	    if (other.date != null)
		return false;
	} else if (!date.equals(other.date))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (room == null) {
	    if (other.room != null)
		return false;
	} else if (!room.equals(other.room))
	    return false;
	return true;
    }

}
