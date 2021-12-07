package com.github.drbookings;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.github.drbookings.ser.ExpenseSer;

public class ExpenseFactory {

    private final BookingOriginProvider provider;

    public ExpenseFactory(final BookingOriginProvider provider) {
	super();
	this.provider = provider;
    }

    public ExpenseFactory() {
	this(BookingOriginProvider.getInstance());
    }

    public BookingOriginProvider getProvider() {
	return provider;
    }

    public List<ExpenseBean> build(final Collection<? extends ExpenseSer> elements) {
	return elements.stream().map(e -> build(e)).collect(Collectors.toList());
    }

    public ExpenseBean build(final ExpenseSer e) {
	final ExpenseBean result = new ExpenseBean(e.name, e.date, e.amount);
	if (e.origin != null) {
	    result.setOrigin(provider.getOrCreateElement(e.origin));
	}
	return result;
    }

}
