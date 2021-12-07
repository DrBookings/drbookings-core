
package com.github.drbookings.ser;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.drbookings.io.LocalDateAdapter;

/**
 * Booking data bean.
 * 
 * @author Alexander Kerner
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
public class BookingBeanSer2 {

    @XmlAttribute
    public String guestName;
    @XmlAttribute
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate checkInDate;
    @XmlAttribute
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate checkOutDate;
    @XmlAttribute
    public String roomName;
    @XmlAttribute
    public String origin;
    @XmlAttribute
    public String checkInNote;
    @XmlAttribute
    public String checkOutNote;
    @XmlAttribute
    public String cleaningFeesExpression;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDate prePaymentRequiredUntil;

    @XmlAttribute
    public String grossPaymentsExpression;
    @XmlElementWrapper
    public List<PaymentSer> payments;

    /**
     * E.g. Airbnb fees. Absolute value, such as 12€.
     */
    @XmlAttribute
    public String serviceFeeExpression;
    /**
     * E.g. BookingBean fees. Relative value, such as 0.12 (12%).
     */
    @XmlAttribute
    public String serviceFeePercentExpression;

    @XmlAttribute
    public String specialRequestNote;
    @XmlAttribute
    public boolean welcomeMailSend;
    @XmlAttribute
    public short numberOfGuests;
    @XmlAttribute
    public String externalId;
    @XmlAttribute
    public String bookingId;
    @XmlAttribute
    public List<String> calendarIds;

    public List<PaymentSer> getPayments() {
	return payments;
    }

    public void setPayments(final List<PaymentSer> payments) {
	this.payments = payments;
    }

    public String getServiceFeeExpression() {
	return serviceFeeExpression;
    }

    public void setServiceFeeExpression(final String serviceFeeExpression) {
	this.serviceFeeExpression = serviceFeeExpression;
    }

    public String getServiceFeePercentExpression() {
	return serviceFeePercentExpression;
    }

    public void setServiceFeePercentExpression(final String serviceFeePercentExpression) {
	this.serviceFeePercentExpression = serviceFeePercentExpression;
    }

    public String getOrigin() {
	return origin;
    }

    public void setOrigin(final String origin) {
	this.origin = origin;
    }

}
