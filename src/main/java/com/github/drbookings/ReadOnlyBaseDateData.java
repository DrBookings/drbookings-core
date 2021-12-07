package com.github.drbookings;

import java.time.LocalDate;
import java.util.Collection;

public interface ReadOnlyBaseDateData<T> extends DateData {

	T get(LocalDate date, String roomName);

	Collection<T> getAll();
}
