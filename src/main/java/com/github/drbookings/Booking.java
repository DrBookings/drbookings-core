package com.github.drbookings;

import java.time.LocalDate;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Booking extends IDedImpl implements OriginProvider, PaymentsProviderBean, DatesSupplier {

    private final BookingOrigin bookingOrigin;

    private final LocalDate checkInDate;

    private final LocalDate checkOutDate;

    private final StringProperty cleaningFeesExpression;

    private final StringProperty grossPaymentExpression;

    private final Guest guest;

    private final ListProperty<Payment> payments;

    private final Room room;

    private String serviceFeesPercentExpression;

    private boolean welcomeMailSend;

    private short numberOfGuests = -1;

    public Booking(final String id, final Room room, final LocalDate checkInDate, final LocalDate checkOutDate,
	    final Guest guest, final BookingOrigin bookingOrigin) {
	super(id);
	this.room = room;
	this.checkInDate = checkInDate;
	this.checkOutDate = checkOutDate;
	this.guest = guest;
	this.bookingOrigin = bookingOrigin;
	payments = new SimpleListProperty<>(FXCollections.observableArrayList());
	this.cleaningFeesExpression = new SimpleStringProperty();
	this.grossPaymentExpression = new SimpleStringProperty();
    }

    public Booking addPayment(final Payment p) {
	payments.add(p);
	return this;
    }

    public MonetaryAmount getCleaningFees() {
	try {
	    return Money.of(Scripting.evaluateExpression(getCleaningFeesExpression()), "EUR");
	} catch (final Exception e) {
	    return Payments2.createMondary(0);
	}
    }

    public float getServiceFeesPercent() {
	try {
	    return (float) Scripting.evaluateExpression(getServiceFeesPercentExpression());
	} catch (final Exception e) {
	    return 0f;
	}
    }

    // Getter / Setter //

    public Guest getGuest() {
	return guest;
    }

    public Room getRoom() {
	return room;
    }

    public String getServiceFeesPercentExpression() {
	return serviceFeesPercentExpression;
    }

    public void setServiceFeesPercentExpression(final String serviceFeesPercentExpression) {
	this.serviceFeesPercentExpression = serviceFeesPercentExpression;
    }

    public boolean isWelcomeMailSend() {
	return welcomeMailSend;
    }

    public void setWelcomeMailSend(final boolean welcomeMailSend) {
	this.welcomeMailSend = welcomeMailSend;
    }

    public short getNumberOfGuests() {
	return numberOfGuests;
    }

    public void setNumberOfGuests(final short numberOfGuests) {
	this.numberOfGuests = numberOfGuests;
    }

    @Override
    public BookingOrigin getBookingOrigin() {
	return bookingOrigin;
    }

    @Override
    public LocalDate getCheckInDate() {
	return checkInDate;
    }

    @Override
    public LocalDate getCheckOutDate() {
	return checkOutDate;
    }

    // //

    @Override
    public String toString() {
	return "Booking " + getBookingOrigin() + ", checkin:" + getCheckInDate() + ", checkout:" + getCheckOutDate()
		+ ", guest:" + getGuest() + ", room:" + getRoom();
    }

    public StringProperty cleaningFeesExpressionProperty() {
	return this.cleaningFeesExpression;
    }

    public String getCleaningFeesExpression() {
	return this.cleaningFeesExpressionProperty().get();
    }

    public void setCleaningFeesExpression(final String cleaningFeesExpression) {
	this.cleaningFeesExpressionProperty().set(cleaningFeesExpression);
    }

    public StringProperty grossPaymentExpressionProperty() {
	return this.grossPaymentExpression;
    }

    public String getGrossPaymentExpression() {
	return this.grossPaymentExpressionProperty().get();
    }

    public void setGrossPaymentExpression(final String grossPaymentExpression) {
	this.grossPaymentExpressionProperty().set(grossPaymentExpression);
    }

    @Override
    public StringProperty getExpectedExpression() {
	return grossPaymentExpressionProperty();
    }

    @Override
    public void setExpectedExpression(final String expression) {
	setGrossPaymentExpression(expression);

    }

    @Override
    public ListProperty<Payment> paymentsProperty() {
	return this.payments;
    }

    @Override
    public ObservableList<Payment> getPayments() {
	return this.paymentsProperty().get();
    }

    public void setPayments(final ObservableList<Payment> payments) {
	this.paymentsProperty().set(payments);
    }

}
