package com.github.drbookings;

import java.time.LocalDate;
import java.util.SortedSet;

public interface DateData {

    SortedSet<LocalDate> allDates();

    int size();

}
