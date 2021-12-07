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
package com.github.drbookings.ui;

import java.time.LocalDate;
import java.util.Collection;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.drbookings.Booking;
import com.github.drbookings.BookingOrigin;
import com.github.drbookings.Cleaning;
import com.github.drbookings.DateEntryImpl;
import com.github.drbookings.IDed;
import com.github.drbookings.IDedImpl;
import com.github.drbookings.OriginProvider;
import com.github.drbookings.Payment;
import com.github.drbookings.PaymentsProviderBean;
import com.github.drbookings.Room;
import com.github.drbookings.RoomDateProvider;
import com.github.drbookings.Scripting;

import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class CleaningBean extends DateEntryImpl<Cleaning>
	implements RoomDateProvider, OriginProvider, PaymentsProviderBean {

    private static final Logger logger = LoggerFactory.getLogger(CleaningBean.class);
    private final Room room;
    private final StringProperty cleaningCostsExpression;
    private final BooleanProperty taxRelevant;
    private final ObjectProperty<Booking> bookingBefore;
    private final ObjectProperty<Booking> bookingAfter;
    private final StringProperty note;
    private final ListProperty<Payment> payments;
    private final IDed idDelegate;

    public static Callback<CleaningBean, Observable[]> extractor() {

	return e -> new Observable[] { e.cleaningCostsExpression, e.taxRelevant, e.bookingBefore, e.bookingAfter,
		e.note, e.payments };
    }

    public CleaningBean(final LocalDate date, final Cleaning element, final Room room) {

	super(date, element);
	this.idDelegate = new IDedImpl(element.getId());
	this.room = room;
	cleaningCostsExpression = new SimpleStringProperty();
	taxRelevant = new SimpleBooleanProperty();
	bookingAfter = new SimpleObjectProperty<>();
	bookingBefore = new SimpleObjectProperty<>();
	note = new SimpleStringProperty();
	payments = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public String getName() {
	return getElement().getName();
    }

    public MonetaryAmount getCleaningCosts() {
	try {
	    return Money.of(Scripting.evaluateExpression(getCleaningCostsExpression()), "EUR");
	} catch (final Exception e) {
	    if (logger.isDebugEnabled()) {
		logger.debug(e.getLocalizedMessage(), e);
	    }
	    return Money.of(0, "EUR");
	}
    }

    @Override
    public StringProperty getExpectedExpression() {
	return cleaningCostsExpression;
    }

    @Override
    public void setExpectedExpression(final String expression) {
	setCleaningCostsExpression(expression);

    }

    @Override
    public BookingOrigin getBookingOrigin() {
	return getBookingBefore() == null ? null : getBookingBefore().getBookingOrigin();
    }

    // Getter / Setter //

    @Override
    public String getId() {
	return idDelegate.getId();
    }

    @Override
    public Room getRoom() {

	return room;
    }

    public StringProperty cleaningCostsExpressionProperty() {
	return cleaningCostsExpression;
    }

    public String getCleaningCostsExpression() {
	return cleaningCostsExpressionProperty().get();
    }

    public void setCleaningCostsExpression(final String cleaningCostsExpression) {
	cleaningCostsExpressionProperty().set(cleaningCostsExpression);
    }

    public BooleanProperty taxRelevantProperty() {
	return taxRelevant;
    }

    public boolean isTaxRelevant() {
	return taxRelevantProperty().get();
    }

    public void setTaxRelevant(final boolean taxRelevant) {
	taxRelevantProperty().set(taxRelevant);
    }

    public ObjectProperty<Booking> bookingBeforeProperty() {
	return bookingBefore;
    }

    public Booking getBookingBefore() {
	return bookingBeforeProperty().get();
    }

    public void setBookingBefore(final Booking bookingBefore) {
	bookingBeforeProperty().set(bookingBefore);
    }

    public ObjectProperty<Booking> bookingAfterProperty() {
	return bookingAfter;
    }

    public Booking getBookingAfter() {
	return bookingAfterProperty().get();
    }

    public void setBookingAfter(final Booking bookingAfter) {
	bookingAfterProperty().set(bookingAfter);
    }

    public StringProperty noteProperty() {
	return note;
    }

    public String getNote() {
	return noteProperty().get();
    }

    public void setNote(final String note) {
	noteProperty().set(note);
    }

    public ListProperty<Payment> paymentsProperty() {
	return this.payments;
    }

    @Override
    public ObservableList<Payment> getPayments() {
	return this.paymentsProperty().get();
    }

    public void setPayments(final Collection<? extends Payment> payments) {
	this.paymentsProperty().setAll(payments);
    }

}
