package com.github.drbookings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.drbookings.ser.ExpenseSer;

public class ExpenseSerFactory {

    public static List<ExpenseSer> build(final Collection<? extends ExpenseBean> expenses) {
	final List<ExpenseSer> result = new ArrayList<>(expenses.size());
	for (final ExpenseBean p : expenses) {
	    result.add(ExpenseSerFactory.build(p));
	}

	return result;
    }

    public static ExpenseSer build(final ExpenseBean expense) {
	final ExpenseSer result = new ExpenseSer();
	result.amount = Float.toString(expense.getAmount().getNumber().floatValue());
	result.date = expense.getDate();
	result.name = expense.getName();
	final BookingOrigin origin = expense.getOrigin();
	if (origin != null) {
	    result.origin = origin.getName();
	}
	return result;
    }

}
