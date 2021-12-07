package com.github.drbookings;

import com.github.drbookings.ui.CleaningBean;

public class CleaningExpense2 extends ExpenseBean {

    private final CleaningBean cleaningEntry;

    public CleaningExpense2(final CleaningBean ce) {
	super(ce.getName(), ce.getDate(), ce.getCleaningCosts());
	cleaningEntry = ce;
    }

    public CleaningBean getCleaningEntry() {
	return cleaningEntry;
    }

}
