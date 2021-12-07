package com.github.drbookings;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.money.MonetaryAmount;

public class Expenses {

    public static final String DEFAULT_MONTHLY_FIX_COSTS_NAME = "monthly-fix-costs";

    public static ExpenseBean buildMonthlyFixCostsExpense(final LocalDate date, final MonetaryAmount amount) {
	return new ExpenseBean(DEFAULT_MONTHLY_FIX_COSTS_NAME, date, amount);
    }

    public static List<ExpenseBean> buildMonthlyFixCostsExpensesForAllRooms(final LocalDate date, final int numRooms,
	    final MonetaryAmount fixCostsOneRoom) {
	final MonetaryAmount fixCostsAllRooms = fixCostsOneRoom.multiply(numRooms);
	return Arrays.asList(buildMonthlyFixCostsExpense(date, fixCostsAllRooms));
    }

    public static List<ExpenseBean> getCommonExpenses(final Collection<? extends ExpenseBean> expenses) {
	final List<ExpenseBean> result = expenses.stream().filter(e -> e.getOrigin() != null)
		.collect(Collectors.toList());
	return result;
    }

    public static List<ExpenseBean> getAssignedExpenses(final Collection<? extends ExpenseBean> expenses,
	    final String bookingOrigin) {
	return getAssignedExpenses(expenses, new BookingOrigin(bookingOrigin));
    }

    public static List<ExpenseBean> getAssignedExpenses(final Collection<? extends ExpenseBean> expenses,
	    final BookingOrigin bookingOrigin) {
	final List<ExpenseBean> result = expenses.stream().filter(e -> e.getOrigin() != null)
		.filter(e -> bookingOrigin.equals(e.getOrigin())).collect(Collectors.toList());
	return result;
    }

}
