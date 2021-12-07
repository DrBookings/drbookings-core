package com.github.drbookings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.drbookings.ui.CleaningBean;

public class CleaningExpensesFactory2 {

    public static List<CleaningExpense2> build(final Collection<? extends CleaningBean> cleanings,
	    final boolean onlyTaxRelevant) {
	final List<CleaningExpense2> result = new ArrayList<>();
	for (final CleaningBean ce : cleanings) {

	    if (onlyTaxRelevant && !ce.isTaxRelevant()) {
		// skip
	    } else {
		final CleaningExpense2 ce2 = new CleaningExpense2(ce);
		ce2.setOrigin(ce.getBookingOrigin());
		result.add(ce2);
	    }
	}
	return result;
    }

}
