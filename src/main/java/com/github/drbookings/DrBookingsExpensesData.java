package com.github.drbookings;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class DrBookingsExpensesData {

    protected final Map<LocalDate, ExpenseBean> entries;

    public DrBookingsExpensesData() {
	this.entries = new TreeMap<>();
    }

    public void addExpense(final ExpenseBean entry) {
	this.entries.put(entry.getDate(), entry);
    }

    public ExpenseBean getExpense(final LocalDate date) {
	return entries.get(date);
    }

}
