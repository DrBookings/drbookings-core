package com.github.drbookings;

import java.time.LocalDate;

import javax.money.MonetaryAmount;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Callback;

/**
 * FX-Bean representing an expense at a given date.
 *
 * @author Alexander Kerner
 *
 */
public class ExpenseBean implements Named, Payment {

    public static Callback<ExpenseBean, Observable[]> extractor() {
	return b -> new Observable[] {};
    }

    private final ObjectProperty<BookingOrigin> origin;

    private final ObjectProperty<Named> named;

    private final ObjectProperty<Payment> payment;

    public ExpenseBean(final String name, final LocalDate date, final String amount) {
	this(name, new PaymentImpl(date, amount));
    }

    public ExpenseBean(final String name, final Payment payment) {
	this.named = new SimpleObjectProperty<>(new NamedImpl(name));
	this.payment = new SimpleObjectProperty<>(payment);
	this.origin = new SimpleObjectProperty<>();
    }

    public ExpenseBean(final String name, final LocalDate date, final MonetaryAmount amount) {
	this(name, new PaymentImpl(date, amount));
    }

    public ExpenseBean(final String name, final LocalDate date, final double n) {
	this(name, new PaymentImpl(date, Double.toString(n)));
    }

    @Override
    public String toString() {

	return "Expense: date:" + getDate() + "\tamount:"
		+ String.format("%8.2f", getAmount().getNumber().doubleValue()) + ",\torigin:" + getOrigin()
		+ ",\tname:" + getName();
    }

    // Delegates //

    @Override
    public MonetaryAmount getAmount() {
	return getPayment().getAmount();
    }

    @Override
    public LocalDate getDate() {
	return getPayment().getDate();
    }

    @Override
    public String getName() {
	return getNamed().getName();
    }

    @Override
    public int compareTo(final Named o) {
	return getNamed().compareTo(o);
    }

    // Getter / Setter //

    public ObjectProperty<BookingOrigin> originProperty() {
	return origin;
    }

    public BookingOrigin getOrigin() {
	return originProperty().get();
    }

    public void setOrigin(final BookingOrigin origin) {
	originProperty().set(origin);
    }

    protected ObjectProperty<Named> namedProperty() {
	return named;
    }

    protected Named getNamed() {
	return namedProperty().get();
    }

    protected void setNamed(final Named named) {
	namedProperty().set(named);
    }

    protected ObjectProperty<Payment> paymentProperty() {
	return payment;
    }

    protected Payment getPayment() {
	return paymentProperty().get();
    }

    protected void setPayment(final Payment payment) {
	paymentProperty().set(payment);
    }
}
